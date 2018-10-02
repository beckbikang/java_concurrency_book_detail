package java7.concurrency.chapter1;

import java7.concurrency.util.Sleeper;

public class ThreadGroupTest {

    public static void main(String[] args){

        ThreadGroup threadGroup = new ThreadGroup("group1");
        for(int i= 0;i < 5;i++){
            String name   = "thread:"+i;
            Thread thread = new Thread(threadGroup, new Task(name));
            thread.start();
        }
        threadGroup.list();

        Sleeper.sleep(2);
        threadGroup.interrupt();

        Sleeper.sleep(6);
    }
}


class Task implements Runnable {

    private String name;
    Task(String name){
        this.name = name;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName());
        Sleeper.sleep(5);
        if (Thread.interrupted()){

        }
    }
}