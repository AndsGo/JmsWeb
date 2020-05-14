package com.ldy.core.util;

import com.ldy.enums.CollectorEnum;
import com.ldy.enums.PoolEnum;

import javax.management.MBeanServerConnection;
import java.io.IOException;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.CompilationMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Jmx api service
 */
public class JmxMetricsService {

    /***以下为具体的MXBean实例***/
    private ClassLoadingMXBean classLoading;
    private OperatingSystemMXBean systemMXBean;
    private RuntimeMXBean runtimeMXBean;
    private ThreadMXBean threadMXBean;
    private CompilationMXBean compilation;
    /**
     * 内存信息
     */
    private MemoryMXBean memory;
    /**
     * 内存区域
     */
    private Map<String, MemoryPoolMXBean> pools = new HashMap<>();

    /**
     * 垃圾收集器
     */
    private Map<String, GarbageCollectorMXBean> collectors = new HashMap<>();
    private boolean isOperatingSystemImpl;

    public JmxMetricsService(MBeanServerConnection mBeanServerConnection) throws IOException {
        //获取内存
        memory = ManagementFactory.newPlatformMXBeanProxy
                (mBeanServerConnection, ManagementFactory.MEMORY_MXBEAN_NAME, MemoryMXBean.class);
        //获取编译信息
        compilation = ManagementFactory.newPlatformMXBeanProxy
                (mBeanServerConnection, ManagementFactory.COMPILATION_MXBEAN_NAME, CompilationMXBean.class);
        //获取系统信息
        systemMXBean = ManagementFactory.newPlatformMXBeanProxy(
                mBeanServerConnection,
                ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME,
                OperatingSystemMXBean.class
        );
        isOperatingSystemImpl = "sun.management.OperatingSystemImpl".equals(systemMXBean.getClass().getName());
        //获取运行时信息
        runtimeMXBean = ManagementFactory.newPlatformMXBeanProxy(
                mBeanServerConnection,
                ManagementFactory.RUNTIME_MXBEAN_NAME,
                RuntimeMXBean.class
        );
        //获取线程信息
        threadMXBean = ManagementFactory.newPlatformMXBeanProxy(
                mBeanServerConnection,
                ManagementFactory.THREAD_MXBEAN_NAME,
                ThreadMXBean.class
        );
        //获取类加载信息
        classLoading = ManagementFactory.newPlatformMXBeanProxy(
                mBeanServerConnection,
                ManagementFactory.CLASS_LOADING_MXBEAN_NAME,
                ClassLoadingMXBean.class
        );
        List<MemoryPoolMXBean> memoryPool = ManagementFactory.getPlatformMXBeans(mBeanServerConnection, MemoryPoolMXBean.class);
        for (MemoryPoolMXBean pool : memoryPool) {
            pools.put(pool.getName(), pool);
        }
        List<GarbageCollectorMXBean> garbageCollectors = ManagementFactory.getPlatformMXBeans(mBeanServerConnection, GarbageCollectorMXBean.class);
        for (GarbageCollectorMXBean garbageCollector : garbageCollectors) {
            collectors.put(garbageCollector.getName(), garbageCollector);
        }
    }

    /**
     * 单位转换
     */
    public static final double KB = 1024D;
    public static final double MB = 1024D * KB;
    public static final double GB = 1024D * MB;

    public static double kb(double b) {
        return b / KB;
    }

    public static double mb(double b) {
        return b / MB;
    }

    public static double gb(double b) {
        return b / GB;
    }

    /**
     * 类加载信息
     */

    /**
     * 已加载类总数
     */
    public long getTotalLoadedClassCount() {
        return classLoading.getTotalLoadedClassCount();
    }

    /**
     * 已加载当前类
     */
    public int getLoadedClassCount() {
        return classLoading.getLoadedClassCount();
    }

    /**
     * 已卸载类总数
     */
    public long getUnloadedClassCount() {
        return classLoading.getUnloadedClassCount();
    }


    /**
     * 编译信息
     */
    /**
     * JIT编译器名称
     */
    public String getCompilationName() {
        return compilation.getName();
    }

    /**
     * 判断jvm是否支持编译时间的监控
     */
    public boolean isCompilationTimeMonitoringSupported() {
        return compilation.isCompilationTimeMonitoringSupported();
    }

