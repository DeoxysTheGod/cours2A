package net.quentin.romeoetjuliette;

public class Account {
    private int balance;

    public Account () {
        this.balance = 100;
    }

    public void withdraw(int amount) {
        this.balance -= amount;
    }

    public int getBalance() {
        return balance;
    }
}
