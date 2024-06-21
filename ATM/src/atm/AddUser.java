package atm;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class AddUser {
    JTextField pinField, atmField, emailField, addressField;
    JCheckBox maleCheckBox, femaleCheckBox;  // Initialize JTextField for PIN and ATM number
    Random random = new Random();  // Create a random number generator

    public void addView() throws SQLException {
        Commons commons = new Commons();
        JFrame frame = (JFrame) commons.Frame();  // Create a JFrame
        Font txt = new Font("", Font.BOLD, 20);
        DatabaseConnection manage = new DatabaseConnection();  // Create a DatabaseConnection instance
        Success success = new Success();

        JPanel contentPanel = new JPanel(); // Create a content panel to hold all components
        contentPanel.setLayout(null); // Use null layout for precise component positioning

        JButton backButton = new JButton("Back");  // Create a "Back" button
        backButton.setBounds(10, 10, 80, 30);
        frame.add(backButton);

        // Add an ActionListener to the "Back" button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Admin admin = new Admin();
                admin.adminView();  // Show the admin view
                frame.dispose();  // Close the current frame
            }
        });

        //--------------NAME--------------------
        JLabel name = new JLabel("Name : ");  // Create a label for the name field
        name.setBounds(50, 200, 100, 25);
        name.setFont(txt);
        JTextField nmField = new JTextField();  // Create a text field for entering the name
        nmField.setBounds(50, 230, 200, 30);
        frame.add(nmField);
        frame.add(name);
        //--------------------------------------

        //--------------EMAIL--------------------
        JLabel emailLabel = new JLabel("Email : ");
        emailLabel.setBounds(350, 300, 500, 25);
        emailLabel.setFont(txt);
        emailField = new JTextField();
        emailField.setBounds(350, 330, 200, 30);
        frame.add(emailField);
        frame.add(emailLabel);
        //--------------------------------------

        //--------------GENDER-------------------
        JLabel genderLabel = new JLabel("Gender : ");
        genderLabel.setBounds(350, 200, 100, 25);
        genderLabel.setFont(txt);
        maleCheckBox = new JCheckBox("Male");
        maleCheckBox.setBounds(350, 230, 100, 30);
        femaleCheckBox = new JCheckBox("Female");
        femaleCheckBox.setBounds(450, 230, 100, 30);
        frame.add(maleCheckBox);
        frame.add(femaleCheckBox);
        frame.add(genderLabel);
        //--------------------------------------

        //--------------Conctact Address------------
        JLabel addressLabel = new JLabel("Address : ");
        addressLabel.setBounds(350, 380, 150, 25);
        addressLabel.setFont(txt);
        addressField = new JTextField();
        addressField.setBounds(350, 410, 200, 30);
        frame.add(addressField);
        frame.add(addressLabel);
        //--------------------------------------

        //----------------Phone Number--------------
        JLabel phone_numberLabel = new JLabel("Phone Number : ");
        phone_numberLabel.setBounds(50, 380, 500, 25);
        phone_numberLabel.setFont(txt);
        JTextField phone_numberField = new JTextField();  // Create a text field for entering the phone number
        phone_numberField.setBounds(50, 410, 200, 30);
        frame.add(phone_numberField);
        frame.add(phone_numberLabel);
        //-----------------------------------------

        //-------------ATMNUMBER------------------
        JLabel atmno = new JLabel("ATM Card Number : ");  // Create a label for the ATM number field
        atmno.setBounds(50, 300, 500, 25);
        atmno.setFont(txt);
        atmField = new JTextField();  // Use the class-level JTextField for ATM number
        atmField.setBounds(50, 330, 200, 30);
        atmField.setEditable(false);  // Make the ATM field non-editable
        frame.add(atmField);
        frame.add(atmno);
        //----------------------------------------

        //-------------ATMPIN------------------
        JLabel atmpin = new JLabel("ATM Card PIN : ");  // Create a label for the ATM PIN field
        atmpin.setBounds(50, 450, 500, 25);
        atmpin.setFont(txt);
        pinField = new JTextField();  // Use the class-level JTextField for the PIN
        pinField.setBounds(50, 480, 200, 30);
        pinField.setEditable(false);  // Make the PIN field non-editable
        frame.add(pinField);
        frame.add(atmpin);
        //----------------------------------------

        //-------------BALANCE------------------
        JLabel bal = new JLabel("BALANCE : ");  // Create a label for the balance field
        bal.setBounds(350, 450, 500, 25);
        bal.setFont(txt);
        JTextField balField = new JTextField();  // Create a text field for entering the balance
        balField.setBounds(350, 480, 200, 30);
        frame.add(balField);
        frame.add(bal);
        //----------------------------------------

        //--------------AUTOGENERATION----------------
        auto();  // Generate random ATM number and PIN
        //---------------------------------------------

        //---------------SUBMIT-------------------
        JButton sbmt = new JButton("SUBMIT");  // Create a "SUBMIT" button
        sbmt.setBounds(200, 500, 200, 50);
        sbmt.setBackground(new Color(0x2B3467));  // Set the background color of the button
        sbmt.setForeground(Color.white);  // Set the text color of the button
        frame.add(sbmt);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        scrollPane.setViewportView(contentPanel);
        frame.setVisible(true);

        // Add an ActionListener to the "SUBMIT" button
        sbmt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!nmField.getText().isEmpty() &&
                    !emailField.getText().isEmpty() &&
                    !addressField.getText().isEmpty() &&
                    !phone_numberField.getText().isEmpty()) {

                    // Check if both checkboxes are selected
                    if (maleCheckBox.isSelected() && femaleCheckBox.isSelected()) {
                        // Display an error message if both genders are selected
                        JOptionPane.showMessageDialog(frame, "Both genders can't be selected.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (balField.getText().isEmpty()) {
                            balField.setText("0");
                        }

                        try {
                            // Retrieve gender selection
                            String gender = "";
                            if (maleCheckBox.isSelected()) {
                                gender = "Male";
                            } else if (femaleCheckBox.isSelected()) {
                                gender = "Female";
                            }

                            // Retrieve email, phone number, and address
                            String email = emailField.getText();
                            String phone_number = phone_numberField.getText();
                            String address = addressField.getText();

                            manage.adding(atmField.getText(), pinField.getText(), nmField.getText(), balField.getText(), email, phone_number, gender, address);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }

                        success.detailView(atmField.getText(), pinField.getText());
                        balField.setText("");
                        nmField.setText("");
                        phone_numberField.setText("");
                        auto();
                    }
                } else {
                    // Display an error message if not all required fields are filled
                    JOptionPane.showMessageDialog(frame, "Please complete all the required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

             //--------------------------------------

        frame.setVisible(true);
    }

    public void auto() {
        String str = "";
        for (int i = 0; i < 16; i++) {
            str += random.nextInt(9 - 0 + 1) + 0;  // Generate a 16-digit random number
        }
        atmField.setText(str);  // Set the ATM number field with the generated number
        str = "";
        for (int i = 0; i < 4; i++) {
            str += random.nextInt(9 - 0 + 1) + 0;  // Generate a 4-digit random PIN
        }
        pinField.setText(str);  // Set the PIN field with the generated PIN
    }
}
