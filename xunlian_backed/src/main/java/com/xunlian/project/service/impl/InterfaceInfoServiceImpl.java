package com.xunlian.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunlian.project.common.ErrorCode;
import com.xunlian.project.exception.BusinessException;
import com.xunlian.common.model.InterfaceInfo;
import com.xunlian.project.service.InterfaceInfoService;
import com.xunlian.project.mapper.InterfaceInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author qianhe
* @description 针对表【interface_info(interface_info)】的数据库操作Service实现
* @createDate 2023-01-29 19:10:08
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService {
    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String url = interfaceInfo.getUrl();
        // 创建时，所有参数必须非空
        if (add) {
            if (StringUtils.isBlank(url)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
        if (StringUtils.isNotBlank(url) && url.length() >50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "名称过长");
        }
    }

}




