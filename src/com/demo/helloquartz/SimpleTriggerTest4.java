package com.demo.helloquartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.DateBuilder.dateOf;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class SimpleTriggerTest4 {

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
                    //立即触发，每隔2秒执行一次，直到22:00
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(2)
                            .repeatForever()
                    )
                    .endAt(dateOf(16,59,0))
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
