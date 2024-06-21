package atm;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Operations {
    DatabaseConnection manage;
    Fail fail;
    Success success;

    Operations() throws SQLException {
        // Initialize database connection, fail, and success objects
        manage = new DatabaseConnection();
        fail = new Fail();
        success = new Success();
    }

    public void opView(String str, int id) throws SQLException {
        Commons commons = new Commons();
        JFrame frame = (JFrame) commons.Frame();
        Font txt = new Font("", Font.BOLD, 15);

        // Create a label to prompt user for input
        JLabel label = new JLabel("Enter the " + str);
        label.setBounds(50, 270, 250, 20);
        label.setFont(txt);

        // Create a text field for user input
        JTextField amt = new JTextField();
        amt.setBounds(50, 300, 500, 35);
        amt.setFont(txt);

        frame.add(label);
        frame.add(amt);
        
        
        // Create a "Submit" button
        JButton sbt = new JButton("SUBMIT");
        sbt.setBounds(200, 400, 200, 50);
        sbt.setFont(new Font("Rockwell", Font.BOLD, 25));
        frame.add(sbt);

        // Create a "Back" button
        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 10, 80, 30);
        frame.add(backButton);

        // Action listener for the "Back" button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Return to the home view
                Home home = new Home();
                try {
                    home.homeView(id);
                } catch (SQLException ex) {
                    Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
                }
                frame.dispose();
            }
        });




