package java7.concurrency.chapter1;

import java.util.concurrent.TimeUnit;

public class ThreadInterrupt {

    public static void main(String[] args) throws Exception{
        Thread task = new Thread(){
            public void run(){
                int i = 0;
                while (true){
                    try{
                        i++;

                        System.out.println(i+" running");
                        if(isInterrupted()){
                            throw new InterruptedException("isInterrupted");
                        }else{
                            TimeUnit.SECONDS.sleep(1);
                        }
                    }catch (InterruptedException e){
                        System.out.println("interrupt over");
                        e.printStackTrace();
                        return;
                    }
                }
            }
        };

        task.start();
        TimeUnit.SECONDS.sleep(3);
        task.interrupt();
        task.join();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("main over");
    }

}
