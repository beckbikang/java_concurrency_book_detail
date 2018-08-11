package chapter1;

public class ExceptHandler {

    public static void main(String[] args){

        Thread thread = new Thread(new MyRunner());
        //注册异常处理器
        thread.setUncaughtExceptionHandler(new ExcpetRealHandler());
        thread.start();
    }
}

class MyRunner implements Runnable{
    public void run() {
        int a = Integer.parseInt("ab");
    }
}



class ExcpetRealHandler implements Thread.UncaughtExceptionHandler{

    /**
     * 捕获运行时异常
     *
     * @param t
     * @param e
     */
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("my UncaughtExceptionHandler");
        System.out.printf("name=%s,stat=%s,id:=%d\n",t.getName(),t.getState(), t.getId());
        e.printStackTrace(System.out);
    }
}
