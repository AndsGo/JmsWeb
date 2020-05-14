package com.ldy.core.util;

import com.ldy.common.exception.LdyErrorCode;
import com.ldy.common.exception.LdyRuntimeException;
import com.ldy.core.model.Peer;
import com.ldy.core.model.ServiceDescriptor;
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

    private static volatile Map<Peer, ServiceDescriptor> jmxServiceMap = new ConcurrentHashMap<>(16);
    /**
     * 获取jmx连接
     *
     * @param peer
     * @throws IOException
     */
    private static synchronized void openMBeanServerConnection(Peer peer) throws IOException {
        if (peer == null || StringUtils.isEmpty(peer.getHost())) {
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
            JmxMetricsService jmxMetricsService = new JmxMetricsService(mBeanServerConnection);
            jmxServiceMap.put(peer, new ServiceDescriptor(peer,jmxConnector,mBeanServerConnection,jmxMetricsService));
        }
    }

    /**
     * 根据一个端点获取 ServiceDescriptor
     *
     * @param peer
     * @return
     * @throws IOException
     */
    public static ServiceDescriptor getMBeanServerConnection(Peer peer) throws IOException {
        //从缓存中获取
        ServiceDescriptor serviceDescriptor = jmxServiceMap.get(peer);
        if (serviceDescriptor != null) {
            serviceDescriptor.setExecuteTime(new Date());
            return serviceDescriptor;
        } else {
            //重新连接
            openMBeanServerConnection(peer);
        }
        serviceDescriptor = jmxServiceMap.get(peer);
        if (serviceDescriptor != null) {
            serviceDescriptor.setExecuteTime(new Date());
            return serviceDescriptor;
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
        jmxServiceMap.entrySet().removeIf(peerJMXConnectorEntry -> {
            Date date = peerJMXConnectorEntry.getValue().getExecuteTime();
            if (date == null) {
                return true;
            }
            //大于半小时,任务连接断开,清除连接
            if ((System.currentTimeMillis() - date.getTime()) > timeInterval) {
                peerJMXConnectorEntry.getValue().close();
                return true;
            }
            //获取连接异常，表示超时
            try {
                peerJMXConnectorEntry.getValue().getJmxConnector().getConnectionId();
            }catch (Exception e){
                peerJMXConnectorEntry.getValue().close();
                return true;
            }
            return false;
        });
    }

}
