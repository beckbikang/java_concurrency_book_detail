package head.first.dp.strategy;

public class strategyM1 {

    public static void main(String[] args){
        
    }
}

interface Flyable1{
    void fly();
}

interface Quackable1{
    void quack();
}

class Ducker1{
    public void swim(){

    }
    public void display(){

    }
}

class DecoyDuck1 extends Ducker1{
    public void display(){

    }
}

class MallardDuck1 extends Ducker1 implements Flyable1,Quackable1{

    public void fly(){

    }
    public void display(){

    }

    public void quack(){

    }
}