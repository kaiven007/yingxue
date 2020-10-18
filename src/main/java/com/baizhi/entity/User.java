package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 创建者：xw
 * 类的作用：
 * 创建时间：2020/9/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "yx_user")
public class User implements Serializable {

    @TableId(type = IdType.UUID)
    @Excel(name = "编号",width = 40)
    private   String  id;

    @Excel(name = "用户名",width = 30)
    private   String  username;

    @Excel(name = "性别",width = 30)
    private   String  sex;

    @Excel(name = "手机号",width = 20)
    private   String  mobile;

    @Excel(name = "签名",width = 30)
    private   String  sign;

    @Excel(name = "头像",type = 2,width = 30 , height = 30,imageType = 1)
    private   String  headShow;

    @Excel(name = "激活状态",width = 10)
    private   String  status;

    @Excel(name = "创建时间",format = "yyyy-MM-dd",width = 20)
    @DateTimeFormat(pattern = "yy-mm-dd")
    private   Date    regTime;

    @Excel(name = "学分",width = 10)
    private   Double  score;

    @Excel(name = "第三方账号",width = 20)
    private   String  wechat;

    private   String  cityId; //城市id

    @TableField(exist = false)
    private  City fcity;

    @TableField(exist = false)
    private  String month;

    @TableField(exist = false)
    private  Integer count;

    @TableField(exist = false)
    private  Integer cityCount;
}
