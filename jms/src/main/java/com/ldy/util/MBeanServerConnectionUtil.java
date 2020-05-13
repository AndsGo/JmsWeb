package com.ldy.util;

import com.ldy.common.exception.LdyErrorCode;
import com.ldy.common.exception.LdyRuntimeException;
import com.ldy.entity.Peer;
import org.springframework.util.StringUtils;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MBeanServerConnectionUtil {

    private static Map<Peer, JMXConnector> jmxConnectorMap = new ConcurrentHashMap<>(16);
    private static Map<Peer, Date> jmxConnectionTimeMap = new ConcurrentHashMap<>(16);
    private static Map<Peer, MBeanServerConnection> mBeanServerConnectionMap = new ConcurrentHashMap<>(16);

    /**
     * 获取jmx连接
     *
     * @param peer
     * @throws IOException
     */
    private static synchronized void openMBeanServerConnection(Peer peer) throws IOException {
        if (peer != null && !StringUtils.isEmpty(peer.getHost())) {
            //抛出参数不合法异常
            throw new LdyRuntimeException(LdyErrorCode.ILLEGAL_ARGUMENTS);
        } else {
            // initiate address of the JMX API connector server
            String serviceURL = "service:jmx:rmi:///jndi/rmi://" + peer.getHost() + ":" + peer.getPort() + "/jmxrmi";
            JMXServiceURL jmxServiceURL = new JMXServiceURL(serviceURL);

            // initiate client side JMX API connector
            // here we set environment attributes to null, because it is not a necessity to what we're going to do
            JMXConnector jmxConnector = JMXConnectorFactory.connect(jmxServiceURL, null);

            MBeanServerConnection mBeanServerConnection = jmxConnector.getMBeanServerConnection();
            jmxConnectorMap.put(peer, jmxConnector);
            jmxConnectionTimeMap.put(peer, new Date());
            mBeanServerConnectionMap.put(peer, mBeanServerConnection);
        }
    }

    /**
     * 根据一个端点获取 MBeanServerConnection
     *
     * @param peer
     * @return
     * @throws IOException
     */
    public static MBeanServerConnection getMBeanServerConnection(Peer peer) throws IOException {
        //从缓存中获取
        MBeanServerConnection mBeanServerConnection = mBeanServerConnectionMap.get(peer);
        if (mBeanServerConnection != null) {
            return mBeanServerConnection;
        } else {
            //重新连接
            openMBeanServerConnection(peer);
        }
        mBeanServerConnection = mBeanServerConnectionMap.get(peer);
        if (mBeanServerConnection != null) {
            return mBeanServerConnection;
        } else {
            throw new LdyRuntimeException(LdyErrorCode.ILLEGAL_ARGUMENTS);
        }
    }

    /**
     * 移除过期的连接
     *
     * @param timeInterval 过期时间
     */
    public static synchronized void removeExpiredConnection(Long timeInterval) {
        jmxConnectorMap.entrySet().removeIf(peerJMXConnectorEntry -> {
            Date date = jmxConnectionTimeMap.get(peerJMXConnectorEntry.getKey());
            if (date == null) {
                return true;
            }
            //大于半小时,认为连接断开,清除连接
            if ((System.currentTimeMillis() - date.getTime()) > timeInterval) {
                try {
                    jmxConnectionTimeMap.remove(peerJMXConnectorEntry.getKey());
                    mBeanServerConnectionMap.remove(peerJMXConnectorEntry.getKey());
                    peerJMXConnectorEntry.getValue().close();
                } catch (IOException e) {
                }
                return true;
            }
            return false;
        });
    }
}
