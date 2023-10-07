package com.xunlian.project.model.dto.userinterfaceinfo;

import lombok.Data;

import java.io.Serializable;

/**
 * 创建请求
 *
 * @TableName product
 */
@Data
public class UserInterfaceInfoAddRequest implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 调用用户Id
     */
    private Long userId;

    /**
     * Id
     */
    private Long interfaceInfoId;

    /**
     * 总调用次数
     */
    private Integer totalNum;


}