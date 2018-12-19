package Java.logic.part999.antoin;


import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

public class Anation {

    public static void main(String[] args) throws Exception{
        testAnation();

    }

    public static void testAnation() throws Exception{
        //基本的处理
        YY yy = new YY();
        System.out.println(yy);

        //注解的继承
        System.out.println(Child.class.isAnnotationPresent(
                Test1.class
        ));

        //序列化
        SimpleDateFormat sdf = new SimpleDateFormat(
          "yyyy-MM-dd"
        );
        Student student = new Student(
                "张三",
                sdf.parse("1990-12-12"),12.1d
        );
        System.out.println(formatDate(student));

        //注入
        ServiceA serviceA =  getInstance(ServiceA.class);
        serviceA.callB();


        //注入2
        ServiceC serviceA2 =  getInstance2(ServiceC.class);
        serviceA2.callB();

        ServiceC serviceA3 =  getInstance2(ServiceC.class);
        serviceA3.callB();


    }

    private static Map<Class<?>, Object> maps = new ConcurrentHashMap<>();

    public static <T> T getInstance2(Class<T> cls){
        try{
            boolean issign = cls.isAnnotationPresent(
                    SimpleSingleton.class
            );

            if(!issign){
                return createObj(cls);
            }
            Object obj = maps.get(cls);
            if(obj != null){
                return (T) obj;
            }
            synchronized (cls){
                obj = maps.get(cls);
                if(obj == null){
                    obj = createObj(cls);
                    maps.put(cls, obj);
                }
            }
            return (T) obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T createObj(Class<T> cls){
        try{
            T obj = cls.newInstance();
            Field[] fields = cls.getDeclaredFields();

            for(Field f :fields){
                if(f.isAnnotationPresent(SimpleInject.class)) {
                    if (!f.isAccessible()) {
                        f.setAccessible(true);
                    }
                    Class<?> fc = f.getType();
                    f.set(obj, getInstance(fc));
                }
            }
            return obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T getInstance(Class<T> cls){
        try{
            T obj = cls.newInstance();
            Field[] fields = cls.getDeclaredFields();

            for(Field f :fields){
                if(f.isAnnotationPresent(SimpleInject.class)) {
                    if (!f.isAccessible()) {
                        f.setAccessible(true);
                    }
                    Class<?> fc = f.getType();
                    f.set(obj, getInstance(fc));
                }
            }
            return obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String formatDate(Object obj){
        try{

            Class<?> cls = obj.getClass();
            StringBuilder sb = new StringBuilder();
            for(Field f : cls.getDeclaredFields()){
                if(!f.isAccessible()){
                    f.setAccessible(true);
                }
                Label label = f.getAnnotation(Label.class);

                String name = label != null ?label.value():f.getName();
                Object val = f.get(obj);
                if( val != null && (f.getType() == Date.class)){
                    val = formatDate(f, val);
                }
                sb.append(name);
                sb.append(":");
                sb.append(val);
                sb.append("\n");

            }
            return sb.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public static String formatDate(Field f, Object val){

        Format format = f.getAnnotation(Format.class);
        if(format != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(
                    format.pattern()
            );
            sdf.setTimeZone(TimeZone.getTimeZone(
                    format.timezone()
            ));
            return sdf.format(val);
        }

        return "";
    }



}

//单例
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface SimpleSingleton{
}

@SimpleSingleton
class ServiceC{
    @SimpleInject
    ServiceD serviced;

    public void callB(){
        System.out.println(this);
        serviced.action();
    }
}


class ServiceD{
    public void action(){
        System.out.println(this);
        System.out.println(" service d");
    }
}





//注入
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface SimpleInject{
}

class ServiceA{
    @SimpleInject
    ServiceB serviceB;

    public void callB(){
        serviceB.action();
    }

}
class ServiceB{
    public void action(){
        System.out.println(" service b");
    }
}



//序列化
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Label{
    String value() default "";
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Format{
    String pattern() default "yyyy-MM-dd HH:mm:ss";
    String timezone() default "GMT+8";
}

class Student{
    @Label("名字")
    String name;

    @Label("生日")
    @Format(pattern = "yyyy/MM/dd")
    Date born;

    @Label("分数")
    double score;

    public Student(){}
    public Student(
            String name,
            Date born,
            double score
    ){
        this.name = name;
        this.born = born;
        this.score = score;
    }
}


@Target({ElementType.METHOD,ElementType.CONSTRUCTOR,
ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface Inject{
    boolean optional()default false;
}

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@interface Test1{
}

@Test1
class Base{}

class Child extends Base{

}



class YY {

    @Override
    public String toString(){
        return "YY";
    }

    @SuppressWarnings({"deprecation","unused"})
    public void test12(){

    }
    @Deprecated
    public void test(){

    }
}
