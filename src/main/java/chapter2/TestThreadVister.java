package chapter2;

import util.Sleeper;

public class TestThreadVister {


    public static void main(String[] args) throws InterruptedException{
        Resource resource = new Resource();

        System.out.println("start runing");

        Thread thread1 = new Thread(new Task1(resource));
        Thread thread2 = new Thread(new Task2(resource));

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println("end");
    }

}

class Resource{
    public synchronized void A(){
        System.out.println("A");
        Sleeper.sleep(3);
    }
    public synchronized void B(){
        System.out.println("B");
    }
}

class Task1 implements Runnable{
    Resource resource;
    Task1(Resource resource){
        this.resource = resource;
    }
    public void run() {
        resource.A();
    }
}

class Task2 implements Runnable{
    Resource resource;
    Task2(Resource resource){
        this.resource = resource;
    }
    public void run() {

        resource.B();
    }
}


