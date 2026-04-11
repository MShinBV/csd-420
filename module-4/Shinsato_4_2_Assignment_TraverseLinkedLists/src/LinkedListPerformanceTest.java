/* Miles Shinsato, 04/11/2026, CSD420-308A, Module 4.2 Assignment: Iterator vs get(index) method */

// This program will be using a LinkedList of 50,000 then 500,000 integers stored, to test traverse times for iterator vs. get(index) method

import java.util.LinkedList;
import java.util.Iterator;

public class LinkedListPerformanceTest {

    public static void main(String[] args) {

        // Running tests for 50,000 and 500,000 elements
        runTest(50000);
        runTest(500000);
    }

    // Runs the full test for a given list size
    public static void runTest(int size) {

        // Setting the message to show the user how many elements are being tested with
        System.out.println("\n===== Testing with " + size + " elements =====");

        // Create and fill the LinkedList with values
        LinkedList<Integer> list = createList(size);

        // Time Iterator traversal
        long iteratorTime = traverseWithIterator(list);

        // Time get(index) traversal
        long getTime = traverseWithGet(list);

        // Setting print message to showcase the results of the tests
        // Time will be given back in milliseconds and rounded after the 2nd decimal
        System.out.printf("Iterator time: %.2f ms%n", iteratorTime / 1_000_000.0);
        System.out.printf("get(index) time: %.2f ms%n", getTime / 1_000_000.0);

        // Check that both methods went through the same data
        long iteratorSum = sumWithIterator(list);
        long getSum = sumWithGet(list);

        // Print message to confirm that data was traversed correctly
        System.out.println("Traversal correct: " + (iteratorSum == getSum));
    }

    // Creates and fills the LinkedList
    public static LinkedList<Integer> createList(int size) {

        LinkedList<Integer> list = new LinkedList<>();

        // Add numbers from 0 to size - 1
        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        return list;
    }

    // Traverses using Iterator (faster for LinkedList)
    public static long traverseWithIterator(LinkedList<Integer> list) {

        long startTime = System.nanoTime();

        Iterator<Integer> iterator = list.iterator();

        while (iterator.hasNext()) {
            iterator.next();
        }

        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    // Traverses using get(index) (slow for LinkedList)
    public static long traverseWithGet(LinkedList<Integer> list) {

        long startTime = System.nanoTime();

        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }

        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    // Sums values using Iterator (used to check correctness)
    public static long sumWithIterator(LinkedList<Integer> list) {

        long sum = 0;

        Iterator<Integer> iterator = list.iterator();

        while (iterator.hasNext()) {
            sum += iterator.next();
        }

        return sum;
    }

    // Sums values using get(index) (used to check correctness)
    public static long sumWithGet(LinkedList<Integer> list) {

        long sum = 0;

        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }

        return sum;
    }
}

/*
The results from multiple test runs show a clear performance difference between Iterator traversal and get(index) traversal in a LinkedList.
The Iterator remains consistently fast for both 50,000 and 500,000 elements, my results were taking approximately under 5 milliseconds in all runs.
This shows that Iterator traversal scales linearly (O(n)) because each element is visited only once in sequence.

In contrast, the get(index) method is significantly slower and becomes worse as the list size increases. At 50,000 elements, it takes around 750 milliseconds.
At 500,000 elements, it increases dramatically to over 82,000 milliseconds (about 82 seconds). This happens because each call to get(i) must traverse the list from the beginning, resulting in repeated scans and O(n^2) time complexity.

The correctness check in each of the several runs returned true, confirming that both traversal methods successfully accessed all elements in the list.

Multiple test runs confirm the same pattern, showing that Iterator is much more efficient for LinkedList traversal, while get(index) becomes extremely inefficient as the dataset grows.
 */