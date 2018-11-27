package com.demo.helloquartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class SimpleTriggerTest1 {

    public static void main(String[] args) {

        try {
            SchedulerFactory factory = new StdSchedulerFactory();
            Scheduler scheduler = factory.getScheduler();

            // define jobDetail
            JobDetail jobDetail = newJob(HelloJob.class)
                    .withIdentity("helloJob","group1")
                    .build();
            // define trigger
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startTime = sdf.parse("2018-11-27 16:13:30");
            Trigger trigger = newTrigger()
                    .withIdentity("trigger","group1")
                    //指定时间开始触发，不重复
                    .startAt(startTime)
                    .forJob("helloJob","group1")
                    .build();

            scheduler.scheduleJob(jobDetail,trigger);

            scheduler.start();
            Thread.sleep(1000000);
            scheduler.shutdown();

        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
