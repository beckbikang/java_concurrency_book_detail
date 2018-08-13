package chapter3;

import util.Sleeper;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    public static void main(String[] args){
        Meeting meeting = new Meeting(10);

        Thread thread1 = new Thread(meeting);
        thread1.start();
        for(int i  =0;i < 10;i++){
            Thread thread = new Thread(
                    new Parter("thread:"+i,meeting)
            );
            thread.start();
        }
        Sleeper.sleep(5);
    }
}


class MeetingCyclicBarrier implements Runnable{

    CyclicBarrier cyclicBarrier;
    MeetingCyclicBarrier(int n){
        cyclicBarrier = new CyclicBarrier(n);
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

class ParterCyclicBarrier implements Runnable{
    private String name;
    private Meeting meeting;

    ParterCyclicBarrier(String name, Meeting meeting){
        this.name = name;
        this.meeting = meeting;
    }
    public void run() {

        long dura = (long) Math.random()*100;
        try{
            Thread.sleep(dura);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        meeting.arrive();
    }
}