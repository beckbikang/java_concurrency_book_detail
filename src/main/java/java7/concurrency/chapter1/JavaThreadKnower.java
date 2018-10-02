package java7.concurrency.chapter1;

import java.util.concurrent.TimeUnit;

/**
 * 本章最重要的是
 *  初步认识实现线程的两种方式
 *  Jconsole和jstack工具
 *  了解线程五个状态的转换
 *      new,blocking,runnable,running,TERMINATED
 *
 *
 */
public class JavaThreadKnower {

    public static void main(String[] args){
        System.out.println("start");
        //tRun();
        tReadMusic();
    }

    public static void tReadMusic(){

        Thread task  = new Thread(){
            public void run(){
                listenMusic();
            }
        };
        task.start();
        browseNews();
    }

    public static void listenMusic(){
        for (;;){
            System.out.println("listenning music");
            System.out.println(Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getId());
            System.out.println(Thread.currentThread().getState());
            System.out.println(Thread.currentThread().getPriority());
            System.out.println("#####");

            sleep(1);
        }
    }
    public static void browseNews(){
        for (;;){
            System.out.println("reading news");

            System.out.println(Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getId());
            System.out.println(Thread.currentThread().getState());
            System.out.println(Thread.currentThread().getPriority());
            System.out.println("#####");
            sleep(1);
        }
    }

    public static void sleep(int second){
            try {
                TimeUnit.SECONDS.sleep(second);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

    }



    public static void tRun(){
        final TikcerWindowRunnable task = new TikcerWindowRunnable();

        Thread thread1 = new Thread(task, "number 1");
        Thread thread2 = new Thread(task, "number 2");
        Thread thread3 = new Thread(task, "number 3");
        Thread thread4 = new Thread(task, "number 4");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }
}



//银行
class TikcerWindowRunnable implements Runnable{
    private int index = 1;
    private final static int MAX = 100;


    public void run(){
        while (index <= MAX){
            System.out.println(Thread.currentThread()+" index:"+index);
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }

}

//音乐



