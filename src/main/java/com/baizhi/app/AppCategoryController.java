package com.baizhi.app;

import com.baizhi.dto.CategoryDTO;
import com.baizhi.entity.Category;
import com.baizhi.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建者：xw
 * 类的作用：
 * 创建时间：2020/10/10
 */
@RestController
@RequestMapping("app")
public class AppCategoryController {

    @Autowired
    private CategoryService categoryService;

    //分类页面展示
    @GetMapping("queryAllCategory")
    public Map<String, Object> queryAllCategory() {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        List<Category> list = categoryService.list(queryWrapper.eq("level","1"));
        //封装结果
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Category category : list) {
            List<Category> list2 = categoryService.queryTwo(category.getId());
            //二级类别封装
            List<CategoryDTO> categoryDTOS2 = new ArrayList<>();
            for (Category category2 : list2) {
                CategoryDTO categoryDTO2 = new CategoryDTO(
                        category2.getId(),
                        category2.getName(),
                        category2.getLevel(),
                        category2.getPId(),
                        null
                );
                categoryDTOS2.add(categoryDTO2);
            }
            //一级类别封装
            CategoryDTO  categoryDTO=new CategoryDTO(
                    category.getId(),
                    category.getName(),
                    category.getLevel(),
                    category.getPId(),
                    categoryDTOS2
            );
            categoryDTOS.add(categoryDTO);
        }
        System.out.println("类别"+categoryDTOS);
        return result(categoryDTOS);
    }

    //二级分类的视频展示
    @RequestMapping("queryCateVideoList")
    public Map<String, Object>  queryCateVideoList(){

        return result(1);
    }

    //返回结果
    public Map<String, Object> result(Object obj) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", obj);
        result.put("message", "操作成功！~");
        result.put("status", 100);
        return result;
    }
}
