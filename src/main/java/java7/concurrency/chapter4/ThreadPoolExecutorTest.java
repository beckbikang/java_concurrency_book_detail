package java7.concurrency.chapter4;

import java7.concurrency.util.Sleeper;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolExecutorTest {

    public static void main(String[] args){
        Server server = new Server();
        for (int i =0; i< 100;i++){
            server.executeTask(new TaskPollTest());
        }
        Sleeper.sleep(3);
        server.stop();
    }
}

class Server{
    private ThreadPoolExecutor executor;
    Server(){
        //executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);

    }
    public void executeTask(TaskPollTest taskPollTest){
        executor.execute(taskPollTest);
        System.out.print("PoolSize:"+executor.getPoolSize());
        System.out.print("ActiveCount:"+executor.getActiveCount());
        System.out.print("CompletedTaskCount:"+executor.getCompletedTaskCount());
        System.out.println();
    }

    public void stop(){
        executor.shutdown();
    }
}

class TaskPollTest implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep((long)Math.random()*100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName());
    }
}
