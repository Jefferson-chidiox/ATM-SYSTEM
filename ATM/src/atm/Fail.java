package atm;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Fail {
    // Method for displaying a failure message in a JFrame
    public void failView(String str) {
        // Create an instance of the Commons class to obtain a configured JFrame
        Commons commons = new Commons();
        JFrame frame = (JFrame) commons.Frame();

        // Create and configure JLabels for the failure message and additional information
        JLabel fail = new JLabel("YOUR TRANSACTIONS FAILED");
        fail.setBounds(0, 280, 600, 50);
        fail.setHorizontalAlignment(JLabel.CENTER);
        fail.setFont(new Font("Rockwell", Font.BOLD, 25));
        
        JLabel st = new JLabel(str);
        st.setBounds(0, 320, 600, 50);
        st.setHorizontalAlignment(JLabel.CENTER);
        st.setFont(new Font("Rockwell", Font.BOLD, 25));
        
        // Add the failure message and additional information labels to the frame
        frame.add(st);
        frame.add(fail);

        // Make the frame visible to display the failure message
        frame.setVisible(true);
    }
}
