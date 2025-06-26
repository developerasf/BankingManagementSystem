
public class FixedAccount extends Account {

    private int tenureYears;

    public FixedAccount(String accountNumber, String pin, String name, String address, String fathersName,
            int age, String religion, String gender, String phoneNumber, String nidNumber, double balance, int tenureYears) {
        super(accountNumber, pin, name, address, fathersName, age, religion, gender, phoneNumber, nidNumber, balance);
        this.tenureYears = tenureYears;
    }

    public String getAccountType() {
        return "FixedAccount";
    }
}
