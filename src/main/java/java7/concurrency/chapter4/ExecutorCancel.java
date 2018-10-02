package java7.concurrency.chapter4;

import java7.concurrency.util.Sleeper;

import java.util.concurrent.*;

public class ExecutorCancel {
    public static void main(String[] args){

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        //其他的
        int number = 6;
        MyFuture myFuture[] = new MyFuture[number];
        for (int i = 0; i< number; i++){
            CancelTask cancelTask = new CancelTask();
            myFuture[i] = new MyFuture(cancelTask);
            executor.submit(cancelTask);
        }
        for (int i = 0; i< number; i++){
            myFuture[i].cancel(true);
        }
        for (int i = 0; i< number; i++){
            try{
                if(myFuture[i].isCancelled()){
                    System.out.printf("is canceled\n");
                }else {
                    System.out.printf("result:%s\n",(String)myFuture[i].get());
                }
            }catch (ExecutionException e){
                e.printStackTrace();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        executor.shutdown();

    }

    public static void t1(){
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        CancelTask task = new CancelTask();
        Future<String> future = executor.submit(task);

        Sleeper.sleep(1);
        System.out.printf("start run");
        future.cancel(true);
    }


}

class MyFuture extends FutureTask<String>{

    MyFuture(Callable<String> callable){
        super(callable);
    }

    @Override
    public void done() {
        if(isCancelled()){
            System.out.println("canceled");
        }else{
            System.out.println("done");
        }
    }
}

class CancelTask implements Callable<String>{
    @Override
    public String call() {
        Sleeper.sleep(2);
        return "abc";
    }
}
