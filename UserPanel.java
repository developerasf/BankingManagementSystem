
import java.awt.*;
import javax.swing.*;

public class UserPanel extends JFrame {

    private JLabel balanceLabel;
    private JButton depositButton, withdrawButton, transferButton, historyButton, logoutButton;
    private BankingSystemManager bank;
    private Account account;

    public UserPanel(BankingSystemManager bank, Account account) {
        this.bank = bank;
        this.account = account;
        setTitle("Banking Management System - User");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(0x1E3A8A));

        balanceLabel = new JLabel("Balance: $" + String.format("%.2f", account.getBalance()));
        balanceLabel.setBounds(150, 20, 200, 30);
        balanceLabel.setForeground(Color.WHITE);
        add(balanceLabel);

        String[] buttons = {"Deposit", "Withdraw", "Transfer", "Transaction History", "Logout"};
        int y = 70;
        for (String btnText : buttons) {
            JButton button = new JButton(btnText);
            button.setBounds(100, y, 200, 40);
            button.setBackground(new Color(0xfbbf24));
            button.addActionListener(e -> {
                if (btnText.equals("Deposit")) {
                    deposit();
                } else if (btnText.equals("Withdraw")) {
                    withdraw();
                } else if (btnText.equals("Transfer")) {
                    transfer();
                } else if (btnText.equals("Transaction History")) {
                    showHistory();
                } else if (btnText.equals("Logout")) {
                    dispose();
                    new LoginPanel();
                }
            });
            add(button);
            y += 60;
        }

        setVisible(true);
    }

    private void deposit() {
        String amountStr = JOptionPane.showInputDialog(this, "Enter deposit amount:");
        try {
            double amount = Double.parseDouble(amountStr);
            if (amount > 0) {
                account.setBalance(account.getBalance() + amount);
                account.addTransaction("Deposited $" + amount + " at " + new java.util.Date());
                bank.saveToFile();
                balanceLabel.setText("Balance: $" + String.format("%.2f", account.getBalance()));
                JOptionPane.showMessageDialog(this, "Deposit successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Amount must be positive!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount!");
        }
    }

    private void withdraw() {
        String amountStr = JOptionPane.showInputDialog(this, "Enter withdrawal amount:");
        try {
            double amount = Double.parseDouble(amountStr);
            if (amount > 0 && amount <= account.getBalance()) {
                account.setBalance(account.getBalance() - amount);
                account.addTransaction("Withdrawn $" + amount);
                bank.saveToFile();
                balanceLabel.setText("Balance: $" + String.format("%.2f", account.getBalance()));
                JOptionPane.showMessageDialog(this, "Withdrawal successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient balance or invalid amount!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount!");
        }
    }

    private void transfer() {
        String targetAccountStr = JOptionPane.showInputDialog(this, "Enter target account number:");
        String amountStr = JOptionPane.showInputDialog(this, "Enter transfer amount:");
        try {
            Account targetAccount = bank.findAccountByNumber(targetAccountStr);
            double amount = Double.parseDouble(amountStr);
            if (targetAccount != null && amount > 0 && amount <= account.getBalance()) {
                account.setBalance(account.getBalance() - amount);
                targetAccount.setBalance(targetAccount.getBalance() + amount);
                account.addTransaction("Transferred $" + amount + " to " + targetAccountStr);
                targetAccount.addTransaction("Received $" + amount + " from " + account.getAccountNumber());
                bank.saveToFile();
                balanceLabel.setText("Balance: $" + String.format("%.2f", account.getBalance()));
                JOptionPane.showMessageDialog(this, "Transfer successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid account or insufficient balance!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount!");
        }
    }

    private void showHistory() {
        String history = String.join("\n", account.getTransactionHistory());
        JOptionPane.showMessageDialog(this, history.isEmpty() ? "No transactions yet!" : history);
    }
}
