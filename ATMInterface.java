import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }
}

public class ATMInterface {
    private BankAccount account;
    private JFrame frame;
    private JLabel balanceLabel;

    public ATMInterface(BankAccount account) {
        this.account = account;
        createUI();
    }

    private void createUI() {
        frame = new JFrame("ATM Interface");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        balanceLabel = new JLabel("Balance: $" + account.getBalance());
        balanceLabel.setBounds(50, 50, 300, 20);

        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.setBounds(50, 100, 150, 30);
        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                balanceLabel.setText("Balance: $" + account.getBalance());
            }
        });

        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(50, 140, 150, 30);
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(frame, "Enter amount to deposit:");
                double amount = Double.parseDouble(input);
                account.deposit(amount);
                balanceLabel.setText("Balance: $" + account.getBalance());
            }
        });

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(50, 180, 150, 30);
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(frame, "Enter amount to withdraw:");
                double amount = Double.parseDouble(input);
                if (amount > account.getBalance()) {
                    JOptionPane.showMessageDialog(frame, "Insufficient balance!");
                } else {
                    account.withdraw(amount);
                    balanceLabel.setText("Balance: $" + account.getBalance());
                }
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(50, 220, 150, 30);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setLayout(null);
        frame.add(balanceLabel);
        frame.add(checkBalanceButton);
        frame.add(depositButton);
        frame.add(withdrawButton);
        frame.add(exitButton);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000); // Initial balance
        new ATMInterface(account);
    }
}
