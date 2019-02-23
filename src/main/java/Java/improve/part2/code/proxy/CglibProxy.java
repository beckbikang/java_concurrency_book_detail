package Java.improve.part2.code.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {


    @Override
    public Object intercept(Object var1, Method var2, Object[] var3, MethodProxy var4) throws Throwable{
        System.out.println("before");
        Object object = var4.invokeSuper(var1,var3);
        System.out.println("end");
        return object;
    }
}
