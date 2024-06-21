package atm;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Quick {
	public void quickView(int id) throws SQLException {
		
		Operations oper = new Operations();
		
		Font txt = new Font("", Font.BOLD, 25);
		Commons commons = new Commons();
		JFrame frame = (JFrame)commons.Frame();
		JLabel two = new JLabel("< 200");
		two.setBounds(30, 250, 200, 30);
		two.setFont(txt);
		JLabel five = new JLabel("500 >");
		five.setBounds(350, 250, 200, 30);
		five.setHorizontalAlignment(JLabel.RIGHT);
		five.setFont(txt);
		JLabel ten = new JLabel("< 1000");
		ten.setBounds(30, 350, 200, 30);
		ten.setFont(txt);
		JLabel twenty = new JLabel("2000 >");
		twenty.setBounds(350, 350, 200, 30);
		twenty.setHorizontalAlignment(JLabel.RIGHT);
		twenty.setFont(txt);
		JLabel fifty = new JLabel("< 5000");
		fifty.setBounds(30, 450, 250, 30);
		fifty.setFont(txt);
		JLabel hundred = new JLabel("10000 >");
		hundred.setBounds(350, 450, 200, 30);
		hundred.setHorizontalAlignment(JLabel.RIGHT);
		hundred.setFont(txt);
		frame.add(two);
		frame.add(five);
		frame.add(ten);
		frame.add(twenty);
		frame.add(fifty);
		frame.add(hundred);
		frame.setVisible(true);
                
                
                //--------------------------------------------
                
                JButton backButton = new JButton("Back");
                backButton.setBounds(10, 10, 80, 30);
                frame.add(backButton);

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
		
		two.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) { 
				oper.withdrawal(200, id);
				frame.dispose();
			}
		});
		five.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) { 
				oper.withdrawal(500, id);
				frame.dispose();
			}
		});
		ten.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) { 
				oper.withdrawal(1000, id);
				frame.dispose();
			}
		});
		twenty.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) { 
				oper.withdrawal(2000, id);
				frame.dispose();
			}
		});
		fifty.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) { 
				oper.withdrawal(5000, id);
				frame.dispose();
			}
		});
		hundred.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) { 
				oper.withdrawal(10000, id);
				frame.dispose();
			}
		});
	}
}