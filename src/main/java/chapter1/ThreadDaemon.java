package chapter1;

import util.Sleeper;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;

/**
 *  1 不停的写入数据
 *  2 启动线程不停的清楚不需要的数据
 *
 */
public class ThreadDaemon {


    public static void main(String[] args) throws Exception{
        Deque<Event> queues  = new ArrayDeque<Event>();



        for(int j = 0; j < 3;j ++){
            Task task = new Task(String.format("thread-%d",j), queues);
            task.start();
        }
        for (int i = 0; i < 10;i++){
            Date date = new Date();
            Event event = new Event();
            event.setDate(date);
            event.setName("number"+i);
            queues.push(event);
            Sleeper.sleep(1);
            System.out.println("put event "+date);
        }
        System.out.println("over");
        Sleeper.sleep(5);
    }

}


class Task extends Thread{
    private Deque<Event> queue;

    public Task(String name, Deque<Event> q){
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

    //clear data
    public void clean(Date date){
        if(queue.isEmpty()){
            return;
        }
        long scap = 0l;
        do {
            Event event = queue.getFirst();
            scap = event.getDate().getTime() - date.getTime();
            System.out.println("first" + event.getDate());

            System.out.println(queue.getLast().getDate());
            System.out.println(scap);
            if (scap > 3000) {
                System.out.printf("%s : %s\n", Thread.currentThread().getName(), event.getDate());
                queue.removeFirst();
            } else {
                //System.out.println("end");
                break;
            }
        }while ( scap > 3000);
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