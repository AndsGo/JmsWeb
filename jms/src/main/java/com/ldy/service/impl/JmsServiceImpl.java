package com.ldy.service.impl;

import com.ldy.common.enums.LdyErrorCode;
import com.ldy.common.exception.LdyRuntimeException;
import com.ldy.common.util.DateUtil;
import com.ldy.core.enums.PoolEnum;
import com.ldy.core.model.JmsServicePrevModel;
import com.ldy.core.model.ServiceDescriptor;
import com.ldy.core.util.JmxMetricsService;
import com.ldy.service.IJmsService;
import com.ldy.vo.reponse.Response4monitorVO;
import com.ldy.vo.reponse.Response4overviewVO;
import com.ldy.vo.reponse.Response4threadVO;
import com.ldy.vo.reponse.ThreadInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.lang.management.MemoryUsage;
import java.lang.management.ThreadInfo;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @version V1.0
 * @Description: JmsServiceImpl
 * @ClassName: com.ldy.service.impl.JmsServiceImpl.java
 * @author: songxulin
 * @date :  2020-05-25 20:12
 */
@Component
public class JmsServiceImpl implements IJmsService {
    /**
     * 获取jmx概述信息
     *
     * @author: songxulin
     * @date :  2020-05-25 20:18
     */
    @Override
    public Response4overviewVO getOverview(ServiceDescriptor descriptor) {
        if (descriptor == null) {
            throw new LdyRuntimeException(LdyErrorCode.SERVER_ERROR.getCode(), "没有获取到ServiceDescriptor");
        }
        JmxMetricsService jmxMetricsService = descriptor.getJmxMetricsService();
        Response4overviewVO overviewVO = new Response4overviewVO();
        overviewVO.setPid(jmxMetricsService.getPid());
        overviewVO.setHost(jmxMetricsService.getHost());
        overviewVO.setBootClassPath(jmxMetricsService.getBootClassPath());
        overviewVO.setVmName(jmxMetricsService.getVmName());
        //包含当前运行的应用的所有参数
        overviewVO.setSystemProperties(jmxMetricsService.getSystemProperties());
        overviewVO.setInputArguments(jmxMetricsService.getInputArguments());
        overviewVO.setVmVendor(jmxMetricsService.getVmVendor());
        overviewVO.setStartTime(jmxMetricsService.getStartTime());
        overviewVO.setUptime(jmxMetricsService.getUptime());
        return overviewVO;
    }

