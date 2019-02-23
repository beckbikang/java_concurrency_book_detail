package Java.improve.part2.code.proxy;

import java.lang.reflect.Constructor;

/**
 * @ Author     ：bikang1@staff.weibo.com
 * @ Date       ：Created in 下午1:49 2019/2/21
 * @ Description：
 * @ Modified By：
 */
public class ReflectProxyer {


    public static Object buildObject(Class<?> cls) {
        Object obj = null;
        try{
            Constructor<?>[] constructor =  cls.getConstructors();
            if(constructor != null && constructor.length > 0){
                obj = constructor[0].newInstance();
            }
            return obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }
}
