package Java.logic.part1;

public class SImpleGramma{


    public static void main(String args[]) throws Exception{

        testUseJava();
    }

    /**
     * 简单使用ajva
     */
    public static void testUseJava() throws Exception{
        int a = 1;

        int b = a+1;

        int c = 0X123;

        System.out.println(a+":"+b+":"+c);

        if(a > 1){
            System.out.println(a);
        }

        String key  = "康";
        String key2 = new String(key.getBytes(),"GB2312");
        System.out.println(key2);

        key2 = new String(key.getBytes(),"GBK");
        System.out.println(key2);

        key2 = new String(key.getBytes(),"UTF-8");
        System.out.println(key2);


    }
}
