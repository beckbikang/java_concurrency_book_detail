package Java.logic.part2;

public class ClassIh {

    public static void main(String args[]){
        tt();
    }

    public static void tt(){
        Coder a = new Coder(100,"tt");
        a.showSale();
        Employe b = a;
        b.showSale();

    }
}


class Employe {
    protected float sale;
    Employe(float s){
        sale = s;
    }

    Employe(){}

    public void showSale(){
        System.out.println("sale:"+sale);
    }
}

class Coder extends  Employe{

    private String name;

    Coder(float s, String n){
        super(s);
        name = n;
    }

    public void setName(String a){
        this.name = a;
    }

    public String getName(){
        return name;
    }

}

