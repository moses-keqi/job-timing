/**
 * Project Name:
 * Class Name:com.timing.service.java
 * <p>
 * Version     Date         Author
 * -----------------------------------------
 * 1.0    2019/8/20 2:10 PM      HanKeQi
 * <p>
 * Copyright (c) 2019, Timing All Rights Reserved.
 */
package com.timing.service;

import com.timing.po.Scheduled;
import com.timing.utils.ReturnT;

/**
 * @author HanKeQi
 * @Description
 * @date 2019/8/20 2:10 PM
 **/

public interface DefaultSchedulingService {

    ReturnT add(Scheduled scheduled) throws Exception;


    void cancelTask(Scheduled scheduled) throws Exception;

    boolean exist(Scheduled scheduled) throws Exception;

}
