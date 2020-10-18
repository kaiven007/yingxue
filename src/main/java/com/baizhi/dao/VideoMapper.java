package com.baizhi.dao;

import com.baizhi.entity.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoMapper extends BaseMapper<Video> {

    //后台
    List<Video>  selectAllVideo(@Param("begin") Integer begin, @Param("rows") Integer rows);

    List<Video>  selectByVideo(@Param("searchField") String searchField, @Param("searchString") String searchString,
                               @Param("searchOper") String searchOper);

    //前台
    List<Video>   selectVideo();

    List<Video>   selectAppVideo(@Param("videoId") String videoId,@Param("cateId") String cateId,@Param("userId") String userId);

    List<Video>   selectNoVideo(String videoId);

    //搜索框
    List<Video>   selectAppByVideo(String content);
}
