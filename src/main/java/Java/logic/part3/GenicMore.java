package Java.logic.part3;


import java.util.List;

public class GenicMore {
    public static void main(String[] args){
        tt();
    }

    public static void tt(){
        System.out.println("test genic extends interface");
        System.out.println("max:"+maxIt(new Integer[]{1,3,2,46,72,33}));
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
    //todo 添加类型的匹配

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

