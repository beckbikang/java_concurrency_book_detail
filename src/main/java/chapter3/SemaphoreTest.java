package chapter3;

import util.Sleeper;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static void main(String[] args){

        PrintQueue printQueue = new PrintQueue();
        for (int i=0; i < 10; i++){
            Thread thread = new Thread(new Task((printQueue)));
            thread.start();
        }
        Sleeper.sleep(5);
    }

}

class Task implements Runnable{
    PrintQueue printQueue;

    Task(PrintQueue printQueue){
        this.printQueue = printQueue;
    }

    public void run() {
        printQueue.PrintData();
    }
}

class PrintQueue{
    private final Semaphore semaphore;

    public PrintQueue(){
        semaphore= new Semaphore(1);
    }

    public void PrintData(){

        try {

            semaphore.acquire();
            Thread thread = Thread.currentThread();

            System.out.printf("name=%s ",thread.getName());
            System.out.printf("state=%s\n",thread.getState());

        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            semaphore.release();
        }

    }
}

