package chapter2;

import util.Sleeper;

public class TestThreadVisterStatic {


    public static void main(String[] args) throws InterruptedException{
        Resource2 resource = new Resource2();

        System.out.println("start runing");

        Thread thread1 = new Thread(new Task11(resource));
        Thread thread2 = new Thread(new Task22(resource));

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println("end");
    }

}

class Resource2{
    public synchronized void A(){
        System.out.println("A");
        Sleeper.sleep(3);
    }
    public static synchronized void B(){
        System.out.println("B");
    }
}

class Task11 implements Runnable{
    Resource2 resource;
    Task11(Resource2 resource){
        this.resource = resource;
    }
    public void run() {
        resource.A();
    }
}

class Task22 implements Runnable{
    Resource2 resource;
    Task22(Resource2 resource){
        this.resource = resource;
    }
    public void run() {

        Resource2.B();
    }
}


