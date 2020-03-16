package com.zhangnx.quartz;

import org.quartz.Job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //打印当前的执行时间，格式为：2017-09-06 00:00:00
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前的时间为(HelloJob)：" + sf.format(date));
        //编写具体的业务逻辑
        System.out.println("孟蝶Hello Job!");
    }
}
