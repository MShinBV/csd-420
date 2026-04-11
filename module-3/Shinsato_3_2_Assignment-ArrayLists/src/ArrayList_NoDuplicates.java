/* Miles Shinsato, 04/11/2026, CSD420-308A, Module 3.2 Assignment: ArrayList */

// This program creates a list of random numbers and removes duplicates using a generic method, then displays both lists for comparison.

// Allows use of ArrayList
import java.util.ArrayList;

// Used to generate random numbers
import java.util.Random;

// Defining public class
public class ArrayList_NoDuplicates {

    // Main method where program starts
    public static void main(String[] args) {

        // Creating ArrayList to store original values
        ArrayList<Integer> originalList = new ArrayList<>();

        // Creating Random object
        Random rand = new Random();

        // Filling the list with 50 random numbers from 1 to 20
        // +1 is used because nextInt(20) gives values from 0–19
        for (int i = 0; i < 50; i++) {
            originalList.add(rand.nextInt(20) + 1);
        }

        // Printing original list (this will contain duplicates)
        System.out.println("Original List (Includes duplicates):");
        System.out.println(originalList);

        // Calling method to remove duplicates
        // Storing the returned list into a new ArrayList
        ArrayList<Integer> noDuplicatesList = removeDuplicates(originalList);

        // Printing the new list (duplicates removed)
        System.out.println("\nList without duplicates:");
        System.out.println(noDuplicatesList);
    }

    // Creating generic method
    // <E> stands for element and allows this method to work with any data type
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {

        // Creating new ArrayList to store only unique values
        ArrayList<E> noDuplicatesList = new ArrayList<>();

        // Looping through each element in the original list
        for (E element : list) {

            // Checking if the new list already contains the element
            // If it does NOT, then add it
            if (!noDuplicatesList.contains(element)) {
                noDuplicatesList.add(element);
            }
        }

        // Returning the new list with duplicates removed
        return noDuplicatesList;
    }
}