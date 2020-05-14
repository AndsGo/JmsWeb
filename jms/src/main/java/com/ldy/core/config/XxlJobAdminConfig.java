package com.ldy.core.config;

import com.ldy.core.scheduler.JmsScheduler;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * jms config
 *
 * @author songxulin
 */

@Component
public class XxlJobAdminConfig implements InitializingBean, DisposableBean {

    private static XxlJobAdminConfig adminConfig = null;
    public static XxlJobAdminConfig getAdminConfig() {
        return adminConfig;
    }


    // ---------------------- XxlJobScheduler ----------------------

    private JmsScheduler jmsScheduler;

    @Override
    public void afterPropertiesSet() throws Exception {
        adminConfig = this;

        jmsScheduler = new JmsScheduler();
        jmsScheduler.init();
    }

    @Override
    public void destroy() throws Exception {
        jmsScheduler.destroy();
    }


    // ---------------------- XxlJobScheduler ----------------------

    // conf
    @Value("${mybatis.type-aliases-package}")
    private String i18n;

}
