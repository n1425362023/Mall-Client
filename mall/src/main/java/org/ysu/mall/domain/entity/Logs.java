package org.ysu.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * Logs
 *
 * 日志实体类，用于记录管理员操作日志。
 */
@TableName(value ="Logs")
@Data
@Accessors(chain = true)
public class Logs {
    /**
     * 日志ID
     */
    private Long id;

    /**
     * 管理员ID
     */
    private Long adminId;

    /**
     * 操作内容
     */
    private String action;

    /**
     * 操作时间
     */
    private Date timestamp;

}
