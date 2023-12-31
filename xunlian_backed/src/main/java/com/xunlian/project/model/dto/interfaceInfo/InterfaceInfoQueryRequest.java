package com.xunlian.project.model.dto.interfaceInfo;

import com.xunlian.project.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 查询请求
 *
 * @author yupi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InterfaceInfoQueryRequest extends PageRequest implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户名
     */
    private String description;



    /**
     * 接口地址
     */
    private String url;

    /**
     * 请求头
     */
    private String requestHeader;
    /**
     * 请求参数
     */
    private String responseParams;
    /**
     * 响应头
     */
    private String responseHeader;

    /**
     * 接口状态(0-关闭，1-开启）
     */
    private Integer status;

    /**
     * 请求类型
     */
    private String method;

    /**
     * 创建人
     */
    private Long userid;


}
