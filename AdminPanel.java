
import java.awt.*;
import javax.swing.*;

public class AdminPanel extends JFrame {

    private JButton viewAccountsButton, searchAccountButton, deleteAccountButton, logoutButton;
    private BankingSystemManager bank;
    private JTextArea accountsTextArea;
    private JScrollPane scrollPane;

    public AdminPanel(BankingSystemManager bank) {
        this.bank = bank;
        setTitle("Banking Management System - Admin");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(0x1E3A8A));

        JLabel title = new JLabel("Admin Dashboard");
        title.setBounds(150, 20, 200, 30);
        title.setForeground(Color.WHITE);
        add(title);

        accountsTextArea = new JTextArea();
        accountsTextArea.setEditable(false);
        scrollPane = new JScrollPane(accountsTextArea);
        scrollPane.setBounds(50, 70, 300, 200);
        add(scrollPane);

        String[] buttons = {"View All Accounts", "Search Account", "Delete Account", "Logout"};
        int y = 280;
        for (String btnText : buttons) {
            JButton button = new JButton(btnText);
            button.setBounds(100, y, 200, 40);
            button.setBackground(new Color(0xfbbf24));
            button.addActionListener(e -> {
                if (btnText.equals("View All Accounts")) {
                    viewAccounts();
                } else if (btnText.equals("Search Account")) {
                    searchAccount();
                } else if (btnText.equals("Delete Account")) {
                    deleteAccount();
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

    private void viewAccounts() {
        String[] accounts = bank.getAllAccounts();
        StringBuilder text = new StringBuilder();
        for (String account : accounts) {
            if (account != null) {
                text.append(account).append("\n");
            }
        }
        accountsTextArea.setText(text.toString());
    }

    private void searchAccount() {
        String accountNumber = JOptionPane.showInputDialog(this, "Enter account number to search:");
        if (accountNumber != null) {
            Account account = bank.findAccountByNumber(accountNumber);
            if (account != null) {
                JOptionPane.showMessageDialog(this, "Account: " + account.getAccountNumber() + "\nName: " + account.getName() + "\nBalance: $" + account.getBalance());
            } else {
                JOptionPane.showMessageDialog(this, "Account not found!");
            }
        }
    }

    private void deleteAccount() {
        String accountNumber = JOptionPane.showInputDialog(this, "Enter account number to delete:");
        if (accountNumber != null) {
            Account account = bank.findAccountByNumber(accountNumber);
            if (account != null && !account.getAccountNumber().equals("1010101010")) {
                if (JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this account?", "Confirm Deletion", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    bank.deleteAccount(accountNumber);
                    bank.saveToFile();
                    JOptionPane.showMessageDialog(this, "Account deleted successfully!");
                    viewAccounts();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Cannot delete admin account or account not found!");
            }
        }
    }
}
