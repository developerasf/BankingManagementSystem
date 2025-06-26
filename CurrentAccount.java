
public class CurrentAccount extends Account {

    public CurrentAccount(String accountNumber, String pin, String name, String address, String fathersName,
            int age, String religion, String gender, String phoneNumber, String nidNumber, double balance) {
        super(accountNumber, pin, name, address, fathersName, age, religion, gender, phoneNumber, nidNumber, balance);
    }

    public String getAccountType() {
        return "CurrentAccount";
    }
}
