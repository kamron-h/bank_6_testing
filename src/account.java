
public class account {

    private String name;
    private double balance;

    public account(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void transfer(account other, double amount) {
        withdraw(amount);
        other.deposit(amount);
    }

    public String toString() {
        return "Account name: " + name + ", balance: " + balance;
    }

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
        System.out.println(b);
    }
}
