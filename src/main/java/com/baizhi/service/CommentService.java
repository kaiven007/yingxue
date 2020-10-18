package com.baizhi.service;

import com.baizhi.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaozhi
 * @since 2020-09-24
 */
public interface CommentService extends IService<Comment> {

    List<Comment>  queryOneComment(Integer page,Integer rows,String searchField,
                                   String searchString,String searchOper);

    List<Comment>  queryTwoComment(Integer page,Integer rows,String id,String searchField,
                                   String searchString,String searchOper);


    Integer  commentOneCount(Integer page,Integer rows,String searchField,
                             String searchString,String searchOper);

    Integer  commentTwoCount(Integer page,Integer rows,String id,String searchField,
                             String searchString,String searchOper);

    void  removeComment(String id);

    void  updateComment(Comment comment);
}
