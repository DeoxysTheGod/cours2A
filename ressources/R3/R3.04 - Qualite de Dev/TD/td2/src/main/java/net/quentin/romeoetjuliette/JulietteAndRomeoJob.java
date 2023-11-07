package net.quentin.romeoetjuliette;

public class JulietteAndRomeoJob implements Runnable {

    private String name;
    private Account account;
    private int retraitTotal;
    private int timeWait;

    public JulietteAndRomeoJob(String name, Account account, int timeWait) {
        this.name = name;
        this.account = account;
        this.retraitTotal = 0;
        this.timeWait = timeWait;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            doWithdraw(10);
        }
    }

    public void doWithdraw(int amount) {
        System.out.println("Début transaction " + this.name);
        System.out.println(account.getBalance());

        if (account.getBalance() - amount >= 0) {
            try {
                Thread.sleep(timeWait);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            account.withdraw(amount);
            retraitTotal += amount;
            System.out.println("Retrait de " + amount + "€ effectué par " + this.name);
        } else {
            System.out.println("Il ne semble pas y avoir assez d'argent: vous avez " + account.getBalance());
        }
    }

    public int getRetraitTotal() {
        return retraitTotal;
    }

    public String getName() {
        return name;
    }
}
