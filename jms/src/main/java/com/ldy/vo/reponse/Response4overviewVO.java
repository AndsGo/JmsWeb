package com.ldy.vo.reponse;


import java.util.List;
import java.util.Map;

/**
 * @Description: 概述VO
 * @ClassName: com.ldy.vo.reponse.Response4OverviewVO.java
 * @author: songxulin
 * @date :  2020-05-25 20:03
 * @version V1.0
 */
public class Response4overviewVO {
    /**
     * 线程id
     */
    private String pid;
    /**
     * 主机
     */
    private String host;
    /**
     * javaHome
     */
    private String bootClassPath;

    /**
     * jvm名称
     */
    private String vmName;
    /**
     * jvm供应商
     */
    private String vmVendor;
    /**
     * 系统属性
     */
    private Map<String, String> systemProperties;
    /**
     * jvm启动参数
     */
    private List<String> inputArguments;
    /**
     * 开始运行时间
     */
    private Long startTime;
    /**
     * 正常运行时间
     */
    private Long uptime;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getBootClassPath() {
        return bootClassPath;
    }

    public void setBootClassPath(String bootClassPath) {
        this.bootClassPath = bootClassPath;
    }

    public String getVmName() {
        return vmName;
    }

    public void setVmName(String vmName) {
        this.vmName = vmName;
    }

    public Map<String, String> getSystemProperties() {
        return systemProperties;
    }

    public void setSystemProperties(Map<String, String> systemProperties) {
        this.systemProperties = systemProperties;
    }

    public List<String> getInputArguments() {
        return inputArguments;
    }

    public void setInputArguments(List<String> inputArguments) {
        this.inputArguments = inputArguments;
    }

    public String getVmVendor() {
        return vmVendor;
    }

    public void setVmVendor(String vmVendor) {
        this.vmVendor = vmVendor;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getUptime() {
        return uptime;
    }

    public void setUptime(Long uptime) {
        this.uptime = uptime;
    }
}
