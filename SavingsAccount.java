
public class SavingsAccount extends Account {

    private double interestRate;

    public SavingsAccount(String accountNumber, String pin, String name, String address, String fathersName,
            int age, String religion, String gender, String phoneNumber, String nidNumber, double balance, double interestRate) {
        super(accountNumber, pin, name, address, fathersName, age, religion, gender, phoneNumber, nidNumber, balance);
        this.interestRate = interestRate;
    }

    public String getAccountType() {
        return "SavingsAccount";
    }
}
