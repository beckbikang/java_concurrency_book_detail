package java7.concurrency.chapter3;

import java7.concurrency.util.Sleeper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class ProduceConsume {

    public static void main(String arg[]){

        List<Integer> list1 = new ArrayList<Integer>();
        List<Integer> list2 = new ArrayList<Integer>();
        Exchanger<List<Integer>> exchanger = new Exchanger<>();

        Producer producer = new Producer(list1,exchanger);
        Consumer consumer = new Consumer(list2,exchanger);

        new Thread(producer).start();
        new Thread(consumer).start();

        Sleeper.sleep(1);

    }
}

class Producer implements Runnable{

    private List<Integer> data;
    private final Exchanger<List<Integer>> exchanger;

    public Producer(List<Integer> list, Exchanger<List<Integer>> exchanger){
        this.data = list;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {

        for(int i = 0;i < 10;i++){
            data.add(i);
        }
        try {
            data = exchanger.exchange(data);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
class Consumer implements Runnable{

    private List<Integer> data;
    private final Exchanger<List<Integer>> exchanger;

    public Consumer(List<Integer> list, Exchanger<List<Integer>> exchanger){
        this.data = list;
        this.exchanger = exchanger;
    }
    @Override
    public void run() {
        try {
            data = exchanger.exchange(data);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        for (int i = 0;i < data.size();i++){
            System.out.printf("%d=%s\n",i, data.get(i));
        }

    }
}
