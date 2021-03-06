package java7.concurrency.chapter2;

import java7.concurrency.util.Sleeper;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest{
    public static void main(String[] args){
        tlock();
        //tlockMore();
        //tLockLock();
    }

    /**
     * 可重入锁
     */
    public static void tLockLock(){
        Lock lock = new ReentrantLock();
        for(int i=0;i < 10 ;i++){
            lock.lock();
        }
        for(int i=0;i < 10 ;i++){
            lock.unlock();
        }
    }
    public static void tlock(){

        LockData lockData = new LockData();
        for (int i  = 0 ; i< 10;i++){
            String name = "thread:"+i;
            Thread thread = new Thread(new Task(name, lockData));
            thread.start();
        }
        Sleeper.sleep(30);
    }
    public static void tlockMore(){
        LockData2 lockData = new LockData2();
        for (int i  = 0 ; i< 10;i++){
            String name = "thread:"+i;
            Thread thread = new Thread(new Task121(name, lockData));
            thread.start();
        }
        Sleeper.sleep(10);
    }
}


class Task121 implements Runnable{
    private String name;
    private LockData2 lockData;
    Task121(String name, LockData2 lockData){
        this.name = name;
        this.lockData = lockData;
    }
    public void run() {
        lockData.printData();
    }
}

class LockData2{
    private final Lock lock = new ReentrantLock();

    public void printData() {

        boolean flag = false;
        while (!flag){
             flag = lock.tryLock();
             if(!flag){
                 continue;
             }
            try {
                System.out.printf("%s-%s\n", Thread.currentThread().getName(), Thread.currentThread().getState());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            break;
        }


    }
}

class LockData{
    private final Lock lock = new ReentrantLock(true);

    public void printData() {
        lock.lock();
        try {
            Long timeNum = (long)(Math.random()*10000);
            Thread.sleep(timeNum);
            System.out.printf("%s-%s-%d\n", Thread.currentThread().getName(), Thread.currentThread().getState(),
                    timeNum/1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        lock.lock();
        try {

            Long timeNum = (long)(Math.random()*10000);
            Thread.sleep(timeNum);
            System.out.printf("%s-%s-%d\n", Thread.currentThread().getName(),
                    Thread.currentThread().getState(),timeNum/1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
class Task implements Runnable{
    private String name;
    private LockData lockData;
    Task(String name, LockData lockData){
        this.name = name;
        this.lockData = lockData;
    }
    public void run() {
        lockData.printData();
    }
}