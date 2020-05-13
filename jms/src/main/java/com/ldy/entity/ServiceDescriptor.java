package com.ldy.entity;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.CompilationMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;

/**
 * 表示一个jmx连接服务
 */
public class ServiceDescriptor {
    private Peer peer;
    private JMXConnector jmxConnector;
    private MBeanServerConnection mBeanServerConnection;

    private ClassLoadingMXBean classLoading;
    private CompilationMXBean compilation;

    private OperatingSystemMXBean system;

    private RuntimeMXBean runtime;
    private ThreadMXBean thread;
}
