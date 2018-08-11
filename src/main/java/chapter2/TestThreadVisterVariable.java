package chapter2;

import util.Sleeper;

public class TestThreadVisterVariable {


    public static void main(String[] args) throws InterruptedException{
        Resource3 resource = new Resource3(10,91);

        System.out.println("start runing");

        Thread thread1 = new Thread(new Task111(resource));
        Thread thread2 = new Thread(new Task222(resource));

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println("end");
    }

}

class Resource3{

    private Object aIObj;
    private Object bIObj;
    private int aI;
    private int bI;
    Resource3(int a, int b){
        aI = a;
        bI = b;
        aIObj = new Object();
        bIObj = new Object();
    }

    public  void A(){
        synchronized(aIObj){
            System.out.println("A");
            Sleeper.sleep(3);
        }
    }
    public void B(){
        synchronized (bIObj){
            System.out.println("B");
        }
    }
}

class Task111 implements Runnable{
    Resource3 resource;
    Task111(Resource3 resource){
        this.resource = resource;
    }
    public void run() {
        resource.A();
        Sleeper.sleep(3);
    }
}

class Task222 implements Runnable{
    Resource3 resource;
    Task222(Resource3 resource){
        this.resource = resource;
    }
    public void run() {
        resource.B();
    }
}


