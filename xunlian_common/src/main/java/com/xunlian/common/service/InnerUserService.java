package com.xunlian.common.service;


import com.xunlian.common.model.User;

public interface InnerUserService {
    User getInvokeUser(String accessKey);
}
