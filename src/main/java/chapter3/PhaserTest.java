package chapter3;

import java.util.concurrent.Phaser;

public class PhaserTest {
    public static void main(String[] args) {

        OtherPhaser phaser = new OtherPhaser(5);
        for (int i =0;i < 5;i++){
            Thread thread = new Thread(new PhaserRunner(phaser));
            thread.start();
        }


    }
}

class OtherPhaser extends Phaser{

    public OtherPhaser(int i){
        super(i);
    }

    @Override
    protected boolean onAdvance(int phase, int registeredParties) {

        System.out.println("on advance!");
        return true;
    }
}


class PhaserRunner  implements Runnable {

    private OtherPhaser phaser;

    PhaserRunner(OtherPhaser phaser){
        this.phaser = phaser;
    }

    public void run() {
        Thread thread = Thread.currentThread();
        System.out.println("before"+thread.getName()+":"+System.currentTimeMillis());
        phaser.arriveAndAwaitAdvance();
        System.out.println("after"+thread.getName()+":"+System.currentTimeMillis());
    }

}
