package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaozhi
 * @since 2020-09-24
 */
@Document(indexName="yingx",type = "video")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("yx_video")
@NoArgsConstructor
@AllArgsConstructor
public class Video implements Serializable {


    @TableId(type = IdType.UUID)
    @Id
    private String id;

    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String title; //标题

    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String intro; //简介

    @Field(type = FieldType.Keyword)
    private String coverUrl; //视频封面

    @Field(type = FieldType.Keyword)
    private String videoUrl; //视频地址

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @Field(type = FieldType.Date, pattern ="yyyy-MM-dd")
    private Date createAt; //发布时间

    @Field(type = FieldType.Keyword)
    private String userId; //用户编号

    @Field(type = FieldType.Keyword)
    private String cid; //类别编号

    @Field(type = FieldType.Keyword)
    private String grpId; //分组编号

    public Video(String id, String title, String intro, String coverUrl, String videoUrl, Date createAt, String userId, String cid, String grpId) {
        this.id = id;
        this.title = title;
        this.intro = intro;
        this.coverUrl = coverUrl;
        this.videoUrl = videoUrl;
        this.createAt = createAt;
        this.userId = userId;
        this.cid = cid;
        this.grpId = grpId;
    }

    @TableField(exist = false)
    private User fuser;  //用户关系属性

    @TableField(exist = false)
    private  Category fcate; //类别关系属性

    @TableField(exist = false)
    private  Group fgroup; //分组关系属性

    @TableField(exist = false)
    private  long  likeNum;//点赞数

    @TableField(exist = false)
    private  Play  fplay; // 播放关系属性


}
