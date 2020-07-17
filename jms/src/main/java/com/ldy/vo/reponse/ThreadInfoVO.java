package com.ldy.vo.reponse;
/**
 * ThreadInfo
 * java.lang.management.ThreadInfo
 * @ClassName: com.ldy.vo.reponse.ThreadInfoVO.java
 * @author: songxulin
 * @date :  2020-06-15 19:29
 * @version V1.0
 */
public class ThreadInfoVO {
    /**
     * 线程名称
     */
    private String       threadName;
    /**
     * 线程id
     */
    private long         threadId;
    /**
     * 测试与此 ThreadInfo相关联的线程是否通过Java Native Interface（JNI）执行本机代码。
     */
    private boolean      inNative;
    /**
     * ThreadInfo相关联的线程是否暂停
     */
    private boolean      suspended;
    /**
     * 线程状态
     */
    private Thread.State threadState;

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    public boolean isInNative() {
        return inNative;
    }

    public void setInNative(boolean inNative) {
        this.inNative = inNative;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public Thread.State getThreadState() {
        return threadState;
    }

    public void setThreadState(Thread.State threadState) {
        this.threadState = threadState;
    }
}
