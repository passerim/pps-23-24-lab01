import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest extends BankAccountTest {

    @Override
    BankAccount createBankAccount(final AccountHolder accountHolder, final double initialBalance) {
        return new SimpleBankAccount(accountHolder, initialBalance);
    }
}
