package chapter4;

import util.Sleeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class CallFutureTest {


    public static void main(String[] args) throws Exception{
        ThreadPoolExecutor threadPoolExecutor =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        List<Future<Integer>> resultList = new ArrayList<>();

        int taskNum = 10;

        for (int i = 0; i < taskNum; i++){
            CallFutureTask callFutureTask = new CallFutureTask();
            Future<Integer> future = threadPoolExecutor.submit(callFutureTask);
            resultList.add(future);
        }
        Sleeper.sleep(3);
        do{
            System.out.printf("getCompletedTaskCount:%d\n",
                    threadPoolExecutor.getCompletedTaskCount());
            for (int i = 0;i < taskNum;i++){
                Future<Integer> future = resultList.get(i);
                System.out.printf("task:%d:%s\t",i,future.isDone());
                if(future.isDone()){
                    System.out.printf("result:%d",(Integer)future.get().intValue());
                }
                System.out.println();
            }
            Sleeper.msleep(500);

        }while (threadPoolExecutor.getCompletedTaskCount() < resultList.size());


        threadPoolExecutor.shutdown();


    }
}


class CallFutureTask implements Callable<Integer>{

    @Override
    public Integer call() {
        Random randow =new Random();
        int number = randow.nextInt();
        System.out.printf("number:%d\n",number);
        return number;
    }
}
