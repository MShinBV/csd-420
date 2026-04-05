/* Miles Shinsato, 04/05/2026, CSD420-308A, Module 2.2 Assignment: Read / Write Integers */

// This program will be generating five random integers and five random double values into a file
// That file will be created if not already existing, and data will append to the file each time it is run
// A separate program will be made to read the data that this file creates

// Allows writing data to a file
import java.io.FileWriter;

// Handles file errors
import java.io.IOException;

// Used to generate random numbers
import java.util.Random;

// Defining the public class
public class WriteData {

    // Creating constant for array size to stay at 5
    public static final int SIZING = 5;

    // Establishing Main method
    public static void main(String[] args) {

        // Creates Random object to generate numbers
        Random rand = new Random();

        // Creates array to store 5 integers
        int[] intArray = new int[SIZING];

        // Creates array to store 5 doubles
        double[] doubleArray = new double[SIZING];

        // Loop to generate 5 random integers
        // Loop runs 5 times
        for(int i = 0; i < SIZING; i++) {

            // Stores random number (0-99) into array
            intArray[i] = rand.nextInt(100);
        }

        // Generates 5 random integers through 5 loops
        for(int i = 0; i < SIZING; i++) {

            // Random double between 0-100
            doubleArray[i] = rand.nextDouble() * 100;
        }

        // Opens file for writing
        // true means append mode (adds data instead of overwriting)
        // If file does not exist it will be created
        try (FileWriter writer = new FileWriter("MilesShinsato_datafile.dat", true)){

            // Writes label into file for the first array (Random Integers)
            writer.write("Random Integers:\n");

            // Loop to write integer array (intArray) values into file
            for(int num : intArray) {

                // Writes each number separated by spaces for better readability
                writer.write(num + " ");
            }

            // Writes next section header for Random Doubles
            writer.write("\n\nRandom Doubles:\n");

            // Loop to write double array (doubleArray) values into file
            for(double num : doubleArray) {

                // Formatting the doubles to show only 2 decimal points for easier readabliity
                writer.write(String.format("%.2f ", num));
            }

            // Adds separator line between program runs to show multiple runs clearly
            writer.write("\n \n----------------------\n \n");


            // Printing message to confirm if program worked
            System.out.println("Data written successfully.");

        }

        // Runs if file error occurs
        catch(IOException e) {

            // Message that will print if there is an error with writing data
            System.out.println("Error writing file.");
        }

    }
}