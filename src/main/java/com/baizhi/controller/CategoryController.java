package com.baizhi.controller;

import com.baizhi.anno.YxueLog;
import com.baizhi.config.EasyPoiUtil;
import com.baizhi.config.Result;
import com.baizhi.entity.Category;
import com.baizhi.entity.User;
import com.baizhi.service.CategoryService;
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

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 创建者：xw
 * 类的作用：
 * 创建时间：2020/9/24
 */
@Slf4j
@RestController
@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private HttpServletResponse response;


    //@YxueLog(value = "一级类别分页查询",tableName = "yx_category")
    @RequestMapping("showCategory")
    public Result<?> showCategory(Integer page,Integer rows,String searchField,
                                  String searchString,String searchOper){

        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        if (searchField!=null) {
            if ("eq".equals(searchOper)) {
                queryWrapper.eq(searchField, searchString);
            }
            if ("cn".equals(searchOper)) {
                queryWrapper.like(searchField, searchString);
            }
        }
        IPage pageInfo = new Page(page, rows);
        IPage pageList = categoryService.page(pageInfo, queryWrapper.eq("level","1"));

        return Result.ok(
                pageList.getRecords(),
                pageList.getCurrent(),
                pageList.getPages(),
                pageList.getTotal()
        );
    }
    //@YxueLog(value = "二级类别分页查询",tableName = "yx_category")
    @RequestMapping("showByIdCategory")
    public Result<?>   showByIdCategory(Integer page,Integer rows,String id,String searchField,
                                        String searchString,String searchOper){

        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        if (searchField!=null) {
            if ("eq".equals(searchOper)) {
                queryWrapper.eq(searchField, searchString);
            }
            if ("cn".equals(searchOper)) {
                queryWrapper.like(searchField, searchString);
            }
        }
        IPage<Category> pageInfo = new Page(page, rows);
        IPage pageList = categoryService.page(pageInfo, queryWrapper.eq("p_id",id));
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
    public Result<?> edit(Category category,String oper){
        System.out.println("添加类别"+category);
        Result result = new Result();
        if(oper.equals("add")){
            if (category.getPId()==null) {
                category.setId(null);
                category.setLevel("1");
                category.setPId(null);
                categoryService.save(category);
            }else if (category.getPId()!=null) {
                category.setId(null);
                category.setLevel("2");
                categoryService.save(category);
            }
            System.out.println("category = " + category);
        }if(oper.equals("del")){

                categoryService.removeCategory(category.getId());

        }if (oper.equals("edit")){
                categoryService.updateCategory(category);
        }
        return result;
    }
    //导出
    @ResponseBody
    @RequestMapping("exportCategory")
    public void exportCategory() {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        List<Category> list=categoryService.list(queryWrapper);
        EasyPoiUtil.exportExcel(list,"分类信息","分类",Category.class,"分类.xls",response);
    }

    //导入
    @RequestMapping("importCategory")
    public String importCategory(MultipartFile multipartFile){
        List<Category> userList= EasyPoiUtil.importExcel(multipartFile, 1, 1, Category.class);
        for (Category category : userList) {
            categoryService.save(category);
            System.out.println(category);
        }
        return "redirect:/login.jsp";
    }
}
