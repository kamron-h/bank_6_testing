import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class BankAccountTestBVA {

    @BeforeMethod
    public void setup() {
        // Reset static state before each test
        BankAccount.resetState();
    }

    // Tests for Account Creation (BVA for initial balances)

    @Test
    public void testCreateAccountMinimumBalanceZero() {
        BankAccount.Status result = BankAccount.createAccount(0);
        assertEquals(result, BankAccount.Status.SUCCESS, "Account creation with balance 0 should succeed.");
    }

    @Test
    public void testCreateAccountMinimumPositiveBalance() {
        BankAccount.Status result = BankAccount.createAccount(1);
        assertEquals(result, BankAccount.Status.SUCCESS, "Account creation with balance 1 should succeed.");
    }

    @Test
    public void testCreateAccountNegativeBalance() {
        BankAccount.Status result = BankAccount.createAccount(-1);
        assertEquals(result, BankAccount.Status.INVALID_INPUT, "Account creation with negative balance should fail.");
    }

    // Tests for Deposit (BVA for deposit amounts)

    @Test
    public void testDepositMinimumValidAmount() {
        BankAccount.createAccount(100);
        BankAccount.Status result = BankAccount.deposit(1, 1);
        assertEquals(result, BankAccount.Status.SUCCESS, "Deposit of 1 should succeed.");
    }

    @Test
    public void testDepositInvalidAmountZero() {
        BankAccount.createAccount(100);
        BankAccount.Status result = BankAccount.deposit(1, 0);
        assertEquals(result, BankAccount.Status.INVALID_INPUT, "Deposit of 0 should fail.");
    }

    @Test
    public void testDepositInvalidAmountNegative() {
        BankAccount.createAccount(100);
        BankAccount.Status result = BankAccount.deposit(1, -1);
        assertEquals(result, BankAccount.Status.INVALID_INPUT, "Deposit of -1 should fail.");
    }

    // Tests for Withdrawal (BVA for withdrawal amounts and balances)

    @Test
    public void testWithdrawMinimumValidAmount() {
        BankAccount.createAccount(100);
        BankAccount.Status result = BankAccount.withdraw(1, 1);
        assertEquals(result, BankAccount.Status.SUCCESS, "Withdrawal of 1 should succeed.");
    }

    @Test
    public void testWithdrawExactBalance() {
        BankAccount.createAccount(100);
        BankAccount.Status result = BankAccount.withdraw(1, 100);
        assertEquals(result, BankAccount.Status.SUCCESS, "Withdrawal of exact balance should succeed.");
    }

    @Test
    public void testWithdrawExceedingBalance() {
        BankAccount.createAccount(100);
        BankAccount.Status result = BankAccount.withdraw(1, 101);
        assertEquals(result, BankAccount.Status.INSUFFICIENT_FUNDS, "Withdrawal exceeding balance should fail.");
    }

    @Test
    public void testWithdrawInvalidAmountZero() {
        BankAccount.createAccount(100);
        BankAccount.Status result = BankAccount.withdraw(1, 0);
        assertEquals(result, BankAccount.Status.INVALID_INPUT, "Withdrawal of 0 should fail.");
    }

    @Test
    public void testWithdrawInvalidAmountNegative() {
        BankAccount.createAccount(100);
        BankAccount.Status result = BankAccount.withdraw(1, -1);
        assertEquals(result, BankAccount.Status.INVALID_INPUT, "Withdrawal of -1 should fail.");
    }

    @Test
    public void testCheckBalanceWithEmptyAccounts() {
        // Boundary Case: Empty accounts map
        Object result = BankAccount.checkBalance(1); // Check balance when no accounts exist
        assertEquals(result, BankAccount.Status.ACCOUNT_NOT_FOUND, "Empty accounts map should return ACCOUNT_NOT_FOUND.");
    }

    @Test
    public void testCheckBalanceWithFirstAccount() {
        // Boundary Case: First valid account
        BankAccount.createAccount(200); // Create first account with balance 200
        Object balance = BankAccount.checkBalance(1); // Check balance for the first account
        assertEquals(balance, 200, "Balance for the first valid account should be returned correctly.");
    }

    @Test
    public void testCheckBalanceWithNonExistentHighAccount() {
        // Boundary Case: Non-existent high account number
        BankAccount.createAccount(300); // Create only one account
        Object result = BankAccount.checkBalance(999); // Check balance for a high non-existent account number
        assertEquals(result, BankAccount.Status.ACCOUNT_NOT_FOUND, "Non-existent high account should return ACCOUNT_NOT_FOUND.");
    }

}
