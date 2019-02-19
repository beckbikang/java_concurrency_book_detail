package Java.improve.part2.code.single;

import java.util.concurrent.CountDownLatch;

public class SingleTest {

    public static void main(String[] args){
        testSingle();
    }

    //测试单例
    public static void testSingle(){


        int num = 6;
        CountDownLatch countDownLatch = new CountDownLatch(num);

        TestThread testThread = new TestThread(countDownLatch);
        ThreadGroup group = new ThreadGroup("tt");
        long startTime = System.currentTimeMillis();
        for(int i=0;i < num;i++){
           new Thread(group,testThread).start();
        }
        try{
            countDownLatch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        long lastTime = System.currentTimeMillis() - startTime;
        System.out.println("lastTime:"+lastTime);
    }

}

class TestThread implements Runnable{

    private CountDownLatch downLatch;

    public TestThread(CountDownLatch downLatch){
        this.downLatch = downLatch;
    }

    @Override
    public void run() {
        for (int i =0;i <100000;i++){
            //Singleton1.getSingleton1();
            //Singleton2.getSingleton2();
            //Singleton3.getSingleton3();
        }
        this.downLatch.countDown();
    }
}

//序列化破坏单例
//简单的单例模式
class Singleton1 implements java.io.Serializable {
    private Singleton1(){}
    private static Singleton1 singleton1 = new Singleton1();
    public static Singleton1 getSingleton1(){
        return singleton1;
    }
    private Object readResolve(){
        return singleton1;
    }
}

//延迟加载
class Singleton2{
    private Singleton2(){}
    private static Singleton2 singleton2 = null;
    public static synchronized Singleton2 getSingleton2(){
        if(singleton2 == null){
            singleton2 = new Singleton2();
        }
        return singleton2;
    }
}

//延迟加载的改进


//内部类实现延时加载
class Singleton3{
    private Singleton3(){}
    private static class Singleton3Hold{
        private static Singleton3 singleton3 = new Singleton3();
    }

    public static Singleton3 getSingleton3(){
        return Singleton3Hold.singleton3;
    }
}



