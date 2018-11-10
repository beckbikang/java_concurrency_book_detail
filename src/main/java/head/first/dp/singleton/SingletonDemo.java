package head.first.dp.singleton;

public class SingletonDemo {

    public static void main(String[] args){
        Singleton6 singleton6 = Singleton6.INSTANCE;
        Singleton6 singleton61 = Singleton6.INSTANCE;
        System.out.println(singleton6+" "+singleton61);

        Resource resource = Singleton6.INSTANCE.getResource();
        Resource resource2 = Singleton6.INSTANCE.getResource();
        System.out.println(resource+" "+resource2);
    }
}



//懒汉式
class SingleObj2{
    private static SingleObj2 singleObj2 = null;
    private SingleObj2(){

    }
    public static SingleObj2 getSingleObj1(){
        if(singleObj2 == null){
            singleObj2 = new SingleObj2();
        }
        return singleObj2;
    }
}

//懒汉式，线程安全
class SingleObj3{
    private static SingleObj3 singleObj3 = null;
    private SingleObj3(){

    }
    public synchronized static SingleObj3 getSingleObj1(){
        if(singleObj3 == null){
            singleObj3 = new SingleObj3();
        }
        return singleObj3;
    }
}

//基本的方式, 饿汉式
class SingleObj1{

    private static SingleObj1 singleObj1 = new SingleObj1();
    private SingleObj1(){
    }
    public static SingleObj1 getSingleObj1(){
        return singleObj1;
    }

    public void showData(){
        System.out.println("show singleObj1");
    }
}

//双检锁/双重校验锁（DCL，即 double-checked locking）
class SingleObj4{
    private static SingleObj4 singleObj4 = null;
    private SingleObj4(){

    }
    public static SingleObj4 getSingleObj1(){
        if(singleObj4 == null){
            synchronized (SingleObj4.class){
                if(singleObj4 == null) {
                    singleObj4 = new SingleObj4();
                }
            }
        }
        return singleObj4;
    }
}

//登记式/静态内部类

/**
 * 对静态域使用延迟初始化，应使用这种方式而不是双检锁方式。
 * 这种方式只适用于静态域的情况，双检锁方式可在实例域需要延迟初始化时使用。
 */
class SingleObj5{
    private static class SingleIn{
        private static final SingleObj5 INSTANCE = new SingleObj5();
    }
    private SingleObj5(){}
    public static SingleObj5 getSingleObj1(){
        return SingleIn.INSTANCE;
    }
}

class Resource{}
enum Singleton6 {
    INSTANCE;
    private Resource resource;
    private Singleton6(){
        resource = new Resource();
    }
    public Resource getResource(){
        return resource;
    }
}







