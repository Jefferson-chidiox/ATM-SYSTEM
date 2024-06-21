package atm;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Home {
    // This method displays the main ATM home screen
    public void homeView(int id) throws SQLException {
        // Create an instance of the Operations class
        Operations operations = new Operations();
        Font txt = new Font("", Font.BOLD, 20);
        Commons commons = new Commons();
        JFrame frame = (JFrame) commons.Frame();

        int quickX = (frame.getWidth() - 200) / 2; // Calculate X position for centering the buttons

        // Create buttons for various operations
        DashedBorderLabel withdraw = new DashedBorderLabel("Withdraw", txt);
        withdraw.setBounds(quickX, 250, 200, 30);
        
        DashedBorderLabel transfer = new DashedBorderLabel("Transfer", txt);
        transfer.setBounds(quickX, 300, 200, 30);

        DashedBorderLabel deposit = new DashedBorderLabel("Deposit", txt);
        deposit.setBounds(quickX, 350, 200, 30);

        DashedBorderLabel sts = new DashedBorderLabel("Mini Statement", txt);
        sts.setBounds(quickX, 400, 200, 30);

        DashedBorderLabel bal = new DashedBorderLabel("Balance Enquiry", txt);
        bal.setBounds(quickX, 450, 200, 30);

        DashedBorderLabel pinchange = new DashedBorderLabel("Change Pin", txt);
        pinchange.setBounds(quickX, 500, 200, 30);
        
        // Add buttons to the frame
        frame.add(withdraw);
        frame.add(transfer);
        frame.add(deposit);
        frame.add(sts);
        frame.add(bal);
        frame.add(pinchange);

        frame.setVisible(true);

        // Add mouse listeners to the buttons to handle user interaction
        withdraw.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // Handle Withdraw button click
                try {
                    operations.opView("Withdraw Amount", id);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                frame.dispose();
            }
        });
        
        transfer.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // Handle Transfer button click
                try {
                    operations.opView("Amount and Account no", id);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                frame.dispose();
            }
        });
        
        deposit.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // Handle Deposit button click
                try {
                    operations.opView("Deposit Amount", id);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                frame.dispose();
            }
        });

        sts.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // Handle Mini Statement button click
                Statements state = new Statements();
                try {
                    state.stateView(id);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                frame.dispose();
            }
        });

        bal.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) { 
                // Handle Balance Enquiry button click
                try {
                    operations.opView("Balance", id);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                frame.dispose();
            }
        });
        
        pinchange.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) { 
                // Handle Change PIN button click
                try {
                    operations.opView("New PIN", id);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                frame.dispose();
            }
        });
    }
}
