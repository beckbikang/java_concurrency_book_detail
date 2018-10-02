package java7.concurrency.chapter2;

import java7.concurrency.util.Sleeper;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReadWriteLock {

    public static void main(String[] args){

        Info info = new Info();
        for (int i = 0;i < 5;i++){
            Thread thread1 = new Thread(new ReadThread(info));
            thread1.start();
        }

        Thread thread2 = new Thread(new WriteThread(info));
        thread2.start();
        Sleeper.sleep(20);

    }
}

class ReadThread implements Runnable{

    private Info info;
    ReadThread(Info info){
        this.info = info;
    }

    public void run() {
        for (int i = 0 ;i < 10;i++){
            Sleeper.sleep(1);
            System.out.printf("read age=%f, weight=%f\n",info.getAge(),info.getWeight());
        }

    }
}
class WriteThread implements Runnable{

    private Info info;
    WriteThread(Info info){
        this.info = info;
    }
    public void run() {
        for (int i = 0 ;i < 3;i++){
            Sleeper.sleep(2);
            info.setAge(Math.random()*10);
            info.setWeight(Math.random()*10);
            System.out.printf("write age=%f, weight=%f\n",info.getAge(),info.getWeight());

        }
    }
}

class Info{
    private double age;
    private double weight;

    private ReadWriteLock lock;
    public Info(){
        age =1;
        weight =10;
        lock = new ReentrantReadWriteLock();
    }

    public double getAge(){
        lock.readLock().lock();
        double val = age;
        lock.readLock().unlock();
        return val;
    }
    public double getWeight(){
        lock.readLock().lock();
        double val = weight;
        lock.readLock().unlock();
        return val;
    }

    public void setWeight(double weight){
        lock.writeLock().lock();
        this.weight = weight;
        lock.writeLock().unlock();
    }

    public void setAge(double age){
        lock.writeLock().lock();
        this.age = age;
        lock.writeLock().unlock();
    }

}


