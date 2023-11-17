import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

/**
 * This is the OddsFrontEnd class containing all the frontend methods
 */
public class OddsFrontEnd extends Application implements OddsFrontEndInterface {

    /**
     * Initialized backend
     */
    private OddsBackend backend;

    /**
     * Initialized an arraylist that holds strings. These strings will be the return
     * values of the prop percentages
     */
    static ArrayList<String> oddsList = new ArrayList<>();

    /**
     * Frontend Constructor
     * 
     * @param backend
     */
    public OddsFrontEnd(OddsBackend backend) {
        this.backend = backend;
    }

    /**
     * Default constructor needed for JavaFX
     */
    public OddsFrontEnd() {
        this.backend = new OddsBackend(new ArrayList<>());
    }

    /**
     * Sets up the stage/display and calls on the following methods when needed.
     * This is the main menu
     * 
     * @param primaryStage
     */
    public void start(Stage primaryStage) {
        BorderPane mainMenuRoot = new BorderPane();

        // Create buttons
        Button addPropButton = new Button("Add a prop");
        Button seeSavedPropsButton = new Button("See saved props");
        Button exitButton = new Button("Exit");

        // Set actions for buttons
        addPropButton.setOnAction(e -> addPropSelected(primaryStage));
        seeSavedPropsButton.setOnAction(e -> showSavedPropsSelected(oddsList, primaryStage));
        exitButton.setOnAction(e -> exitButton(primaryStage));

        // Add buttons to the top and bottom of the BorderPane
        mainMenuRoot.setTop(createTopPane(addPropButton, seeSavedPropsButton));
        mainMenuRoot.setBottom(createBottomPane(exitButton));

        // Load the image
        Image image = new Image("file:System.jpeg");

        // Create an ImageView and set the image
        ImageView imageView = new ImageView(image);

        // Add the ImageView to the center of the BorderPane
        mainMenuRoot.setCenter(imageView);

        // Create a scene and set it on the stage
        Scene mainMenuScene = new Scene(mainMenuRoot, 400, 300);
        primaryStage.setScene(mainMenuScene);

        // Set the stage title and show it
        primaryStage.setTitle("Odds Helper");
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();

    }

    /**
     * Creates top pane
     * 
     * @param addPropButton
     * @param seeSavedPropsButton
     * @return
     */
    private BorderPane createTopPane(Button addPropButton, Button seeSavedPropsButton) {
        BorderPane topPane = new BorderPane();

        // Align buttons to the left and right of the top pane
        topPane.setLeft(addPropButton);
        topPane.setRight(seeSavedPropsButton);

        return topPane;
    }

    /**
     * Creates bottom pane
     * 
     * @param exitButton
     * @return
     */
    private BorderPane createBottomPane(Button exitButton) {
        BorderPane bottomPane = new BorderPane();

        // Align the exit button to the left of the bottom pane
        bottomPane.setLeft(exitButton);
        // Center the exit button
        BorderPane.setAlignment(exitButton, Pos.CENTER);

        return bottomPane;
    }

    /**
     * Handles the UI for the add a prop screen
     * 
     * @param primaryStage
     */
    public void addPropSelected(Stage primaryStage) {
        clearCurrentGUI(primaryStage);
        BorderPane addPropRoot = new BorderPane();
        // Create labels
        Label playerNameLabel = new Label("Player Name:");
        Label propTypeLabel = new Label("Prop Type:");
        Label lineLabel = new Label("Line:");
        Label overOddsLabel = new Label("Over Odds:");
        Label underOddsLabel = new Label("Under Odds:");

        // Create text fields
        TextField playerNameField = new TextField();
        TextField propTypeField = new TextField();
        TextField lineField = new TextField();
        TextField overOddsField = new TextField();
        TextField underOddsField = new TextField();

        // Create Enter button (move to the right side)
        Button enterButton = new Button("Enter");
        enterButton.setOnAction(e -> addPropEnterButton(playerNameField, propTypeField, lineField, overOddsField,
                underOddsField, primaryStage));

        // Create Back button
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> backButton(primaryStage));

        // Create a VBox to hold labels, text fields, and buttons
        VBox vbox = new VBox(10); // 10 is the spacing between nodes
        vbox.getChildren().addAll(playerNameLabel, playerNameField, propTypeLabel, propTypeField, lineLabel, lineField,
                overOddsLabel, overOddsField, underOddsLabel, underOddsField, enterButton);
        vbox.setAlignment(Pos.CENTER_LEFT);

        // Set VBox to the center of the BorderPane
        addPropRoot.setCenter(vbox);
        addPropRoot.setBottom(backButton);
        BorderPane.setAlignment(backButton, Pos.BOTTOM_LEFT);

