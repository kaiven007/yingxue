package com.baizhi.controller;


import com.baizhi.config.*;
import com.baizhi.entity.*;
import com.baizhi.service.*;
import com.baizhi.uitl.EsSearch;
import com.baizhi.uitl.MyEsVideoConfig;
import com.baizhi.viodes.TestViodes;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@RestController
@Controller
@RequestMapping("video")
public class VideoController {

    @Autowired
    private VideoService  videoService;
    @Autowired
    private UserService  userService;
    @Autowired
    private PlayService  playService;
    @Autowired
    private CommentService  commentService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CityService  cityService;
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    // 用来产生随机验证码的
    private static Random rand = new Random();
    private static String[] str = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

    @RequestMapping("showVideo")
    public Result2<?> showVideo(Integer page,Integer rows,String searchField,
                                String searchString,String searchOper){

       Result2<List<Video>> result = new Result2<>();
        List<Video> list = null;
        if(searchField!=null) {

                list = videoService.queryByVideo(searchField, searchString,searchOper);

        }else {
            list = videoService.queryAllVideo(page, rows);
        }
        Integer totals = videoService.videoCount();
        Integer total = totals % rows == 0 ? totals / rows : totals / rows + 1;
        result.setPage(page);
        result.setRows(list);
        result.setTotal(total);
        result.setRecords(totals);
        return  result;
    }
    //添加，修改，删除
    @ResponseBody
    @RequestMapping("edit")
    public Result<?> edit(Video video, String oper) throws Exception {
        System.out.println("视频添加"+video);
        Result result = new Result();
        if(oper.equals("add")){
            video.setId(null);
            video.setCreateAt(new Date());
            video.setCid(video.getFcate().getName());
            video.setUserId(video.getFuser().getUsername());
            video.setGrpId(null);
            videoService.save(video);
            result.setMessage(video.getId());
            System.out.println("视频添加 = " +video);
        }if(oper.equals("del")){
            Video video1 = videoService.getById(video.getId());
            //获取封面路径
            String path1 = request.getServletContext().getRealPath("img");
            try {
                //删除视频七牛云上存储的数据
                QiniuYunUtil.deleteFileFromQiniu(video1.getVideoUrl());
                //删除封面
                File file1 = new File(path1+"/"+video1.getCoverUrl());
                file1.delete();
                //删除数据
                videoService.removeById(video.getId());
                //删除播放量
                QueryWrapper<Play> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("video_id",video.getId());
                playService.remove(queryWrapper);
                //删除评论
                QueryWrapper<Comment> wrapper = new QueryWrapper<>();
                wrapper.eq("source_id",video.getId());
                commentService.remove(wrapper);
            }catch (Exception e){
                e.printStackTrace();
                videoService.removeById(video.getId());
            }
        }if (oper.equals("edit")){
            videoService.updateVideo(video);
        }
        return result;
    }

    //进行视频文件上传
    @RequestMapping("videoUpload")
    public void videoUpload(String id, MultipartFile videoUrl) throws Exception {

        // 存放产生的随机数
        StringBuffer sms;
        // 生成三位数的随机数
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < 3; i++) {
            buf.append(str[rand.nextInt(10)]);
        }
        sms = buf;
        // 获取当前时间
        Date now = new Date();
        // 格式化字符
        SimpleDateFormat date = new SimpleDateFormat("yyyy MM dd hh mm ss");
        String datestring = date.format(now);
        // 去除时间里的空格
        String nokongge = datestring.replaceAll(" ", "");
        String longid = nokongge + sms;
        long randomid = new Long(Long.parseLong(longid));
        System.out.println("视频："+id);
        System.out.println(videoUrl);
        //获取文件名
        String filename = UUID.randomUUID().toString() +videoUrl.getOriginalFilename();
        System.out.println("上传视频文件名"+filename);

        //获取上传路径
        String path = request.getServletContext().getRealPath("/videos");
        videoUrl.transferTo(new File(path + "/" + filename));

        //获取全限定名
        String pathPlus = path + "\\"+filename;
        System.out.println("视频全限定名:"+pathPlus);
        //MD5解析视频码
        String fileMD5 = MD5Util.getFileMD5(new File(pathPlus));
        System.out.println("解析视频码"+fileMD5);
        //交由七牛云管理视频
        String upload = QiniuYunUtil.upload(pathPlus,fileMD5,filename);

        String  one="D:\\后期项目\\yingxue\\src\\main\\webapp\\img\\";
        String   two=randomid + ".jpg";
        String picurl = one+two;//截图存放路径

        String  fileVideo="http://qhbh8lkb1.hn-bkt.clouddn.com//"+filename;
        System.out.println("图片的路径："+picurl);
        TestViodes.fetchFrame(fileVideo,picurl);

        //MD5解析图片码
        String fileMD52 = MD5Util.getFileMD5(new File(pathPlus));
        System.out.println("解析图片码"+fileMD5);
        //交由七牛云管理视频
        String uploadCover = QiniuYunUtil.upload(picurl,fileMD52,two);
        //管理完毕删除本地保存文件
        File file = new File(pathPlus);
        file.delete();
        //补全video
        Video video = new Video();
        video.setId(id);
        video.setCoverUrl("http://qhbh8lkb1.hn-bkt.clouddn.com//"+uploadCover);
        video.setVideoUrl("http://qhbh8lkb1.hn-bkt.clouddn.com//"+upload);
        videoService.updateById(video);
    }

    //类别下拉列表的值
    @RequestMapping("cate")
    public StringBuilder cate(String sty) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<select>");
        //类别下拉
        if(sty.equals("category")){
            QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("level", "2");
            List<Category> list = categoryService.list(queryWrapper);
            for (Category category : list) {
                stringBuilder.append("<option value='").append(category.getId()).append("'>").append(category.getName()).append("</option>");
            }
        }

        //用户下拉
        if(sty.equals("user")) {
            List<User> list = userService.list();
            for (User user : list) {
                stringBuilder.append("<option value='").append(user.getId()).append("'>").append(user.getUsername()).append("</option>");
            }
        }
        //视频下拉
        if(sty.equals("video")) {
            List<Video> list = videoService.list();
            for (Video video : list) {
                stringBuilder.append("<option value='").append(video.getId()).append("'>").append(video.getTitle()).append("</option>");
            }
        }

        //城市下拉
        if(sty.equals("city")) {
            QueryWrapper<City> queryWrapper=new QueryWrapper<>();
            List<City> list = cityService.list(queryWrapper.eq("leaf",'0'));
            for (City city : list) {
                stringBuilder.append("<option value='").append(city.getId()).append("'>").append(city.getName()).append("</option>");
            }
        }
        stringBuilder.append("</select>");
        return stringBuilder;
    }
    //搜索视频
    @RequestMapping("videoSearch")
    public  List<Video> videoSearch(String content){

        System.out.println("传参"+content);

        List<Video> list=MyEsVideoConfig.querySearchVideos(content,elasticsearchTemplate);

        return list;
    }

    //更新索引
    @RequestMapping("addVideoEs")
    public  void addVideoEs(){

        videoService.addEs();

    }
}

