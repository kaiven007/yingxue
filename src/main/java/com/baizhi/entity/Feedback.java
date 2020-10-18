package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaozhi
 * @since 2020-09-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("yx_feedback")
public class Feedback implements Serializable {

    @TableId(type = IdType.UUID)
    @Excel(name = "编号",width = 40)
    private String id;

    @Excel(name = "反馈标题",width = 30)
    private String title;  //反馈标题

    @Excel(name = "反馈内容",width = 40)
    private String content; //反馈内容

    @Excel(name = "反馈时间",format = "yyyy-MM-dd",width = 10)
    @DateTimeFormat(pattern = "yy-mm-dd")
    private Date createAt; //反馈时间

    @Excel(name = "反馈用户",width = 20)
    private String userId; //用户id

    @TableField(exist = false)
    private User fuser;  //用户关系属性

}
