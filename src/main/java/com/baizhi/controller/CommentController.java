package com.baizhi.controller;


import com.baizhi.anno.YxueLog;
import com.baizhi.config.*;
import com.baizhi.entity.Category;
import com.baizhi.entity.Comment;
import com.baizhi.entity.User;
import com.baizhi.entity.Video;
import com.baizhi.service.CommentService;
import com.baizhi.viodes.TestViodes;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Slf4j
@RestController
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService  commentService;
    @Autowired
    private HttpServletResponse response;


    //@YxueLog(value = "评论分页查询",tableName = "yx_comment")
    @RequestMapping("showAllComment")
    public Result2<?> showAllComment(Integer page, Integer rows,String searchField,
                                     String searchString,String searchOper){

        Result2<List<Comment>> result = new Result2<>();
        List<Comment> list = commentService.queryOneComment(page,rows,searchField,searchString,searchOper);

        Integer totals = commentService.commentOneCount(page,rows,searchField,searchString,searchOper);
        Integer total = totals % rows == 0 ? totals / rows : totals / rows + 1;
        result.setPage(page);
        result.setRows(list);
        result.setTotal(total);
        result.setRecords(totals);
        return  result;
    }
    //@YxueLog(value = "二级评论分页查询",tableName = "yx_comment")
    @RequestMapping("showByIdComment")
    public Result2<?>   showByIdComment(Integer page,Integer rows,String id,String searchField,
                                        String searchString,String searchOper){
        Result2<List<Comment>> result = new Result2<>();
        List<Comment> list = commentService.queryTwoComment(page,rows,id,searchField,searchString,searchOper);
        Integer totals = commentService.commentTwoCount(page,rows,id,searchField,searchString,searchOper);
        Integer total = totals % rows == 0 ? totals / rows : totals / rows + 1;
        result.setPage(page);
        result.setRows(list);
        result.setTotal(total);
        result.setRecords(totals);
        return  result;
    }

    @ResponseBody
    @RequestMapping("edit")
    public Result<?> edit(Comment comment,String oper){
        System.out.println("添加评论"+comment);
        Result result = new Result();
        if(oper.equals("add")){
            if (comment.getInteractId()==null) {
                comment.setId(null);
                comment.setUserId(comment.getFuser().getUsername());
                System.out.println("视频id："+comment.getFvideo().getVideoUrl());
                comment.setSourceId(comment.getFvideo().getVideoUrl());
                comment.setCreateAt(new Date());
                comment.setInteractId(null);
                commentService.save(comment);
            }else if (comment.getInteractId()!=null) {
                comment.setId(null);
                comment.setUserId(comment.getFuser().getUsername());
                System.out.println("视频id："+comment.getFvideo().getVideoUrl());
                comment.setSourceId(comment.getFvideo().getVideoUrl());
                comment.setCreateAt(new Date());
                comment.setInteractId(comment.getId());
                commentService.save(comment);
            }

        }if(oper.equals("del")){

            commentService.removeComment(comment.getId());

        }if (oper.equals("edit")){
            commentService.updateComment(comment);
        }
        return result;
    }
    //导出
    @ResponseBody
    @RequestMapping("exportComment")
    public void exportComment() {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        List<Comment> list=commentService.list(queryWrapper);
        EasyPoiUtil.exportExcel(list,"评论信息","评论",Comment.class,"评论.xls",response);
    }

    //导入
    @RequestMapping("importComment")
    public String importComment(MultipartFile multipartFile){
        List<Comment> userList= EasyPoiUtil.importExcel(multipartFile, 1, 1, Comment.class);
        for (Comment comment : userList) {
            commentService.save(comment);
            System.out.println(comment);
        }
        return "redirect:/login.jsp";
    }
}

