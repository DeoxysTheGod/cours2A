package net.quentin.romeoetjuliette;

public class Main {
    public static void main(String[] args) {
        Account compte = new Account();
        JulietteAndRomeoJob romeoJob =  new JulietteAndRomeoJob("Roméo", compte,500);
        JulietteAndRomeoJob julietteJob = new JulietteAndRomeoJob("Juliette", compte,499);

        Thread romeoThread = new Thread(romeoJob);
        Thread julietteThread = new Thread(julietteJob);

        romeoThread.start();
        julietteThread.start();

        try {
            romeoThread.join();
            julietteThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(romeoJob.getName() + " a retiré un total de " + romeoJob.getRetraitTotal() + "€");
        System.out.println(julietteJob.getName() + " a retiré un total de " + julietteJob.getRetraitTotal() + "€");
    }
}
