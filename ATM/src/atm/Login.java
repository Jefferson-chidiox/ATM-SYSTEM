package atm;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Login extends Commons {

    public void loginView() {
        Commons common = new Commons();
        JFrame frame = (JFrame) common.Frame();
        Font txt = new Font("", Font.BOLD, 15);
        Pin pin = new Pin();

        //---------------CARDNUMBER----------------
        // Create a label for the card number
        JLabel card = new JLabel("CARD NUMBER");
        card.setBounds(50, 270, 250, 20);
        card.setFont(txt);

        // Create a text field for entering the card number
        JTextField cardNumber = new JTextField();
        cardNumber.setBounds(180, 270, 300, 25);
        cardNumber.setFont(txt);
        frame.add(cardNumber);
        frame.add(card);
        //-----------------------------------------

        //----------------ADMIN--------------------
        // Create a label for the admin login option
        JLabel admin = new JLabel("ADMIN LOGIN >");
        admin.setBounds(370, 340, 570, 30);
        admin.setFont(txt);
        frame.add(admin);

        // Add a mouse listener to the "ADMIN LOGIN" label
        admin.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // Call the pinView method with "admin" as the argument
                pin.pinView("admin");
                frame.dispose();
            }
        });
        //------------------------------------------

        //-----------------BUTTON-----------------
        // Create a "CONTINUE" button
        JButton cont = new JButton("CONTINUE");
        cont.setBounds(190, 340, 130, 30);
        cont.setBackground(new Color(464646));
        cont.setForeground(Color.white);
        cont.setFont(new Font("Sans Serif", Font.BOLD, 15));
        frame.add(cont);

        // Add an action listener to the "CONTINUE" button
        cont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cardNumber.getText().length() == 16) {
                    // Call the pinView method with the card number as the argument
                    pin.pinView(cardNumber.getText());
                    frame.dispose();
                } else {
                    // If the card number is not 16 digits long, display an error message
                    Fail fail = new Fail();
                    fail.failView("WRONG CARD NUMBER!!!");
                    frame.dispose();
                }
            }
        });
        //----------------------------------------

        // Make the frame visible
        frame.setVisible(true);
    }
}
