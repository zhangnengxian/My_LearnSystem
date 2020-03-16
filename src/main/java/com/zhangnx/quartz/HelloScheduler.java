package com.zhangnx.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class HelloScheduler {

    public static void main(String[] args) throws SchedulerException {
        /**
         * JobDetail：用来绑定Job，并且在job执行的时候携带一些执行的信息
         */
        //创建一个JobDetail实例，将该实例与HelloJob Class绑定
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("myJo55b","gr555oup1").build();

        /**
         * Trigger：用来触发job去执行的，包括定义了什么时候去执行，
         * 第一次执行，是否会一直重复地执行下去，执行几次等
         */
        //创建一个Trigger实例，定义该job立即执行，并且每隔2秒钟重复执行一次，直到程序停止
        /**
         * trigger通过builder模式来创建，TriggerBuilder.newTrigger()
         * withIdentity():定义一个标识符，定义了组
         * startNow()：定义现在开始执行，
         * withSchedule(SimpleScheduleBuilder.simpleSchedule()：withSchedule也是builder模式创建
         *.withIntervalInSeconds(2).repeatForever())：定义了执行频度：每2秒钟执行一次，不间断地重复执行
         * build()：创建trigger
         */
        Trigger trigger = TriggerBuilder.newTrigger()
                                                    .withIdentity("myTrigger","group1").startNow()
                                                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                                                    .withIntervalInSeconds(1).repeatForever()).build();

        //创建scheduler实例：
        /**
         * scheduler区别于trigger和jobDetail，是通过factory模式创建的
         */
        //创建一个ScheduleFactory
        SchedulerFactory sfact = new StdSchedulerFactory();
        Scheduler scheduler = sfact.getScheduler();
        scheduler.start();
        
        //需要将jobDetail和trigger传进去，并将jobDetail和trigger绑定在一起。
        scheduler.scheduleJob(jobDetail,trigger);

    }

}
