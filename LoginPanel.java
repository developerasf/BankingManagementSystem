
import java.awt.*;
import javax.swing.*;

public class LoginPanel extends JFrame {

    private JTextField accountNumberField;
    private JPasswordField pinField;
    private JButton loginButton, signUpButton;
    private BankingSystemManager bank = new BankingSystemManager();

    public LoginPanel() {

        setTitle("Banking Management System - Login");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(0x1E3A8A));

        Font largerFont = new Font("Arial", Font.PLAIN, 14);
        Font btnFont = new Font("Arial", Font.BOLD, 14);
        Font headerFont = new Font("Arial", Font.BOLD, 22);

        Color gold = new Color(0xfbbf24);

        JLabel title = new JLabel("Welcome to Premium Bank");
        title.setBounds(60, 40, 300, 30);
        title.setForeground(gold);
        title.setFont(headerFont);
        add(title);

        JLabel accountLabel = new JLabel("ACCOUNT NO :");
        accountLabel.setBounds(100, 70, 200, 50);
        accountLabel.setForeground(Color.WHITE);
        accountLabel.setFont(largerFont);
        add(accountLabel);

        accountNumberField = new JTextField();
        accountNumberField.setBounds(100, 110, 200, 30);
        accountNumberField.setFont(largerFont);
        add(accountNumberField);

        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setBounds(100, 140, 200, 30);
        pinLabel.setForeground(Color.WHITE);
        pinLabel.setFont(largerFont);
        add(pinLabel);

        pinField = new JPasswordField();
        pinField.setBounds(100, 170, 200, 30);
        pinField.setFont(largerFont);
        add(pinField);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 210, 200, 30);
        loginButton.setBackground(gold);
        loginButton.setFont(btnFont);
        loginButton.addActionListener(e -> login());
        add(loginButton);

        signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(100, 250, 200, 30);
        signUpButton.setBackground(gold);
        signUpButton.setFont(btnFont);
        signUpButton.addActionListener(e -> new SignUpPanel(bank));
        add(signUpButton);

        setVisible(true);

        bank.loadFromFile();

        boolean adminExists = false;
        for (int i = 0; i < bank.getAccountCount(); i++) {
            Account account = bank.getAccount(i);
            if (account != null && account.getAccountNumber().equals("1010101010")) {
                adminExists = true;
                break;
            }
        }
        if (!adminExists) {
            Account adminAccount = new CurrentAccount("1010101010", "9709", "Admin", "", "", 0, "", "", "", "", 0.0);
            bank.addAccount(adminAccount);
            bank.saveToFile();
        }
    }

    private void login() {
        String accountNumber = accountNumberField.getText();
        String pin = new String(pinField.getPassword());
        Account account = bank.findAccount(accountNumber, pin);
        if (account != null) {
            if (accountNumber.equals("1010101010") && pin.equals("9709")) {
                new AdminPanel(bank);
                dispose();
            } else {
                new UserPanel(bank, account);
                dispose();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid account number or PIN!");
        }
    }

    public static void main(String[] args) {
        new LoginPanel();
    }
}
