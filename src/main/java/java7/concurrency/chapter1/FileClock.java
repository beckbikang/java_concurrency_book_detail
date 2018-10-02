package java7.concurrency.chapter1;


import java7.concurrency.util.Sleeper;

import java.util.Date;

public class FileClock{


    public static void main(String[] args){
        RunnerClock runnerClock = new RunnerClock();
        Thread thread = new Thread(runnerClock);
        thread.start();
        Sleeper.sleep(5);
        thread.interrupt();
    }


}

class RunnerClock implements Runnable{

    public void run() {
        for (int i=0; i< 10;i++){
            System.out.println(new Date());
            Sleeper.sleep(1);
        }
    }

}