    /**
     * @return Response4monitorVO
     * @Description: 获取jmx监控信息
     * @MethodName: com.ldy.service.impl.JmsServiceImpl#getMonitor(com.ldy.core.model.ServiceDescriptor)
     * @return  Response4monitorVO
     * @author: songxulin
     * @date :  2020-06-02 21:17
     */
    @Override
    public Response4monitorVO getMonitor(ServiceDescriptor descriptor) {
        if (descriptor == null) {
            throw new LdyRuntimeException(LdyErrorCode.SERVER_ERROR.getCode(), "没有获取到ServiceDescriptor");
        }
        Response4monitorVO response4monitorVO = new Response4monitorVO();
        JmxMetricsService jmxMetricsService = descriptor.getJmxMetricsService();
        /***获取cpu信息***/
        //获取cpu信息
        JmsServicePrevModel jmsServicePrevModel = descriptor.getJmsServiceModel();
        long prevUpTime = jmsServicePrevModel.getPrevUpTime();
        long upTime = jmxMetricsService.getUptime();
        long prevProcessCpuTime = jmsServicePrevModel.getPrevProcessCpuTime();
        long processCpuTime = jmxMetricsService.getProcessCpuTime();
        long prevProcessGcTime = jmsServicePrevModel.getPrevProcessGcTime();
        long processGcTime = jmxMetricsService.getTotalGarbageCollectionTime();
        //记录数据
        jmsServicePrevModel.setPrevUpTime(upTime);
        jmsServicePrevModel.setPrevProcessCpuTime(processCpuTime);
        jmsServicePrevModel.setPrevProcessGcTime(processGcTime);

        long upTimeDiff = upTime - prevUpTime;
        //获取可用内核数
        int processorCount = jmxMetricsService.getSystemAvailableProcessors();
        String hmsDate = DateUtil.formatHMSDate(new Date(jmxMetricsService.getStartTime() + upTime));
        response4monitorVO.setTime(hmsDate);
        if (prevUpTime == -1 || prevProcessCpuTime == -1) {
            Response4monitorVO.Cpu cpu = new Response4monitorVO.Cpu(0.0, 0.0);
            response4monitorVO.setCpu(cpu);
        } else {
            //计算cpu使用率
            long processTimeDiff = processCpuTime - prevProcessCpuTime;
            //processTimeDiff 取到得是纳秒数  1ms = 1000000ns
            double cpuDetail = processTimeDiff * 100.0 /1000000/ processorCount / upTimeDiff;
            //计算gccpu使用率
            long processGcTimeDiff = processGcTime - prevProcessGcTime;
            double gcDetail = processGcTimeDiff * 100.0 /1000000/ processorCount / upTimeDiff;
            Response4monitorVO.Cpu cpu = new Response4monitorVO.Cpu(cpuDetail, gcDetail);
            response4monitorVO.setCpu(cpu);
        }
        /**获取内存信息**/
        //堆内存
        MemoryUsage heapMemoryUsage = jmxMetricsService.getHeapMemoryUsage();
        //元空间
        MemoryUsage metaspaceUsage = jmxMetricsService.getPoolUsage(PoolEnum.META_SPACE);
        Response4monitorVO.Memory memory = new Response4monitorVO.Memory(heapMemoryUsage, metaspaceUsage);
        response4monitorVO.setMemory(memory);
        /**获取类加信息**/
        int loadedClassCount = jmxMetricsService.getLoadedClassCount();
        long totalLoadedClassCount = jmxMetricsService.getTotalLoadedClassCount();
        long unloadedClassCount = jmxMetricsService.getUnloadedClassCount();
        Response4monitorVO.Classes classes = new Response4monitorVO.Classes(loadedClassCount, totalLoadedClassCount, unloadedClassCount);
        response4monitorVO.setClasses(classes);
        /**获取线程信息**/
        int threadCount = jmxMetricsService.getThreadCount();
        long totalStartedThreadCount = jmxMetricsService.getTotalStartedThreadCount();
        long daemonThreadCount = jmxMetricsService.getDaemonThreadCount();
        int peakThreadCount = jmxMetricsService.getPeakThreadCount();
        Response4monitorVO.Thread tread = new Response4monitorVO.Thread(threadCount, totalStartedThreadCount, daemonThreadCount, peakThreadCount);
        response4monitorVO.setThread(tread);
        return response4monitorVO;
    }

    @Override
    public Response4threadVO getThreadAll(ServiceDescriptor descriptor) {
        Response4threadVO response4threadVO = new Response4threadVO();
        JmsServicePrevModel jmsServicePrevModel = descriptor.getJmsServiceModel();
        long preThreadTime = jmsServicePrevModel.getPreThreadTime();
        if(preThreadTime==-1){
            jmsServicePrevModel.setPreThreadTime(System.currentTimeMillis());
        }
        JmxMetricsService jmxMetricsService = descriptor.getJmxMetricsService();
        //获取线程数量信息
        int threadCount = jmxMetricsService.getThreadCount();
        long totalStartedThreadCount = jmxMetricsService.getTotalStartedThreadCount();
        long daemonThreadCount = jmxMetricsService.getDaemonThreadCount();
        int peakThreadCount = jmxMetricsService.getPeakThreadCount();
        Response4threadVO.ThreadCountVO threadCountVo = new Response4threadVO.ThreadCountVO(threadCount, totalStartedThreadCount, daemonThreadCount, peakThreadCount);
        response4threadVO.setThreadCountVO(threadCountVo);
        //获取所有线程信息
        Map<Long, ThreadInfo> allThreadInfoMap = jmxMetricsService.getAllThreadInfo();
        Map<Long, ThreadInfoVO> map =new HashMap<>(allThreadInfoMap.size()*2);
        allThreadInfoMap.forEach((k,v)->{
            ThreadInfoVO infoVO = new ThreadInfoVO();
            BeanUtils.copyProperties(v,infoVO);
            map.put(k,infoVO);
        });
        response4threadVO.setThreadInfoVOMap(map);
        return response4threadVO;
    }
}
