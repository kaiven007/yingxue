package com.baizhi.dto;

import com.baizhi.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 创建者：xw
 * 类的作用：
 * 创建时间：2020/10/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO  {
    private String id;//类别id
    private String cateName;// 类别名
    private String levels;// 类别级别
    private String parentId;// 上级类别id
    private List<CategoryDTO>   categoryList;// 下级类别集合
}
