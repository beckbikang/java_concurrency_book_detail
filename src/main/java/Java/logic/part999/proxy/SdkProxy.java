package Java.logic.part999.proxy;



import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SdkProxy {

    public static void main(String[] args){

        Worker myworker = new MyWorker();

        Worker worker = (Worker) Proxy.newProxyInstance(
            Worker.class.getClassLoader(),
                new Class<?>[]{Worker.class},
                new SimpleProxyHandler(myworker)
        );

        worker.run();
    }
}


class SimpleProxyHandler implements InvocationHandler{

    private Object object;

    public SimpleProxyHandler(Object o){
        this.object = o;
    }

    /**
     * 动态代理
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable{
        System.out.println("enter:"+method.getName());

        Object result = method.invoke(object,args);

        System.out.println("leaving:"+method.getName());
        return result;
    }

}

