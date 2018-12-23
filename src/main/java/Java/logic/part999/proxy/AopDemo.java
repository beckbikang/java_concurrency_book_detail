package Java.logic.part999.proxy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class AopDemo {

    public static void main(String[] args){

    }
}


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Aspect{
    Class<?>[] value();
}


@Aspect({SayHi.class})
class LogAspect{

}


