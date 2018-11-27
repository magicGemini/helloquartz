package com.demo.helloquartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import java.util.ArrayList;

public class DumbJob2 implements Job {

    private String hello;
    private float floatValue;
    private ArrayList dates;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey key = jobExecutionContext.getJobDetail().getKey();
        System.err.println("instance["+key+"] - hello: "+hello+", floatValue: "+floatValue);
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public void setFloatValue(float floatValue) {
        this.floatValue = floatValue;
    }

    public void setDates(ArrayList dates) {
        this.dates = dates;
    }
}
