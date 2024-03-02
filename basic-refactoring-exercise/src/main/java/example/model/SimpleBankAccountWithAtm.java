package example.model;

public class SimpleBankAccountWithAtm implements BankAccount {

    private final double FEE = 1.0;
    private final AccountHolder holder;
    private double balance;

    public SimpleBankAccountWithAtm(final AccountHolder holder, final double balance) {
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
        if (checkAmount(amount) && checkUser(userID) && isDepositAllowed(amount)){
            this.balance = this.balance + amount - FEE;
        }
    }

    @Override
    public void withdraw(final int userID, final double amount) {
        if (checkAmount(amount) && checkUser(userID) && isWithdrawAllowed(amount)) {
            this.balance = this.balance - amount - FEE;
        }
    }

    private boolean checkAmount(final double amount) {
        return  amount > 0;
    }

    private boolean checkUser(final int id) {
        return this.holder.getId() == id;
    }

    private boolean isDepositAllowed(final double amount) {
        return (this.balance + amount > FEE);
    }

    private boolean isWithdrawAllowed(final double amount) {
        return (this.balance > amount + FEE);
    }
}
