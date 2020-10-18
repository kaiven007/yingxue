package com.baizhi.service;

import com.baizhi.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.util.List;

/**
 * 创建者：xw
 * 接口的作用：
 * 创建时间：2020/9/25
 */
public interface VideoService extends IService<Video> {

    //后台
    List<Video>  queryAllVideo(Integer page,Integer rows);

    List<Video>  queryByVideo(String searchField, String searchString,String searchOper);

    Integer videoCount();

    void updateVideo(Video video);

    void  addEs();

    //前台
    List<Video>  queryVideo();
    //搜索框
    List<Video>   queryAppByVideo(String content);
    //视频详细信息展示
    List<Video>   queryVideo(String videoId,String cateId,String userId);

    List<Video>   queryNoVideo(String videoId);
}
