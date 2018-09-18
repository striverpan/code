package com.pan.util.quartz.example1;

import org.apache.tools.ant.taskdefs.Sleep;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by pan on 2018/2/5.
 */
public class Test1 {

    public void run() {
        Job job = new HelloJob();
        JobDetail jobDetail = newJob(HelloJob.class).withIdentity("hello", "group1").build();

        Date runTime = evenMinuteDate(new Date());
        Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();
        try {
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.scheduleJob(jobDetail,trigger);
            scheduler.start();
            Thread.sleep(10000);
            scheduler.shutdown(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Test1().run();
    }
}
