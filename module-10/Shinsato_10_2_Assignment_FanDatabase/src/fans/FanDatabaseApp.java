/* Miles Shinsato, 05/17/2026, CSD420-308A, Module 10.2 Assignment: Fan Database */

// This program is made to create an interface to view and update records on the fans table


import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class FanDatabaseApp extends JFrame {

    // these hold what the user types / what comes from the database
    private JTextField idField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField favoriteTeamField;

    // database info (used to connect to MySQL)
    private static final String URL = "jdbc:mysql://localhost:3306/databasedb";
    private static final String USER = "student1";
    private static final String PASSWORD = "pass";

    public FanDatabaseApp() {

        // basic window setup
        setTitle("Fan Database App");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // simple grid layout: rows, columns
        setLayout(new GridLayout(5, 2));

        // ===== ID FIELD =====
        // user enters ID here so we know which record to look up
        add(new JLabel("ID:"));
        idField = new JTextField();
        add(idField);

        // ===== FIRST NAME =====
        // will be filled from database OR edited by user
        add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        add(firstNameField);

        // ===== LAST NAME =====
        add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        add(lastNameField);

        // ===== FAVORITE TEAM =====
        add(new JLabel("Favorite Team:"));
        favoriteTeamField = new JTextField();
        add(favoriteTeamField);

        // buttons for actions
        JButton displayButton = new JButton("Display");
        JButton updateButton = new JButton("Update");

        add(displayButton);
        add(updateButton);

        // when clicked, run displayFan method (gets data from DB)
        displayButton.addActionListener(e -> displayFan());

        // when clicked, runs updateFan method (saves changes to DB)
        updateButton.addActionListener(e -> updateFan());

        setVisible(true);
    }

    // this method creates a connection to the MySQL database
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // ================= DISPLAY RECORD =================
    // takes ID from textbox and pulls matching record from database
    public void displayFan() {

        String sql = "SELECT firstname, lastname, favoriteteam FROM fans WHERE ID=?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // get ID from input box
            int id = Integer.parseInt(idField.getText());
            stmt.setInt(1, id);

            // run query and store result
            ResultSet rs = stmt.executeQuery();

            // if a record exists, fill the text fields
            if (rs.next()) {
                firstNameField.setText(rs.getString("firstname"));
                lastNameField.setText(rs.getString("lastname"));
                favoriteTeamField.setText(rs.getString("favoriteteam"));
            } else {
                // if no record found for that ID
                JOptionPane.showMessageDialog(this, "No record found");
            }

        } catch (Exception e) {
            // catches errors like invalid ID or DB issues
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    // ================= UPDATE RECORD =================
    // takes values from text fields and updates database row
    public void updateFan() {

        String sql = "UPDATE fans SET firstname=?, lastname=?, favoriteteam=? WHERE ID=?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // get updated values from user input
            stmt.setString(1, firstNameField.getText());
            stmt.setString(2, lastNameField.getText());
            stmt.setString(3, favoriteTeamField.getText());

            // ID is used to decide which row to update
            stmt.setInt(4, Integer.parseInt(idField.getText()));

            // execute update query
            int rows = stmt.executeUpdate();

            // check if anything was actually updated
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Updated successfully");
            } else {
                JOptionPane.showMessageDialog(this, "No record found to update");
            }

        } catch (Exception e) {
            // shows error if something goes wrong (bad input or DB issue)
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    // starts the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FanDatabaseApp::new);
    }
}