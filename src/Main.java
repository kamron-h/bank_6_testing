import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Simple Bank Account Management Program!");

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. Check account balance");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine()); // Input validation for integers
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                continue;
            }

            switch (choice) {
                case 1: // Create Account
                    System.out.print("Enter the initial balance for the account: ");
                    int initialBalance;
                    try {
                        initialBalance = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        continue;
                    }

                    BankAccount.Status createStatus = BankAccount.createAccount(initialBalance);
                    if (createStatus == BankAccount.Status.SUCCESS) {
                        System.out.println("Account created successfully!");
                    } else {
                        System.out.println("Failed to create account. Reason: INVALID_INPUT");
                    }
                    break;

                case 2: // Deposit Money
                    System.out.print("Enter the account number: ");
                    int depositAccountNumber;
                    try {
                        depositAccountNumber = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid account number. Please enter a valid integer.");
                        continue;
                    }

                    System.out.print("Enter the deposit amount: ");
                    int depositAmount;
                    try {
                        depositAmount = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount. Please enter a valid integer.");
                        continue;
                    }

                    BankAccount.Status depositStatus = BankAccount.deposit(depositAccountNumber, depositAmount);
                    System.out.println("Deposit status: " + depositStatus);
                    break;

                case 3: // Withdraw Money
                    System.out.print("Enter the account number: ");
                    int withdrawAccountNumber;
                    try {
                        withdrawAccountNumber = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid account number. Please enter a valid integer.");
                        continue;
                    }

                    System.out.print("Enter the withdrawal amount: ");
                    int withdrawAmount;
                    try {
                        withdrawAmount = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount. Please enter a valid integer.");
                        continue;
                    }

                    BankAccount.Status withdrawStatus = BankAccount.withdraw(withdrawAccountNumber, withdrawAmount);
                    System.out.println("Withdrawal status: " + withdrawStatus);
                    break;

                case 4: // Check Balance
                    System.out.print("Enter the account number: ");
                    int balanceAccountNumber;
                    try {
                        balanceAccountNumber = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid account number. Please enter a valid integer.");
                        continue;
                    }

                    Object balanceResult = BankAccount.checkBalance(balanceAccountNumber);
                    if (balanceResult instanceof Integer) {
                        System.out.println("Account balance: " + balanceResult);
                    } else {
                        System.out.println("Error: " + balanceResult);
                    }
                    break;

                case 5: // Exit Program
                    System.out.println("Thank you for using the Simple Bank Account Management Program. Goodbye!");
                    scanner.close();
                    return;

                default: // Invalid Choice
                    System.out.println("Invalid choice. Please choose a number between 1 and 5.");
            }
        }
    }
}
