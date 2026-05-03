// Miles Shinsato, 05/01/2026, CSD420-308A, Module 7.2 Assignment: Styled Circles

// This program will be creating a window that shows 4 different circles in a few different styles outlined in the CSS file

package com.example.shinsato_7_2_assignment_circles;

// necessary imports for JavaFX application, layout, shapes, and positioning
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class StyledCircles extends Application {

    @Override
    public void start(Stage primaryStage) {

        // create four circles, radius is 50px each
        Circle circle1 = new Circle(50);
        Circle circle2 = new Circle(50);
        Circle circle3 = new Circle(50);
        Circle circle4 = new Circle(50);

        // apply CSS class to circle1 and circle2
        // this class is defined in mystyle.css to be white with a black outline
        circle1.getStyleClass().add("white-fill-black-stroke");
        circle2.getStyleClass().add("white-fill-black-stroke");

        // apply CSS class for circle3 and circle4
        // setting to override default style and fill with proper color
        circle3.setId("red-circle");
        circle4.setId("green-circle");

        // layout (horizontal row)
        // circles will be spaced 20px apart
        HBox root = new HBox(20);

        // aligning the row of circles to be centered
        root.setAlignment(Pos.CENTER);

        // adding all circles to layout container
        root.getChildren().addAll(circle1, circle2, circle3, circle4);

        // create scene 500px x 160px in size
        Scene scene = new Scene(root, 500, 160);

        // link external CSS file
        // NOTE: mystyle.css must be in the correct resource folder
        scene.getStylesheets().add("mystyle.css");

        // setting the name for the window
        primaryStage.setTitle("Styled Circles");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}