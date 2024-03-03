import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccountWithAtm;

class SimpleBankAccountWithAtmTest extends BankAccountTest {

    @Override
    protected double getFee() {
        return 1.0;
    }

    @Override
    BankAccount createBankAccount(final AccountHolder accountHolder, final double initialBalance) {
        return new SimpleBankAccountWithAtm(accountHolder, initialBalance);
    }
}
