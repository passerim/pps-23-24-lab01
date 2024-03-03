import example.model.AccountHolder;
import example.model.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

abstract class BankAccountTest {

    private final double INITIAL_BALANCE = 1;
    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    protected double getFee() {
        return 0;
    }

    abstract BankAccount createBankAccount(final AccountHolder accountHolder, final double initialBalance);

    @BeforeEach
    void beforeEach() {
        final int accountHolderId = 1;
        final String accountHolderName = "Mario";
        final String accountHolderSurname = "Rossi";
        accountHolder = new AccountHolder(accountHolderName, accountHolderSurname, accountHolderId);
        bankAccount = createBankAccount(accountHolder, INITIAL_BALANCE);
    }

    @Test
    void testInitialBalance() {
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        final double amount = 100.0;
        bankAccount.deposit(accountHolder.getId(), amount);
        assertEquals(INITIAL_BALANCE + amount - getFee(), bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        final double amount = 100.0;
        final double otherAmount = 50.0;
        final int otherAccountId = 2;
        bankAccount.deposit(accountHolder.getId(), amount);
        bankAccount.deposit(otherAccountId, otherAmount);
        assertEquals(INITIAL_BALANCE + amount - getFee(), bankAccount.getBalance());
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
        assertEquals(INITIAL_BALANCE + depositedAmount - withdrewAmount - 2 * getFee(), bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        final double depositedAmount = 100.0;
        final double withdrewAmount = 70.0;
        final int otherAccountId = 2;
        bankAccount.deposit(accountHolder.getId(), depositedAmount);
        bankAccount.withdraw(otherAccountId, withdrewAmount);
        assertEquals(INITIAL_BALANCE + depositedAmount - getFee(), bankAccount.getBalance());
    }

    @Test
    void testWithdrawBalanceNotSufficient() {
        final double depositedAmount = 100.0;
        bankAccount.deposit(accountHolder.getId(), depositedAmount);
        bankAccount.withdraw(accountHolder.getId(), INITIAL_BALANCE + depositedAmount + 1);
        assertEquals(INITIAL_BALANCE + depositedAmount - getFee(), bankAccount.getBalance());
    }

    @Test
    void testWithdrawNegativeAmount() {
        final double amount = -10.0;
        bankAccount.withdraw(accountHolder.getId(), amount);
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }
}
