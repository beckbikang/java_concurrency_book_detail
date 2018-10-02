package java7.concurrency.chapter3;

import java.util.concurrent.CyclicBarrier;

public class CyclicTestNet {

    static CyclicBarrier c = new CyclicBarrier(2, new A());

    public static void main(String[] args) {
        new Thread(new Runnable() {

            public void run() {
                try {
                    c.await();
                } catch (Exception e) {

                }
                System.out.println(1);
            }
        }).start();

        try {
            c.await();
        } catch (Exception e) {

        }
        System.out.println(2);
    }

    static class A implements Runnable {
        public void run() {
            System.out.println(3);
        }

    }

}
