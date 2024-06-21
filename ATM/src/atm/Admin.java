package atm;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.ResultSet;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Admin {
    public void adminView() {
        Commons commons = new Commons();
        JFrame frame = (JFrame) commons.Frame();

        // -------------ADDUSERS---------------------
        JButton add = new JButton("ADD USERS");
        add.setBounds(150, 250, 300, 100);
        add.setBackground(new Color(0x2B3467));
        add.setForeground(Color.white);
        add.setFont(new Font("Rockwell", Font.BOLD, 25));
        frame.add(add);

        // Action listener for "ADD USERS" button
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddUser user = new AddUser();
                try {
                    user.addView();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                frame.dispose();
            }
        });
        //------------------------------------------

        // -------------USERS---------------------
        JButton usersButton = new JButton("USERS");
        usersButton.setBounds(150, 100, 300, 100);
        usersButton.setBackground(new Color(0x2B3467));
        usersButton.setForeground(Color.white);
        usersButton.setFont(new Font("Rockwell", Font.BOLD, 25));
        frame.add(usersButton);

        // Action listener for "USERS" button
        usersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call a method to retrieve and display users
                displayUsers();
            }
        });
        //--------------EXIT---------------------------
        JButton exit = new JButton("EXIT");
        exit.setBounds(150, 400, 300, 100);
        exit.setBackground(new Color(0x2B3467));
        exit.setForeground(Color.white);
        exit.setFont(new Font("Rockwell", Font.BOLD, 25));
        frame.add(exit);

        // Action listener for "EXIT" button
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        //---------------------------------------------
        frame.setVisible(true);
    }

    private void displayStatements(int userId) {
        // Create an instance of the Statements class and call the stateView method
        Statements statements = new Statements();
        try {
            statements.stateView(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

private void displayUsers() {
    JFrame usersFrame = new JFrame("Users");
    usersFrame.setSize(800, 600);

    JPanel usersPanel = new JPanel();
    usersFrame.add(usersPanel);

    DefaultTableModel tableModel = new DefaultTableModel();
    JTable table = new JTable(tableModel);

    // Add columns to the table
    tableModel.addColumn("User ID");
    tableModel.addColumn("Card");
    tableModel.addColumn("Name");
    tableModel.addColumn("Balance");
    tableModel.addColumn("Email");
    tableModel.addColumn("Phone Number");
    tableModel.addColumn("Gender");
    tableModel.addColumn("Address");

    try {
        DatabaseConnection db = new DatabaseConnection();
        ResultSet resultSet = db.getUsers();

        while (resultSet.next()) {
            int userId = resultSet.getInt("id");
            String card = resultSet.getString("card");
            String name = resultSet.getString("uname");
            int balance = resultSet.getInt("bal");
            String email = resultSet.getString("email");
            String phoneNumber = resultSet.getString("phone_number");
            String gender = resultSet.getString("gender");
            String address = resultSet.getString("address");

            // Add rows to the table
            tableModel.addRow(new Object[]{userId, card, name, balance, email, phoneNumber, gender, address});
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    JScrollPane scrollPane = new JScrollPane(table);
    // Set the preferred size of the scroll pane to make the entire table larger
    scrollPane.setPreferredSize(new Dimension(1000, 600)); // Adjust the width and height as needed

    usersPanel.add(scrollPane);

    // Mouse click listener for the table
    table.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int userId = (int) tableModel.getValueAt(selectedRow, 0);
                displayStatements(userId);
            }
        }
    });

    usersFrame.setVisible(true);
    JLabel boldLabel = new JLabel("<html><b>Click on the user to see their transaction history</b></html>");
    boldLabel.setBounds(20, 620, 500, 30);
    usersPanel.add(boldLabel);
}

}
