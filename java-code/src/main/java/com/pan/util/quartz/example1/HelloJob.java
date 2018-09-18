package com.pan.util.quartz.example1;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pan on 2018/2/5.
 */
public class HelloJob implements Job{

    private Logger logger = LoggerFactory.getLogger(HelloJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("job running");
        logger.info("job running");
    }
}
