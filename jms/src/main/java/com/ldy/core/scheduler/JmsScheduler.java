package com.ldy.core.scheduler;

import com.ldy.core.thread.JmsConnectorLosedMonitorHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Jmx后台任务类
 * @author songxulin
 */

public class JmsScheduler {
    private static final Logger logger = LoggerFactory.getLogger(JmsScheduler.class);


    public void init() throws Exception {
        JmsConnectorLosedMonitorHelper.getInstance().start();
    }

    
    public void destroy() throws Exception {
        JmsConnectorLosedMonitorHelper.getInstance().toStop();
    }


    // ---------------------- jmx-client ----------------------
//    private static ConcurrentMap<String, ExecutorBiz> executorBizRepository = new ConcurrentHashMap<String, ExecutorBiz>();
//    public static ExecutorBiz getExecutorBiz(String address) throws Exception {
//        // valid
//        if (address==null || address.trim().length()==0) {
//            return null;
//        }
//
//        // load-cache
//        address = address.trim();
//        ExecutorBiz executorBiz = executorBizRepository.get(address);
//        if (executorBiz != null) {
//            return executorBiz;
//        }
//
//        // set-cache
//        executorBiz = new ExecutorBizClient(address, XxlJobAdminConfig.getAdminConfig().getAccessToken());
//
//        executorBizRepository.put(address, executorBiz);
//        return executorBiz;
//    }

}
