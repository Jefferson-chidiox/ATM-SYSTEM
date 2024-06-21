package atm;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class Pin {
    public void pinView(String cardNum) {
        Commons common = new Commons();
        JFrame frame = (JFrame) common.Frame();
        Font txt = new Font("", Font.BOLD, 15);
        Home home = new Home();
        Admin admin = new Admin();

        //---------------PASSWORD----------------
        // Create a label for entering the PIN
        JLabel pswd = new JLabel("ENTER YOUR PIN");
        pswd.setBounds(50, 270, 250, 20);
        pswd.setFont(txt);

        // Create a password field for entering the PIN
        JPasswordField pswdField = new JPasswordField();
        pswdField.setBounds(180, 270, 300, 25);
        pswdField.setFont(txt);
        frame.add(pswdField);
        frame.add(pswd);
        //-----------------------------------------

        //-----------------BUTTON-----------------
        // Create a "CONTINUE" button
        JButton cont = new JButton("CONTINUE");
        cont.setBounds(220, 350, 150, 30);
        cont.setBackground(new Color(0x2B3467));
        cont.setForeground(Color.white);
        cont.setFont(new Font("Rockwell", Font.BOLD, 15));
        frame.add(cont);

        // Add an action listener to the "CONTINUE" button
        cont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DatabaseConnection man = new DatabaseConnection();
                    char[] passwordChars = pswdField.getPassword();
                    String pwd = new String(passwordChars);

                    // Check the card number and PIN in the database
                    ResultSet rst = man.check(cardNum, pwd);

                    if (rst.next()) {
                        if (rst.getString("card").equals("admin")) {
                            // If the card number is "admin," open the admin view
                            admin.adminView();
                            frame.dispose();
                        } else {
                            // If it's a regular card, open the home view with the user's ID
                            home.homeView(rst.getInt("id"));
                            frame.dispose();
                        }
                    } else {
                        // If no match is found in the database, display an error message
                        Fail fail = new Fail();
                        fail.failView("WRONG PIN");
                        frame.dispose();
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        //----------------------------------------
        frame.setVisible(true);
    }
}
