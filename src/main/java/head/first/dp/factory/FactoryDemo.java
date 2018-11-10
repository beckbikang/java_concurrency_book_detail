package head.first.dp.factory;

public class FactoryDemo {


    public static void main(String[] args){

        ShapFactory shapFactory = new ShapFactory();
        Shap shap1 = shapFactory.makeShap(ShapFactory.TYPE_CYCLE);
        Shap shap2 = shapFactory.makeShap(ShapFactory.TYPE_RECTANGLE);

        shap1.draw();
        shap2.draw();
    }
}

interface Shap{
    void draw();
}

class Rectangle implements Shap{

    @Override
    public void draw(){
        System.out.println("Rectangle draw");
    }

}

class Cycle implements Shap{

    @Override
    public void draw(){
        System.out.println("Cycle draw");
    }
}

class ShapFactory {

    public static final String TYPE_CYCLE = "cycle";
    public static final String TYPE_RECTANGLE = "rectangle";


    public Shap makeShap(String shapName){
        if(shapName == null || shapName.equals("")){
            return null;
        }
        if(shapName.equalsIgnoreCase(TYPE_CYCLE)){
            return new Cycle();
        }else if(shapName.equalsIgnoreCase(TYPE_RECTANGLE)){
            return new Rectangle();
        }
        return null;
    }

}