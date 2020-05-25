package com.ldy.service.impl;

import com.ldy.common.enums.LdyErrorCode;
import com.ldy.common.exception.LdyRuntimeException;
import com.ldy.core.model.ServiceDescriptor;
import com.ldy.core.util.JmxMetricsService;
import com.ldy.service.IJmsService;
import com.ldy.vo.reponse.Response4OverviewVO;
import org.springframework.stereotype.Component;

/**
 * @Description: JmsServiceImpl
 * @ClassName: com.ldy.service.impl.JmsServiceImpl.java
 * @author: songxulin
 * @date :  2020-05-25 20:12
 * @version V1.0
 */
@Component
public class JmsServiceImpl implements IJmsService {
    /**
     * 获取jmx概述信息
     * @author: songxulin
     * @date :  2020-05-25 20:18
     */
    @Override
    public Response4OverviewVO getOverview(ServiceDescriptor descriptor) {
        if(descriptor==null){
            throw new LdyRuntimeException(LdyErrorCode.SERVER_ERROR.getCode(),"没有获取到ServiceDescriptor");
        }
        JmxMetricsService jmxMetricsService = descriptor.getJmxMetricsService();
        Response4OverviewVO overviewVO = new Response4OverviewVO();
        overviewVO.setPid(jmxMetricsService.getPid());
        overviewVO.setHost(jmxMetricsService.getHost());
        overviewVO.setBootClassPath(jmxMetricsService.getBootClassPath());
        return null;
    }
}
