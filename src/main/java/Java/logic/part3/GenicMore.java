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


        //泛型的比较
        Integer[] arr = new Integer[]{1,3,4,5,6};
        int f = 3;

        //泛型方法
        System.out.println("index="+indexOf(arr,f));
        Pair2<String,Integer> pair21 = makePair2("beck",31);
        System.out.println(pair21.getFirst()+" "+pair21.getSecond());

        //指定方法的范围
        NumberPair<Integer,Double> numberPair = new NumberPair<>(1,2.1);
        System.out.println("NumberPair:"+numberPair.getFirst()+" "+numberPair.getSecond());

        //泛型上限是某一个接口
        Integer[] integersArr = new Integer[]{1,3,4,5,6};
        Integer integer = maxData(integersArr);
        System.out.println("max:"+integer);


    }

    //上届为指定的接口

    public static <T extends Comparable> T maxData(T[] arr){
        T max = arr[0];
        for (int i = 0;i < arr.length; i++){
            if(arr[i].compareTo(max) > 0){
                max = arr[i];
            }
        }
        return max;
    }

    //单个数据类型的泛型
    public static <T> int indexOf(T[] arr, T e){
        for (int i =0; i < arr.length; i++){
            if(arr[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    //多个数据类型的泛型
    public static <U,V>  Pair2<U,V> makePair2(U u,V v){
        return new Pair2<>(u,v);
    }
}

//泛型接口
interface Comparable2<E>{
    public int compareTo(E e);

}

interface Comparabletor2<E>{
    public int compareTo(E e1, E e2);
    boolean equals(Object o);
}

/*
final class Integer2 extends Number implements Comparable2<Integer2>{
    public int compareTo(Integer2 integer2){
        return compareTo(integer2);
    }
}
*/

//动态数组
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

class NumberPair<T extends Number,U extends Number> extends Pair2<T,U>{

    public NumberPair(T t, U u){
        super(t,u);
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