package com.ldy.controller;

import com.ldy.common.response.Response;
import com.ldy.common.response.ResponseHelper;
import com.ldy.core.model.Peer;
import com.ldy.core.model.ServiceDescriptor;
import com.ldy.core.util.JmxMetricsService;
import com.ldy.core.util.MBeanServerConnectionUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * jms请求接口
 * songxulin
 */
@RestController
public class JmsController {
    @CrossOrigin
    @RequestMapping("/jms/getConnection")
    public Response getConnection(Peer peer) {
        try {
            ServiceDescriptor descriptor = MBeanServerConnectionUtil.getMBeanServerConnection(peer);
            JmxMetricsService jmxMetricsService = descriptor.getJmxMetricsService();
            if(jmxMetricsService!=null){
                String connectionId = descriptor.getJmxConnector().getConnectionId();
                System.out.println("descriptor:"+connectionId);
                System.out.println("jmxMetricsService.getName()"+jmxMetricsService.getName());
                return ResponseHelper.buildOk(jmxMetricsService.getName());
            }else {
                return ResponseHelper.buildFail("没有获取到连接");
            }
        } catch (IOException e) {
            return ResponseHelper.buildFail(e.getMessage());
        }
    }
}
