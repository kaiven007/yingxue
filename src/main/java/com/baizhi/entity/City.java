package com.baizhi.entity;

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
@TableName(value = "cities")
public class City implements Serializable {

    private String    id;
    private String    name;
    private String    url;
    private char      leaf;
    private String    parentId;

}
