package net.quentin;

import java.util.Random;

public class TName implements Runnable {

    private Random rand = new Random();
    private String name;

    public TName(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(rand.nextInt(200));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(this.name);
        }
    }
}
