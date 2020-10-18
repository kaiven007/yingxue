package com.baizhi.controller;


import com.baizhi.anno.YxueLog;
import com.baizhi.config.EasyPoiUtil;
import com.baizhi.config.Result;
import com.baizhi.config.Result2;
import com.baizhi.entity.Comment;
import com.baizhi.entity.Feedback;
import com.baizhi.entity.User;
import com.baizhi.entity.Video;
import com.baizhi.service.FeedbackService;
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
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@Controller
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService  feedbackService;
    @Autowired
    private HttpServletResponse response;

    //@YxueLog(value = "反馈分页查询",tableName = "yx_feedback")
    @RequestMapping("showFeedback")
    public Result2<?> showFeedback(Integer page, Integer rows){

        Result2<List<Feedback>> result = new Result2<>();
        List<Feedback> list = feedbackService.queryAllFeedback(page, rows);

        Integer totals = feedbackService.feedbackCount();
        Integer total = totals % rows == 0 ? totals / rows : totals / rows + 1;
        result.setPage(page);
        result.setRows(list);
        result.setTotal(total);
        result.setRecords(totals);
        return  result;
    }
    @ResponseBody
    @RequestMapping("edit")
    public Result<?> edit(Feedback feedback, String oper){
        System.out.println("添加反馈"+feedback);
        Result result = new Result();
        if(oper.equals("add")){

                feedback.setId(null);
                feedback.setUserId(feedback.getFuser().getUsername());
                feedback.setCreateAt(new Date());
                feedbackService.save(feedback);

        }if(oper.equals("del")){

            feedbackService.removeById(feedback.getId());

        }if (oper.equals("edit")){
            feedbackService.updateFeedback(feedback);
        }
        return result;
    }
    //导出
    @ResponseBody
    @RequestMapping("exportFeedback")
    public void exportComment() {
        QueryWrapper<Feedback> queryWrapper = new QueryWrapper<>();
        List<Feedback> list=feedbackService.list(queryWrapper);
        EasyPoiUtil.exportExcel(list,"反馈信息","反馈",Feedback.class,"反馈.xls",response);
    }

    //导入
    @RequestMapping("importFeedback")
    public String importFeedback(MultipartFile multipartFile){
        List<Feedback> feedbackList= EasyPoiUtil.importExcel(multipartFile, 1, 1, Feedback.class);
        for (Feedback feedback : feedbackList) {
            feedbackService.save(feedback);
            System.out.println(feedback);
        }
        return "redirect:/login.jsp";
    }

}

