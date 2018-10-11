package Java.logic.part3;

import java.util.ArrayList;
import java.util.List;

public class Genic {
    public static void main(String[] args){

        List<? extends Number> list = new ArrayList<Integer>();
        //list.add(123);
        A<Double> a = new A<Double>(12.3);
        System.out.println("T:"+a.getT());

        List<? super CC> list1 = new ArrayList<CC>();
        list1.add(new C1());
        list1.add(new CC());
        System.out.println("list1.size="+list1.size());

    }
}


interface Compare<T>{
    boolean comp(T a, T b);
}
interface C {}
class CC {
    CC(){}
}
class C1 extends CC implements C{
    public int a;
}


class A2<T extends Compare<T>>{

}

class A<T extends Number>{
    private T t;
    A(){}
    A(T t){
        this.t = t;
    }
    public T getT(){
        return t;
    }
}
