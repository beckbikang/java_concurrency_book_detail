package java7.concurrency.chapter5;

import java7.concurrency.util.Sleeper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class RecursiveTaskTest {
    public static void main(String[] args){
        System.out.println("start run");

        int n = 10000;
        List<Integer> list = getList(n);
        TaskR taskR = new TaskR(list, 0, n);
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(taskR);

        do{
            System.out.printf("active=%d,steal=%d,paralle=%d\n",pool.getActiveThreadCount(),
                    pool.getStealCount(),pool.getParallelism());
            Sleeper.msleep(100);

        }while (!taskR.isDone());
        pool.shutdown();
        try{
            int result = taskR.get();
            System.out.println("result:"+result);
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }


    }

    public static List<Integer> getList(int n){
        List<Integer> list = new ArrayList<>();
        for (int i = 0;i < n;i++){
            list.add(i);
        }
        return list;
    }
}

class TaskR extends RecursiveTask<Integer>{

    private static final long serialVersionUID = 1l;
    private List<Integer> list;
    private int start;
    private int end;

    public TaskR(List<Integer> l, int s, int e){
        this.list =l;
        this.start = s;
        this.end = e;
    }

    @Override
    public Integer compute(){
        int result = 0;

        if(end - start < 10){
            result = doSomething(list,start,end);
        }else {
            int mid = (end+start)/2;
            TaskR t1 = new TaskR(list,start,mid+1);
            TaskR t2 = new TaskR(list,mid+1,end);
            invokeAll(t1,t2);
            try{
                result = t1.get() + t2.get();
            }catch (InterruptedException | ExecutionException e){
                e.printStackTrace();
            }
        }

        return result;
    }

    public int doSomething(List<Integer> l, int start, int end){
        int ret = 0;
        for(int i = start;i < end; i++){
            ret += l.get(i);
        }
        return ret;
    }





}
