package Java.logic.part999.proxy;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy {

    public static void main(String[] args){

        SayHi sayHi = getProxy(SayHi.class);

        sayHi.say();

    }

    public static <T> T getProxy(Class<T> cls){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(cls);
        enhancer.setCallback(new SimpleInterceptor());
        return (T) enhancer.create();
    }
}

class SayHi{
    public void say(){
        System.out.println("ha ha ha!");
    }
}

//简单代理
class SimpleInterceptor implements MethodInterceptor{

    public Object intercept(Object o,
                            Method method,
                            Object[] args,
                            MethodProxy methodProxy)
    throws Throwable{
        System.out.println("entering "+method.getName());
        Object result = methodProxy.invokeSuper(o,args);
        System.out.println("leaving "+method.getName());

        return result;
    }
}