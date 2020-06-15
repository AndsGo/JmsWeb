package com.ldy.core.model;



/**
 * 一个jmx连接model
 * 记录所连接jvm的一些历史记录
 * @ClassName: com.ldy.core.model.JmsServiceModel.java
 * @author: songxulin
 * @date :  2020-06-04 19:41
 * @version V1.0
 */
public class JmsServicePrevModel {
    /**
     * 上一次记录的运行时间
     */
    private long prevUpTime = -1;
    /**
     * 上一次cpu运行时间
     */
    private long prevProcessCpuTime = -1;
    /**
     * 上一次gc运行时间
     */
    private long prevProcessGcTime = -1;
    /**
     * 上一次监控线程时间
     */
    private long preThreadTime = -1;

    public long getPrevUpTime() {
        return prevUpTime;
    }

    public void setPrevUpTime(long prevUpTime) {
        this.prevUpTime = prevUpTime;
    }

    public long getPrevProcessCpuTime() {
        return prevProcessCpuTime;
    }

    public void setPrevProcessCpuTime(long prevProcessCpuTime) {
        this.prevProcessCpuTime = prevProcessCpuTime;
    }

    public long getPrevProcessGcTime() {
        return prevProcessGcTime;
    }

    public void setPrevProcessGcTime(long prevProcessGcTime) {
        this.prevProcessGcTime = prevProcessGcTime;
    }

    public long getPreThreadTime() {
        return preThreadTime;
    }

    public void setPreThreadTime(long preThreadTime) {
        this.preThreadTime = preThreadTime;
    }
}
