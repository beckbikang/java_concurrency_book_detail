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

        //接口使用
        IntAClass intAClass = new IntAClass(12);
        System.out.println("subt="+intAClass.subt(1));


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

        //指定为number的子类
        //NumberPair<String, Integer> numberPair1 = new NumberPair<String, Integer>();

        //泛型上限是某一个接口
        Integer[] integersArr = new Integer[]{1,3,4,5,6};
        Integer integer = maxData(integersArr);
        System.out.println("max:"+integer);


        //上界为其他类型
        //T 继承了E ，这是一种向上转型
        DynamicArray<Number> dynamicArray1 = new DynamicArray<>();
        DynamicArray<Integer> dynamicArray2 = new DynamicArray<>();
        dynamicArray2.add(12);
        dynamicArray2.add(34);
        dynamicArray1.addAll(dynamicArray2);
        dynamicArray1.addAll3(dynamicArray2);
        System.out.println("dynamicArray1 len="+dynamicArray1.getLen());
        //只能读不能写
        DynamicArray<?extends Number> dynamicArray3 = new DynamicArray<>();
        //dynamicArray3.add(123);

        //需要写入的情况
        DynamicArray<Number> dynamicArray22 = new DynamicArray<>();
        DynamicArray<Integer> dynamicArray11 = new DynamicArray<>();
        dynamicArray11.add(1);
        dynamicArray11.add(2);
        //拷贝失败
        // dynamicArray11.copyTo(dynamicArray22);
        dynamicArray11.copyTo2(dynamicArray22);

        //泛型与继承
        DynamicArray<Son> sonDynamicArray = new DynamicArray<>();
        sonDynamicArray.add(new Son(1));
        sonDynamicArray.add(new Son(2));
        //DynamicArray.max2(sonDynamicArray);
        Son son = DynamicArray.max(sonDynamicArray);


        //运行时信息
        Pair<String> pair11 = new Pair<>("1","2");
        Pair<Integer> pair22 = new Pair<>(1,2);
        System.out.println(pair11.getClass() == Pair.class);
        System.out.println(pair22.getClass() == Pair.class);
        System.out.println(pair22.getClass() == pair11.getClass());


    }



    //上届为指定的接口
    public static <T extends Comparable> T maxData(T[] arr) {
        T max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(arr[i].compareTo(max) > 0){
                max = arr[i];
            }
        }
        return max;
    }

    public static <T extends Comparable<T>> T maxIt(T[] arr){
        T max = arr[0];
        for (int i=0; i < arr.length; i++){
            if(arr[i].compareTo(max) > 0){
                max = arr[i];
            }
        }
        return max;
    }

    //泛型方法
    public static <T> int indexOf(T[] arr, T e){
        for (int i = 0; i < arr.length; i++){
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

interface IntA<T>{
    public T subt(T t);
}

class IntAClass implements IntA<Integer>{
    int i;
    IntAClass(int i){
        this.i = i;
    }
    public Integer subt(Integer t){
        return i - t.intValue();
    }
}

/*
final class Integer2 extends Number implements Comparable2<Integer2>{
    public int compareTo(Integer2 integer2){
        return compareTo(integer2);
    }
}
*/

class Father implements Comparable<Father>{
    private int sortOrder;
    public Father(int s){
        this.sortOrder = s;
    }

    public int compareTo(Father f){
        if(sortOrder < f.sortOrder){
            return -1;
        }else if(sortOrder > f.sortOrder){
            return 1;
        }else{
            return 0;
        }
    }
}

class Son extends Father{
    public Son(int sortOrder){
        super(sortOrder);
    }
}



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

    public <T extends E>void addAll(DynamicArray<T> c){
        for (int i=0;i<c.size; i++){
            add(c.get(i));
        }
    }

    public void addAll2(DynamicArray<E> c){
        for (int i=0;i<c.size; i++){
            add(c.get(i));
        }
    }

    public void addAll3(DynamicArray<?extends E> c){
        for (int i=0;i<c.size; i++){
            add(c.get(i));
        }
    }

    public void copyTo(DynamicArray<E> dest){
        for (int i=0;i<size; i++){
            dest.add(get(i));
        }
    }

    public void copyTo2(DynamicArray<? super E> dest){
        for (int i=0;i<size; i++){
            dest.add(get(i));
        }
    }

    public static <T extends Comparable> T max2(DynamicArray<T> arr){
        T max = arr.get(0);
        for (int i = 0; i < arr.getLen(); i++) {
            if(arr.get(i).compareTo(max) > 0){
                max = arr.get(i);
            }
        }
        return max;
    }

    public static <T extends Comparable<? super T>> T max(DynamicArray<T> arr){
        T max = arr.get(0);
        for (int i = 0; i < arr.getLen(); i++) {
            if(arr.get(i).compareTo(max) > 0){
                max = arr.get(i);
            }
        }
        return max;
    }

    //解析通配符

    /**
     *
     * 1）< T extends E> 用于 定义 类型 参数， 它 声明 了 一个 类型 参数 T，
     * 可 放在 泛 型 类 定义 中 类 名 后面、 泛 型 方法 返回 值 前面。
     *
     * 2）<？ extends E> 用于 实例 化 类型 参数，
     * 它 用于 实例 化 泛 型 变量 中的 类型 参数，
     * 只是 这个 具体 类型 是 未知 的， 只 知道 它是 E 或 E 的 某个 子 类型。
     */

    /**
     * 无限通配符
     *
     * public static int indexOf( DynamicArray<?> arr, Object elm)
     * 可以 改为：
     * public static <T> int indexOf( DynamicArray<T> arr, Object elm)
     *
     * <?> <? extends T>只能 读， 不能 写。
     *      解决方式：1
     *
     *          private static < T> void swapInternal(
     *                  DynamicArray< T> arr, int i, int j){
     *                  T tmp = arr. get( i);
     *                  arr. set( i, arr. get( j));
     *                  arr. set( j, tmp);
     *          }
     *         public static void swap( DynamicArray<?> arr, int i, int j){
     *              swapInternal( arr, i, j);
     *         } //swap 可以 调用 swapInternal，
     *
     *
     *
     * 1） 通配符 形式 都可 以用 类型 参数 的 形式 来 替代，
     *      通配符 能做 的， 用 类型 参数 都 能做。
     * 2） 通配符 形式 可以 减少 类型 参数， 形式上 往往 更为 简单，
     *      可读性 也 更好， 所以， 能用 通配符 的 就用 通配符。
     * 3） 如果 类型 参数 之间 有 依赖 关系， 或者 返回 值 依赖 类型 参数，
     *          或者 需要 写 操作， 则 只能 用 类型 参数。
     * 4） 通配符 形式 和 类型 参数 往往 配合 使用，
     *      比如， 上面 的 copy 方法， 定义 必要 的 类型 参数，
     *      使用 通配符 表达 依赖， 并 接受 更 广泛 的 数据 类型。

     *
     * 超类型通配符  <? supers E>  表示E某个父类型
     *
     *
     *  不 能将 DynamicArray< Integer> 看作 DynamicArray< Number>，
     *  但我 们 这里 的 用法 是 没有 问题 的，
     *  Java 解决 这个 问题 的 方法 就是 超 类型 通配符，
     *  可以 将 copyTo 代码 改为：
     *
     *
     *  不 能将 DynamicArray< Integer> 看作 DynamicArray< Number>，
     *  public void copyTo( DynamicArray< E> dest){
     *          for( int i= 0; i< size; i++){
     *              dest. add( get( i));
     *          }
     *   }
     *
     *
     *  public void copyTo( DynamicArray<? super E> dest){
     *      for( int i= 0; i< size; i++){
     *          dest. add( get( i));
     *      }
     *  }
     *
     * 超 类型 通配符 另一个 常用 的 场合 是 Comparable/ Comparator 接口。
     * 同样， 我们 先来 看下 如果不 使用 会有 什么 限制。


     public static < T extends Comparable< T>> T max( DynamicArray< T> arr){

     }
     *
     *
     *
     *  class Base implements Comparable< Base>{
            public int compareTo( Base o) {}
        }
         class Child extends Base {
         }

        //错误
        Child maxChild = max( childs);

     public static < T extends Comparable<? super T>> T max( DynamicArray< T> arr){
     }
     public static < T extends Comparable<? super Child>> T max( DynamicArray< T> arr){
     }

     等价声明:
        public void copyTo( DynamicArray<? super E> dest)
        public < T super E> void copyTo( DynamicArray< T> dest)

     通配符说明
        形式<？>、<？ super E> 和<？ extends E>，
     1） 它们 的 目的 都是 为了 使 方法 接口 更为 灵活， 可以 接受 更为 广泛 的 类型。
     2）<？ super E> 用于 灵活 写入 或 比较，使得对象可以写入父类型的容器，
        使得 父 类型 的 比较 方法 可以 应用于 子类 对象， 它不 能被 类型 参数 形式 替代。
     3）<？> 和<？ extends E> 用于 灵活 读取，
        使得 方法 可以 读取 E 或 E 的 任意 子 类型 的 容器 对象，
        它们 可以 用 类型 参数 的 形式 替代， 但 通配符 形式 更为 简洁。
    
     *
     *
     *
     */



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
