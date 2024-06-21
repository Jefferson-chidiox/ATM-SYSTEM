package atm;

import java.sql.SQLException;

public class ATM {

    // Main method is the entry point of the program.
    public static void main(String[] args) throws InterruptedException, SQLException {
        // Create a new Login object.
        Login login = new Login();

        // Display the login view.
        login.loginView();
    }
}
