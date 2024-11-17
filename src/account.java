
public class account {

    private String name;
    private double balance;
    private String confirmation;

    public account(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public String getConfirmation() {

        switch (confirmation) {
            case "SUCCESS":
                confirmation = "SUCCESS";
                break;
            case "INSUFFICIENT_FUNDS":
                confirmation = "INSUFFICIENT_FUNDS";
                break;
            case "ACCOUNT_NOT_FOUND":
                confirmation = "ACCOUNT_NOT_FOUND";
                break;
            default:
                confirmation = "INVALID_INPUT";
                break;
        }
        return confirmation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void transfer(account other, int amount) {
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
