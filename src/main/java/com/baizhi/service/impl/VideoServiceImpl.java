package com.baizhi.service.impl;


import com.baizhi.dao.VideoMapper;
import com.baizhi.entity.Video;
import com.baizhi.service.VideoService;
import com.baizhi.uitl.VideoRepository;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建者：xw
 * 类的作用：
 * 创建时间：2020/9/25
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public List<Video> queryAllVideo(Integer page,Integer rows) {
        Integer  begin=rows*(page-1);
        List<Video> list=videoMapper.selectAllVideo(begin,rows);

        System.out.println("视频分页："+list);
        return list;
    }

    @Override
    public List<Video> queryByVideo(String searchField, String searchString,String searchOper) {
        QueryWrapper<Video> queryWrapper=new QueryWrapper<>();
        List<Video> list=null;
        if ("eq".equals(searchOper)) {
            list = videoMapper.selectList(queryWrapper.eq(searchField, searchString));
        }
        if ("cn".equals(searchOper)) {
            list = videoMapper.selectList(queryWrapper.like(searchField, searchString));
        }
        return list;
    }

    @Override
    public Integer videoCount() {
        QueryWrapper<Video> queryWrapper=new QueryWrapper<>();
        Integer integer=videoMapper.selectCount(queryWrapper);
        return  integer;
    }

    //修改
    @Override
    public void updateVideo(Video video) {

        videoMapper.updateById(video);
    }


    @Override
    public void addEs() {
        List<Video> list=videoMapper.selectVideo();
        videoRepository.saveAll(list);
    }

    //前台视频展示
    @Override
    public List<Video> queryVideo() {
        List<Video> list=videoMapper.selectVideo();
        return list;
    }
    //视频详细信息
    @Override
    public List<Video> queryVideo(String videoId, String cateId, String userId) {
        List<Video> list=videoMapper.selectAppVideo(videoId,cateId,userId);
        return list;
    }

    @Override
    public List<Video> queryNoVideo(String videoId) {
        List<Video> list=videoMapper.selectNoVideo(videoId);
        return list;
    }

    @Override
    public List<Video> queryAppByVideo(String content) {
        List<Video> list=videoMapper.selectAppByVideo(content);
        return list;
    }


}
