package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 创建者：xw
 * 类的作用：
 * 创建时间：2020/9/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "yx_category")
public class Category implements Serializable {

    @TableId(type = IdType.UUID)
    @Excel(name = "编号",width = 40)
    private String id;

    @Excel(name = "类别名称",width = 40)
    private String name;//类别名称

    @Excel(name = "分类级别",width = 40)
    private String level;//分类级别

    @Excel(name = "一级类别Id",width = 40)
    private String pId;//父类id
}
