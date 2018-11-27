package com.demo.helloquartz;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DumbJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();

        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();

//        String jobSays = dataMap.getString("jobSays");
//        float fv = dataMap.getFloatValue("floatValue");
//        System.err.println("Instance["+ jobKey + "] - jobSays: "+jobSays+", floatValue: " + fv);

        ArrayList<Date> state = (ArrayList) dataMap.get("dates");
        state.add(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Date date : state){
            System.err.println("time: " + sdf.format(date));
        }
    }
}
