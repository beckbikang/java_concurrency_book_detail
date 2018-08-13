package chapter4;

import util.Sleeper;

import java.util.Date;
import java.util.concurrent.*;

public class ScheduledThreadPoolExecutorTest2 {

    public static void main(String[] args){

        //定时任务

        SimpleTask task = new SimpleTask();
        ScheduledThreadPoolExecutor executor =
                (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);

        ScheduledFuture<?> list =
                executor.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);

        //周期性的任务
        executor.shutdown();

    }
}

class SimpleTask implements Runnable{

    @Override
    public void run() {
        Date date = new Date();
        System.out.printf("current Time%s\n",date);
    }
}
