package java7.concurrency.chapter3;

import java7.concurrency.util.Sleeper;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    public static void main(String[] args){

        int num = 3;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(
                num,
                new LastRunner());
        MeetingCyclicBarrier meeting =
                new MeetingCyclicBarrier(cyclicBarrier);
        for(int i  =0;i < num;i++){
            Thread thread = new Thread(
                    new ParterCyclicBarrier("thread:"+i,meeting)
            );
            thread.start();
        }
        Sleeper.sleep(2);
        System.out.println("over");
    }
}


class MeetingCyclicBarrier implements Runnable{

    private final CyclicBarrier cyclicBarrier;
    MeetingCyclicBarrier(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }

    public void arrive(){
        System.out.println("arrived");
    }

    public void run() {
        try {
            cyclicBarrier.await();
            System.out.println("all people arrived");
        }catch (BrokenBarrierException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class LastRunner implements Runnable{
    public void run() {
        System.out.println("last running");
    }
}

class ParterCyclicBarrier implements Runnable{
    private String name;
    private MeetingCyclicBarrier meeting;

    ParterCyclicBarrier(String name,
                        MeetingCyclicBarrier meeting){
        this.name = name;
        this.meeting = meeting;
    }
    public void run() {

        long dura = (long) Math.random()*100;
        /*
        try{
            Thread.sleep(dura);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        */
        meeting.arrive();
    }
}