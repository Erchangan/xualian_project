package com.xunlian.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunlian.project.exception.BusinessException;

import com.xunlian.project.common.ErrorCode;
import com.xunlian.project.mapper.UserInterfaceInfoMapper;
import com.xunlian.common.model.UserInterfaceInfo;
import com.xunlian.project.service.UserInterfaceInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lenovo
 * @description 针对表【user_interfacei_info】的数据库操作Service实现
 * @createDate 2023-09-30 15:09:01
 */
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper,UserInterfaceInfo> implements UserInterfaceInfoService {
    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;
    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        // 创建时，所有参数必须非空
        if (add) {
            if (userInterfaceInfo.getInterfaceInfoId() <= 0 || userInterfaceInfo.getUserId() <= 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口不存在");
            }
        }
        if (userInterfaceInfo.getLeftNumber() < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "剩余次数小于0");
        }
    }

    /**
     * 更新调用次数
     * @param userId
     * @param interfaceInfoId
     */
    @Override
    public boolean invokeCount(long userId, long interfaceInfoId) {
        if (userId <= 0&&interfaceInfoId<=0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        LambdaQueryWrapper<UserInterfaceInfo> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserInterfaceInfo::getInterfaceInfoId, interfaceInfoId)
                .eq(UserInterfaceInfo::getUserId, userId);
        UserInterfaceInfo userInterfaceInfo = userInterfaceInfoMapper.selectOne(lambdaQueryWrapper);
        if(userInterfaceInfo==null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        LambdaUpdateWrapper<UserInterfaceInfo> lambdaUpdateWrapper=new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(UserInterfaceInfo::getInterfaceInfoId,interfaceInfoId)
                .eq(UserInterfaceInfo::getUserId,userId)
                .gt(UserInterfaceInfo::getLeftNumber,0)
                .set(UserInterfaceInfo::getTotalNumber,userInterfaceInfo.getTotalNumber()+1)
                .set(UserInterfaceInfo::getLeftNumber,userInterfaceInfo.getLeftNumber()-1);
        userInterfaceInfoMapper.update(userInterfaceInfo, lambdaUpdateWrapper);
        return true;
    }
}




