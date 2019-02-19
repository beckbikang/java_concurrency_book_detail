package Java.logic.part999.loader;

import Java.logic.part6.File.BinaryFileUtils;

import java.io.IOException;

public class TestLoder {

    public static void main(String args[]){
        testLoad();
    }

    public static void testLoad(){
        //加载器
        ClassLoader loader = TestLoder.class.getClassLoader();
        while (loader != null){
            System.out.println(loader.getClass().getName());
            loader = loader.getParent();
        }
        System.out.println(loader);

        //测试委托机制
        System.out.println(ClassLoader.getSystemClassLoader().getClass().getName());
        ClassLoader loader1 = ClassLoader.getSystemClassLoader();
        try{
            Class<?> cls = loader1.loadClass("java.util.Arrays");
            ClassLoader loader2 = cls.getClassLoader();
            System.out.println(loader2);
        }catch (Exception e){
            e.printStackTrace();
        }

        //使用loader加载本地的类
        try{
            String className = TestLoder.class.getName()+"$Hello";
            Class<?> cls2 = loader1.loadClass(className);
            Class<?> cls3 = Class.forName(className);
        }catch (Exception e){
            e.printStackTrace();
        }



    }

    public static class Hello{
        static {
            System.out.println("hello");
        }
    }
}

class MyClassLoader extends ClassLoader{


    private static final String BASE_DIR = "/xx";


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException{
        String fileName = name.replaceAll("\\.", "/");
        fileName = BASE_DIR+fileName+".class";
        try{
            byte[] bytes = BinaryFileUtils.readFileToByteArray(fileName);
            return defineClass(name,bytes,0, bytes.length);
        }catch (IOException e){
            throw new ClassNotFoundException("faild to load class "+name, e);
        }

    }


}







