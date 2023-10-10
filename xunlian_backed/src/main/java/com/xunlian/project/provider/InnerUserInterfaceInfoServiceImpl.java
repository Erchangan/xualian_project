package com.xunlian.project.provider;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xunlian.common.model.UserInterfaceInfo;
import com.xunlian.common.service.InnerUserInterfaceInfoService;
import com.xunlian.project.mapper.UserInterfaceInfoMapper;
import com.xunlian.project.service.UserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {
    @Autowired
    private UserInterfaceInfoService userInterfaceInfoService;
    @Autowired
    private UserInterfaceInfoMapper userInterfaceInfoMapper;
    @Override
    public boolean invokeCount(long userId, long interfaceInfoId) {
        return userInterfaceInfoService.invokeCount(userId,interfaceInfoId);
    }
    @Override
    public int getLeftCount(long userId, long interfaceInfoId) {
        LambdaQueryWrapper<UserInterfaceInfo> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserInterfaceInfo::getUserId,userId);
        lambdaQueryWrapper.eq(UserInterfaceInfo::getInterfaceInfoId,interfaceInfoId);
        UserInterfaceInfo userInterfaceInfo = userInterfaceInfoMapper.selectOne(lambdaQueryWrapper);
        Integer leftNumber = userInterfaceInfo.getLeftNumber();
        return leftNumber;
    }
}
