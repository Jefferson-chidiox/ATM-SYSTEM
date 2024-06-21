package atm;

import java.awt.Font;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Success {
    public void successView(int id) throws SQLException {
        // Create instances of necessary classes
        Home home = new Home();
        Commons commons = new Commons();
        JFrame frame = (JFrame) commons.Frame();

        // Create a "Back" button
        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 10, 80, 30);
        frame.add(backButton);

        // Add an action listener to the "Back" button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the "Back" button click
                Home home = new Home();
                try {
                    home.homeView(id); // Show the home screen
                } catch (SQLException ex) {
                    Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
                }
                frame.dispose(); // Close the current operations screen
            }
        });

        //-----------------SUCCESS------------------
        // Create a label to display a success message
        JLabel sucss = new JLabel("TRANSACTION SUCCESS.");
        sucss.setBounds(0, 280, 600, 50);
        sucss.setHorizontalAlignment(JLabel.CENTER);
        sucss.setFont(new Font("Rockwell", Font.BOLD, 25));
        frame.add(sucss);
        //-----------------------------------------

        // Show the home view
        home.homeView(id);
        frame.setVisible(true);
    }

    //--------------------------------------------

    public void detailView(String num, String pin) {
        Commons commons = new Commons();
        JFrame frame = (JFrame) commons.Frame();

        //-----------------DETAILS------------------
        // Create a label to instruct the user to remember the details
        JLabel sucss = new JLabel("REMEMBER THE DETAILS!!!");
        sucss.setBounds(0, 200, 600, 50);
        sucss.setHorizontalAlignment(JLabel.CENTER);
        sucss.setFont(new Font("Rockwell", Font.BOLD, 25));
        frame.add(sucss);

        // Create text fields to display card number and PIN
        JTextField number = new JTextField("CARD NUMBER : " + num);
        number.setBounds(0, 300, 600, 50);
        number.setEditable(false);
        number.setHorizontalAlignment(JLabel.CENTER);
        number.setFont(new Font("Rockwell", Font.BOLD, 20));
        frame.add(number);

        JTextField pinno = new JTextField("DEFAULT PIN : " + pin);
        pinno.setBounds(0, 400, 600, 50);
        pinno.setHorizontalAlignment(JLabel.CENTER);
        pinno.setEditable(false);
        pinno.setFont(new Font("Rockwell", Font.BOLD, 20));
        frame.add(pinno);
        //-----------------------------------------

        frame.setVisible(true);
    }
}
