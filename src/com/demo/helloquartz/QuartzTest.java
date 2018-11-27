package com.demo.helloquartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.ArrayList;
import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;


public class QuartzTest {

    public static void main(String[] args) {

        try {

            SchedulerFactory factory = new StdSchedulerFactory();
            Scheduler scheduler = factory.getScheduler();
            scheduler.start();

            ArrayList dates = new ArrayList();
            dates.add(new Date());
            Thread.sleep(1000);
            dates.add(new Date());
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("dates",dates);

            // define the job and tie it to our HelloJob class
            JobDetail job = newJob(DumbJob2.class)
                    .withIdentity("job1", "group1")
                    .usingJobData("hello","Hello world")
                    .usingJobData("floatValue",3.1415f)
                    .usingJobData(jobDataMap)
                    .build();
            // Trigger the job to run now
            Trigger trigger = newTrigger()
                    .withIdentity("myTrigger", "group1")
                    .startNow()
                    .withSchedule(
                            simpleSchedule()
                                    .withIntervalInSeconds(5)
                                    .repeatForever()
                    )
                    .build();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);

            Thread.sleep(10000);
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
