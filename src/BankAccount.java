import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankAccount {

    private int accountNumber;
    private int balance;

    // Map to store accounts
    private static Map<Integer, BankAccount> accounts = new HashMap<>();
    private static int nextAccountNumber = 1; // Auto-incrementing account numbers

    // Reset method for testing purposes
    public static void resetState() {
        accounts.clear();
        nextAccountNumber = 1;
    }

    // Enum for operation statuses
    public enum Status {
        SUCCESS,
        INSUFFICIENT_FUNDS,
        ACCOUNT_NOT_FOUND,
        INVALID_INPUT
    }

    // Constructor
    private BankAccount(int accountNumber, int initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    // Static methods

    public static Status createAccount(int initialBalance) {
        if (initialBalance < 0) {
            return Status.INVALID_INPUT;
        }
        int accountNumber = nextAccountNumber++;
        accounts.put(accountNumber, new BankAccount(accountNumber, initialBalance));
        System.out.println("Account created successfully. Account Number: " + accountNumber);
        return Status.SUCCESS;
    }

    public static Status deposit(int accountNumber, int depositAmount) {
        if (depositAmount <= 0) {
            return Status.INVALID_INPUT;
        }
        BankAccount account = accounts.get(accountNumber);
        if (account == null) {
            return Status.ACCOUNT_NOT_FOUND;
        }
        account.balance += depositAmount;
        return Status.SUCCESS;
    }

    public static Status withdraw(int accountNumber, int withdrawAmount) {
        if (withdrawAmount <= 0) {
            return Status.INVALID_INPUT;
        }
        BankAccount account = accounts.get(accountNumber);
        if (account == null) {
            return Status.ACCOUNT_NOT_FOUND;
        }
        if (account.balance < withdrawAmount) {
            return Status.INSUFFICIENT_FUNDS;
        }
        account.balance -= withdrawAmount;
        return Status.SUCCESS;
    }

    public static Object checkBalance(int accountNumber) {
        BankAccount account = accounts.get(accountNumber);
        if (account == null) {
            return Status.ACCOUNT_NOT_FOUND;
        }
        return account.balance;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Bank Account Management Program!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter initial balance: ");
                    int initialBalance = scanner.nextInt();
                    System.out.println(createAccount(initialBalance));
                    break;
                case 2:
                    System.out.print("Enter account number: ");
                    int depositAccountNumber = scanner.nextInt();
                    System.out.print("Enter deposit amount: ");
                    int depositAmount = scanner.nextInt();
                    System.out.println(deposit(depositAccountNumber, depositAmount));
                    break;
                case 3:
                    System.out.print("Enter account number: ");
                    int withdrawAccountNumber = scanner.nextInt();
                    System.out.print("Enter withdrawal amount: ");
                    int withdrawAmount = scanner.nextInt();
                    System.out.println(withdraw(withdrawAccountNumber, withdrawAmount));
                    break;
                case 4:
                    System.out.print("Enter account number: ");
                    int balanceAccountNumber = scanner.nextInt();
                    Object result = checkBalance(balanceAccountNumber);
                    if (result instanceof Integer) {
                        System.out.println("Current balance: " + result);
                    } else {
                        System.out.println(result);
                    }
                    break;
                case 5:
                    System.out.println("Thank you for using the Bank Account Management Program!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
