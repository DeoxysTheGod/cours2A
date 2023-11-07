package net.quentin;

public class Main {
    public static void main(String[] args) {
        TName job = new TName("Jack");
        TName job1 = new TName("Pierre");
        TName job2 = new TName("Paul");

        Thread t = new Thread(job);
        Thread t1 = new Thread(job1);
        Thread t2 = new Thread(job2);

        t.start();
        t1.start();
        t2.start();
        try {
            t.join();
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("FIN");
    }
}