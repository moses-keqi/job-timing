/**
 * Project Name:
 * Class Name:com.timing.service.java
 * <p>
 * Version     Date         Author
 * -----------------------------------------
 * 1.0    2019/8/20 2:43 PM      HanKeQi
 * <p>
 * Copyright (c) 2019, Timing All Rights Reserved.
 */
package com.timing.service;

import com.timing.BaseTest;
import com.timing.po.Scheduled;
import com.timing.po.enums.ExecuteType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author HanKeQi
 * @Description
 * @date 2019/8/20 2:43 PM
 **/
@Slf4j
public class DefaultSchedulingServiceTest extends BaseTest {

    @Autowired
    private DefaultSchedulingService defaultSchedulingService;

    @Test
    public void fixedRate(){
        Scheduled scheduled = new Scheduled();
        scheduled.setExecuteType(ExecuteType.FIXED_RATE);
        scheduled.setExecute("5000");
        scheduled.setId(99999l);
        scheduled.setHandler("test");
        try {
            defaultSchedulingService.add(scheduled);
            Thread.sleep(1000 * 60);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void DATE_DEF(){
        Scheduled scheduled = new Scheduled();
        scheduled.setExecuteType(ExecuteType.DATE_DEF);
        scheduled.setExecute("2019-08-20 15:14:10");
        scheduled.setId(99999l);
        scheduled.setHandler("test");
        try {
            defaultSchedulingService.add(scheduled);
            Thread.sleep(1000 * 60);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void cron(){
        Scheduled scheduled = new Scheduled();
        scheduled.setExecuteType(ExecuteType.CRON);
        scheduled.setExecute("0/5 * * * * ?");
        scheduled.setId(99999l);
        scheduled.setHandler("test");
        try {
            defaultSchedulingService.add(scheduled);
            Thread.sleep(1000 * 60);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
