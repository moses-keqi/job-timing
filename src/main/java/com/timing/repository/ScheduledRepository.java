/**
 * Project Name:
 * Class Name:com.timing.repository.java
 * <p>
 * Version     Date         Author
 * -----------------------------------------
 * 1.0    2019/8/19 5:18 PM      HanKeQi
 * <p>
 * Copyright (c) 2019, Timing All Rights Reserved.
 */
package com.timing.repository;

import com.timing.po.Scheduled;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author HanKeQi
 * @Description
 * @date 2019/8/19 5:18 PM
 **/

public interface ScheduledRepository extends JpaRepository<Scheduled, Long> {

    List<Scheduled> findByState(boolean state);
}
