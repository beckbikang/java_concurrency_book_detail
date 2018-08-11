package chapter1;

import util.Sleeper;

import java.util.Date;

public class ThreadLocalTest {

    public static void main(String[] args){
        Date d = new Date();
        for (int i=0;i<2;i++){
            String name = "thread:"+i;
            Thread thread = new Thread(new LocalRunner(name, d));
            thread.start();
            Sleeper.sleep(1);
        }


        Sleeper.sleep(20);
    }
}


class LocalRunner implements Runnable{
    ThreadLocal<Date> local= new ThreadLocal<Date>(){
        protected Date initalValue(){
            return new Date();
        }
    };

    private Date date;
    private String name;
    LocalRunner(String name, Date d){
        date = d;
        this.name = name;
    }

    public void run() {
        System.out.println(name+" before:"+date);
        date = new Date();
        local.set(new Date());
        Sleeper.sleep(3);
        System.out.println(name+"end:"+date);
        Date d = local.get();
        System.out.println(name+" local data:"+d);

    }
}