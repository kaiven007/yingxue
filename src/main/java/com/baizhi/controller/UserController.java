package com.baizhi.controller;

import com.baizhi.anno.AddCache;
import com.baizhi.anno.DelCache;
import com.baizhi.anno.YxueLog;
import com.baizhi.config.*;
import com.baizhi.dto.CityDTO;
import com.baizhi.dto.UserDTO;
import com.baizhi.dto.UserViewDTO;
import com.baizhi.entity.*;
import com.baizhi.service.CommentService;
import com.baizhi.service.FeedbackService;
import com.baizhi.service.UserService;
import com.baizhi.service.VideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.util.*;


/**
 * 创建者：xw
 * 类的作用：
 * 创建时间：2020/9/23
 */
@Slf4j
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService  userService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private VideoService videoServicel;
    @Autowired
    private CommentService  commentService;
    @Autowired
    private FeedbackService  feedbackService;

    //@YxueLog(value = "用户分页查询",tableName = "yx_user")
    @ResponseBody
    //@AddCache
    @RequestMapping("showUser")
    public  HashMap<String, Object>  showUser(Integer page,Integer rows){
        HashMap<String, Object> map = new HashMap<>();
        //Result2<List<User>> result = new Result2<>();
        List<User> list = userService.queryAllUser(page, rows);
        Integer totals = userService.userCount();
        Integer total = totals % rows == 0 ? totals / rows : totals / rows + 1;
        map.put("page",page); //数据
        map.put("rows",list); //数据
        map.put("total",total); //总条数
        map.put("records",totals); //总页数
        /*result.setPage(page);
        result.setRows(list);
        result.setTotal(total);
        result.setRecords(totals);*/
        return  map;
    }
    //添加、修改、删除
    //@DelCache
    @ResponseBody
    @RequestMapping("edit")
    public Result<?> edit(User user,String oper){
        Result result = new Result();
        if(oper.equals("add")){
            user.setId(null);
            user.setStatus("1");
            user.setRegTime(new Date());
            user.setCityId(user.getFcity().getName());
            userService.save(user);
            result.setMessage(user.getId());
            System.out.println("user = " + user);
        }if(oper.equals("del")){
            //删除用户视频
            QueryWrapper<Video> queryWrapper1= new QueryWrapper<>();
            queryWrapper1.eq("user_id",user.getId());
            videoServicel.remove(queryWrapper1);
             //删除用户评论
            QueryWrapper<Comment> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("user_id",user.getId());
            commentService.remove(queryWrapper2);
            //删除反馈
            QueryWrapper<Feedback> queryWrapper3 = new QueryWrapper<>();
            queryWrapper3.eq("user_id",user.getId());
            feedbackService.remove(queryWrapper3);

            userService.removeById(user.getId());
        }if (oper.equals("edit")){
            user.setHeadShow(null);
            userService.updateUser(user);
        }
        return result;
    }
    //@YxueLog(value = "修改激活状态",tableName = "yx_user")
    //修改激活状态
    @RequestMapping("updateStatus")
    public void updateStatus(String id){
        User user = userService.queryById(id);
        if(user.getStatus().equals("1")){
            user.setStatus("2");
        }else{
            user.setStatus("1");
        }
        userService.updateUser(user);
    }
    //进行文件上传
    //@YxueLog(value = "头像上传",tableName = "yx_user")
    @RequestMapping("headUpload")
    public void headUpload(String id, MultipartFile headShow) throws Exception{
        System.out.println("哈哈哈哈哈哈哈"+id);
        String filename=headShow.getOriginalFilename();
        System.out.println(filename);
        //1.获取绝对路径
        String path=request.getServletContext().getRealPath("/img");
        //2.文件上传
            headShow.transferTo(new File(path+"/"+filename));
        //获取全限定名
        String pathPlus = path + "\\"+filename;

        //MD5解析图片码
        String fileMD5 = MD5Util.getFileMD5(new File(pathPlus));

        //交由七牛云管理图片
        String upload = QiniuYunUtil.upload(pathPlus,fileMD5,filename);

        User user=new User();
        user.setId(id);
        user.setHeadShow("http://qhbh8lkb1.hn-bkt.clouddn.com//"+upload);
        userService.updateById(user);
    }
    //导出
    @ResponseBody
    @RequestMapping("exportUser")
    public void exportUser() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<User> list=userService.list(queryWrapper);
        EasyPoiUtil.exportExcel(list,"用户信息","用户",User.class,"用户.xls",response);
    }

    //导入
    @RequestMapping("importUser")
    public String importUser(MultipartFile multipartFile){
        List<User> userList= EasyPoiUtil.importExcel(multipartFile, 1, 1, User.class);
        for (User user : userList) {
            userService.save(user);
            System.out.println(user);
        }
        return "redirect:/login.jsp";
    }

    //统计图
    @ResponseBody
    @RequestMapping("userView")
    public HashMap<String, Object> userView(){
          /*http  应用层协议    短链接
        tcp/ip   网络层协议   长连接*/

        List<User>  list1=userService.queryView("男");
        System.out.println("男生"+list1);
        List<User>  list2=userService.queryView("女");
        System.out.println("女生"+list2);
        List<UserViewDTO> boyList=new ArrayList<>();
        for (User user:list1){
            UserViewDTO  userViewDTO=new UserViewDTO();
                    userViewDTO.setMonths(user.getMonth());
                    userViewDTO.setCounts(user.getCount());
                    boyList.add(userViewDTO);
        }
        List<UserViewDTO> girlsList=new ArrayList<>();
        for (User user:list2) {
            UserViewDTO  userViewDTO=new UserViewDTO();
            userViewDTO.setMonths(user.getMonth());
            userViewDTO.setCounts(user.getCount());
            girlsList.add(userViewDTO);
        }

        //整合数据
        Set<String>  month=new LinkedHashSet<>();
        List<Integer>  boySet=new ArrayList<>();
        List<Integer>  girlsSet=new ArrayList<>();
        for (UserViewDTO userViewDTO:boyList) {
            /*System.out.println("boyList"+boyList);
            System.out.println("男生:"+userViewDTO);*/
            month.add(userViewDTO.getMonths());
            boySet.add(userViewDTO.getCounts());
        }
        for (UserViewDTO userViewDTO:girlsList) {
            /*System.out.println("girlsList"+girlsList);
            System.out.println("女生:"+userViewDTO);*/
            month.add(userViewDTO.getMonths());
            girlsSet.add(userViewDTO.getCounts());
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("month", month);
        map.put("boys", boySet);
        map.put("girls", girlsSet);
        System.out.println("arrayList"+month);
        System.out.println("map:"+map);
        return map;
    }

    @ResponseBody
    @RequestMapping("userMap")
    public ArrayList<UserDTO> userMap(){

        List<User>  list1=userService.queryMap("男");
        System.out.println("地图男生"+list1);
        List<User>  list2=userService.queryMap("女");
        System.out.println("地图女生"+list2);

        ArrayList<CityDTO> boyList = new ArrayList<>();
        ArrayList<CityDTO> girlList = new ArrayList<>();
        for (User  user:list1){
            CityDTO cityDTO=new CityDTO();
            cityDTO.setName(user.getFcity().getName());
            cityDTO.setValue(user.getCityCount());
            boyList.add(cityDTO);
        }
        for (User  user:list2){
            CityDTO cityDTO=new CityDTO();
            cityDTO.setName(user.getFcity().getName());
            cityDTO.setValue(user.getCityCount());
            girlList.add(cityDTO);
        }
        ArrayList<UserDTO> list = new ArrayList<>();
        list.add(new UserDTO("男生",boyList));
        list.add(new UserDTO("女生",girlList));
        System.out.println("地图"+list);
        return list;
    }
}



