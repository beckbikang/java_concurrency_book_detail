package Java.logic.part2;

import java.io.FileInputStream;

public class ExceptionTest {

    public static void main(String[] args) throws Exception{

        tt();
    }

    public static void tt() throws Exception{
        System.out.println("tt");


        try(AutoCloseable ac = new FileInputStream("tt.log")){

            System.out.println("haha");
        }

        /*
        //不适用变量
        try(String str = t2()){
            System.out.println("end");
        }
        */
    }

    public static String t2() throws Exception{
        throw new Exception("haha");
    }
}
