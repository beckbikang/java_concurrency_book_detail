package util;

import java.util.concurrent.TimeUnit;

public class Sleeper {

    public static void sleep(int second){
        try{
            TimeUnit.SECONDS.sleep(second);
        }catch (InterruptedException e){
            System.out.println("sleep InterruptedException");
            e.printStackTrace();
        }
    }
}
