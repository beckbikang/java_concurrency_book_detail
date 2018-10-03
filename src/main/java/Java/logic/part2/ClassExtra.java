package Java.logic.part2;

public class ClassExtra {

    public static void main(String args[]){
        tt();
    }

    public static void tt(){

        //内部类
        Outer outer = new Outer("add", 123);
        Outer.Inner1 inner1 = outer.new Inner1();
        Outer.Inner2 inner2 = new Outer.Inner2();

        //枚举类型
        Size s = Size.S;
        System.out.println(s.getA());
        s = Size.fromA("M");
        System.out.println(s.getT());


    }
}

//接口
interface A{
    void a();
    static void ta1(){
        System.out.println("ta1");
    }
    default void a2() {
        System.out.println("a2");
    }
}

interface B extends A{
    void b();
}

//抽象类
abstract class AbstractClass{

    abstract void on();
}

//内部类

class Outer {

    public static  int age;

    private String name;
    Outer(String n, int a){
        name = n;
        age = a;
    }


    public void showData(){
        final String str = "abc";
        class InnerS {
            public void show(){
                System.out.println("str:"+str);
            }
        }
        InnerS innerS = new InnerS();
        innerS.show();
    }

    class Inner1 {

        public void showName(){
            System.out.println(name);
        }
    }

    static class Inner2 {
        public static void showAge(){
            System.out.println(age);
        }
    }
}


//枚举的特性

enum Size {
    S("S", "小号"),
    M("M", "中号"),
    L("L", "盗号");

    private String a;
    private String t;

    private Size(String a, String t){
        this.a = a;
        this.t = t;
    }

    public String getA(){
        return a;
    }
    public String getT(){
        return t;
    }

    public static Size fromA(String a){

        for (Size size: Size.values()){

            if(size.getA().equals(a)){
                return size;
            }
        }
        return null;
    }



}



//枚举的原理
/*
final class Size extends Enum<Size>{

    public static final Size S = new Size("SMALL",0);
    public static final Size M = new Size("MEDIUM", 1);
    public static final Size L = new Size("LARGE", 1);

    private static final Size[] VALUES = new Size[]{
            S,M,L
    };

    private Size(String name, int ord){
        super(name, ord);
    }

    public static Size[] values(){
        Size[] values = new Size[VALUES.length];

        System.arraycopy(VALUES,0,values,
                0,VALUES.length);
        return values;
    }

    public static Size valueof(String name){
        return Enum.valueOf(Size.class, name);
    }
}
*/




