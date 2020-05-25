package com.ldy.vo.reponse;

/**
 * @Description: 概述VO
 * @ClassName: com.ldy.vo.reponse.Response4OverviewVO.java
 * @author: songxulin
 * @date :  2020-05-25 20:03
 * @version V1.0
 */
public class Response4OverviewVO {
    /**
     * 线程id
     */
    private String pid;
    /**
     * 主机
     */
    private String host;
    /**
     * 主类
     */
    private String bootClassPath;

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
}
