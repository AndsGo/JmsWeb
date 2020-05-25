package com.ldy.service;

import com.ldy.core.model.ServiceDescriptor;
import com.ldy.vo.reponse.Response4OverviewVO;

/**
 * @Description:  JmsService
 * @ClassName: com.ldy.service.JmsService.java
 * @author: songxulin
 * @date :  2020-05-25 20:11
 * @version V1.0
 */
public interface IJmsService {
    Response4OverviewVO getOverview(ServiceDescriptor descriptor);
}
