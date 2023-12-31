package com.xunlian.common.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName user_interfacei_info
 */
@TableName(value ="user_interfacei_info")
@Data
public class UserInterfaceInfo implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long userId;

    /**
     * 
     */
    private Long interfaceInfoId;

    /**
     * 
     */
    private Integer totalNumber;

    /**
     * 
     */
    private Integer leftNumber;

    /**
     * 
     */
    private Integer status;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    @TableLogic
    private Integer idDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}