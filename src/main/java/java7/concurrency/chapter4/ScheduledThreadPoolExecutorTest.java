package java7.concurrency.chapter4;

import java7.concurrency.util.Sleeper;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorTest {

    public static void main(String[] args){

        //定时任务
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor =
                (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(2);
        for (int i = 0 ;i < 4;i ++){
            scheduledThreadPoolExecutor.schedule(new ScheduledThreadPoolExecutorTask(),
                    i+1, TimeUnit.SECONDS);
        }

        //设置执行状态
        Sleeper.sleep(2);
        scheduledThreadPoolExecutor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
        scheduledThreadPoolExecutor.shutdown();
        try{
            scheduledThreadPoolExecutor.awaitTermination(1,TimeUnit.SECONDS);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        //周期性的任务


    }
}

class ScheduledThreadPoolExecutorTask implements Callable<String>{

    @Override
    public String call() {
        Date date = new Date();
        System.out.printf("current Time%s\n",date);
        return "";
    }
}
