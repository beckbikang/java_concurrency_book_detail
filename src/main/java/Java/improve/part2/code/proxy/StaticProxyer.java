package Java.improve.part2.code.proxy;

import net.sf.cglib.proxy.Enhancer;

import javax.security.auth.Subject;
import java.lang.reflect.Proxy;

/**
 * @ Author     ：bikang1@staff.weibo.com
 * @ Date       ：Created in 上午11:28 2019/2/21
 * @ Description：
 * @ Modified By：
 */
public class StaticProxyer {

    public static void main(String[] args){
        //testStaticProxy();
        //testDynamicProxy();
        testCglibProxy();

        testReflect();
    }

    //动态代理
    public static void testDynamicProxy(){

        DynamicProxyer proxyer = new DynamicProxyer(new BoySayer());
        //生成文件
        System.getProperties().put("sun.misc.ProxyGenerator.files","true");

        Sayer sayer = (Sayer) (Proxy.newProxyInstance(
                Sayer.class.getClassLoader(),
                new Class[] {Sayer.class}, proxyer));
        sayer.say();

        sayer.write();

    }

    //静态代理
    public static void testStaticProxy(){
        Sayer sayer = new BoySayer();
        Sayer sayer1 = new GirlSayer();

        ProxySayer.proxySayer(sayer);
        ProxySayer.proxySayer(sayer1);
        ProxySayer.proxyWriter(sayer);
        ProxySayer.proxyWriter(sayer1);
    }

    //cglib代理
    public static void testCglibProxy(){
        Enhancer enhancer = new Enhancer(); //字节码增强器
        enhancer.setSuperclass(OtherSayer.class);  //代理类
        enhancer.setCallback(new CglibProxy());//回调的方法
        OtherSayer otherSayer =(OtherSayer) enhancer.create();
        otherSayer.sayer();
    }

    //使用反射
    public static void testReflect(){
        OtherSayer otherSayer = (OtherSayer)ReflectProxyer.buildObject(OtherSayer.class);
        if(otherSayer != null){
            otherSayer.sayer();
        }
    }


}

class OtherSayer{
    public OtherSayer(){}
    public void sayer(){
        System.out.println("other sayer");
    }
}

class ProxySayer{
    public static void proxySayer(Sayer sayer){
        System.out.println("before");
        sayer.say();
        System.out.println("after");
    }
    public static void proxyWriter(Sayer sayer){
        System.out.println("before");
        sayer.write();
        System.out.println("after");
    }
}

interface Sayer{
    void say();
    void write();
}
class BoySayer implements Sayer{
    public void say(){
        System.out.println("boy sayer");
    }

    public void write(){
        System.out.println("boy write");
    }
}

class GirlSayer implements Sayer{
    public void say(){
        System.out.println("girl say");
    }

    public void write(){
        System.out.println("girl write");
    }
}
