package head.first.dp.strategy;

public class strategyM {

    public static void main(String[] args){

        MallardDuck mallardDuck = new MallardDuck();
        mallardDuck.display();

        DecoyDuck decoyDuck = new DecoyDuck();
        decoyDuck.display();



    }
}


interface Flyable{
    void fly();
}

interface Quackable{
    void quack();
}

class Ducker{

    Flyable flyable;
    Quackable quackable;

    public void swim(){

    }
    public void display(){
        quack();
        fly();
    }

    public void setFlyable(Flyable flyable){
        this.flyable = flyable;
    }

    public void setQuackable(Quackable quackable){
        this.quackable = quackable;
    }

    public void quack(){
        if(quackable != null)
        quackable.quack();
    }

    public void fly(){
        if(flyable != null)
        flyable.fly();
    }
}

class DecoyDuck extends Ducker{
    public void display(){
        System.out.println("nothing");
    }
}

class QuackMouth implements Quackable{
    @Override
    public void quack(){
        System.out.println("quack with mouth");
    }
}

class FlySwing implements Flyable{
    @Override
    public void fly() {
        System.out.println("fly with swing");
    }
}

class MallardDuck extends Ducker{

    MallardDuck(){
        setFlyable(new FlySwing());
        setQuackable(new QuackMouth());
    }

    public void display(){
        super.display();
        System.out.println("MallardDuck display");
    }

}