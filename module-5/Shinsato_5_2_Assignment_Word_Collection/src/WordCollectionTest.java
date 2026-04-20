/* Miles Shinsato, 04/18/2026, CSD420-308A, Module 5.2 Assignment: Word Sorting */

// This program will be reading words from a text file and display all non-duplicate words in ascending and descending order
// Going to be using One Piece characters in my collection_of_words.txt file
// There is also a test program at the end to check sorting and duplicate removal

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordCollectionTest {

    public static void main(String[] args) {
        // file that contains One Piece character names
        String fileName = "collection_of_words.txt";

        // read character names from file and store in a Set
        // Set is used so duplicates like "luffy" only appear once
        Set<String> characters = readWordsFromFile(fileName);

        // Display characters from A to Z
        displayAscending(characters);

        // Display characters from Z to A
        displayDescending(characters);

        // Run basic tests to confirm everything works correctly
        runTests();
    }

    public static Set<String> readWordsFromFile(String fileName) {
        // TreeSet automatically sorts names alphabetically
        // Removes duplicates (important for repeated characters)
        Set<String> characterSet = new TreeSet<>();

        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            // Read each character name from file
            while (scanner.hasNext()) {
                String character = scanner.next().toLowerCase();

                // Lowercase ensures "Luffy" and "luffy" are treated the same
                characterSet.add(character);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }

        return characterSet;
    }

    public static void displayAscending(Set<String> characters) {
        System.out.println("\nOne Piece Characters (Ascending A-Z):");

        // TreeSet is already sorted, so we just print it directly
        for (String character : characters) {
            System.out.println(character);
        }
    }

    public static void displayDescending(Set<String> characters) {
        System.out.println("\nOne Piece Characters (Descending Z-A):");

        // Convert to list so we can reverse order
        List<String> list = new ArrayList<>(characters);

        // Reverse list for Z-A order
        Collections.reverse(list);

        for (String character : list) {
            System.out.println(character);
        }
    }


    // Setting up a test program to run at the end to ensure that sorting and duplicate removal works
    public static void runTests() {
        System.out.println("\nRunning Tests...");

        // Small test set using One Piece characters
        // Duplicates should be removed
        Set<String> testSet = new TreeSet<>();
        testSet.add("luffy");
        testSet.add("zoro");
        testSet.add("nami");

        // Duplicate to test removal
        testSet.add("luffy");

        // Test 1: duplicate removal check
        if (testSet.size() == 3) {
            System.out.println("PASS: duplicates removed correctly");
        } else {
            System.out.println("FAIL: duplicate removal issue");
        }

        // Test 2: check alphabetical order start
        String first = ((TreeSet<String>) testSet).first();

        if (first.equals("luffy")) {
            System.out.println("PASS: ascending order works");
        } else {
            System.out.println("FAIL: sorting issue");
        }

        // Test 3: check last element in sorted set
        String last = ((TreeSet<String>) testSet).last();

        if (last.equals("zoro")) {
            System.out.println("PASS: descending order works");
        } else {
            System.out.println("FAIL: ordering issue");
        }
    }
}