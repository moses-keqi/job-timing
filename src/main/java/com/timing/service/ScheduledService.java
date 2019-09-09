/**
 * Project Name:
 * Class Name:com.timing.service.java
 * <p>
 * Version     Date         Author
 * -----------------------------------------
 * 1.0    2019/8/20 11:57 AM      HanKeQi
 * <p>
 * Copyright (c) 2019, Timing All Rights Reserved.
 */
package com.timing.service;

import com.timing.po.Scheduled;

import java.util.List;

/**
 * @author HanKeQi
 * @Description
 * @date 2019/8/20 11:57 AM
 **/

public interface ScheduledService {

    List<Scheduled> getByState(boolean state) throws Exception;

    List<Scheduled> getAll() throws Exception;

    Scheduled getById(Long id) throws Exception;

    Scheduled save(Scheduled scheduled) throws Exception;

    void del(Long id) throws Exception;

}
