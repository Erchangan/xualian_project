package com.xunlian.project.provider;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xunlian.common.service.InnerInterfaceInfoService;
import com.xunlian.project.common.ErrorCode;
import com.xunlian.project.exception.BusinessException;
import com.xunlian.project.mapper.InterfaceInfoMapper;
import com.xunlian.common.model.InterfaceInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
@DubboService
public class InnerInterfaceInfoServiceImpl implements InnerInterfaceInfoService {
    @Autowired
    private InterfaceInfoMapper interfaceInfoMapper;
    @Override
    public InterfaceInfo getInterfaceInfo(String url, String method) {
        if(StringUtils.isAnyBlank(url,method)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        LambdaQueryWrapper<InterfaceInfo> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(InterfaceInfo::getUrl,url);
        lambdaQueryWrapper.eq(InterfaceInfo::getMethod,method);
        InterfaceInfo interfaceInfo = interfaceInfoMapper.selectOne(lambdaQueryWrapper);
        return interfaceInfo;
    }
}
