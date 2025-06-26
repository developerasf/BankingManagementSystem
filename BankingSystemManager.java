
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class BankingSystemManager {

    private Account[] accounts = new Account[1000];
    private int accountCount = 0;

    public void addAccount(Account account) {
        if (accountCount < accounts.length) {
            accounts[accountCount++] = account;
        }
    }

    public Account findAccount(String accountNumber, String pin) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i] != null && accounts[i].getAccountNumber().equals(accountNumber) && accounts[i].getPin().equals(pin)) {
                return accounts[i];
            }
        }
        return null;
    }

    public Account findAccountByNumber(String accountNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i] != null && accounts[i].getAccountNumber().equals(accountNumber)) {
                return accounts[i];
            }
        }
        return null;
    }

    public void deleteAccount(String accountNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i] != null && accounts[i].getAccountNumber().equals(accountNumber)) {
                accounts[i] = accounts[--accountCount];
                break;
            }
        }
    }

    public String[] getAllAccounts() {
        String[] allAccounts = new String[accountCount];
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i] != null) {
                allAccounts[i] = accounts[i].getAccountNumber() + ": " + accounts[i].getName() + " - $" + String.format("%.2f", accounts[i].getBalance());
            }
        }
        return allAccounts;
    }

    public void saveToFile() {
        try (FileWriter writer = new FileWriter("accounts.txt")) {
            for (int i = 0; i < accountCount; i++) {
                if (accounts[i] != null) {
                    writer.write(accounts[i].getAccountNumber() + ";" + accounts[i].getPin() + ";" + accounts[i].getName() + ";"
                            + accounts[i].getBalance() + ";" + accounts[i].getAccountType() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadFromFile() {
        try (Scanner scanner = new Scanner(new File("accounts.txt"))) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(";");
                String accountNumber = data[0];
                String pin = data[1];
                String name = data[2];
                double balance = Double.parseDouble(data[3]);
                String accountType = data[4];
                Account account;
                if (accountType.equals("SavingsAccount")) {
                    account = new SavingsAccount(accountNumber, pin, name, "", "", 0, "", "", "", "", balance, 0.0);
                } else if (accountType.equals("FixedAccount")) {
                    account = new FixedAccount(accountNumber, pin, name, "", "", 0, "", "", "", "", balance, 0);
                } else {
                    account = new CurrentAccount(accountNumber, pin, name, "", "", 0, "", "", "", "", balance);
                }
                addAccount(account);
            }
        } catch (FileNotFoundException e) {

        }
    }

    public String generateAccountNumber() {
        Random rand = new Random();
        String accountNumber;
        do {
            accountNumber = String.format("%010d", rand.nextInt(1000000000));
        } while (findAccountByNumber(accountNumber) != null);
        return accountNumber;
    }

    public String generatePin() {
        Random rand = new Random();
        return String.format("%04d", rand.nextInt(10000));
    }

    public int getAccountCount() {
        return accountCount;
    }

    public Account getAccount(int i) {
        if (i >= 0 && i < accountCount && accounts[i] != null) {
            return accounts[i];
        }
        return null;
    }

}
