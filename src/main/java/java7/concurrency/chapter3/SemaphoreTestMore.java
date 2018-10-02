package java7.concurrency.chapter3;

import java7.concurrency.util.Sleeper;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreTestMore {

    public static void main(String[] args){

        PrintQueueMore printQueue = new PrintQueueMore();
        for (int i=0; i < 10; i++){
            Thread thread = new Thread(new TaskMore((printQueue)));
            thread.start();
        }
        Sleeper.sleep(5);
    }

}

class TaskMore implements Runnable{
    PrintQueueMore printQueue;

    TaskMore(PrintQueueMore printQueue){
        this.printQueue = printQueue;
    }

    public void run() {
        printQueue.PrintData();
    }
}

class PrintQueueMore{
    private final Semaphore semaphore;
    private boolean freeRes[];
    private Lock lock;

    public PrintQueueMore(){
        semaphore= new Semaphore(3);
        freeRes = new boolean[3];
        for (int i = 0; i < 3;i++){
            freeRes[i] = true;
        }
        lock = new ReentrantLock();
    }


    public void PrintData(){

        try {
            semaphore.acquire();
            Thread thread = Thread.currentThread();
            int ret = getPrintRet();
            System.out.printf("Printer %d ",ret);
            System.out.printf("name=%s ",thread.getName());
            System.out.printf("state=%s\n",thread.getState());
            TimeUnit.SECONDS.sleep(1);
            if(ret > 0){
                freeRes[ret] = true;
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }

    public int getPrintRet(){
        int ret = -1;
        try{
            lock.lock();

            for(int i=0; i< freeRes.length; i++){
                if(freeRes[i]){
                    ret = i;
                    freeRes[i] = false;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return ret;
    }
}

