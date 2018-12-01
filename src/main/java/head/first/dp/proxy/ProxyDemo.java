package head.first.dp.proxy;

public class ProxyDemo {

    public static void main(String[] args){

        Transer transer = new Transer(new Chinese());
        transer.say();
    }
}

interface Speaker {
    void say();
}

class Chinese implements Speaker{
    @Override
    public void say(){
        System.out.println("china say");
    }
}

class Transer implements Speaker{

    private Speaker speaker;

    public Transer(Speaker speaker){
        this.speaker = speaker;
    }

    @Override
    public void say(){
        speaker.say();
    }
}