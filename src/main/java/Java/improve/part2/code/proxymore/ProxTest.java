package Java.improve.part2.code.proxymore;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxTest {
    public static void main(String[] args){
        //simpleProxy();
        jdkProxy();
        //jdkProxy2();
    }

    public static void simpleProxy(){
        IDBQuery idbQuery = new DBQueryProxy();
        long startTime = System.currentTimeMillis();
        idbQuery.request();
        idbQuery.request();
        long lastTime = (System.currentTimeMillis() - startTime);
        System.out.println("lastTime:"+lastTime);
    }

    public static void jdkProxy(){
        long startTime = System.currentTimeMillis();
        IDBQuery idbQuery = createJdkProxy();
        System.out.println(idbQuery.request());
        System.out.println(idbQuery.request());
        long lastTime = (System.currentTimeMillis() - startTime);
        System.out.println("lastTime:"+lastTime);
    }

    public static IDBQuery createJdkProxy(){
        IDBQuery jdkProxy = (IDBQuery) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader()
                ,new Class[]{IDBQuery.class},new JDKQueryHandler());
        return jdkProxy;
    }

    public static void jdkProxy2(){
        long startTime = System.currentTimeMillis();
        IDBQuery idbQuery = createJdkProxy2();
        System.out.println(idbQuery.request());
        System.out.println(idbQuery.request());
        long lastTime = (System.currentTimeMillis() - startTime);
        System.out.println("lastTime:"+lastTime);
    }

    public static IDBQuery createJdkProxy2(){
        DBQuery dbQuery = new DBQuery();
        IDBQuery jdkProxy = (IDBQuery) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader()
                ,new Class[]{IDBQuery.class},new JDKQueryHandler2(dbQuery));
        return jdkProxy;
    }

}

class CglibDbquery implements MethodInterceptor{
    IDBQuery real = null;

    @Override
    public Object intercept(Object var1, Method var2, Object[] var3, MethodProxy var4) throws Throwable{
        return null;
    }

}



interface IDBQuery{
    String request();
}

class DBQueryProxy implements IDBQuery{
    private DBQuery real = null;

    @Override
    public String request(){
        if(real == null){
            real = new DBQuery();
        }
        return real.request();
    }
}

class DBQuery implements IDBQuery{
    public DBQuery(){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public String request(){
        return "query string";
    }
}

//这样我们可以在代理类前做一些东西
//jdk的动态代理
class JDKQueryHandler implements InvocationHandler{
    IDBQuery real = null;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable{
        if(real == null){
            real = new DBQuery();
        }

        //Object result = method.invoke(object, args);
        return method.invoke(real,args);
    }
}

//这样我们可以在代理类前做一些东西
class JDKQueryHandler2 implements InvocationHandler{
    IDBQuery real = null;

    public JDKQueryHandler2(IDBQuery real){
        this.real = real;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable{
        return real.request();
    }
}












