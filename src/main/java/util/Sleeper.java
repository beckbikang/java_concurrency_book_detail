package util;

import java.util.concurrent.TimeUnit;

public class Sleeper {

    public static void sleep(int second){
        try{
            TimeUnit.SECONDS.sleep(second);
        }catch (InterruptedException e){
            System.out.println("catch sleep InterruptedException");
            e.printStackTrace();
        }
    }
    public static void msleep(int millSecond){
        try{
            TimeUnit.MILLISECONDS.sleep(millSecond);
        }catch (InterruptedException e){
            System.out.println("catch sleep InterruptedException");
            e.printStackTrace();
        }
    }
}
