package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaozhi
 * @since 2020-09-28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("yx_log")
public class Log implements Serializable {

    @Excel(name = "编号",width = 40)
    private String id; //日志编号

    @Excel(name = "操作用户名",width = 20)
    private String username; //操作用户名

    @Excel(name = "操作时间",format = "yyyy-MM-dd",width = 40)
    private Date operationAt; //操作时间

    @Excel(name = "操作表名",width = 40)
    private String tableName; //操作表名

    @Excel(name = "操作的业务类型",width = 40)
    private String operationMethod; //操作的业务类型

    @Excel(name = "操作的方法签名",width = 40)
    private String methodName; //操作的方法签名

    @Excel(name = "操作的数据id",width = 40)
    private String dataId; //操作的数据id

    @Excel(name = "数据恢复",width = 40)
    private String dataInfo; //数据恢复


}
