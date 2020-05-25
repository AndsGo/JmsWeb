package com.ldy.controller;

import com.ldy.common.response.Response;
import com.ldy.common.response.ResponseHelper;
import com.ldy.core.model.Peer;
import com.ldy.core.model.ServiceDescriptor;
import com.ldy.core.util.JmxMetricsService;
import com.ldy.core.util.MBeanServerConnectionUtil;
import com.ldy.service.IJmsService;
import com.ldy.vo.reponse.Response4OverviewVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Set;

/**
 * jms请求接口
 * @author songxulin
 */
@RestController
public class JmsController {
    private static final Logger logger = LoggerFactory.getLogger(JmsController.class);

    @Autowired
    private IJmsService jmsService;
    /**
     * 获取已经连接的节点
     * @return
     */
    @RequestMapping("/jms/getPeerList")
    public Response getPeerList() {
        Set<Peer> peerSet = MBeanServerConnectionUtil.getPeerSet();
        logger.info("peerSet:",peerSet);
        return ResponseHelper.buildOk(peerSet);
    }
    /**
     * 获取jmx连接
     * @author: songxulin
     * @date :  2020-05-25 20:12
     */
    @RequestMapping("/jms/getConnection")
    public Response getConnection(Peer peer) {
        try {
            ServiceDescriptor descriptor = MBeanServerConnectionUtil.getMBeanServerConnection(peer);
            String connectionId = descriptor.getJmxConnector().getConnectionId();
            return ResponseHelper.buildOk(connectionId);
        } catch (Exception e) {
            logger.info("获取连接失败");
            return ResponseHelper.buildFail(e.getMessage());
        }
    }

    /**
     * 获取jvm概述信息
     * @param peer
     * @return Response
     */
    @RequestMapping("/jms/getOverview")
    public Response getOverview(Peer peer) {
        try {
            ServiceDescriptor descriptor = MBeanServerConnectionUtil.getMBeanServerConnection(peer);
            Response4OverviewVO response4OverviewVO= jmsService.getOverview(descriptor);
            return ResponseHelper.buildOk(response4OverviewVO);
        } catch (Exception e) {
            logger.info("获取连接失败");
            return ResponseHelper.buildFail(e.getMessage());
        }
    }


}
