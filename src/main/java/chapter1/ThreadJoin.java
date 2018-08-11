package chapter1;

import util.Sleeper;

public class ThreadJoin {
    public static void main(String[] args){
        Thread thread = new Thread(new ThreadJoinTest());
        Thread thread2 = new Thread(new ThreadJoinTest());
        thread.start();
        thread2.start();
        try {

            thread.join(1000);
            thread2.join(1000);
            /*
            thread.join();
            thread2.join();
            */
        }catch (InterruptedException e){
            System.out.printf("InterruptedException");
        }
        System.out.println("over");
    }
}

class ThreadJoinTest implements Runnable{

    public void run() {
        for (int i=0; i< 3;i++){
            System.out.printf("i=%d\n",i);
            Sleeper.sleep(2);
        }
    }
}
