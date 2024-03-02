import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccountWithAtm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleBankAccountWithAtmTest {

    private final double FEE = 1.0;
    private final double INITIAL_BALANCE = 0;
    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void beforeEach() {
        accountHolder = new AccountHolder("Mario", "Rossi", 1);
        bankAccount = new SimpleBankAccountWithAtm(accountHolder, INITIAL_BALANCE);
    }

    @Test
    void testInitialBalance() {
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        final double amount = 100.0;
        bankAccount.deposit(accountHolder.getId(), amount);
        assertEquals(amount - FEE, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        final double amount = 100.0;
        final double other_amount = 50.0;
        final int other_account_id = 2;
        bankAccount.deposit(accountHolder.getId(), amount);
        bankAccount.deposit(other_account_id, other_amount);
        assertEquals(amount - FEE, bankAccount.getBalance());
    }

    @Test
    void testDepositBalanceNotSufficient() {
        final double amount = 0.5;
        bankAccount.deposit(accountHolder.getId(), amount);
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testDepositNegativeAmount() {
        final double amount = -10.0;
        bankAccount.deposit(accountHolder.getId(), amount);
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        final double depositedAmount = 100.0;
        final double withdrewAmount = 70.0;
        bankAccount.deposit(accountHolder.getId(), depositedAmount);
        bankAccount.withdraw(accountHolder.getId(), withdrewAmount);
        assertEquals(depositedAmount - withdrewAmount - 2 * FEE, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        final double depositedAmount = 100.0;
        final double withdrewAmount = 70.0;
        final int other_account_id = 2;
        bankAccount.deposit(accountHolder.getId(), depositedAmount);
        bankAccount.withdraw(other_account_id, withdrewAmount);
        assertEquals(depositedAmount - FEE, bankAccount.getBalance());
    }

    @Test
    void testWithdrawBalanceNotSufficient() {
        final double depositedAmount = 100.0;
        bankAccount.deposit(accountHolder.getId(), depositedAmount);
        bankAccount.withdraw(accountHolder.getId(), depositedAmount);
        assertEquals(depositedAmount - FEE, bankAccount.getBalance());
    }

    @Test
    void testWithdrawNegativeAmount() {
        final double amount = -10.0;
        bankAccount.withdraw(accountHolder.getId(), amount);
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }
}
