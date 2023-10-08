package com.xunlian.project.provider;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xunlian.common.service.InnerUserService;
import com.xunlian.project.common.ErrorCode;
import com.xunlian.project.exception.BusinessException;
import com.xunlian.project.mapper.UserMapper;
import com.xunlian.common.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class InnerUserServiceImpl implements InnerUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getInvokeUser(String accessKey) {
        if(StringUtils.isAnyBlank(accessKey)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getAccessKey,accessKey);
        User user = userMapper.selectOne(lambdaQueryWrapper);
        return user;
    }
}
