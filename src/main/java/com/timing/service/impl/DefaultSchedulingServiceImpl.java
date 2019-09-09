/**
 * Project Name:
 * Class Name:com.timing.service.impl.java
 * <p>
 * Version     Date         Author
 * -----------------------------------------
 * 1.0    2019/8/20 2:10 PM      HanKeQi
 * <p>
 * Copyright (c) 2019, Timing All Rights Reserved.
 */
package com.timing.service.impl;

import com.timing.business.ConcurrentBusiness;
import com.timing.config.DefaultSchedulingConfigurer;
import com.timing.handle.IJobHandler;
import com.timing.po.Scheduled;
import com.timing.po.enums.ExecuteType;
import com.timing.service.DefaultSchedulingService;
import com.timing.utils.CronHelper;
import com.timing.utils.ReturnT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author HanKeQi
 * @Description
 * @date 2019/8/20 2:10 PM
 **/
@Service
public class DefaultSchedulingServiceImpl implements DefaultSchedulingService {

    @Autowired
    private ConcurrentBusiness concurrentBusiness;

    @Autowired
    private DefaultSchedulingConfigurer defaultSchedulingConfigurer;


    @Override
    public ReturnT add(Scheduled scheduled) throws Exception {
        String taskId = String.valueOf(scheduled.getId());
        ExecuteType executeType = scheduled.getExecuteType();
        if (ExecuteType.FIXED_RATE == executeType){
            defaultSchedulingConfigurer.addTriggerTask(taskId, triggerTask(scheduled), Long.valueOf(scheduled.getExecute()));
        }else {
            defaultSchedulingConfigurer.addTriggerTask(taskId, triggerTask(scheduled));
        }
        return ReturnT.SUCCESS;
    }

    @Override
    public void cancelTask(Scheduled scheduled) throws Exception {
        defaultSchedulingConfigurer.cancelTriggerTask(String.valueOf(scheduled.getId()));
    }

    @Override
    public boolean exist(Scheduled scheduled) throws Exception {
        return defaultSchedulingConfigurer.hasTask(String.valueOf(scheduled.getId()));
    }

    private TriggerTask triggerTask(Scheduled scheduled) throws Exception {
        ExecuteType executeType = scheduled.getExecuteType();

        String execute = scheduled.getExecute();
        execute = ExecuteType.CRON == executeType ? execute :
                ExecuteType.DATE_DEF == executeType ?  CronHelper.scheduledCron(scheduled.getExecute()) : null;

        CronTrigger cronTrigger = StringUtils.isEmpty(execute)? null : new CronTrigger(execute);
        Trigger trigger = cronTrigger != null ?  cronTrigger :  triggerContext -> null;
        return new TriggerTask(() -> {
            IJobHandler jobHandler = (IJobHandler) concurrentBusiness.get(scheduled.getHandler());
            try {
                jobHandler.execute(scheduled.getParam());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, trigger);
    }

}
