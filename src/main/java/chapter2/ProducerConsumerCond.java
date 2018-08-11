package chapter2;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerCond {

    public static void main(String[] args){
        Food2<Chocolates2> foods = new Food2<Chocolates2>();
        Thread thread1 = new Thread(new FoodMaker2(foods));
        Thread thread2 = new Thread(new FoodEater2(foods));

        thread1.start();
        thread2.start();


        try{
            thread1.join();
            thread2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class Food2 <T>{

    private ReentrantLock lock;
    private Condition full;
    private Condition empty;
    private LinkedList<T> list;
    private int max;

    public Food2(){
        list = new LinkedList<T>();
        max = 10;
        lock = new ReentrantLock();
        full = lock.newCondition();
        empty = lock.newCondition();
    }

    public  void make(T e){
        lock.lock();
        try {
            while (list.size() == max) {
                full.await();
            }
            list.add(e);
            System.out.printf("add data size=%d\n", list.size());
            empty.signalAll();
        }catch (InterruptedException exp){
            System.out.println("###InterruptedException ");
            exp.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void eat(){
        lock.lock();
        try {
            while (list.size() == 0) {
                empty.await();
            }
            list.poll();
            //出队列
            System.out.printf("remove data size=%d\n",list.size());
            full.signalAll();
        }catch (InterruptedException exp){
            System.out.println("###InterruptedException ");
            exp.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

class Chocolates2{}

class FoodMaker2 implements Runnable{

    Food2<Chocolates2> food;
    FoodMaker2(Food2<Chocolates2> food){
        this.food = food;
    }

    public void run() {
        for (int i = 0; i < 20;i++){

            food.make(new Chocolates2());
        }
    }
}

class FoodEater2 implements Runnable{
    Food2<Chocolates2> food;
    FoodEater2(Food2<Chocolates2> food){
        this.food = food;
    }

    public void run() {
        System.out.println("foodEater");
        for (int i = 0; i < 20;i++){
            food.eat();
        }
    }
}
