package java7.concurrency.chapter1;

import java7.concurrency.util.Sleeper;

public class GroupHandler {

    public static void main(String[] args) throws Exception{

        ThreadGroup threadGroup = new MyGroup("group");
        for (int i=0; i < 2;i++){
            Thread thread = new Thread(threadGroup,new GroupTask("thread:"+1));
            thread.start();
        }

        threadGroup.wait();
    }
}


class GroupTask implements Runnable{
    private String name;
    GroupTask(String n){
        name = n;
    }
    public void run() {
        Sleeper.sleep(1);
        int i = Integer.parseInt("a");
    }
}

class MyGroup extends ThreadGroup{

    public MyGroup(String name){
        super(name);
    }
    /**
     * 捕获运行时异常
     *
     * @param t
     * @param e
     */
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Group my UncaughtExceptionHandler");
        System.out.printf("name=%s,stat=%s,id:=%d\n",t.getName(),t.getState(), t.getId());
        e.printStackTrace(System.out);
    }
}