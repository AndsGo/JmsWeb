package com.ldy.service.impl;

import com.ldy.common.enums.LdyErrorCode;
import com.ldy.common.exception.LdyRuntimeException;
import com.ldy.core.model.ServiceDescriptor;
import com.ldy.core.util.JmxMetricsService;
import com.ldy.service.IJmsService;
import com.ldy.vo.reponse.Response4monitorVO;
import com.ldy.vo.reponse.Response4overviewVO;
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
    public Response4overviewVO getOverview(ServiceDescriptor descriptor) {
        if(descriptor==null){
            throw new LdyRuntimeException(LdyErrorCode.SERVER_ERROR.getCode(),"没有获取到ServiceDescriptor");
        }
        JmxMetricsService jmxMetricsService = descriptor.getJmxMetricsService();
        Response4overviewVO overviewVO = new Response4overviewVO();
        overviewVO.setPid(jmxMetricsService.getPid());
        overviewVO.setHost(jmxMetricsService.getHost());
        overviewVO.setBootClassPath(jmxMetricsService.getBootClassPath());
        overviewVO.setVmName(jmxMetricsService.getVmName());
        //包含当前运行的应用的所有参数
        overviewVO.setSystemProperties(jmxMetricsService.getSystemProperties());
        overviewVO.setInputArguments(jmxMetricsService.getInputArguments());
        overviewVO.setVmVendor(jmxMetricsService.getVmVendor());
        overviewVO.setStartTime(jmxMetricsService.getStartTime());
        overviewVO.setUptime(jmxMetricsService.getUptime());
        return overviewVO;
    }
    /**
     * @Description: 获取jmx监控信息
     * @MethodName: com.ldy.service.impl.JmsServiceImpl#getMonitor(com.ldy.core.model.ServiceDescriptor)
     * @return  Response4monitorVO
     * @author: songxulin
     * @date :  2020-06-02 21:17
     */
    @Override
    public Response4monitorVO getMonitor(ServiceDescriptor descriptor) {
        if(descriptor==null){
            throw new LdyRuntimeException(LdyErrorCode.SERVER_ERROR.getCode(),"没有获取到ServiceDescriptor");
        }
        return null;
    }
}
