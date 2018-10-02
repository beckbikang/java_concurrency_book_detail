package java7.concurrency.chapter4;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class CallFutureAllTest {


    public static void main(String[] args) throws Exception{
        ThreadPoolExecutor threadPoolExecutor =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        int taskNum = 10;

        List<CallFutureTask> list = new ArrayList<>();


        for (int i = 0; i < taskNum; i++){
            CallFutureTask callFutureTask = new CallFutureTask();
            list.add(callFutureTask);
        }

        List<Future<Integer>> futureList = null;
        try {
            futureList = threadPoolExecutor.invokeAll(list);
        } catch(InterruptedException e){
            e.printStackTrace();
        }

        for (Future f: futureList){
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

