package com.ldy.controller;

import com.ldy.common.response.Response;
import com.ldy.common.response.ResponseHelper;
import com.ldy.core.model.Peer;
import com.ldy.core.model.ServiceDescriptor;
import com.ldy.core.util.MBeanServerConnectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Set;

/**
 * jms请求接口
 * songxulin
 */
@RestController
public class JmsController {
    private static final Logger logger = LoggerFactory.getLogger(JmsController.class);
    @RequestMapping("/jms/getPeerList")
    public Response getPeerList() {
        Set<Peer> peerSet = MBeanServerConnectionUtil.getPeerSet();
        logger.info("peerSet:",peerSet);
        return ResponseHelper.buildOk(peerSet);
    }

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
}
