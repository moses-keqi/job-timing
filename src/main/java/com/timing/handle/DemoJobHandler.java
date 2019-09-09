/**
 * Project Name:
 * Class Name:com.timing.handle.java
 * <p>
 * Version     Date         Author
 * -----------------------------------------
 * 1.0    2019/8/20 11:04 AM      HanKeQi
 * <p>
 * Copyright (c) 2019, Timing All Rights Reserved.
 */
package com.timing.handle;

import com.timing.annotation.JobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author HanKeQi
 * @Description
 * @date 2019/8/20 11:04 AM
 **/
@JobHandler("demoJobHandler")
@Component
@Slf4j
public class DemoJobHandler extends IJobHandler {

    @Override
    public void execute(String param) throws Exception {
        log.info("test ok...");
    }
}
