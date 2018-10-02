package java7.concurrency.chapter3;

import java7.concurrency.util.Sleeper;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

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


class Meeting implements Runnable{

    private final CountDownLatch countDownLatch;
    Meeting(int n){
        countDownLatch = new CountDownLatch(n);
    }

    public void arrive(){
        System.out.println("arrived");
        countDownLatch.countDown();
    }

    public void run() {
        try {
            countDownLatch.await();
            System.out.println("all people arrived");
        }catch (InterruptedException e){

        }
    }
}

class Parter implements Runnable{
    private String name;
    private Meeting meeting;

    Parter(String name, Meeting meeting){
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