    /**
     * 总编译时间
     */
    public long getTotalCompilationTime() {
        if (!isCompilationTimeMonitoringSupported()) {
            return -1L;
        }
        return compilation.getTotalCompilationTime();
    }


    /**
     * 操作系统信息
     */
    /**
     * 系统名称
     * <p>
     * 相当于 System.getProperty("os.name")
     */
    public String getSystemName() {
        return systemMXBean.getName();
    }

    /**
     * 系统版本
     * <p>
     * 相当于System.getProperty("os.version")
     */
    public String getSystemVersion() {
        return systemMXBean.getVersion();
    }

    /**
     * 操作系统的架构
     * <p>
     * 相当于System.getProperty("os.arch")
     */
    public String getSystemArch() {
        return systemMXBean.getArch();
    }

    /**
     * 可用的内核数
     * <p>
     * 相当于 Runtime.availableProcessors()
     */
    public int getSystemAvailableProcessors() {
        return systemMXBean.getAvailableProcessors();
    }

    /**
     * 获取系统负载平均值
     *
     * @since 1.6
     */
    public double getSystemLoadAverage() {
        return systemMXBean.getSystemLoadAverage();
    }

    /**
     * public native long getCommittedVirtualMemorySize();
     * <p>
     * public native long getFreeSwapSpaceSize();
     * <p>
     * public native long getFreePhysicalMemorySize();
     * <p>
     * public native long getMaxFileDescriptorCount();
     * <p>
     * public native long getOpenFileDescriptorCount();
     * <p>
     * public native long getProcessCpuTime();
     * <p>
     * public native double getProcessCpuLoad();
     * <p>
     * public native double getSystemCpuLoad();
     * <p>
     * public native long getTotalPhysicalMemorySize();
     * <p>
     * public native long getTotalSwapSpaceSize();
     */

    public boolean isOperatingSystemImpl() {
        return isOperatingSystemImpl;
    }

    private long getLong(String methodName) {
        try {
            final Method method = systemMXBean.getClass().getMethod(methodName, (Class<?>[]) null);
            method.setAccessible(true);
            return (long) method.invoke(systemMXBean, (Object[]) null);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            // Do Noting
        }
        return -1L;
    }

    private double getDouble(String methodName) {
        try {
            final Method method = systemMXBean.getClass().getMethod(methodName, (Class<?>[]) null);
            method.setAccessible(true);
            return (double) method.invoke(systemMXBean, (Object[]) null);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            // Do Noting
        }
        return -1D;
    }

    public long getCommittedVirtualMemorySize() {
        if (!isOperatingSystemImpl()) {
            return -1L;
        }
        return getLong("getCommittedVirtualMemorySize");
    }

    public long getTotalSwapSpaceSize() {
        if (!isOperatingSystemImpl()) {
            return -1L;
        }
        return getLong("getTotalSwapSpaceSize");
    }

    public long getFreeSwapSpaceSize() {
        if (!isOperatingSystemImpl()) {
            return -1L;
        }
        return getLong("getFreeSwapSpaceSize");
    }

    public long getProcessCpuTime() {
        if (!isOperatingSystemImpl()) {
            return -1L;
        }
        return getLong("getProcessCpuTime");
    }

    public long getFreePhysicalMemorySize() {
        if (!isOperatingSystemImpl()) {
            return -1L;
        }
        return getLong("getFreePhysicalMemorySize");
    }

    public long getTotalPhysicalMemorySize() {
        if (!isOperatingSystemImpl()) {
            return -1L;
        }
        return getLong("getTotalPhysicalMemorySize");
    }

    public long getOpenFileDescriptorCount() {
        if (!isOperatingSystemImpl()) {
            return -1L;
        }
        return getLong("getOpenFileDescriptorCount");
    }

    public long getMaxFileDescriptorCount() {
        if (!isOperatingSystemImpl()) {
            return -1L;
        }
        return getLong("getMaxFileDescriptorCount");
    }

    public double getSystemCpuLoad() {
        if (!isOperatingSystemImpl()) {
            return -1D;
        }
        return getDouble("getSystemCpuLoad");
    }

    public double getProcessCpuLoad() {
        if (!isOperatingSystemImpl()) {
            return -1D;
        }
        return getDouble("getProcessCpuLoad");
    }

    /**
     * 运行时信息
     */
    /**
     * pid@主机名 = vmId
     */
    public String getName() {
        return runtimeMXBean.getName();
    }

