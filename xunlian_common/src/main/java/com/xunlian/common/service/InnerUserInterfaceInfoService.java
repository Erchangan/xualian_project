package com.xunlian.common.service;

public interface InnerUserInterfaceInfoService {
    boolean invokeCount(long userId,long interfaceId);

    int getLeftCount(long userId,long interfaceInfoId);
}
