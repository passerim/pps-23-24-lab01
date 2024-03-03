package example.model;

public class SimpleBankAccountWithAtm implements BankAccount {

    private final double FEE = 1.0;
    private final SimpleBankAccount bankAccount;

    public SimpleBankAccountWithAtm(AccountHolder holder, double balance) {
        bankAccount = new SimpleBankAccount(holder, balance);
    }

    @Override
    public AccountHolder getHolder() {
        return bankAccount.getHolder();
    }

    @Override
    public double getBalance() {
        return bankAccount.getBalance();
    }

    @Override
    public void deposit(int userID, double amount) {
        bankAccount.deposit(userID, amount - FEE);
    }

    @Override
    public void withdraw(int userID, double amount) {
        bankAccount.withdraw(userID, amount + FEE);
    }
}
