package head.first.dp.flyweight;


import java.util.HashMap;

public class FlyweightDemo {

    public static void main(String[] args){

        ShapFactory shapFactory = new ShapFactory();

        Shap shap1 = shapFactory.makeShaper("AShap");
        Shap shap2 = shapFactory.makeShaper("AShap");
        Shap shap3 = shapFactory.makeShaper("AShap");

        shap1.draw();shap2.draw();shap3.draw();

        System.out.println(shap1+ " "+shap2+" "+shap3);


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

class ShapFactory {
    private HashMap<String,Shap> map = new HashMap<>();
    public ShapFactory(){}

    public Shap makeShaper(String name){

        if(name.equals("AShap")){
            Shap shap = map.get("AShap");
            if(shap == null){
                shap = new AShap();
                map.put("AShap", shap);
            }
            return shap;
        }
        return null;
    }
}