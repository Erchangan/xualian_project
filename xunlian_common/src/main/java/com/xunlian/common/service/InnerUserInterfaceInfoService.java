package com.xunlian.common.service;

public interface InnerUserInterfaceInfoService {
    boolean invokeCount(long userId,long interfaceInfoId);

    int getLeftCount(long userId,long interfaceInfoId);
}
