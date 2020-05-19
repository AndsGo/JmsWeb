package com.ldy.controller;

import com.ldy.common.response.Response;
import com.ldy.common.response.ResponseHelper;
import com.ldy.core.model.Peer;
import com.ldy.core.util.MBeanServerConnectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * jms请求接口
 * songxulin
 */
@RestController
public class JmsController {
    private static final Logger logger = LoggerFactory.getLogger(JmsController.class);
    @RequestMapping("/jms/getPeerList")
    public Response getPeerList(Peer peer) {
        Set<Peer> peerSet = MBeanServerConnectionUtil.getPeerSet();
        logger.info("peerSet:",peerSet);
        return ResponseHelper.buildOk(peerSet);
    }

    @RequestMapping("/jms/getPeerList2")
    public Response getPeerList2(Peer peer) {
        Set<Peer> peerSet = MBeanServerConnectionUtil.getPeerSet();
        logger.info("peerSet:",peerSet);
        return ResponseHelper.buildOk(peerSet);
    }
}
