package chapter4;

import util.Sleeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

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
            System.out.printf("getCompletedTaskCount:%d ",
                    threadPoolExecutor.getCompletedTaskCount());
            for (int i = 0;i < taskNum;i++){
                Future<Integer> future = resultList.get(i);
                System.out.printf("task:%d :%s\t",i,future.isDone());
                System.out.println();
            }
            Sleeper.msleep(10);

        }while (threadPoolExecutor.getCompletedTaskCount() < resultList.size());

        for (Future f: resultList){
            int ret = 0;
            try {

                Integer integer = (Integer)f.get();
                ret = integer.intValue();
            }catch (ExecutionException e){
                e.printStackTrace();
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.printf("vale=%d\n", ret);
        }

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
