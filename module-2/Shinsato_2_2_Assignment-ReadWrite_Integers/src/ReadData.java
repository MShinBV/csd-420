/* Miles Shinsato, 04/05/2026, CSD420-308A, Module 2.2 Assignment: Read / Write Integers */

// This program will be reading the values created in the WriteData Program that it is paired with

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Defining the public class
public class ReadData {

    // Constant for the file name
    public static final String FILENAME = "MilesShinsato_datafile.dat";

    // Establishing Main method
    public static void main(String[] args) {

        // Try-with-resources ensures the Scanner is closed automatically
        try (Scanner reader = new Scanner(new File(FILENAME))) {

            // Loop reads each line from the file
            while (reader.hasNextLine()) {

                // Stores one line from the file
                String line = reader.nextLine();

                // Prints line to console
                System.out.println(line);
            }

        }
        // Runs if the file is not found
        catch (FileNotFoundException e) {

            // Prints error message
            System.out.println("File not found: " + FILENAME);
        }

    }
}