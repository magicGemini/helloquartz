package com.demo.helloquartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.DateBuilder.dateOf;
import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class SimpleTriggerTest5 {

    public static void main(String[] args) {
        try {
            SchedulerFactory factory = new StdSchedulerFactory();
            Scheduler scheduler = factory.getScheduler();
            //define jobDetail
            JobDetail jobDetail = newJob(HelloJob.class)
                    .withIdentity("helloJob", "grp1")
                    .build();
            //define trigger
            Trigger trigger = newTrigger()
                    .withIdentity("trigger", "grp1")
                    //在下一分钟的整点触发，然后每2秒重复一次
                    .startAt(evenMinuteDate(null))
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(2)
                            .repeatForever()
                    )
                    .endAt(dateOf(17,03,0))
                    .forJob(jobDetail)
                    .build();

            //bind scheduler
            scheduler.scheduleJob(jobDetail, trigger);

            scheduler.start();
            Thread.sleep(100000);
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
