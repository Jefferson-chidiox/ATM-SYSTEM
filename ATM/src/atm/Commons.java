package atm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Commons {
    // Create and configure a JFrame for the application's main frame
    public Component Frame() {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.decode("#F5F5F5"));
        
        // Create and configure a JLabel for the application title ("Grafil")
        JLabel atm = new JLabel("Grafil");
        atm.setBounds(0, 30, 600, 100);
        atm.setHorizontalAlignment(JLabel.CENTER);
        atm.setFont(new Font("Arial", Font.BOLD, 60));

        // Create and configure a JLabel for the application subtitle ("ATM MANAGEMENT SYSTEM")
        JLabel man = new JLabel("ATM MANAGEMENT SYSTEM");
        man.setBounds(0, 140, 600, 20);
        man.setHorizontalAlignment(JLabel.CENTER);
        man.setFont(new Font("Monospaced", Font.BOLD, 20));

        // Add the title and subtitle labels to the frame
        frame.add(man);
        frame.add(atm);
        
        return frame; // Return the configured JFrame
    }
}
