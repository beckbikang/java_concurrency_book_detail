package java7.concurrency.chapter1;

import java7.concurrency.util.Sleeper;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;
import java.util.NoSuchElementException;

/**
 *  1 启动几个线程不停的写入数据
 *  2 启动线程不停的处理超时数据
 *
 */
public class ThreadDaemon {


    public static void main(String[] args) throws Exception{
        Deque<Event> queues  = new ArrayDeque<Event>();

        for(int j = 0; j < 3;j ++){
            Thread thread = new Thread(new PutTask("thread-%d"+j, queues));
            thread.start();
        }

        CleanTask cleanTask= new CleanTask("clean1",queues);
        cleanTask.start();

        cleanTask.join();
        System.out.println("over");
    }
}

class PutTask implements Runnable{

    private Deque<Event> queue;
    private String name;
    public PutTask(String name ,Deque<Event> q){
        this.name = name;
        queue = q;
    }
    public void run() {
        for(int i= 0;i < 100 ;i++){
            Event event = new Event();
            event.setName(name);
            event.setDate(new Date());
            queue.addFirst(event);
            System.out.printf("put %s : %s\n", Thread.currentThread().getName(), event.getDate());
            Sleeper.sleep(1);
        }
    }
}


class CleanTask extends Thread{
    private Deque<Event> queue;

    public CleanTask(String name, Deque<Event> q){
        super(name);
        queue = q;
        this.setDaemon(true);
    }

    public void run() {

        while (true){
            Date date = new Date();
            clean(date);
        }
    }

    /**
     * 定时删除过期的时间
     * @param date
     */
    public void clean(Date date){
        if(queue.isEmpty()){
            return;
        }
        long scap = 0l;
        boolean flag = false;
        do {
            if (queue.isEmpty()){
                return;
            }
            Event event;
            try {
                event = queue.getLast();
            }catch (NoSuchElementException e){
                return;
            }
            scap = date.getTime()- event.getDate().getTime();
            //System.out.printf("cap=%d\n",scap);
            if (scap > 3000) {
                queue.removeLast();
                System.out.printf("delete %s :cur=%s delete=%s\n",
                        Thread.currentThread().getName(),date, event.getDate());
                flag = true;
            }
        }while ( scap > 3000);
        if (flag){
            System.out.printf("queue_size=%d\n", queue.size());
        }
    }

}






class Event{
    private Date date;
    private String name;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}