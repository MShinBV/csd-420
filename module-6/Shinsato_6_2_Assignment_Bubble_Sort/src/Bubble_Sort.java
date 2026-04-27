/* Miles Shinsato, 04/18/2026, CSD420-308A, Module 6.2 Assignment: Bubble Sort */

import java.util.Comparator;

// Bubble Sort Examples
// This program demonstrates:
// Bubble sort using Comparable
// Bubble sort using Comparator
// Sorting custom objects (Pirate Names and their Bounties)
public class Bubble_Sort {

    // =========================
    // COMPARABLE BUBBLE SORT
    // =========================
    public static <T extends Comparable<T>> void bubbleSortComparable(T[] array) {

        T temp;

        // Print original array
        printArray(array);

        // Outer loop controls number of passes
        for (int i = 0; i < array.length - 1; i++) {

            // Inner loop compares adjacent elements
            for (int j = 0; j < array.length - i - 1; j++) {

                // If current element is greater than next element, swap
                if (array[j].compareTo(array[j + 1]) > 0) {

                    // swap logic
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    System.out.println("Switch made (Comparable)");
                }
            }

            // Print array after each full pass
            printArray(array);
        }
    }

    // =========================
    // COMPARATOR BUBBLE SORT
    // =========================
    public static <T> void bubbleSortComparator(T[] array, Comparator<T> comparator) {

        T temp;

        // Print original array
        printArray(array);

        for (int i = 0; i < array.length - 1; i++) {

            for (int j = 0; j < array.length - i - 1; j++) {

                // Use comparator instead of natural ordering
                if (comparator.compare(array[j], array[j + 1]) > 0) {

                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    System.out.println("Switch made (Comparator)");
                }
            }

            // Print after each pass
            printArray(array);
        }
    }

    // =========================
    // PRINT METHOD (GENERIC)
    // =========================
    public static <T> void printArray(T[] arrayParam) {

        System.out.print("\narray = {");

        for (T element : arrayParam) {
            System.out.print(" [" + element + "] ");
        }

        System.out.println("};\n");
    }

    // =========================
    // MAIN METHOD (TESTING)
    // =========================
    public static void main(String[] args) {

        // -------- Comparable Test --------
        Integer[] numbers = {5, 3, 4, 9, 0, 1, 2};

        System.out.println("=== Comparable Bubble Sort (Integers) ===");
        bubbleSortComparable(numbers);

        // -------- Comparator Test --------
        String[] names = {"Luffy", "Doflamingo", "Sabo", "Marco"};

        System.out.println("=== Comparator Bubble Sort (Strings by Alphabetical Order) ===");
        bubbleSortComparator(names, Comparator.naturalOrder());

        // -------- Custom Class Test --------
        Pirate[] pirates = {
                new Pirate("Luffy", 3000000, "Straw Hat"),
                new Pirate("Doflamingo", 340000, "Donquixote"),
                new Pirate("Sabo", 602000, "Revolutionary Army"),
                new Pirate("Shanks", 4048900, "Red Hair"),
                new Pirate("Marco", 1374000, "Whitebeard")
        };

        System.out.println("=== Comparator Bubble Sort (Pirate Bounties) ===");

        bubbleSortComparator(pirates,
                (a, b) -> Long.compare(a.getBounty(), b.getBounty()));
    }
}

// =========================
// CUSTOM CLASS
// =========================
class Pirate {

    private String name;
    private long bounty;
    private String crew;

    public Pirate(String name, long bounty, String crew) {
        this.name = name;
        this.bounty = bounty;
        this.crew = crew;
    }

    public long getBounty() {
        return bounty;
    }

    @Override
    public String toString() {
        return name + " | " + bounty + " | " + crew;
    }
}