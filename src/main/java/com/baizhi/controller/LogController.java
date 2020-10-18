package com.baizhi.controller;
import com.baizhi.config.EasyPoiUtil;
import com.baizhi.config.Result;
import com.baizhi.entity.Log;
import com.baizhi.service.LogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建者：xw
 * 类的作用：
 * 创建时间：2020/9/28
 */
@Slf4j
@Controller
@RequestMapping("log")
public class LogController {

    @Autowired
    private LogService logService;
    @Autowired
    private HttpServletResponse response;

    //@YxueLog(value = "日志查询",tableName = "yx_log")
    @ResponseBody
    @RequestMapping("showLog")
    public Result<?> showLog(Integer page, Integer rows){

        IPage pageInfo = new Page(page, rows);
        QueryWrapper<Log> queryWrapper = new QueryWrapper<>();
        IPage pageList =logService.page(pageInfo, queryWrapper);

        return Result.ok(
                pageList.getRecords(),
                pageList.getCurrent(),
                pageList.getPages(),
                pageList.getTotal()
        );
    }

    //添加、修改、删除
    @ResponseBody
    @RequestMapping("edit")
    public Result<?> edit(Log log,String oper){
        Result result = new Result();
        if(oper.equals("del")){
            logService.removeById(log.getId());
        }if (oper.equals("edit")){
            //log.s(null);
            //logService.updateUser(user);
        }
        return result;
    }

    //导出
    @ResponseBody
    @RequestMapping("exportLog")
    public void exportLog() {
        QueryWrapper<Log> queryWrapper = new QueryWrapper<>();
        List<Log> logs=logService.list(queryWrapper);
        EasyPoiUtil.exportExcel(logs,"日志信息","日志",Log.class,"日志.xls",response);
    }

    //导出模板
    @ResponseBody
    @RequestMapping("exportLogTemplate")
    public void exportLogTemplate() {
        List<Log> logs=new ArrayList<>();
        logs.add(new Log(null,null,null,null,null,null,null,null));
        EasyPoiUtil.exportExcel(logs,"日志信息","日志模板",Log.class,"日志模板.xls",response);
    }
    //导入
    @RequestMapping("importLog")
    public String importLog(MultipartFile multipartFile){
        List<Log> logs= EasyPoiUtil.importExcel(multipartFile, 1, 1, Log.class);
        for (Log log : logs) {
            logService.save(log);
            System.out.println(log);
        }
        return "redirect:/login.jsp";
    }
}
