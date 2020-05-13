package com.ldy.controller;

import com.ldy.entity.Peer;
import com.ldy.util.MBeanServerConnectionUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.MBeanServerConnection;
import java.io.IOException;

/**
 * jms请求接口
 * songxulin
 */
@RestController
public class JmsController {

    @RequestMapping("/getUsers")
    public String getConnection(Peer peer) {
        try {
            MBeanServerConnection mBeanServerConnection = MBeanServerConnectionUtil.getMBeanServerConnection(peer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
