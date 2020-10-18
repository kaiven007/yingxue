package com.baizhi.service.impl;

import com.baizhi.anno.YxueLog;
import com.baizhi.dao.CategoryMapper;
import com.baizhi.entity.Category;
import com.baizhi.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建者：xw
 * 类的作用：
 * 创建时间：2020/9/24
 */
@Service
public class CategoryServiceImpl  extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    @YxueLog(value = "二级类别修改",tableName = "yx_category")
    public void updateCategory(Category category) {

        categoryMapper.updateById(category);
    }

    @Override
    public List<Category> queryTwo(String id) {
        QueryWrapper<Category> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("p_id",id);
        //查询所有
        List<Category> category=categoryMapper.selectList(queryWrapper);
        System.out.println("二级类别："+category);
        return  category;
    }

    @Override
    @YxueLog(value = "删除类别",tableName = "yx_category")
    public void removeCategory(String id) {
       Category category=categoryMapper.selectById(id);
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("p_id",category.getId());
        List<Category>  list=categoryMapper.selectList(queryWrapper);
          if (category.getLevel().equals("1")&&list.size()==0){
              categoryMapper.deleteById(category.getId());
          }else if (category.getLevel().equals("2")){
              categoryMapper.deleteById(category.getId());
          }

    }

}
