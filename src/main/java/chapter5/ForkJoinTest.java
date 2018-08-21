package chapter5;

import util.Sleeper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinTest {

    public static void main(String[] args){

        List<Ceil> ceils = getList(100);
        Task task = new Task(ceils, 0, ceils.size(), 20);
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(task);
        do{
            System.out.printf("active=%d,steal=%d,paralle=%d\n",pool.getActiveThreadCount(),
                    pool.getStealCount(),pool.getParallelism());
            Sleeper.msleep(5);

        }while (!task.isDone());
        pool.shutdown();
        for (int i= 0; i< ceils.size(); i++){
            System.out.println("A:"+ceils.get(i).getA());
        }
        System.out.printf("status:%s",task.isCompletedNormally());



    }

    public static List<Ceil> getList(int i){
        List<Ceil> ceils = new ArrayList<>();
        for (int j = 0; j<i;j++){
            Ceil ceil = new Ceil();
            ceils.add(ceil);
        }
        return ceils;
    }
}

class Ceil{
    private int a;

    public int getA(){
        return a;
    }
    Ceil(){
        a = 0;
    }
    public void add(int c){
        a += c;
    }
}

class Task extends RecursiveAction{
    private static final long serialVersionUID = 131l;
    int start,end;
    List<Ceil> ceils;
    int addNum;
    public Task(List<Ceil> c,int s,int e, int i){
        ceils = c;
        start = s;
        end = e;
        addNum = i;
    }

    @Override
    public void compute(){

        if(end - start < 10){
            doSomething();
        }else {
            int mid = (end+start)/2;
            //System.out.println("getQueuedTaskCount="+getQueuedTaskCount());
            Task t1 = new Task(ceils,start,mid+1, addNum);
            Task t2 = new Task(ceils,mid+1,end, addNum);
            invokeAll(t1,t2);
        }

    }

    public void doSomething(){
        for(int i=start;i < end; i++){
            System.out.println(i+",");
            Ceil ceil = ceils.get(i);
            ceil.add(addNum);
        }
    }
}