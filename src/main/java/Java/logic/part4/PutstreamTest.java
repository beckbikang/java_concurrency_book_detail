package Java.logic.part4;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class PutstreamTest {

    public static void main(String[] args){
        woutput();
    }

    /**
     * 输出数据
     *
     */
    public static void woutput(){

        OutputStream outputStream;
        try{
            outputStream = new FileOutputStream("tt.log");
            String data = "abc";
            outputStream.write(data.getBytes());
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("end");
        }
    }

    public static void tt(){
        System.out.println("tt");
    }
}
