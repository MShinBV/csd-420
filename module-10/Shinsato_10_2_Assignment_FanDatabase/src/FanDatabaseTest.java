/* Miles Shinsato, 05/17/2026, CSD420-308A, Module 10.2 Assignment: Fan Database */

// This is a test program to ensure all methods and interface functions correctly


public class FanDatabaseTest {

    public static void main(String[] args) {

        // start the app (opens the GUI)
        FanDatabaseApp app = new FanDatabaseApp();

        // just letting me know it started
        System.out.println("Fan Database App started successfully");

        // this is a GUI program to test
        System.out.println("Please use the GUI to test Display and Update");

        // not calling displayFan or updateFan here because they need input from the text fields and would cause issues if fields are empty
    }
}