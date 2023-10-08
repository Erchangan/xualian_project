package com.xunlian.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xunlian.common.model.UserInterfaceInfo;

/**
 * @author lenovo
 * @description 针对表【user_interfacei_info】的数据库操作Service
 * @createDate 2023-09-30 15:09:01
 */
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {
    /**
     * 参数校验
     * @param userInterfaceInfo
     * @param add
     */
    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);

    /**
     * 更新接口调用次数
     * @param userId
     * @param interfaceInfoId
     * @return
     */
    boolean invokeCount(long userId,long interfaceInfoId);

}
