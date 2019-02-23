package Java.improve.part2.code.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ Author     ：bikang1@staff.weibo.com
 * @ Date       ：Created in 下午1:05 2019/2/21
 * @ Description：
 * @ Modified By：
 */
public class DynamicProxyer implements InvocationHandler{

    private Object object;

    public DynamicProxyer(Object o){
        this.object = o;
    }

    public  Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        System.out.println("before");
        Object result = method.invoke(object, args);
        System.out.println("after");
        return result;
    }


}