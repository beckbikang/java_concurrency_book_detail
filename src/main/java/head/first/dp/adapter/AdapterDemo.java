package head.first.dp.adapter;

/**
 * 电源的例子
 */
public class AdapterDemo {

    public static void main(String[] args){

        PowerPlate220 powerPlate220 = new ChinaPowerPlate();
        AdapterPowerPlate110 adapterPowerPlate110 = new AdapterPowerPlate110(powerPlate220);

        JapanHotel japanHotel = new JapanHotel();
        japanHotel.insertPower(adapterPowerPlate110);
    }
}

interface PowerPlate220 {
    void charge220();
}

class ChinaPowerPlate implements PowerPlate220{
    public void charge220(){
        System.out.println(" use 220v");
    }
}

/**
 * 当我去日本旅游，需要带一个适配器，在日本能够给手机充电
 */

class  JapanHotel {
    public void insertPower(PowerPlate110 powerPlate110){
        powerPlate110.charge110();
    }
}

/**
 * 我带了一个适配器, 把日本的110v转换成能冲110v
 */

class AdapterPowerPlate110 implements PowerPlate110{

    PowerPlate220 powerPlate220;
    public AdapterPowerPlate110(PowerPlate220 powerPlate220){
        this.powerPlate220 = powerPlate220;
    }

    public void charge110(){
        powerPlate220.charge220();
    }
}



interface PowerPlate110 {
    void charge110();
}

class JapanPowerPlate implements PowerPlate110{
    public void charge110(){
        System.out.println(" use 110v");
    }
}

