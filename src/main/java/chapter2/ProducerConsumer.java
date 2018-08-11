package chapter2;

import java.util.LinkedList;

public class ProducerConsumer {

    public static void main(String[] args){
        Food<Chocolates> foods = new Food<Chocolates>();
        Thread thread1 = new Thread(new FoodMaker(foods));
        Thread thread2 = new Thread(new FoodEater(foods));

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

class Food <T>{
    private LinkedList<T> list;
    private int max;

    public Food(){
        list = new LinkedList<T>();
        max = 10;
    }

    public synchronized void make(T e){
        while (list.size() == max){
            try {
                wait();
            }catch (InterruptedException exp){
                System.out.println("###InterruptedException ");
                exp.printStackTrace();
            }
        }
        list.add(e);
        System.out.printf("add data size=%d\n",list.size());
        notifyAll();
    }

    public synchronized void eat(){
        while (list.size() == 0){
            try {
                wait();
            }catch (InterruptedException exp){
                System.out.println("###InterruptedException ");
                exp.printStackTrace();
            }
        }
        //出队列
        list.poll();
        System.out.printf("remove data size=%d\n",list.size());
        notifyAll();
    }
}
class Chocolates{}

class FoodMaker implements Runnable{

    Food<Chocolates> food;
    FoodMaker(Food<Chocolates> food){
        this.food = food;
    }

    public void run() {
        for (int i = 0; i < 20;i++){

            food.make(new Chocolates());
        }
    }
}

class FoodEater implements Runnable{
    Food<Chocolates> food;
    FoodEater(Food<Chocolates> food){
        this.food = food;
    }

    public void run() {
        System.out.println("foodEater");
        for (int i = 0; i < 20;i++){
            food.eat();
        }
    }
}
