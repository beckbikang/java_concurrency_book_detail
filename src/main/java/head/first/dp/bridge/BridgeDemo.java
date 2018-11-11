package head.first.dp.bridge;


public class BridgeDemo {

    public static void main(String[] args){

        Line line = new Line(new GreenDraw());
        line.draw();

        Line line1 = new Line(new RedDraw());
        line1.draw();
    }
}


interface DrawIt{
    void drawShap();
}

class GreenDraw implements DrawIt{
    public void drawShap(){
        System.out.println("green draw");
    }
}
class RedDraw implements DrawIt{
    public void drawShap(){
        System.out.println("red draw");
    }
}

abstract class Shap{
    protected DrawIt drawIt;
    public Shap(DrawIt drawIt){
        this.drawIt = drawIt;
    }
    abstract void draw();
}

class Line extends Shap{
    public Line(DrawIt drawIt){
        super(drawIt);
    }
    public void draw(){
        drawIt.drawShap();
    }
}


