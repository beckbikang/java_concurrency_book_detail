package chapter1;

import util.Sleeper;

import java.util.concurrent.ThreadFactory;

public class ThreadFactoryTest {

    public static void main(String[] args){

        Factory factory = new Factory();
        for(int i= 0; i < 10;i++){
            Thread t = factory.newThread(new FactoryTask());
            t.start();
        }

        Sleeper.sleep(3);
        System.out.println("program over");

    }
}

class FactoryTask implements Runnable{
    public void run() {
        System.out.println(Thread.currentThread().getName()+" running");
        Sleeper.sleep(1);
    }
}
class Factory implements ThreadFactory{
    private int count;

    public int getCount(){
        return count;
    }
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, "thread"+count);
        count++;
        return thread;
    }
}
