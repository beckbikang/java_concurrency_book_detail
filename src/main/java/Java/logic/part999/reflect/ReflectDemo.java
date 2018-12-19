package Java.logic.part999.reflect;

import java.lang.reflect.*;
import java.util.*;

public class ReflectDemo {

    public static void main(String[] args){

        testReflect();
    }

    //处理反射
    public static void testReflect(){
        Class<?> cls = Date.class;
        int[] oarr = new int[10];
        Class<? extends int[]> aClass = oarr.getClass();

        try{
            Class<?> cls2 = Class.forName("java.util.HashMap");
            System.out.println(cls2.getName());

            //method
            Class<?> cls3 = Integer.class;
            Method method = cls3.getMethod(
                    "parseInt",
                    new Class[]{String.class});
            int a = (int)method.invoke(cls3,"123");
            System.out.println(a);

            Map<String, Integer> map = HashMap.class.newInstance();
            map.put("abc", 1234);
            System.out.println(map.get("abc"));

            //student
            Constructor<Student> constructor =
                                 Student.class.getConstructor(
                                 new Class[]{
                                         String.class, int.class
                                 }
                                 );
            Student st = constructor.newInstance("abc", 123);
            System.out.println("####");
            st.show();
            System.out.println(cls3.isInstance(Student.class));


            System.out.println(oarr.getClass().getComponentType());

            //数组
            int[] arr = (int[]) Array.newInstance(
                    int.class,10
            );
            arr[0] = 123;
            System.out.println(arr[0]);

            //基本应用
            String stud = toString(st);
            System.out.println(stud);

            Student st2 = toType(fromString(stud), Student.class);
            st2.show();

            //获取泛型信息
            Class<?>cls5 = A.class;

            for(TypeVariable t : cls5.getTypeParameters()){
                System.out.println(t.getName()+" "+Arrays.toString(
                        t.getBounds()
                ));
            }
            //自动断
            Field fu = cls5.getDeclaredField("u");
            System.out.println(fu.getGenericType());

            Field lis = cls5.getDeclaredField("list");
            Type tupe = lis.getGenericType();
            if(tupe instanceof ParameterizedType){

                ParameterizedType ptype = (ParameterizedType) tupe;

                System.out.println(ptype.getRawType()+" params"+
                Arrays.toString(
                        ptype.getActualTypeArguments()
                ));

            }


            Method m = cls5.getMethod("test",new Class[]{
                    List.class
            });

            for(Type t : m.getGenericParameterTypes()){
                System.out.println(t);
            }





        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static String toString(Object obj){
        StringBuilder sb = new StringBuilder();

        try{
            Class<?> cls = obj.getClass();
            String name = cls.getName();
            sb.append(name);
            sb.append("|");

            Field[] fields = cls.getDeclaredFields();
            for (Field f : fields){
                if(!f.isAccessible()){
                    f.setAccessible(true);
                }
                sb.append(f.getName());
                sb.append("=");
                sb.append(f.get(obj).toString());
                sb.append("|");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static Object fromString(String str){

        try{
            String[] lines = str.split("\\|");
            if(lines.length < 1){
                return null;
            }
            Class<?> cls = Class.forName(lines[0]);
            Object obj = cls.newInstance();
            if(lines.length > 1){
                for (int i=1; i < lines.length; i++){
                    String[] fv = lines[i].split("=");
                    if(fv.length != 2) continue;
                    Field f = cls.getDeclaredField(fv[0]);
                    if(!f.isAccessible()){
                        f.setAccessible(true);
                    }
                    setFileValue(f,obj,fv[1]);
                }
            }
            return obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //设置便利
    public static void setFileValue(Field f,Object obj,String val) throws Exception{
        Class<?> type = f.getType();
        if(type == int.class){
            f.setInt(obj, Integer.parseInt(val));
        }else if(type == byte.class){
            f.setByte(obj,Byte.parseByte(val));
        }else if(type == short.class){
            f.setShort(obj, Short.parseShort(val));
        }else if(type == long.class){
            f.setLong(obj, Long.parseLong(val));
        }else if(type == float.class){
            f.setFloat(obj, Float.parseFloat(val));
        }else if(type == double.class){
            f.setDouble(obj, Double.parseDouble(val));
        }else if(type == char.class){
            f.setChar(obj, val.charAt(0));
        }else if(type == boolean.class){
            f.setBoolean(obj, Boolean.parseBoolean(val));
        }else if( type == String.class){
            f.set(obj, val);
        }else{
            Constructor<?> ctor = type.getConstructor(
                    new Class[]{String.class}
            );
            f.set(obj, ctor.newInstance(val));
        }

    }


    public static <T> T toType(Object obj, Class<T> cls){

        return cls.cast(obj);
    }
}

class A<U extends  Comparable<U>, V>{
    U u;
    V v;
    List<String> list;
    public U test(List<? extends Number> numbers){
        return null;
    }

}

class Student{
    private String name;
    private int age;

    public Student(){}
    public Student(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void show(){
        System.out.println("name:"+name+" age:"+age);
    }
}
