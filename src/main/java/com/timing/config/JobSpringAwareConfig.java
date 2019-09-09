/**
 * Project Name:
 * Class Name:com.timing.config.java
 * <p>
 * Version     Date         Author
 * -----------------------------------------
 * 1.0    2019/8/20 10:10 AM      HanKeQi
 * <p>
 * Copyright (c) 2019, Timing All Rights Reserved.
 */
package com.timing.config;

import com.timing.business.ConcurrentBusiness;
import com.timing.annotation.JobHandler;
import com.timing.handle.IJobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * @author HanKeQi
 * @Description
 * @date 2019/8/20 10:10 AM
 **/
@Component
@Slf4j
public class JobSpringAwareConfig implements ApplicationContextAware {

    @Autowired
    private ConcurrentBusiness concurrentBusiness;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        try {
            log.info("init JobHandler start");
            initJobHandlerRepository(applicationContext);
            log.info("init JobHandler end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initJobHandlerRepository(ApplicationContext applicationContext) throws Exception {
        if (applicationContext == null) {
            return;
        }
        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(JobHandler.class);
        if (!CollectionUtils.isEmpty(serviceBeanMap)) {
            serviceBeanMap.forEach((key, value)->{
                if (value instanceof IJobHandler) {
                    String name = value.getClass().getAnnotation(JobHandler.class).value();
                    IJobHandler handler = (IJobHandler) value;
                    log.info("name = {}, handler = {}", name, handler);
                    concurrentBusiness.put(name, handler);
                }
            });
        }
    }


}
