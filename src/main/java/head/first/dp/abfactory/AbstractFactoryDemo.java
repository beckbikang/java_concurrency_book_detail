package head.first.dp.abfactory;

/**
 * @ Author     ：bikang1@staff.weibo.com
 * @ Date       ：Created in 下午5:09 2018/11/10
 * @ Description：
 * @ Modified By：
 */
public class AbstractFactoryDemo {


    public static void main(String[] args){

        AbstractFactory colorFactory = FactoryProduce.getAbstractFactory(
                FactoryProduce.TYPE_COLOR_FACTORY
        );
        colorFactory.getColor(ColorFactory.TYPE_GREEN).fill();
        colorFactory.getColor(ColorFactory.TYPE_RED).fill();


        AbstractFactory shapFactory = FactoryProduce.getAbstractFactory(
                FactoryProduce.TYPE_SHAP_FACTORY
        );
        shapFactory.getShap(ShapFactory.TYPE_CYCLE).draw();
        shapFactory.getShap(ShapFactory.TYPE_RECTANGLE).draw();

    }
}

abstract class AbstractFactory{
    abstract Shap getShap(String shapName);
    abstract Color getColor(String colorName);
}

class ColorFactory extends AbstractFactory{


    public static final String TYPE_RED = "red";
    public static final String TYPE_GREEN = "green";

    public Shap getShap(String shapName){
        return null;
    }


    public Color getColor(String colorName){
        if(colorName == null || colorName.equals("")){
            return null;
        }
        if(colorName.equalsIgnoreCase(TYPE_RED)){
            return new Red();
        }else if(colorName.equalsIgnoreCase(TYPE_GREEN)){
            return new Green();
        }
        return null;
    }
}

class ShapFactory extends AbstractFactory{

    public static final String TYPE_CYCLE = "cycle";
    public static final String TYPE_RECTANGLE = "rectangle";


    public Shap getShap(String shapName){
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

    public Color getColor(String colorName){
        return null;
    }

}


class FactoryProduce {

    public static final String TYPE_SHAP_FACTORY = "ShapFactory";
    public static final String TYPE_COLOR_FACTORY = "ColorFactory";

    public static AbstractFactory getAbstractFactory(String factoryName){

        if(factoryName.equalsIgnoreCase(TYPE_SHAP_FACTORY)){
            return new ShapFactory();
        }else if(factoryName.equalsIgnoreCase(TYPE_COLOR_FACTORY)){
            return new ColorFactory();
        }

        return null;
    }

}



interface Color{
    void fill();
}

class Red implements Color{

    @Override
    public void fill(){
        System.out.println("Red fill");
    }
}

class Green implements Color{

    @Override
    public void fill(){
        System.out.println("Green fill");
    }
}


interface Shap{
    void draw();
}

class Rectangle implements Shap {

    @Override
    public void draw(){
        System.out.println("Rectangle draw");
    }

}

class Cycle implements Shap {

    @Override
    public void draw(){
        System.out.println("Cycle draw");
    }
}