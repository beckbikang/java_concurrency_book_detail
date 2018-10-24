package Java.logic.part6.File;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class IoStreamTest {

    public static void main(String[] args){
        trw();
    }

    public static void trw(){
        try{
            DataOutputStream outputStream = new DataOutputStream(
                    new FileOutputStream("rr.log")
            );
            outputStream.write("123".getBytes(),0,3);
            outputStream.close();



        }catch (IOException e){

        }

    }
}
