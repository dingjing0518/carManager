package com.jinshi.util;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {

    /**
     * 客户端向服务端发请求
     * Details：配置定时任务
     */
    @Bean(name = "jobDetail")
    public MethodInvokingJobDetailFactoryBean jobDetail(QuartzUtil quartzUtil) {// QuartzUtil为需要执行的任务
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        /*
         *  是否并发执行
         *  例如每5s执行一次任务，但是当前任务还没有执行完，就已经过了5s了，
         *  如果此处为true，则下一个任务会执行，如果此处为false，则下一个任务会等待上一个任务执行完后，再开始执行
         */
        jobDetail.setConcurrent(true);
        jobDetail.setName("success");// 设置任务的名字
        jobDetail.setGroup("camera");// 设置任务的分组，这些属性都可以存储在数据库中，在多任务的时候使用
        //为需要执行的实体类对应的对象
        jobDetail.setTargetObject(quartzUtil);
        //通过这几个配置，告诉JobDetailFactoryBean我们需要执行定时执行ScheduleTask类中的sayHello方法
        jobDetail.setTargetMethod("time");
        return jobDetail;
    }

    /**
     * attention:
     * Details：配置定时任务的触发器，也就是什么时候触发执行定时任务
     */
    @Bean(name = "jobTrigger")
    public CronTriggerFactoryBean jobTrigger(JobDetail jobDetail) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(jobDetail);
        trigger.setCronExpression("*/3 * * * * ?");// 初始时的cron表达式
        trigger.setStartDelay(5000*60);
        return trigger;
    }

    // 配置定时任务2 --- 更新服务端时间
    @Bean(name = "jobDetailSecond")
    public MethodInvokingJobDetailFactoryBean jobDetailSecond(ThreadUtil threadUtil) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(true);
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(threadUtil);
        // 需要执行的方法
        jobDetail.setTargetMethod("timeTwo");
        return jobDetail;
    }

    // 配置触发器2
    @Bean(name = "jobTriggerSecond")
    public CronTriggerFactoryBean jobTriggerSecond(JobDetail jobDetailSecond) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(jobDetailSecond);
        trigger.setCronExpression("*/10 * * * * ?");
        trigger.setStartDelay(1000*60);
        return trigger;
    }

    // 配置定时任务3--- ping 摄像机
    @Bean(name = "jobDetailThird")
    public MethodInvokingJobDetailFactoryBean jobDetailThird(PingUtil pingUtil) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(true);
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(pingUtil);
        // 需要执行的方法
        jobDetail.setTargetMethod("timeThree");
        return jobDetail;
    }

    // 配置触发器3
    @Bean(name = "jobTriggerThird")
    public CronTriggerFactoryBean jobTriggerThird(JobDetail jobDetailThird) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(jobDetailThird);
        trigger.setCronExpression("*/30 * * * * ?");
        trigger.setStartDelay(1000*60);
        return trigger;
    }

    // 配置定时任务4 -----修改今天是否为节假日属性
    @Bean(name = "jobDetailFourth")
    public MethodInvokingJobDetailFactoryBean jobDetailFourth(HolidayUtil holidayUtil) throws Exception{
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(true);
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(holidayUtil);
        // 需要执行的方法
        jobDetail.setTargetMethod("timeFour");
        return jobDetail;
    }

    // 配置触发器4
    @Bean(name = "jobTriggerFourth")
    public CronTriggerFactoryBean jobTriggerFourth(JobDetail jobDetailFourth) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(jobDetailFourth);
        trigger.setCronExpression("0 30 0 * * ?");//0 0 0 */1 * ?
        trigger.setStartDelay(3000*60);
        return trigger;
    }

    // 配置定时任务5----检测是否需要手动抬落杆
    @Bean(name = "jobDetailFive")
    public MethodInvokingJobDetailFactoryBean jobDetailFive(GateUtil gateUtil) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(true);
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(gateUtil);
        // 需要执行的方法
        jobDetail.setTargetMethod("timeFive");
        return jobDetail;
    }

    // 配置触发器5
    @Bean(name = "jobTriggerFive")
    public CronTriggerFactoryBean jobTriggerFive(JobDetail jobDetailFive) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(jobDetailFive);
        trigger.setCronExpression("*/3 * * * * ?");
        return trigger;
    }

    // 配置定时任务6----车队模式轮询
    @Bean(name = "jobDetailSix")
    public MethodInvokingJobDetailFactoryBean jobDetailSix(CarTeamUtil carTeamUtil) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(true);
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(carTeamUtil);
        // 需要执行的方法
        jobDetail.setTargetMethod("timeSix");
        return jobDetail;
    }

    // 配置触发器6
    @Bean(name = "jobTriggerSix")
    public CronTriggerFactoryBean jobTriggerSix(JobDetail jobDetailSix) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(jobDetailSix);
        trigger.setCronExpression("*/3 * * * * ?");
        trigger.setStartDelay(1000*60);
        return trigger;
    }

    /**
     * attention:
     * Details：定义quartz调度工厂
     */
    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactory(Trigger jobTrigger,Trigger jobTriggerSecond,
                                                 Trigger jobTriggerThird,Trigger jobTriggerFourth,
                                                 Trigger jobTriggerFive,Trigger jobTriggerSix) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        // 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
        bean.setOverwriteExistingJobs(true);
        // 延时启动，应用启动1秒后
        bean.setStartupDelay(1);
        // 注册触发器
        bean.setTriggers(jobTrigger,jobTriggerSecond,jobTriggerThird,jobTriggerFourth,jobTriggerFive,jobTriggerSix);
        return bean;
    }

    //多任务时的Scheduler，动态设置Trigger。一个SchedulerFactoryBean可能会有多个Trigger
    @Bean(name = "multitaskScheduler")
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        return schedulerFactoryBean;
    }

    @Bean("QianYiCameraUtil")
    public QianYiCameraUtil qianYiCameraUtil() {
        return new QianYiCameraUtil("");
    }
}
