import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class BankAccountEPTest {

    @BeforeMethod
    public void setup() {
        // Reset static state before each test
        BankAccount.resetState();
    }

    @Test
    public void testCheckBalanceValidAccount() {
        BankAccount.createAccount(100); // Create account with initial balance
        Object balance = BankAccount.checkBalance(1); // Check balance
        assertEquals(balance, 100, "Balance should be 100 for account 1.");
    }

    @Test
    public void testCheckBalanceNonExistentAccount() {
        Object result = BankAccount.checkBalance(999); // Non-existent account
        assertEquals(result, BankAccount.Status.ACCOUNT_NOT_FOUND, "Non-existent account should return ACCOUNT_NOT_FOUND.");
    }

    @Test
    public void testCheckBalanceWithEmptyAccounts() {
        Object result = BankAccount.checkBalance(1); // Check balance when no accounts exist
        assertEquals(result, BankAccount.Status.ACCOUNT_NOT_FOUND, "Empty accounts map should return ACCOUNT_NOT_FOUND.");
    }

    @Test
    public void testDepositValidAmount() {
        BankAccount.createAccount(100); // Create account with initial balance
        BankAccount.Status result = BankAccount.deposit(1, 50);
        assertEquals(result, BankAccount.Status.SUCCESS, "Deposit should succeed.");
    }

    @Test
    public void testDepositInvalidAmountZero() {
        BankAccount.createAccount(100);
        BankAccount.Status result = BankAccount.deposit(1, 0);
        assertEquals(result, BankAccount.Status.INVALID_INPUT, "Deposit of zero should be invalid.");
    }

    @Test
    public void testDepositInvalidAmountNegative() {
        BankAccount.createAccount(100);
        BankAccount.Status result = BankAccount.deposit(1, -10);
        assertEquals(result, BankAccount.Status.INVALID_INPUT, "Deposit of negative amount should be invalid.");
    }

    @Test
    public void testNonExistentAccountOperations() {
        BankAccount.Status depositResult = BankAccount.deposit(999, 50);
        assertEquals(depositResult, BankAccount.Status.ACCOUNT_NOT_FOUND, "Deposit to non-existent account should fail.");

        BankAccount.Status withdrawResult = BankAccount.withdraw(999, 50);
        assertEquals(withdrawResult, BankAccount.Status.ACCOUNT_NOT_FOUND, "Withdrawal from non-existent account should fail.");
    }

    @Test
    public void testWithdrawValidAmount() {
        BankAccount.createAccount(100); // Create account with initial balance
        BankAccount.Status result = BankAccount.withdraw(1, 50);
        assertEquals(result, BankAccount.Status.SUCCESS, "Withdrawal should succeed.");
    }

    @Test
    public void testWithdrawExactBalance() {
        BankAccount.createAccount(100);
        BankAccount.Status result = BankAccount.withdraw(1, 100); // Withdraw entire balance
        assertEquals(result, BankAccount.Status.SUCCESS, "Withdrawal of the exact balance should succeed.");
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        BankAccount.createAccount(100);
        BankAccount.Status result = BankAccount.withdraw(1, 150); // Withdraw more than balance
        assertEquals(result, BankAccount.Status.INSUFFICIENT_FUNDS, "Withdrawal with insufficient funds should fail.");
    }

    @Test
    public void testWithdrawInvalidAmountZero() {
        BankAccount.createAccount(100);
        BankAccount.Status result = BankAccount.withdraw(1, 0); // Zero withdrawal
        assertEquals(result, BankAccount.Status.INVALID_INPUT, "Withdrawal of zero should be invalid.");
    }

    @Test
    public void testWithdrawInvalidAmountNegative() {
        BankAccount.createAccount(100);
        BankAccount.Status result = BankAccount.withdraw(1, -50); // Negative withdrawal
        assertEquals(result, BankAccount.Status.INVALID_INPUT, "Withdrawal of negative amount should be invalid.");
    }

}