    /**
     * 进程ID
     */
    public String getPid() {
        return runtimeMXBean.getName().split("@")[0];
    }


    /**
     * 引导类路径
     */
    public String getBootClassPath() {
        return runtimeMXBean.getBootClassPath();
    }

    /**
     * 库路径
     */
    public String getLibraryPath() {
        return runtimeMXBean.getLibraryPath();
    }

    /**
     * 类路径
     */
    public String getClassPath() {
        return runtimeMXBean.getClassPath();
    }

    /**
     * jvm规范名称
     */
    public String getSpecName() {
        return runtimeMXBean.getSpecName();
    }

    /**
     * jvm规范运营商
     */
    public String getSpecVendor() {
        return runtimeMXBean.getSpecVendor();
    }

    /**
     * jvm规范版本
     * <p>
     * 1.8
     */
    public String getSpecVersion() {
        return runtimeMXBean.getSpecVersion();
    }

    /**
     * jvm名称
     * <p>
     * 相当于System.getProperty("java.vm.name")
     */
    public String getVmName() {
        return runtimeMXBean.getVmName();
    }

    /**
     * jvm运营商
     * <p>
     * 相当于System.getProperty("java.vm.vendor")
     */
    public String getVmVendor() {
        return runtimeMXBean.getVmVendor();
    }

    /**
     * jvm实现版本
     * <p>
     * 相当于System.getProperty("java.vm.version")
     * <p>
     * 25.131-b11
     */
    public String getVmVersion() {
        return runtimeMXBean.getVmVersion();
    }

    public String getManagementSpecVersion() {
        return runtimeMXBean.getManagementSpecVersion();
    }

    /**
     * jvm启动时间（毫秒）
     */
    public long getStartTime() {
        return runtimeMXBean.getStartTime();
    }

    /**
     * jvm正常运行时间（毫秒）
     */
    public long getUptime() {
        return runtimeMXBean.getUptime();
    }

    /**
     * 获取系统属性
     */
    public Map<String, String> getSystemProperties() {
        return runtimeMXBean.getSystemProperties();
    }

    /**
     * JVM 启动参数
     */
    public List<String> getInputArguments() {
        return runtimeMXBean.getInputArguments();
    }


    /**
     * 线程
     */

    /**
     * 返回当前活动线程数，包括守护和非守护线程
     */
    public int getThreadCount() {
        return threadMXBean.getThreadCount();
    }

    /**
     * 峰值活动线程数
     */
    public int getPeakThreadCount() {
        return threadMXBean.getPeakThreadCount();
    }

    /**
     * JVM 启动以来创建的线程总数
     */
    public long getTotalStartedThreadCount() {
        return threadMXBean.getTotalStartedThreadCount();
    }

    /**
     * 守护线程数
     */
    public long getDaemonThreadCount() {
        return threadMXBean.getDaemonThreadCount();
    }

    /**
     * 获取所有的线程信息
     */
    public Map<Long, ThreadInfo> getAllThreadInfo() {
        Map<Long, ThreadInfo> allThreadInfo = new HashMap<>();
        long[] threadIds = threadMXBean.getAllThreadIds();
        for (long threadId : threadIds) {
            ThreadInfo threadInfo = threadMXBean.getThreadInfo(threadId);
            allThreadInfo.put(threadId, threadInfo);
        }
        return allThreadInfo;
    }

    /**
     * 所有的死锁线程
     */
    public Map<Long, ThreadInfo> getAllDeadlockedThreadInfo() {
        Map<Long, ThreadInfo> allDeadlockedThreadInfo = new HashMap<>();
        long[] threadIds = threadMXBean.findDeadlockedThreads();
        for (long threadId : threadIds) {
            ThreadInfo threadInfo = threadMXBean.getThreadInfo(threadId);
            allDeadlockedThreadInfo.put(threadId, threadInfo);
        }
        return allDeadlockedThreadInfo;
    }


    /**
     * MemoryManager
     */
    /**
     * 堆内内存使用量
     * <pre>
     *        +----------------------------------------------+
     *        +////////////////           |                  +
     *        +////////////////           |                  +
     *        +----------------------------------------------+
     *
     *        |--------|
     *           init
     *        |---------------|
     *               used
     *        |---------------------------|
     *                  committed
     *        |----------------------------------------------|
     *                            max
     * </pre>
     */
    public MemoryUsage getHeapMemoryUsage() {
        return memory.getHeapMemoryUsage();
    }

