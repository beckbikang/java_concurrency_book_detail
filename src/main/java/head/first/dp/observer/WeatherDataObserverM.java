package head.first.dp.observer;

import java.util.Observable;
import java.util.Observer;

public class WeatherDataObserverM {

    public static void main(String[] args){
        WeatherData weatherData = new WeatherData();
        CurrentCondDisplay currentCondDisplay = new CurrentCondDisplay(weatherData);
        weatherData.setData(1,1.1f,1.2f);


    }
}

class WeatherData extends Observable{

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    private float temperature;
    private float humidity;
    private float pressure;


    public WeatherData(){}

    public void setData(float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measureChange();
    }

    public void measureChange(){
        setChanged();
        notifyObservers();
    }
}

interface Displayer{
    void display();
}

class CurrentCondDisplay implements Observer, Displayer{
    private float temperature;
    private float humidity;
    private float pressure;

    Observable observable;

    public CurrentCondDisplay(Observable o){
        this.observable = o;
        observable.addObserver(this);
    }

    public void update(Observable o, Object arg){
        if(o instanceof WeatherData){
            WeatherData weatherData = (WeatherData) o;
             this.temperature = weatherData.getTemperature();
             this.humidity = weatherData.getHumidity();
             this.pressure = weatherData.getPressure();
            display();
        }
    }
    public void display(){
        System.out.println("change temperature="+temperature+
            "humidity="+humidity+" pressure="+pressure
        );
    }
}
