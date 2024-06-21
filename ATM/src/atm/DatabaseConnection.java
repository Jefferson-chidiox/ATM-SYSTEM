package atm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DatabaseConnection {
    Connection con;

    // Constructor to establish a database connection
    DatabaseConnection() throws SQLException {
        String username = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/atm";
        con = DriverManager.getConnection(url, username, password);
    }

    // Check user credentials
    public ResultSet check(String usr, String pass) throws SQLException {
        String str = "SELECT * FROM users WHERE card = '" + usr + "' AND pin = '" + pass + "'";
        Statement stm = con.createStatement();
        ResultSet rst = stm.executeQuery(str);
        return rst;
    }
    
    public int getTransactionCount(int id) throws SQLException {
    String query = "SELECT COUNT(transid) AS transaction_count, SUM(amount) AS total_amount FROM transactions WHERE id = ? AND DATE(trans_date) = CURDATE()";
    PreparedStatement preparedStatement = con.prepareStatement(query);
    preparedStatement.setInt(1, id);
    ResultSet resultSet = preparedStatement.executeQuery();

    if (resultSet.next()) {
        int transactionCount = resultSet.getInt("transaction_count");
        int totalAmount = resultSet.getInt("total_amount");
        return totalAmount;
    }

    return 0; // User hasn't made any transactions today
    }


    // Deposit money into a user's account
    public void deposit(int amt, int id) throws SQLException {
        String str = "UPDATE users SET bal = bal + " + amt + " WHERE id = " + id;
        Statement stm = con.createStatement();
        stm.executeUpdate(str);
        int bal = balCheck(id);
        str = "INSERT INTO transactions (id, amount, stat, bal, trans_date) VALUES(" + id + ", " + amt + ", 'dep', " + bal +", CURRENT_TIMESTAMP)";
        Statement stm2 = con.createStatement();
        stm2.executeUpdate(str);
    }

    // Withdraw money from a user's account
    public int withdraw(int amt, int id) throws SQLException {
        int bal = balCheck(id);
        if (bal >= amt) {
            String str = "UPDATE users SET bal = bal - " + amt + " WHERE id = " + id;
            Statement stm = con.createStatement();
            stm.executeUpdate(str);
            bal -= amt;
            str = "INSERT INTO transactions (id, amount, stat, bal, trans_date) VALUES(" + id + ", " + amt + ", 'wit', " + bal + ", CURRENT_TIMESTAMP)";                        
            Statement stm2 = con.createStatement();
            stm2.executeUpdate(str);
            return 1;
        }
        return 0;
    }

    // Transfer money from a user's account
    public int transfer(int amt, int id) throws SQLException {
        int bal = balCheck(id);
        if (bal >= amt) {
            String str = "UPDATE users SET bal = bal - " + amt + " WHERE id = " + id;
            Statement stm = con.createStatement();
            stm.executeUpdate(str);
            bal -= amt;
            str = "INSERT INTO transactions (id, amount, stat, bal, trans_date) VALUES(" + id + ", " + amt + ", 'trans', " + bal + ", CURRENT_TIMESTAMP)";
            Statement stm2 = con.createStatement();
            stm2.executeUpdate(str);
            return 1;
        }
        return 0;
    }

    // Change a user's PIN
    public void pinchange(String pin, int id) throws SQLException {
        String str = "UPDATE users SET pin = '" + pin + "' WHERE id = " + id;
        Statement stm = con.createStatement();
        stm.executeUpdate(str);
    }

    // Get a user's PIN
    public String getPin(int id) throws SQLException {
        String str = "SELECT pin FROM users WHERE id = " + id;
        Statement stm = con.createStatement();
        ResultSet rst = stm.executeQuery(str);
        if (rst.next()) {
            return rst.getString("pin");
        }
        return null;
    }

    // Check the balance of a user's account
    public int balCheck(int id) throws SQLException {
        String str = "SELECT bal FROM users WHERE id = " + id;
        Statement stm = con.createStatement();
        ResultSet rst = stm.executeQuery(str);
        rst.next();
        return rst.getInt("bal");
    }

    // Get a user's transaction history
    public ResultSet stmt(int id) throws SQLException {
        String str = "SELECT * FROM transactions WHERE id = " + id + " order by transid desc";
        Statement stm = con.createStatement();
        ResultSet rst = stm.executeQuery(str);
        return rst;
    }

    // Add a new user to the database
    public void adding(String card, String pin, String name, String bal, String email, String phone_number, String gender, String address) throws SQLException {
        String str = "INSERT INTO users (card, pin, uname, bal, email, phone_number, gender, address) values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = con.prepareStatement(str);
        preparedStatement.setString(1, card);
        preparedStatement.setString(2, pin);
        preparedStatement.setString(3, name);
        preparedStatement.setString(4, bal);
        preparedStatement.setString(5, email);
        preparedStatement.setString(6, phone_number);
        preparedStatement.setString(7, gender);
        preparedStatement.setString(8, address);
        preparedStatement.executeUpdate();
    }

    // Get a list of all users in the database
    public ResultSet getUsers() throws SQLException {
        String query = "SELECT id, card, uname, bal, email, phone_number, gender, address FROM users";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        return preparedStatement.executeQuery();
    }
}
