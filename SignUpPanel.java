
import java.awt.*;
import javax.swing.*;

public class SignUpPanel extends JFrame {

    private JTextField nameField, addressField, fathersNameField, ageField, religionField,
            phoneNumberField, nidNumberField, tenureField, interestRateField;
    private JComboBox<String> genderCombo, accountTypeCombo;
    private JButton confirmButton, backButton;
    private BankingSystemManager bank;

    public SignUpPanel(BankingSystemManager bank) {
        this.bank = bank;
        setTitle("Banking Management System - Sign Up");
        setSize(400, 580);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(0x1E3A8A));

        Font lgFont = new Font("Arial", Font.BOLD, 22);
        JLabel title = new JLabel("Create New Account");
        title.setBounds(70, 20, 300, 30);
        title.setForeground(Color.WHITE);
        title.setFont(lgFont);
        add(title);

        String[] genders = {"Male", "Female", "Other"};
        String[] accountTypes = {"CurrentAccount", "SavingsAccount", "FixedAccount"};

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 70, 120, 30);
        nameLabel.setForeground(Color.WHITE);
        add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(180, 70, 150, 30);
        add(nameField);

        JLabel fathersNameLabel = new JLabel("Father's Name:");
        fathersNameLabel.setBounds(50, 110, 120, 30);
        fathersNameLabel.setForeground(Color.WHITE);
        add(fathersNameLabel);
        fathersNameField = new JTextField();
        fathersNameField.setBounds(180, 110, 150, 30);
        add(fathersNameField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(50, 150, 120, 30);
        addressLabel.setForeground(Color.WHITE);
        add(addressLabel);
        addressField = new JTextField();
        addressField.setBounds(180, 150, 150, 30);
        add(addressField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(50, 190, 120, 30);
        ageLabel.setForeground(Color.WHITE);
        add(ageLabel);
        ageField = new JTextField();
        ageField.setBounds(180, 190, 150, 30);
        add(ageField);

        JLabel religionLabel = new JLabel("Religion:");
        religionLabel.setBounds(50, 230, 120, 30);
        religionLabel.setForeground(Color.WHITE);
        add(religionLabel);
        religionField = new JTextField();
        religionField.setBounds(180, 230, 150, 30);
        add(religionField);

        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setBounds(50, 270, 120, 30);
        phoneNumberLabel.setForeground(Color.WHITE);
        add(phoneNumberLabel);
        phoneNumberField = new JTextField();
        phoneNumberField.setBounds(180, 270, 150, 30);
        add(phoneNumberField);

        JLabel nidNumberLabel = new JLabel("NID Number:");
        nidNumberLabel.setBounds(50, 310, 120, 30);
        nidNumberLabel.setForeground(Color.WHITE);
        add(nidNumberLabel);
        nidNumberField = new JTextField();
        nidNumberField.setBounds(180, 310, 150, 30);
        add(nidNumberField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(50, 350, 120, 30);
        genderLabel.setForeground(Color.WHITE);
        add(genderLabel);
        genderCombo = new JComboBox<>(genders);
        genderCombo.setBounds(180, 350, 150, 30);
        add(genderCombo);

        JLabel accountTypeLabel = new JLabel("Account Type:");
        accountTypeLabel.setBounds(50, 390, 120, 30);
        accountTypeLabel.setForeground(Color.WHITE);
        add(accountTypeLabel);
        accountTypeCombo = new JComboBox<>(accountTypes);
        accountTypeCombo.setBounds(180, 390, 150, 30);
        add(accountTypeCombo);

        JLabel tenureLabel = new JLabel("Tenure Year:");
        tenureLabel.setBounds(50, 430, 120, 30);
        tenureLabel.setForeground(Color.WHITE);
        add(tenureLabel);
        tenureField = new JTextField();
        tenureField.setBounds(180, 430, 150, 30);
        add(tenureField);
        tenureField.setVisible(false);
        tenureLabel.setVisible(false);

        JLabel interestRateLabel = new JLabel("Interest Rate:");
        interestRateLabel.setBounds(50, 430, 120, 30);
        interestRateLabel.setForeground(Color.WHITE);
        add(interestRateLabel);
        interestRateField = new JTextField();
        interestRateField.setBounds(180, 430, 150, 30);
        add(interestRateField);
        interestRateField.setVisible(false);
        interestRateLabel.setVisible(false);

        accountTypeCombo.addItemListener(e -> {
            String selectedType = (String) accountTypeCombo.getSelectedItem();
            if ("SavingsAccount".equals(selectedType)) {
                interestRateField.setVisible(true);
                interestRateLabel.setVisible(true);
                tenureField.setVisible(false);
                tenureLabel.setVisible(false);
            } else if ("FixedAccount".equals(selectedType)) {
                tenureField.setVisible(true);
                tenureLabel.setVisible(true);
                interestRateField.setVisible(false);
                interestRateLabel.setVisible(false);
            } else {
                tenureField.setVisible(false);
                tenureLabel.setVisible(false);
                interestRateField.setVisible(false);
                interestRateLabel.setVisible(false);
            }
        });

        Color btnColor = new Color(0xfbbf24);

        confirmButton = new JButton("Confirm");
        confirmButton.setBounds(200, 480, 140, 30);
        confirmButton.setBackground(btnColor);
        confirmButton.addActionListener(e -> createAccount());
        add(confirmButton);

        backButton = new JButton("Back to Login");
        backButton.setBounds(50, 480, 140, 30);
        backButton.setBackground(btnColor);
        backButton.addActionListener(e -> {
            dispose();
            Frame[] frames = Frame.getFrames();
            boolean loginFound = false;
            for (Frame frame : frames) {
                if (frame instanceof LoginPanel) {
                    frame.setVisible(true);
                    frame.toFront();
                    loginFound = true;
                    break;
                }
            }
            if (!loginFound) {
                new LoginPanel();
            }
        });
        add(backButton);

        setVisible(true);
    }

    private void createAccount() {
        try {
            String accountNumber = bank.generateAccountNumber();
            String pin = bank.generatePin();
            String name = nameField.getText();
            String address = addressField.getText();
            String fathersName = fathersNameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String religion = religionField.getText();
            String gender = (String) genderCombo.getSelectedItem();
            String phoneNumber = phoneNumberField.getText();
            String nidNumber = nidNumberField.getText();
            String accountType = (String) accountTypeCombo.getSelectedItem();
            Account account;
            if ("SavingsAccount".equals(accountType)) {
                double interestRate = Double.parseDouble(interestRateField.getText());
                account = new SavingsAccount(accountNumber, pin, name, address, fathersName, age, religion, gender, phoneNumber, nidNumber, 0.0, interestRate);
            } else if ("FixedAccount".equals(accountType)) {
                int tenureYears = Integer.parseInt(tenureField.getText());
                account = new FixedAccount(accountNumber, pin, name, address, fathersName, age, religion, gender, phoneNumber, nidNumber, 0.0, tenureYears);
            } else {
                account = new CurrentAccount(accountNumber, pin, name, address, fathersName, age, religion, gender, phoneNumber, nidNumber, 0.0);
            }
            bank.addAccount(account);
            bank.saveToFile();
            JOptionPane.showMessageDialog(this, "Account created! Account No: " + accountNumber + ", PIN: " + pin);
            dispose();
            new LoginPanel();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for age, tenure, or interest rate!");
        }
    }
}
