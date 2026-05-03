/* Miles Shinsato, 05/02/2026, CSD420-308A, Module 8.2 Assignment: Multithreading */

// This program demonstrates multithreading using three concurrent threads generating characters
// One thread generates characters, one generates number digits, one generates random characters
// Uses Platform.runLater for safe UI updates and join() to ensure all threads complete
// Output is placed into JavaFX text area as they are generated

import java.util.Random;

// JavaFX imports
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MilesShinsatoThreeThreads extends Application {

    // establishing how many characters each thread will generate, a 10,000 character output
    private static final int COUNT = 10_000;

    // shared lock object so threads don't print over each other at the same time
    // keeps output from getting messy or partially overlapping
    private static final Object lock = new Object();

    // TextArea will act as our output window
    // static so all threads can access it
    private static TextArea outputArea;

    public static void main(String[] args) {

        // launch JavaFX application instead of running directly
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // create a text area to display output
        outputArea = new TextArea();
        outputArea.setWrapText(true);

        // making scene output area full and scalable
        VBox root = new VBox(outputArea);
        VBox.setVgrow(outputArea, javafx.scene.layout.Priority.ALWAYS);

        // setting Scene size properties and name
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Thread Output Window");
        primaryStage.setScene(scene);
        primaryStage.show();

        // run threads in a separate method so UI doesn't freeze
        runThreads();
    }


    private void runThreads() {

        // create each thread and give it its specific task
        Thread letterThread = new Thread(new LetterTask());
        Thread numberThread = new Thread(new NumberTask());
        Thread symbolThread = new Thread(new SymbolTask());

        // start all threads
        // will run at the same time
        letterThread.start();
        numberThread.start();
        symbolThread.start();

        // adding basic test code to make sure all threads finish
        // join() forces main to wait until each thread is done
        new Thread(() -> {
            try {
                letterThread.join();
                numberThread.join();
                symbolThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // update UI safely after all threads finish
            Platform.runLater(() -> outputArea.appendText("\n\nAll threads finished."));
            System.out.println("\n\nAll threads finished.");
        }).start();
    }

    // helper method to print to both console and JavaFX window
    private static void printOutput(String text) {
        synchronized (lock) {
            System.out.print(text);

            // Platform.runLater ensures UI updates happen on JavaFX thread
            Platform.runLater(() -> outputArea.appendText(text));
        }
    }

    // =========================
    // Thread 1: Letters
    // =========================
    static class LetterTask implements Runnable {

        public void run() {

            // Random object to generate random values
            Random rand = new Random();

            // loop 10,000 times (one character per loop)
            for (int i = 0; i < COUNT; i++) {

                // generate a random lowercase letter
                char letter = (char) ('a' + rand.nextInt(26));

                printOutput(String.valueOf(letter));
            }
        }
    }

    // =========================
    // Thread 2: Numbers
    // =========================
    static class NumberTask implements Runnable {

        public void run() {

            Random rand = new Random();

            for (int i = 0; i < COUNT; i++) {

                // generate a random number from 0 to 9
                int number = rand.nextInt(10);

                printOutput(String.valueOf(number));
            }
        }
    }

    // =========================
    // Thread 3: Symbols
    // =========================
    static class SymbolTask implements Runnable {

        // array of allowed special characters
        private final char[] symbols = {'!', '@', '#', '$', '%', '&', '*'};

        public void run() {

            Random rand = new Random();

            for (int i = 0; i < COUNT; i++) {

                // pick a random symbol from the array
                char symbol = symbols[rand.nextInt(symbols.length)];

                printOutput(String.valueOf(symbol));
            }
        }
    }
}