    /**
     * 堆外内存使用量
     * <pre>
     *        +----------------------------------------------+
     *        +////////////////           |                  +
     *        +////////////////           |                  +
     *        +----------------------------------------------+
     *
     *        |--------|
     *           init
     *        |---------------|
     *               used
     *        |---------------------------|
     *                  committed
     *        |----------------------------------------------|
     *                            max
     * </pre>
     */
    public MemoryUsage getNonHeapMemoryUsage() {
        return memory.getNonHeapMemoryUsage();
    }

    /**
     * 当前 JVM 的内存区域名称
     */
    public Set<String> getPoolNames() {
        return pools.keySet();
    }

    /**
     * 对应的内存区域是否存在
     */
    public boolean existMemoryPool(PoolEnum name) {
        return pools.containsKey(name.getPoolName());
    }

    public MemoryUsage getPoolUsage(PoolEnum name) {
        if (!existMemoryPool(name)) {
            return nullUsage(null);
        }
        return nullUsage(pools.get(name.getPoolName()).getUsage());
    }

    public MemoryType getPoolType(PoolEnum name) {
        if (!existMemoryPool(name)) {
            return null;
        }
        return pools.get(name.getPoolName()).getType();
    }

    /**
     * 返回 JVM 最近在此内存池中回收未使用的对象 所花费的内存使用量
     */
    public MemoryUsage getPoolCollectionUsage(PoolEnum name) {
        if (!existMemoryPool(name)) {
            return nullUsage(null);
        }
        return nullUsage(pools.get(name.getPoolName()).getCollectionUsage());
    }

    /**
     * 峰值内存使用量
     */
    public MemoryUsage getPoolPeakUsage(PoolEnum name) {
        if (!existMemoryPool(name)) {
            return nullUsage(null);
        }
        return nullUsage(pools.get(name.getPoolName()).getPeakUsage());
    }


    /**
     * 以字节为单位返回此内存池的使用阈值
     */
    public long getPoolUsageThreshold(PoolEnum name) {
        if (!existMemoryPool(name)) {
            return -1;
        }
        return pools.get(name.getPoolName()).getUsageThreshold();
    }

    public long getPoolUsageThresholdCount(PoolEnum name) {
        if (!existMemoryPool(name)) {
            return -1;
        }
        return pools.get(name.getPoolName()).getUsageThresholdCount();
    }

    public long getPoolCollectionUsageThreshold(PoolEnum name) {
        if (!existMemoryPool(name)) {
            return -1;
        }
        return pools.get(name.getPoolName()).getCollectionUsageThreshold();
    }

    public long getPoolCollectionUsageThresholdCount(PoolEnum name) {
        if (!existMemoryPool(name)) {
            return -1;
        }
        return pools.get(name.getPoolName()).getCollectionUsageThresholdCount();
    }

    private MemoryUsage nullUsage(MemoryUsage usage) {
        return null == usage ? new MemoryUsage(-1L, 0L, 0L, -1L) : usage;
    }

    /**
     * 当前 JVM 的垃圾收集器
     */
    public Set<String> getCollectorNames() {
        return collectors.keySet();
    }

    /**
     * 是否存在对应的垃圾收集器
     */
    public boolean existCollector(CollectorEnum collector) {
        return collectors.containsKey(collector.getCollectorName());
    }

    /**
     * 垃圾收集器次数
     */
    public long getGarbageCollectionCount(CollectorEnum collector) {
        if (!existCollector(collector)) {
            return -1;
        }
        return collectors.get(collector.getCollectorName()).getCollectionCount();
    }

    /**
     * 垃圾回收期总耗时
     */
    public long getGarbageCollectionTime(CollectorEnum collector) {
        if (!existCollector(collector)) {
            return -1;
        }
        return collectors.get(collector.getCollectorName()).getCollectionTime();
    }

    /**
     * 垃圾回收器 可回收的区域名称
     */
    public List<String> getGarbageCollectionMemoryPoolNames(CollectorEnum collector) {
        if (!existCollector(collector)) {
            return new ArrayList<>();
        }
        String[] memoryPoolNames = collectors.get(collector.getCollectorName()).getMemoryPoolNames();
        return new ArrayList<>(Arrays.asList(memoryPoolNames));
    }


}