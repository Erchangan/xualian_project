package com.xunlian.project.model.dto.interfaceInfo;

import lombok.Data;

import java.io.Serializable;

@Data
public class InterfaceInfoInvokeRequest implements Serializable {
    private long id;
    private String userRequestParams;
}