        Scene addUIScene = new Scene(addPropRoot, 400, 300);
        primaryStage.setScene(addUIScene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");

    }

    /**
     * Handles the UI after enter is pressed on the add prop UI
     * 
     * @param playerNameField
     * @param propTypeField
     * @param lineField
     * @param overOddsField
     * @param underOddsField
     * @param primaryStage
     */
    private void addPropEnterButton(TextField playerNameField, TextField propTypeField, TextField lineField,
            TextField overOddsField, TextField underOddsField, Stage primaryStage) {

        // Get the values from the text fields
        String playerName = playerNameField.getText();
        String propType = propTypeField.getText();

        // Parse other fields as needed (e.g., for numerical values)
        double line = Double.parseDouble(lineField.getText());
        double overOdds = Double.parseDouble(overOddsField.getText());
        double underOdds = Double.parseDouble(underOddsField.getText());
        String[] calculatedStrings = new String[2];

        // Gets the final string with prop percentages
        calculatedStrings = backend.getPropPercentages(underOdds, overOdds, line, propType, playerName);

        // Sets each of the two strings to its own variable
        String higherOddsString = new String(calculatedStrings[0]);
        String lowerOddsString = new String(calculatedStrings[1]);

        // Clears and starts process for new GUI
        clearCurrentGUI(primaryStage);
        BorderPane addPropRoot = new BorderPane();

        // Creates labels with the final return string
        Label higherOddsLabel = new Label(higherOddsString);
        Label lowerOddsLabel = new Label(lowerOddsString);

        // Save prop button handling
        Button savePropButton = new Button("Save Prop");
        savePropButton.setOnAction(e -> savePropSelected(oddsList, higherOddsString, primaryStage));

        // Back button handling
        Button propShownScreenBackButton = new Button("Back");
        propShownScreenBackButton.setOnAction(e -> backButton(primaryStage));

        // Creates a vbox and sets position for the labels and save button
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(higherOddsLabel, lowerOddsLabel, savePropButton);
        vbox.setAlignment(Pos.CENTER);
        addPropRoot.setCenter(vbox);

        // Sets location of back button
        addPropRoot.setBottom(propShownScreenBackButton);
        BorderPane.setAlignment(propShownScreenBackButton, Pos.BOTTOM_LEFT);

        // Sets scene
        Scene propReturnedUIScene = new Scene(addPropRoot, 400, 300);
        primaryStage.setScene(propReturnedUIScene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");

    }

    /**
     * Sets the display and calls necessary backend methods to save prop
     * 
     * @param oddsList
     * @param highestOddsString
     * @paran primaryStage
     */
    public void savePropSelected(ArrayList<String> oddsList, String highestOddsString, Stage primaryStage) {
        // Calls backend method to actually save prop to the oddsList
        backend.saveHighestPropOdds(oddsList, highestOddsString);

        // Clears current GUI
        clearCurrentGUI(primaryStage);

        BorderPane savePropSelectedRoot = new BorderPane();

        Label savedConfirmationLabel = new Label("Prop Saved");

        Button savePropSelectedScreenBackButton = new Button("Back");
        savePropSelectedScreenBackButton.setOnAction(e -> backButton(primaryStage));

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(savedConfirmationLabel);
        vbox.setAlignment(Pos.CENTER);
        savePropSelectedRoot.setCenter(vbox);

        savePropSelectedRoot.setBottom(savePropSelectedScreenBackButton);
        BorderPane.setAlignment(savePropSelectedScreenBackButton, Pos.BOTTOM_LEFT);

        Scene propSavedUIScene = new Scene(savePropSelectedRoot, 400, 300);
        primaryStage.setScene(propSavedUIScene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
    }

    /**
     * Sets the display and calls necessary backend methods to see saved props
     * 
     * @param oddsList
     * @param primaryStage
     */
    public void showSavedPropsSelected(ArrayList<String> oddsList, Stage primaryStage) {
        int length = oddsList.size();

        // If there are no saved props
        if (length == 0) {
            clearCurrentGUI(primaryStage);
            BorderPane noSavedPropsRoot = new BorderPane();

            Label noSavedPropsLabel = new Label("No Saved Props");

            Button noSavedPropBackButton = new Button("Back");
            noSavedPropBackButton.setOnAction(e -> backButton(primaryStage));

            VBox vbox = new VBox(10);
            vbox.getChildren().addAll(noSavedPropsLabel, noSavedPropBackButton);
            vbox.setAlignment(Pos.CENTER);
            noSavedPropsRoot.setCenter(vbox);

            Scene noSavedPropsUIScene = new Scene(noSavedPropsRoot, 400, 300);
            primaryStage.setScene(noSavedPropsUIScene);
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("");

            // If there are saved props
        } else if (length > 0) {
            clearCurrentGUI(primaryStage);
            BorderPane savedPropsRoot = new BorderPane();

            Button savedPropScreenBackButton = new Button("Back");
            savedPropScreenBackButton.setOnAction(e -> backButton(primaryStage));

            Button clearSavedPropsButton = new Button("Clear Saved Props");
            clearSavedPropsButton.setOnAction(e -> clearSavedPropsSelected(oddsList, primaryStage));

            VBox vbox = new VBox(10);
            for (int i = 0; i < length; i++) {
                String prop = oddsList.get(i);
                Label savedPropLabel = new Label(prop);
                vbox.getChildren().add(savedPropLabel);
            }
            vbox.setAlignment(Pos.CENTER);
            savedPropsRoot.setCenter(vbox);

            savedPropsRoot.setLeft(savedPropScreenBackButton);
            BorderPane.setAlignment(savedPropScreenBackButton, Pos.BOTTOM_LEFT);

            savedPropsRoot.setRight(clearSavedPropsButton);
            BorderPane.setAlignment(clearSavedPropsButton, Pos.BOTTOM_RIGHT);

            Scene savedPropsUIScene = new Scene(savedPropsRoot, 400, 300);
            primaryStage.setScene(savedPropsUIScene);
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("");

        }
    }

    /**
     * Sets the display and calls necessary backend methods to clear saved props
     * 
     * @param oddsList
     * @param primaryStage
     */
    public void clearSavedPropsSelected(ArrayList<String> oddsList, Stage primaryStage) {
        clearCurrentGUI(primaryStage);
        BorderPane clearSavedPropsRoot = new BorderPane();

        Button confirmButton = new Button("PRESS TO CONFIRM");
        confirmButton.setOnAction(e -> confirmButtonSelected(primaryStage, oddsList));

        Button clearConfirmScreenBackButton = new Button("Back");
        clearConfirmScreenBackButton.setOnAction(e -> backButton(primaryStage));

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(confirmButton);
        vbox.setAlignment(Pos.CENTER);
        clearSavedPropsRoot.setCenter(vbox);

        clearSavedPropsRoot.setBottom(clearConfirmScreenBackButton);
        BorderPane.setAlignment(clearConfirmScreenBackButton, Pos.BOTTOM_LEFT);

        Scene confirmClearUIScene = new Scene(clearSavedPropsRoot, 400, 300);
        primaryStage.setScene(confirmClearUIScene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");

    }

    /**
     * Handles the UI if the confirm button is selected when trying to clear saved
     * props
     * 
     * @param primaryStage
     * @param oddsList
     */
    public void confirmButtonSelected(Stage primaryStage, ArrayList<String> oddsList) {
        backend.clearList(oddsList);
        clearCurrentGUI(primaryStage);
        BorderPane confirmedButtonSelectedRoot = new BorderPane();

        Label succesfullClearLabel = new Label("Succesfully Cleared");

        Button successScreenBackButton = new Button("Back");
        successScreenBackButton.setOnAction(e -> backButton(primaryStage));

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(succesfullClearLabel);
        vbox.setAlignment(Pos.CENTER);
        confirmedButtonSelectedRoot.setCenter(vbox);

        confirmedButtonSelectedRoot.setBottom(successScreenBackButton);
        BorderPane.setAlignment(successScreenBackButton, Pos.BOTTOM_LEFT);

        Scene propsClearedUIScene = new Scene(confirmedButtonSelectedRoot, 400, 300);
        primaryStage.setScene(propsClearedUIScene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
    }

    /**
     * Closes app (close stage)
     * 
     * @param primaryStage
     */
    public void exitButton(Stage primaryStage) {
        primaryStage.close();
    }

    /**
     * Clears the GUI
     * 
     * @param primaryStage
     */
    private void clearCurrentGUI(Stage primaryStage) {
        BorderPane emptyPane = new BorderPane();
        Scene emptyScene = new Scene(emptyPane, 400, 300);
        primaryStage.setScene(emptyScene);
    }

    /**
     * Sets UI to main screen
     * 
     * @param primaryStage
     */
    public void backButton(Stage primaryStage) {
        clearCurrentGUI(primaryStage);
        start(primaryStage);
    }

    /**
     * Deeclares frontend and backend, and starts the main command loop
     * 
     * @param args
     */
    public static void main(String[] args) {
        OddsBackend back = new OddsBackend(oddsList);

        OddsFrontEnd frontend = new OddsFrontEnd(back); // Use the constructor

        launch(args);
    }

}
