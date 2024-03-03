package example.model;

/**
 * This class represent a particular instance of a BankAccount.
 * In particular, a Simple Bank Account allows always the deposit
 * while the withdrawal is allowed only if the balance greater or equal the withdrawal amount
 */
public class SimpleBankAccount implements BankAccount {

    private final AccountHolder holder;
    private double balance;

    public SimpleBankAccount(final AccountHolder holder, final double balance) {
        this.holder = holder;
        this.balance = balance;
    }

    @Override
    public AccountHolder getHolder() {
        return this.holder;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(final int userID, final double amount) {
        if (checkAmount(amount) && checkUser(userID) && isDepositAllowed(amount)) {
            this.balance = this.balance + amount;
        }
    }

    @Override
    public void withdraw(final int userID, final double amount) {
        if (checkAmount(amount) && checkUser(userID) && isWithdrawAllowed(amount)) {
            this.balance = this.balance - amount;
        }
    }

    private boolean checkUser(final int id) {
        return this.holder.getId() == id;
    }

    private boolean checkAmount(final double amount) {
        return amount > 0;
    }

    private boolean isDepositAllowed(final double amount) {
        return this.balance + amount >= 0;
    }

    private boolean isWithdrawAllowed(final double amount) {
        return this.balance - amount >= 0;
    }
}
