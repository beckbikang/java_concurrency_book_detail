package Java.logic.part3;

public class CollectTest {
    public static void main(String[] args) throws Exception{

        tt();
    }

    public static void tt() throws Exception{

        Point<String, Integer> point = new Point<>();
        point.setA("a");
        point.setB(12);
        point.show();

    }
}

class Point<X,Y>{
    private X a;
    private Y b;


    public  void show(){
        System.out.println("X:"+a+" Y:"+b);
    }

    public X getA() {
        return a;
    }

    public void setA(X a) {
        this.a = a;
    }

    public Y getB() {
        return b;
    }

    public void setB(Y b) {
        this.b = b;
    }
}
