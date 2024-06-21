package atm;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Statements {
    public void stateView(int id) throws SQLException {
        DefaultTableModel model = new DefaultTableModel();
        Commons commons = new Commons();
        JFrame frame = (JFrame) commons.Frame();
        DatabaseConnection manage = new DatabaseConnection();

        //----------------LABEL-----------------------
        JLabel label = new JLabel("MINI STATEMENTS");
        label.setBounds(0, 200, 575, 30);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Rockwell", Font.BOLD, 25));
        frame.add(label);
        //--------------------------------------------

        //--------------------------------------------
        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 10, 80, 30);
        frame.add(backButton);

        // Add an ActionListener to the "Back" button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Home home = new Home();
                try {
                    home.homeView(id); // Show the home screen
                } catch (SQLException ex) {
                    Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
                }
                frame.dispose(); // Close the current operations screen
            }
        });

        //---------------TABLE--------------------
        JTable table = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model = (DefaultTableModel) table.getModel();
        model.addColumn("ID");
        model.addColumn("DEPOSIT");
        model.addColumn("WITHDRAW");
        model.addColumn("TRANSFER");
        model.addColumn("BALANCE");
        model.addColumn("DATE"); 
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(80);
        JScrollPane sc = new JScrollPane(table);
        sc.setBounds(50, 250, 500, 200);
        frame.add(sc);
        //-----------------------------------------------

        //--------------------TABLEDATA------------------------
        ResultSet rst = manage.stmt(id);
        int i = 0;
        while (rst.next()) {
            model.addRow(new Object[0]);
            model.setValueAt(rst.getInt("transid"), i, 0);

            // Check the transaction type
            if (rst.getString("stat").equals("dep")) {
                model.setValueAt(rst.getString("amount"), i, 1);
                model.setValueAt(rst.getTimestamp("trans_date"), i, 5); // Set the date column
                model.setValueAt("", i, 2); // Withdraw
                model.setValueAt("", i, 3); // Transfer
            } else if (rst.getString("stat").equals("trans")) {
                model.setValueAt("", i, 1); // Deposit
                model.setValueAt(rst.getString("amount"), i, 3);
                model.setValueAt(rst.getTimestamp("trans_date"), i, 5); // Set the date column
                model.setValueAt("", i, 2); // Withdraw
            } else {
                model.setValueAt("", i, 1); // Deposit
                model.setValueAt("", i, 3); // Transfer
                model.setValueAt(rst.getString("amount"), i, 2);
                model.setValueAt(rst.getTimestamp("trans_date"), i, 5); // Set the date column
            }

            model.setValueAt(rst.getInt("bal"), i, 4);
            i++;
        }

        //-----------------------------------------------------

        frame.setVisible(true);
    }
}
