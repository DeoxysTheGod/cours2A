package net.quentin;

public class TAlphabet implements Runnable {

    public void run() {
        for (char c = 'a'; c <= 'z'; c++) {
            System.out.println(c);
        }
    }
}