// Action listener for the "Submit" button
sbt.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String inputAmount = amt.getText();
        
        if (inputAmount.isEmpty()) {
            // Display an error message if the text field is empty
            JOptionPane.showMessageDialog(frame, "Text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Do nothing if the text field is empty
        }

        int transactionAmount = Integer.parseInt(inputAmount);

        // Check the user's daily transaction limit
        try {
            if (inputAmount.length() < 3) {
                // Display an error message if the amount has less than 3 digits
                JOptionPane.showMessageDialog(frame, "Amount must have at least 3 digits.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int totalAmountToday = manage.getTransactionCount(id);
                int remainingDailyLimit = 25000 - totalAmountToday;

                if (transactionAmount > remainingDailyLimit) {
                    JOptionPane.showMessageDialog(frame, "Exceeded daily transaction limit ($25,000).", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (totalAmountToday >= 25000) {
                    JOptionPane.showMessageDialog(frame, "Exceeded daily transaction limit ($25,000).", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (totalAmountToday + transactionAmount > 25000) {
                    JOptionPane.showMessageDialog(frame, "Transaction would exceed daily limit ($25,000).", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (str.equals("Withdraw Amount")) {
                        // Perform a withdrawal operation
                        withdrawal(Integer.parseInt(amt.getText()), id);
                        frame.dispose();
                    } else if (str.equals("Deposit Amount")) {
                        try {
                            // Perform a deposit operation
                            manage.deposit(Integer.parseInt(amt.getText()), id);
                            success.successView(id);
                            frame.dispose();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }else if (str.equals("New PIN")) {

                        try {
                            changePin(id);
                        } catch (SQLException ex) {
                            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                    }else if (str.equals("Amount and Account no")) {
                        // Additional input for transferring to another account
                        JTextField recipientField = new JTextField();
                        recipientField.setBounds(50, 380, 500, 35);
                        recipientField.setFont(txt);
                        frame.add(recipientField);

                        sbt.setBounds(200, 450, 200, 50); // Moved below recipientField
                        sbt.setFont(new Font("Rockwell", Font.BOLD, 25));
                        frame.add(sbt);
                        sbt.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // Get the recipient's account number
                                String accountNumber = recipientField.getText();
                                if (accountNumber.length() >= 5 && accountNumber.matches("\\d+")) {
                                    // Account number has at least 5 digits and consists of only digits
                                    transfer(Integer.parseInt(amt.getText()), id);
                                    frame.dispose();
                                } else {
                                    // Display an error message if the account number is not valid
                                    JOptionPane.showMessageDialog(frame, "Account number must have at least 5 digits and consist of only digits.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        });
                    }
                }
            }
        }  catch (SQLException e1) {
                e1.printStackTrace();
        }
     }
  });

        if (str.equals("Balance")) {
            // Hide unnecessary elements for balance check
            amt.setVisible(false);
            sbt.setVisible(false);
            label.setText("Your Balance is : ");
            JLabel bal;
            try {
                // Display the user's balance
                bal = new JLabel(manage.balCheck(id) + "");
                bal.setBounds(0, 325, 600, 20);
                bal.setHorizontalAlignment(JLabel.CENTER);
                bal.setFont(new Font("", Font.BOLD, 25));
                frame.add(bal);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        frame.setVisible(true);
    }

    public void withdrawal(int amount, int id) {
        try {
            // Attempt a withdrawal
            int check = manage.withdraw(amount, id);
            if (check == 1) {
                success.successView(id);
            } else {
                fail.failView("INSUFFICIENT BALANCE!!!");
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void transfer(int amount, int id) {
        try {
            // Attempt a transfer
            int check = manage.transfer(amount, id);
            if (check == 1) {
                success.successView(id);
            } else {
                fail.failView("TRANSFER FAILED!!!");
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    
public void changePin(int id) throws SQLException {
    Commons commons = new Commons();
    JFrame frame = (JFrame) commons.Frame();
    Font txt = new Font("", Font.BOLD, 15);
    
    // Check the user's daily transaction limit
    int totalAmountToday = manage.getTransactionCount(id);
    if (totalAmountToday >= 25000) {
        JOptionPane.showMessageDialog(frame, "Transaction limit exceeded. Try changing PIN tomorrow.", "Error", JOptionPane.ERROR_MESSAGE);
        return; // Don't proceed with PIN change
    }
    
    // Create a label to prompt the user for the old PIN
    JLabel oldPinLabel = new JLabel("Enter your old PIN");
    oldPinLabel.setBounds(50, 270, 250, 20);
    oldPinLabel.setFont(txt);

    // Create a text field for the old PIN
    JTextField oldPinField = new JTextField();
    oldPinField.setBounds(50, 300, 500, 35);
    oldPinField.setFont(txt);

    frame.add(oldPinLabel);
    frame.add(oldPinField);

    // Create a label to prompt the user for the new PIN
    JLabel newPinLabel = new JLabel("Enter your new PIN");
    newPinLabel.setBounds(50, 340, 250, 20);
    newPinLabel.setFont(txt);

    // Create a text field for the new PIN
    JTextField newPinField = new JTextField();
    newPinField.setBounds(50, 370, 500, 35);
    newPinField.setFont(txt);

    frame.add(newPinLabel);
    frame.add(newPinField);

    // Create a label to confirm the new PIN
    JLabel confirmPinLabel = new JLabel("Confirm your new PIN");
    confirmPinLabel.setBounds(50, 410, 250, 20);
    confirmPinLabel.setFont(txt);

    // Create a text field to confirm the new PIN
    JTextField confirmPinField = new JTextField();
    confirmPinField.setBounds(50, 440, 500, 35);
    confirmPinField.setFont(txt);

    frame.add(confirmPinLabel);
    frame.add(confirmPinField);

    // Create a "Back" button
    JButton backButton = new JButton("Back");
    backButton.setBounds(10, 10, 80, 30);
    frame.add(backButton);

    // Action listener for the "Back" button
    backButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Return to the home view
            Home home = new Home();
            try {
                home.homeView(id);
            } catch (SQLException ex) {
                Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
            }
            frame.dispose();
        }
    });

    // Create a "Submit" button
    JButton sbt = new JButton("SUBMIT");
    sbt.setBounds(200, 500, 200, 50);
    sbt.setFont(new Font("Rockwell", Font.BOLD, 25));
    frame.add(sbt);

    // Action listener for the "Submit" button
    sbt.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get the input old PIN, new PIN, and confirmed new PIN
            String oldPin = oldPinField.getText();
            String newPin = newPinField.getText();
            String confirmPin = confirmPinField.getText();

            try {
                if (!oldPin.equals(manage.getPin(id))) {
                    JOptionPane.showMessageDialog(frame, "Old PIN is incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!newPin.equals(confirmPin)) {
                    JOptionPane.showMessageDialog(frame, "New PIN and confirmed PIN do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Change the PIN
                    manage.pinchange(newPin, id);
                    success.successView(id);
                    frame.dispose();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    });

    frame.setVisible(true);
}

}
