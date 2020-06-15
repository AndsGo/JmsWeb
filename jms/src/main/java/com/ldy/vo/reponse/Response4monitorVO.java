package com.ldy.vo.reponse;


import java.lang.management.MemoryUsage;

/**
 * @version V1.0
 * @Description: 监控页面VO
 * @ClassName: com.ldy.vo.reponse.Response4OverviewVO.java
 * @author: songxulin
 * @date :  2020-05-25 20:03
 */
public class Response4monitorVO {
    /**
     * 检测节点时间 HH:mm:ss
     */
    private String time;

    private Cpu cpu;

    private Memory memory;

    private Classes classes;

    private Thread thread;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public Cpu getCpu() {
        return cpu;
    }

    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public static class Cpu {
        /**
         * cpu使用率
         */
        private Double cpuUse;
        /**
         * gc使用率
         */
        private Double gcUse;

        public Cpu() {
            super();
        }

        public Cpu(double cpuUse, double gcUse) {
            this.cpuUse = cpuUse;
            this.gcUse = gcUse;
        }

        public Double getCpuUse() {
            return cpuUse;
        }

        public void setCpuUse(Double cpuUse) {
            this.cpuUse = cpuUse;
        }

        public Double getGcUse() {
            return gcUse;
        }

        public void setGcUse(Double gcUse) {
            this.gcUse = gcUse;
        }
    }

    public static class Memory {
        public Memory() {
            super();
        }

        public Memory(MemoryUsage heapMemoryUsage, MemoryUsage metaspaceUsage) {
            this.heapMemoryUsage = heapMemoryUsage;
            this.metaspaceUsage = metaspaceUsage;
        }

        /**
         * 堆内存使用
         */
        private MemoryUsage heapMemoryUsage;
        /**
         * 元空间
         */
        private MemoryUsage metaspaceUsage;

        public MemoryUsage getHeapMemoryUsage() {
            return heapMemoryUsage;
        }

        public void setHeapMemoryUsage(MemoryUsage heapMemoryUsage) {
            this.heapMemoryUsage = heapMemoryUsage;
        }

        public MemoryUsage getMetaspaceUsage() {
            return metaspaceUsage;
        }

        public void setMetaspaceUsage(MemoryUsage metaspaceUsage) {
            this.metaspaceUsage = metaspaceUsage;
        }
    }


    public static class Classes {
        public Classes() {
            super();
        }

        public Classes(int loadedClassCount, long totalLoadedClassCount, long unloadedClassCount) {
            this.loadedClassCount = loadedClassCount;
            this.totalLoadedClassCount = totalLoadedClassCount;
            this.unloadedClassCount = unloadedClassCount;
        }

        /**
         * 当前类加载数量
         */
        private int loadedClassCount;
        /**
         * 总加载数量
         */
        private long totalLoadedClassCount;
        /**
         * 未加载类数量
         */
        private long unloadedClassCount;

        public int getLoadedClassCount() {
            return loadedClassCount;
        }

        public void setLoadedClassCount(int loadedClassCount) {
            this.loadedClassCount = loadedClassCount;
        }

        public long getTotalLoadedClassCount() {
            return totalLoadedClassCount;
        }

        public void setTotalLoadedClassCount(long totalLoadedClassCount) {
            this.totalLoadedClassCount = totalLoadedClassCount;
        }

        public long getUnloadedClassCount() {
            return unloadedClassCount;
        }

        public void setUnloadedClassCount(long unloadedClassCount) {
            this.unloadedClassCount = unloadedClassCount;
        }
    }

    public static class Thread {
        public Thread() {
            super();
        }

        public Thread(int threadCount, long totalStartedThreadCount, long daemonThreadCount, int peakThreadCount) {
            this.threadCount = threadCount;
            this.totalStartedThreadCount = totalStartedThreadCount;
            this.daemonThreadCount = daemonThreadCount;
            this.peakThreadCount = peakThreadCount;
        }
        /**
         * 当前活动线程数
         */
        private int threadCount;
        /**
         * 已启动的总数
         */
        private long totalStartedThreadCount;
        /**
         * 守护线程
         */
        private long daemonThreadCount;
        /**
         * 实时峰值
         */
        private int peakThreadCount;

        public int getThreadCount() {
            return threadCount;
        }

        public void setThreadCount(int threadCount) {
            this.threadCount = threadCount;
        }

        public long getTotalStartedThreadCount() {
            return totalStartedThreadCount;
        }

        public void setTotalStartedThreadCount(long totalStartedThreadCount) {
            this.totalStartedThreadCount = totalStartedThreadCount;
        }

        public long getDaemonThreadCount() {
            return daemonThreadCount;
        }

        public void setDaemonThreadCount(long daemonThreadCount) {
            this.daemonThreadCount = daemonThreadCount;
        }

        public int getPeakThreadCount() {
            return peakThreadCount;
        }

        public void setPeakThreadCount(int peakThreadCount) {
            this.peakThreadCount = peakThreadCount;
        }
    }
}
