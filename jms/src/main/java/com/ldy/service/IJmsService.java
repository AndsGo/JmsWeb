package com.ldy.service;

import com.ldy.core.model.ServiceDescriptor;
import com.ldy.vo.reponse.Response4monitorVO;
import com.ldy.vo.reponse.Response4overviewVO;

/**
 * @Description:  JmsService
 * @ClassName: com.ldy.service.JmsService.java
 * @author: songxulin
 * @date :  2020-05-25 20:11
 * @version V1.0
 */
public interface IJmsService {
    /**
     * 获取jmx概述信息
     * @param descriptor
     * @return Response4overviewVO
     */
    Response4overviewVO getOverview(ServiceDescriptor descriptor);

    /**
     * 获取jmx监控信息
     * @param descriptor
     * @return Response4monitorVO
     */
    Response4monitorVO getMonitor(ServiceDescriptor descriptor);
}
