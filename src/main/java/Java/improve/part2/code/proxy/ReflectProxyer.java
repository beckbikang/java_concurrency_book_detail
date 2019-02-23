package Java.improve.part2.code.proxy;

import java.lang.reflect.Constructor;


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
