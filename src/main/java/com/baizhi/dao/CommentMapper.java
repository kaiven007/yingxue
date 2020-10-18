package com.baizhi.dao;

import com.baizhi.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comment> {

    List<Comment>  selectComment(@Param("begin") Integer begin, @Param("rows") Integer rows,
                                 @Param("searchField") String searchField,@Param("searchString") String searchString,@Param("searchOper") String searchOper);

    List<Comment>  selectTwoComment(@Param("begin") Integer begin, @Param("rows") Integer rows,@Param("id") String id,
                                    @Param("searchField") String searchField,@Param("searchString") String searchString,
                                    @Param("searchOper") String searchOper);
}
