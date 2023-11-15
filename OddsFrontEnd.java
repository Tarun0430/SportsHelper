import java.util.Scanner;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.application.Platform;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.Arrays;

public class OddsFrontEnd extends Application implements OddsFrontEndInterface{
 /**
     * Constructor
     * @param backend
     * @param scanner
     */
     //public OddsFrontend(OddsBackEnd backend) {
       // this.backend = backend;
   // }   

     /**
     * Sets up the stage/display and calls on the following methods when needed
     * @param primaryStage
     */
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Create buttons
        Button addPropButton = new Button("Add a prop");
        Button seeSavedPropsButton = new Button("See saved props");
        Button exitButton = new Button("Exit");

        // Set actions for buttons
        addPropButton.setOnAction(e -> handleAddPropButton());
        seeSavedPropsButton.setOnAction(e -> handleSeeSavedPropsButton());
        exitButton.setOnAction(e -> primaryStage.close());

        // Add buttons to the top and bottom of the BorderPane
        root.setTop(createTopPane(addPropButton, seeSavedPropsButton));
        root.setBottom(createBottomPane(exitButton));

        // Create a scene and set it on the stage
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);

        // Set the stage title and show it
        primaryStage.setTitle("Prop Manager");
        primaryStage.show();

    }
        private void handleAddPropButton() {
        // Implement the action for "Add a prop" button
        System.out.println("Add a prop button clicked");
    }

    private void handleSeeSavedPropsButton() {
        // Implement the action for "See saved props" button
        System.out.println("See saved props button clicked");
    }

    private BorderPane createTopPane(Button addPropButton, Button seeSavedPropsButton) {
        BorderPane topPane = new BorderPane();

        // Align buttons to the left and right of the top pane
        topPane.setLeft(addPropButton);
        topPane.setRight(seeSavedPropsButton);

        return topPane;
    }

    private BorderPane createBottomPane(Button exitButton) {
        BorderPane bottomPane = new BorderPane();

        // Align the exit button to the left of the bottom pane
        bottomPane.setLeft(exitButton);
        // Center the exit button
        BorderPane.setAlignment(exitButton, Pos.CENTER);

        return bottomPane;
    }


     /**
     * Sets the display for if Add prop is selected
     */
    public void AddPropSelected(){

    }

    /**
     * Sets the display and calls necessary backend methods to save prop
     */
    public void savePropSelected(){

    }

    /**
     * Sets the display and calls necessary backend methods to see saved props
     */
    public void showSavedPropsSelected(){

    }

    /**
     * Sets the display and calls necessary backend methods to clear saved props
     */
    public void clearSavedPropsSelected(){

    }

    /**
     * Takes user back to main menu
     */
    public void backButton(){

    }

    /**
     * Closes app (close stage)
     */
    public void exitButton(){

    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
