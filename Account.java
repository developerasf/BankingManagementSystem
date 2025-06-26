
import java.util.ArrayList;

public abstract class Account {

    private String accountNumber;
    private String pin;
    private String name;
    private String address;
    private String fathersName;
    private int age;
    private String religion;
    private String gender;
    private String phoneNumber;
    private String nidNumber;
    private double balance;
    private ArrayList<String> transactionHistory;

    public Account(String accountNumber, String pin, String name, String address, String fathersName,
            int age, String religion, String gender, String phoneNumber, String nidNumber, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.name = name;
        this.address = address;
        this.fathersName = fathersName;
        this.age = age;
        this.religion = religion;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.nidNumber = nidNumber;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    public abstract String getAccountType();
}
