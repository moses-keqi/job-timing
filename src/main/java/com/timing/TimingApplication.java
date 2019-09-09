package com.timing;

import com.timing.po.Scheduled;
import com.timing.service.DefaultSchedulingService;
import com.timing.service.ScheduledService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

/**
 * @author HanKeQi
 * @Description
 * @date 2019/8/19 3:38 PM
 **/
@SpringBootApplication
@EnableAsync
@Slf4j
@EnableScheduling
public class TimingApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TimingApplication.class);
    }

    @Autowired
    private DefaultSchedulingService defaultSchedulingService;

    @Autowired
    private ScheduledService scheduledService;

    //启动时初始化定时任务
    @Override
    public void run(String... args) throws Exception {
        List<Scheduled> list = scheduledService.getByState(true);
        list.forEach((scheduled -> {
            try {
                defaultSchedulingService.add(scheduled);
            } catch (Exception e) {
                log.info("init  error scheduled  id = {}, handler = {}", scheduled.getId(), scheduled.getHandler());
                e.printStackTrace();
            }
        }));
    }

}
