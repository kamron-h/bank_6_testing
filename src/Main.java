//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
// Import account from src/account.java

//import src.account.java;

public class Main {
    public static void main(String[] args) {
        account a = new account("Alice", 1000);
        account b = new account("Bob", 2000);

        System.out.println(a);
        System.out.println(b);

        a.deposit(500);
        b.withdraw(200);

        System.out.println(a);
        System.out.println(b);

        a.transfer(b, 300);

        System.out.println(a);
    }
}
