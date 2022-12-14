package com.lp.last.framework.quartz;

import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class DefaultQuartzConfig implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    @Bean
    public SchedulerFactoryBeanCustomizer schedulerFactoryBeanCustomizer() {
        return (SchedulerFactoryBean schedulerFactoryBean) -> {
            schedulerFactoryBean.setJobFactory(new AutowireBeanJobFactory(applicationContext.getAutowireCapableBeanFactory()));
        };
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
