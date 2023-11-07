package net.quentin;

public class TNumber implements Runnable {
    public void run() {
        for (int i = 1; i < 27; i++) {
            System.out.println(i);
        }
    }
}
