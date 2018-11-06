package head.first.dp.decorator.runno;

/**
 * 简单的装饰器模式
 *
 *
 */
public class DecoratorPatternDemo {


    public static void main(String[] args){

        RedShapeDecorator redShapeDecorator = new RedShapeDecorator(new Circle());
        redShapeDecorator.draw();

        RedShapeDecorator redShapeDecorator2 = new RedShapeDecorator(new Rectangle());
        redShapeDecorator2.draw();

    }
}


interface Shape{
    void draw();
}

class Circle implements Shape{

    @Override
    public void draw(){
        System.out.println("Circle draw");
    }
}

class Rectangle implements Shape{

    @Override
    public void draw(){
        System.out.println("Rectangle draw");
    }
}

class DecoratorShap implements Shape{

    protected Shape shape;
    public DecoratorShap(Shape shape){
        this.shape = shape;
    }

    @Override
    public void draw(){
        shape.draw();
    }
}

class RedShapeDecorator extends DecoratorShap{

    public RedShapeDecorator(Shape shape){
        super(shape);
    }

    @Override
    public void draw(){
        shape.draw();
        dealRedThing(shape);
    }

    public void dealRedThing(Shape shape){
        System.out.println("red shaper");
    }
}
