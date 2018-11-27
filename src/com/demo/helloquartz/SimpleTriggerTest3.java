package com.demo.helloquartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class SimpleTriggerTest3 {

    public static void main(String[] args) {
        try {
            SchedulerFactory factory = new StdSchedulerFactory();
            Scheduler scheduler = factory.getScheduler();
            //define jobDetail
            JobDetail jobDetail = newJob(HelloJob.class)
                    .withIdentity("helloJob","grp1")
                    .build();
            //define trigger
            Trigger trigger = newTrigger()
                    .withIdentity("trigger","grp1")
                    //5秒以后开始触发，仅执行一次
                    .startAt(DateBuilder.futureDate(5, DateBuilder.IntervalUnit.SECOND))
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
