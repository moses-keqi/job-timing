/**
 * Project Name:
 * Class Name:com.timing.service.impl.java
 * <p>
 * Version     Date         Author
 * -----------------------------------------
 * 1.0    2019/8/20 11:58 AM      HanKeQi
 * <p>
 * Copyright (c) 2019, Timing All Rights Reserved.
 */
package com.timing.service.impl;

import com.timing.po.Scheduled;
import com.timing.repository.ScheduledRepository;
import com.timing.service.ScheduledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HanKeQi
 * @Description
 * @date 2019/8/20 11:58 AM
 **/

@Service
public class ScheduledServiceImpl implements ScheduledService {

    @Autowired
    private ScheduledRepository scheduledRepository;


    @Override
    public List<Scheduled> getByState(boolean state) throws Exception {
        return scheduledRepository.findByState(state);
    }

    @Override
    public List<Scheduled> getAll() throws Exception {
        return scheduledRepository.findAll();
    }

    @Override
    public Scheduled getById(Long id) throws Exception {
        return scheduledRepository.findById(id).get();
    }

    @Override
    public Scheduled save(Scheduled scheduled) throws Exception {
        scheduledRepository.save(scheduled);
        return scheduled;
    }

    @Override
    public void del(Long id) throws Exception {
        scheduledRepository.deleteById(id);
    }
}
