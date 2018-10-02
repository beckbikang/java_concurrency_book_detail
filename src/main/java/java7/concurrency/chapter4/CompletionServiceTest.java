package java7.concurrency.chapter4;

import java7.concurrency.util.Sleeper;

import java.util.Date;
import java.util.concurrent.*;

public class CompletionServiceTest {
    public static void main(String[] args) throws Exception{
        ThreadPoolExecutor threadPoolExecutor =
                (ThreadPoolExecutor) Executors.newCachedThreadPool();


        threadPoolExecutor.setRejectedExecutionHandler(new Handle());
        CompletionService<String> cs = new ExecutorCompletionService<>(threadPoolExecutor);
        Meta meta = new Meta();
        CSend cSend = new CSend(cs);
        Thread thread = new Thread(cSend);

        CSend cSend1 = new CSend(cs);
        Thread thread2 = new Thread(cSend1);

        Cprocess cprocess = new Cprocess(cs);
        Thread thread3 = new Thread(cprocess);
        thread.start();
        thread2.start();
        thread3.start();

        try {
            thread.join();
            thread2.join();
            thread3.join();

        }catch (InterruptedException e){
            e.printStackTrace();
        }

        try{
            threadPoolExecutor.awaitTermination(1, TimeUnit.SECONDS);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        cprocess.setEnd(true);

        Sleeper.sleep(2);
        threadPoolExecutor.shutdown();



    }
}

class Handle implements RejectedExecutionHandler{
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("xxx");
    }
}

class Meta implements Callable<String>{
    @Override
    public String call() {
        Sleeper.msleep(100);
        Date d = new Date();
        System.out.println(d);
        return d.toString();
    }
}

class CSend implements Runnable{
    CompletionService<String> cs;
    public     CSend(CompletionService<String> cs){
        this.cs = cs;
    }

    @Override
    public void run() {
        Meta meta = new Meta();
        cs.submit(meta);
    }
}

class Cprocess implements Runnable{

    boolean end;
    CompletionService<String> cs;
    public      Cprocess(CompletionService<String> cs){
        this.cs = cs;
        end = false;
    }

    public void setEnd(boolean end){
        this.end = end;
    }

    @Override
    public void run() {
        while (!end){
            try {
                Future<String> future = cs.poll(20, TimeUnit.SECONDS);
                if(future != null){
                    String ret = future.get();
                    System.out.println("ret:"+ret);
                }
            }catch (InterruptedException | ExecutionException e){
                e.printStackTrace();
            }
        }
        System.out.println("over");
    }

}
