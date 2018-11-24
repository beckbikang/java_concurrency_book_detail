package head.first.dp.facade;


public class FacadeDemo {

    public static void main(String[] args){
        AShapMaker aShapMaker = new AShapMaker();
        aShapMaker.AShapDraw();
    }
}


interface Shap{
    void draw();
}

class AShap implements Shap{
    @Override
    public void draw(){
        System.out.println("AShap draw");
    }
}

class AShapMaker {


    public void AShapDraw(){
        AShap aShap = new AShap();
        aShap.draw();
    }
}