package com.demo.helloquartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class SimpleTriggerTest2 {

    public static void main(String[] args) {
        try {

            SchedulerFactory factory = new StdSchedulerFactory();
            Scheduler scheduler = factory.getScheduler();

            //define jobDetail
            JobDetail jobDetail = newJob(HelloJob.class)
                    .withIdentity("helloJob", "grp1")
                    .build();
            // define trigger
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startTime = sdf.parse("2018-11-27 16:27:30");
            //指定时间触发，每隔2秒执行一次，重复10次
            Trigger trigger = newTrigger()
                    .withIdentity("trigger", "grp1")
                    .startAt(startTime)
                    .withSchedule(
                            simpleSchedule()
                                    .withIntervalInSeconds(2)
                                    .withRepeatCount(10)
                    )
                    .forJob(jobDetail)
                    .build();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(jobDetail, trigger);

            scheduler.start();
            Thread.sleep(100000);
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
