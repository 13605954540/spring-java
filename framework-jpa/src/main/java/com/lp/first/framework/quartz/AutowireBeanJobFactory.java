package com.lp.first.framework.quartz;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.util.Assert;

public class AutowireBeanJobFactory extends SpringBeanJobFactory {
    private final AutowireCapableBeanFactory beanFactory;

    AutowireBeanJobFactory(AutowireCapableBeanFactory beanFactory) {
        Assert.notNull(beanFactory, "Bean factory must not be null");
        this.beanFactory = beanFactory;
    }

    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);
        this.beanFactory.autowireBean(jobInstance);
        this.beanFactory.initializeBean(jobInstance, bundle.getJobDetail().getKey().toString());
        return jobInstance;
    }
}
