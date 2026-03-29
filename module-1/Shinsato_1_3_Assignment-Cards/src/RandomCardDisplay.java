/* Miles Shinsato, 03/28/2026 CSD420-308A, Module 1.3 Assignment: JavaFX Random Card Display */

// This program will be randomly generating 4 random card images with a refresh button to refresh them
// Images of cards are store inside a "cards" folder.

// Import JavaFX core application class (required for all JavaFX programs)
import javafx.application.Application;

// Used to create the window contents
import javafx.scene.Scene;

// Button control for refreshing cards
import javafx.scene.control.Button;

// Classes used for loading and displaying images
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// Layout containers for organizing components
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

// JavaFX window (stage)
import javafx.stage.Stage;

// File handling for loading images
import java.io.FileInputStream;

// Used to handle file loading errors
import java.io.FileNotFoundException;

// Collections used for creating list of cards, randomizing card selection, and interface for lists
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Creating main class (RandomCardDisplay) and extending Application
public class RandomCardDisplay extends Application {

    // Constant representing total number of cards (TOTAL_CARDS) in the deck
    private static final int TOTAL_CARDS = 52;

    // Array to hold the 4 card image display areas
    private ImageView[] cardShowcase = new ImageView[4];

    // Main method launches the JavaFX application
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // VBox (mainRow) arranges items vertically with 10px spacing
        VBox mainRow = new VBox(10);

        // HBox (cardBox) arranges card images horizontally with spacing
        HBox cardBox = new HBox(10);

        // Using Pos.CENTER to center Vbox (mainRow) and Hbox (cardBox)
        Pos center = Pos.CENTER;
        mainRow.setAlignment(center);
        cardBox.setAlignment(center);

        // Create 4 ImageView objects to hold the card images
        for (int i = 0; i < 4; i++) {

            // Create empty image container
            cardShowcase[i] = new ImageView();

            // Set consistent card size for display (100 x 150)
            cardShowcase[i].setFitWidth(100);
            cardShowcase[i].setFitHeight(150);

            // Using set PreserveRatio to keep proportions correct
            cardShowcase[i].setPreserveRatio(true);

            // Add each card display area to the horizontal layout
            cardBox.getChildren().add(cardShowcase[i]);
        }

        // Create button to refresh cards
        Button refreshButton = new Button("Refresh Cards");

        // Lambda expression handles button click event
        // When clicked, new random cards are displayed
        refreshButton.setOnAction(e -> displayRandomCards());

        // Show 4 random cards when program first starts
        displayRandomCards();

        // Add card row and button to main layout
        mainRow.getChildren().addAll(cardBox, refreshButton);

        // Create scene (windowContents)
        Scene windowContents = new Scene(mainRow, 450, 250);

        // Set window title
        primaryStage.setTitle("Random Card Display");

        // Attach scene to stage
        primaryStage.setScene(windowContents);

        // Set minimum window size so cards don't disappear off the screen
        primaryStage.setMinWidth(450);
        primaryStage.setMinHeight(250);

        // Display window
        primaryStage.show();
    }

    // Method (displayRandomCards) responsible for selecting and displaying 4 random cards
    private void displayRandomCards() {

        // List used to store card numbers 1–52
        List<Integer> cardNumbers = new ArrayList<>();

        // Populate list with all card numbers
        for (int i = 1; i <= TOTAL_CARDS; i++) {
            cardNumbers.add(i);
        }

        // Randomize order of cards
        Collections.shuffle(cardNumbers);

        // Select first 4 cards from shuffled list
        for (int i = 0; i < 4; i++) {

            try {

                // Build file path dynamically
                String filePath = "cards/" + cardNumbers.get(i) + ".png";

                // Load image from file
                Image image = new Image(new FileInputStream(filePath));

                // Display image in ImageView
                cardShowcase[i].setImage(image);

            }
            catch (FileNotFoundException ex) {

                // Error message if image cannot be located
                System.out.println("Card image not found: " + ex.getMessage());
            }
        }
    }
}