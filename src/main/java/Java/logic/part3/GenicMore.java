package Java.logic.part3;

import java.util.Arrays;
import java.util.Random;

public class GenicMore {

    public static void main(String[] args){
        testPair();
    }

    public static void testPair(){
        System.out.println("start pair");
        Pair<Integer> pair = new Pair<>(1,2);
        System.out.println(pair.getFirst()+" "+pair.getSecond());
        System.out.println("start pair2");

        Pair2<String, Integer> pair2 = new Pair2<>("tom",123);
        System.out.println(pair2.getFirst()+" "+pair2.getSecond());

        //pair3
        Pair3 pair3 = new Pair3("tom", 123);
        System.out.println(pair3.getFirst()+" "+pair3.getSecond());

        //DynamicArray
        DynamicArray<Double> doubleDynamicArray = new DynamicArray<>();
        Random random = new Random();
        int size = 1+random.nextInt(100);
        System.out.println("size="+size);
        for(int i = 0;i < size;i++ ){
            doubleDynamicArray.add(Math.random());
        }
        System.out.println("doubleDynamicArray len:"+doubleDynamicArray.getLen());
        System.out.println(doubleDynamicArray.get(random.nextInt(size)));

        DynamicArray<Pair2<String, Integer>> pair2DynamicArray = new DynamicArray<>();
        pair2DynamicArray.add(pair2);
        System.out.println("pair2DynamicArray len:"+pair2DynamicArray.getLen());

    }
}

class DynamicArray<E>{

    private static final int DEFAULT_ARRAY_SIZE = 10;
    private int size;
    private int len;
    private Object[] dataArr;

    public DynamicArray(){
        dataArr = new Object[DEFAULT_ARRAY_SIZE];
        len = 0;
    }

    private void expandSize(int minCap){
        int oldCap = dataArr.length;
        if(oldCap >= minCap){
            return;
        }
        int newCap = oldCap*2;
        if(newCap < minCap){
            newCap = minCap;
        }
        dataArr = Arrays.copyOf(dataArr,newCap);
    }

    public void add(E e){
        expandSize(size+1);
        dataArr[size++] = e;
        len++;
    }

    public E get(int i){
        return (E)dataArr[i];
    }

    public E set(int i, E e){
        E oldVal = get(i);
        dataArr[i] = e;
        return oldVal;
    }

    public int getLen(){
        return len;
    }

    public int getSize(){
        return size;
    }


}


class Pair<T>{
    T first;
    T second;
    public Pair(T f, T s){
        first = f;
        second = s;
    }
    public T getFirst(){
        return first;
    }
    public T getSecond(){
        return second;
    }
}

class Pair2<T,U>{
    T first;
    U second;
    public Pair2(T f, U s){
        first = f;
        second = s;
    }
    public T getFirst(){
        return first;
    }
    public U getSecond(){
        return second;
    }
}

class Pair3{
    Object first;
    Object second;
    public Pair3(Object f, Object s){
        first = f;
        second = s;
    }
    public Object getFirst(){
        return first;
    }
    public Object getSecond(){
        return second;
    }
}