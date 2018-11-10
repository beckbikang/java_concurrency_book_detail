package head.first.dp.builder;

/**
 * 建造者模式这个例子不错
 *
 *
 */

public class BuilderDemo {

    public static void main(String[] args){

    }
}

interface Packing{
    String pack();
}

interface Item{
    String name();
    float price();
    Packing packing();
}

class Wrapper implements Packing{

    @Override
    public String pack(){
        return "Wrapper";
    }
}

class Bottle implements Packing{

    @Override
    public String pack(){
        return "Bottle";
    }

}

abstract class Burger implements Item{

    @Override
    public abstract float price();
    @Override
    public Packing packing(){
        return new Wrapper();
    }
}

abstract class ColdDrink implements Item {

    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}

class Burger1 extends Burger {

    @Override
    public float price() {
        return 18.0f;
    }

    @Override
    public String name() {
        return " Burger1";
    }
}

class Cola extends ColdDrink{

    @Override
    public float price() {
        return 4.0f;
    }

    @Override
    public String name() {
        return " cola";
    }
}






