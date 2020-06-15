package com.ldy.core.model;


import com.ldy.core.util.JmxMetricsService;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import java.io.IOException;
import java.util.Date;

/**
 * 表示一个jmx连接服务
 */
public class ServiceDescriptor {
    /**
     * 端点
     */
    private Peer peer;
    /**
     * JMX连接
     */
    private JMXConnector jmxConnector;
    /**
     * 代表MBean服务器的MBeanServer接口扩展了此接口
     */
    private MBeanServerConnection mBeanServerConnection;
    /**
     * 执行时间
     */
    private Date executeTime;

    private JmxMetricsService jmxMetricsService;
    private JmsServicePrevModel jmsServiceModel;

    public ServiceDescriptor(Peer peer, JMXConnector jmxConnector, MBeanServerConnection mBeanServerConnection, JmxMetricsService jmxMetricsService) {
        this.peer = peer;
        this.jmxConnector = jmxConnector;
        this.mBeanServerConnection = mBeanServerConnection;
        this.jmxMetricsService = jmxMetricsService;
        this.executeTime = new Date();
        this.jmsServiceModel = new JmsServicePrevModel();
    }

    public Peer getPeer() {
        return peer;
    }

    public void setPeer(Peer peer) {
        this.peer = peer;
    }

    public JMXConnector getJmxConnector() {
        return jmxConnector;
    }

    public void setJmxConnector(JMXConnector jmxConnector) {
        this.jmxConnector = jmxConnector;
    }

    public MBeanServerConnection getmBeanServerConnection() {
        return mBeanServerConnection;
    }

    public void setmBeanServerConnection(MBeanServerConnection mBeanServerConnection) {
        this.mBeanServerConnection = mBeanServerConnection;
    }

    public JmxMetricsService getJmxMetricsService() {
        return jmxMetricsService;
    }

    public void setJmxMetricsService(JmxMetricsService jmxMetricsService) {
        this.jmxMetricsService = jmxMetricsService;
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    public JmsServicePrevModel getJmsServiceModel() {
        return jmsServiceModel;
    }

    public void setJmsServiceModel(JmsServicePrevModel jmsServiceModel) {
        this.jmsServiceModel = jmsServiceModel;
    }

    public void close() {
        try {
            if (this.jmxConnector != null) {
                this.jmxConnector.close();
            }
        } catch (IOException e) {
        }
        this.mBeanServerConnection = null;
        this.jmxMetricsService = null;
        this.executeTime = null;
        this.peer = null;
    }
}
