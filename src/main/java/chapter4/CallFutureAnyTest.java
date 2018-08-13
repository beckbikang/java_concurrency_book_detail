package chapter4;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallFutureAnyTest {


    public static void main(String[] args) throws Exception{
        ThreadPoolExecutor threadPoolExecutor =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        int taskNum = 10;

        List<CallFutureTask> list = new ArrayList<>();


        for (int i = 0; i < taskNum; i++){
            CallFutureTask callFutureTask = new CallFutureTask();
            list.add(callFutureTask);
        }

        try {

            Integer ret = threadPoolExecutor.invokeAny(list);
            System.out.printf("ret=%d\n",ret);
        }catch (ExecutionException e){
            e.printStackTrace();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        threadPoolExecutor.shutdown();


    }
}

