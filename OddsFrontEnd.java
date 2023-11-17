import java.util.Scanner;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is the OddsFrontEnd class containing all the frontend methods
 */
public class OddsFrontEnd extends Application implements OddsFrontEndInterface {
    private OddsBackend backend;
    static ArrayList<String> oddsList = new ArrayList<>();

    public OddsFrontEnd(OddsBackend backend) {
        this.backend = backend;
    }

    public OddsFrontEnd() {
        this.backend = new OddsBackend(new ArrayList<>());
    }

    /**
     * Sets up the stage/display and calls on the following methods when needed
     * 
     * @param primaryStage
     */
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Create buttons
        Button addPropButton = new Button("Add a prop");
        Button seeSavedPropsButton = new Button("See saved props");
        Button exitButton = new Button("Exit");

        // Set actions for buttons
        addPropButton.setOnAction(e -> addPropSelected(primaryStage));
        seeSavedPropsButton.setOnAction(e -> showSavedPropsSelected(oddsList, primaryStage));
        exitButton.setOnAction(e -> exitButton(primaryStage));

        // Add buttons to the top and bottom of the BorderPane
        root.setTop(createTopPane(addPropButton, seeSavedPropsButton));
        root.setBottom(createBottomPane(exitButton));

        // Load the image
        Image image = new Image("file:System.jpeg");

        // Create an ImageView and set the image
        ImageView imageView = new ImageView(image);

        // Add the ImageView to the center of the BorderPane
        root.setCenter(imageView);

        // Create a scene and set it on the stage
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);

        // Set the stage title and show it
        primaryStage.setTitle("Prop Manager");
        primaryStage.show();

    }

    /**
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
     * 
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

        Scene addPropScene = new Scene(addPropRoot, 400, 300);
        primaryStage.setScene(addPropScene);

    }

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

        calculatedStrings = backend.getPropPercentages(underOdds, overOdds, line,
                propType, playerName);

        String higherOddsString = new String(calculatedStrings[0]);
        String lowerOddsString = new String(calculatedStrings[1]);

        // Clears and starts process for new GUI
        clearCurrentGUI(primaryStage);
        BorderPane addPropRoot = new BorderPane();

        Label higherOddsLabel = new Label(higherOddsString);
        Label lowerOddsLabel = new Label(lowerOddsString);

        Button savePropButton = new Button("Save Prop");
        savePropButton.setOnAction(e -> savePropSelected(oddsList, higherOddsString, primaryStage));

        Button propShownScreenBackButton = new Button("Back");
        propShownScreenBackButton.setOnAction(e -> backButton(primaryStage));

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(higherOddsLabel, lowerOddsLabel, savePropButton);
        vbox.setAlignment(Pos.CENTER);
        addPropRoot.setCenter(vbox);

        addPropRoot.setBottom(propShownScreenBackButton);
        BorderPane.setAlignment(propShownScreenBackButton, Pos.BOTTOM_LEFT);

        Scene addPropScene = new Scene(addPropRoot, 400, 300);
        primaryStage.setScene(addPropScene);

    }

    /**
     * Sets the display and calls necessary backend methods to save prop
     */
    public void savePropSelected(ArrayList<String> oddsList, String highestOdds, Stage primaryStage) {
        backend.saveHighestPropOdds(oddsList, highestOdds);
        clearCurrentGUI(primaryStage);
        BorderPane addPropRoot = new BorderPane();

        Label savedConfirmationLabel = new Label("Prop Saved");

        Button savePropSelectedScreenBackButton = new Button("Back");
        savePropSelectedScreenBackButton.setOnAction(e -> backButton(primaryStage));

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(savedConfirmationLabel);
        vbox.setAlignment(Pos.CENTER);
        addPropRoot.setCenter(vbox);

        addPropRoot.setBottom(savePropSelectedScreenBackButton);
        BorderPane.setAlignment(savePropSelectedScreenBackButton, Pos.BOTTOM_LEFT);

        Scene addPropScene = new Scene(addPropRoot, 400, 300);
        primaryStage.setScene(addPropScene);
    }

    /**
     * Sets the display and calls necessary backend methods to see saved props
     */
    public void showSavedPropsSelected(ArrayList<String> oddsList, Stage primaryStage) {
        int length = oddsList.size();
        if (length == 0) {
            clearCurrentGUI(primaryStage);
            BorderPane addPropRoot = new BorderPane();

            Label noSavedPropsLabel = new Label("No Saved Props");

            Button noSavedPropBackButton = new Button("Back");
            noSavedPropBackButton.setOnAction(e -> backButton(primaryStage));

            VBox vbox = new VBox(10);
            vbox.getChildren().addAll(noSavedPropsLabel, noSavedPropBackButton);
            vbox.setAlignment(Pos.CENTER);
            addPropRoot.setCenter(vbox);

            Scene addPropScene = new Scene(addPropRoot, 400, 300);
            primaryStage.setScene(addPropScene);
        } else if (length > 0) {
            clearCurrentGUI(primaryStage);
            BorderPane addPropRoot = new BorderPane();

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
            addPropRoot.setCenter(vbox);

            addPropRoot.setLeft(savedPropScreenBackButton);
            BorderPane.setAlignment(savedPropScreenBackButton, Pos.BOTTOM_LEFT);

            addPropRoot.setRight(clearSavedPropsButton);
            BorderPane.setAlignment(clearSavedPropsButton, Pos.BOTTOM_RIGHT);

            Scene addPropScene = new Scene(addPropRoot, 400, 300);
            primaryStage.setScene(addPropScene);

        }
    }

    /**
     * Sets the display and calls necessary backend methods to clear saved props
     */
    public void clearSavedPropsSelected(ArrayList<String> oddsList, Stage primaryStage) {
        clearCurrentGUI(primaryStage);
        BorderPane addPropRoot = new BorderPane();

        Button confirmButton = new Button("PRESS TO CONFIRM");
        confirmButton.setOnAction(e -> confirmButtonSelected(primaryStage, oddsList));

        Button clearConfirmScreenBackButton = new Button("Back");
        clearConfirmScreenBackButton.setOnAction(e -> backButton(primaryStage));

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(confirmButton);
        vbox.setAlignment(Pos.CENTER);
        addPropRoot.setCenter(vbox);

        addPropRoot.setBottom(clearConfirmScreenBackButton);
        BorderPane.setAlignment(clearConfirmScreenBackButton, Pos.BOTTOM_LEFT);

        Scene addPropScene = new Scene(addPropRoot, 400, 300);
        primaryStage.setScene(addPropScene);

    }

    public void confirmButtonSelected(Stage primaryStage, ArrayList<String> oddsList) {
        backend.clearList(oddsList);
        clearCurrentGUI(primaryStage);
        BorderPane addPropRoot = new BorderPane();

        Label succesfullClearLabel = new Label("Succesfully Cleared");

        Button successScreenBackButton = new Button("Back");
        successScreenBackButton.setOnAction(e -> backButton(primaryStage));

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(succesfullClearLabel);
        vbox.setAlignment(Pos.CENTER);
        addPropRoot.setCenter(vbox);

        addPropRoot.setBottom(successScreenBackButton);
        BorderPane.setAlignment(successScreenBackButton, Pos.BOTTOM_LEFT);
    }

    /**
     * Closes app (close stage)
     */
    public void exitButton(Stage primaryStage) {
        primaryStage.close();
    }

    /**
     * 
     * @param primaryStage
     */
    private void clearCurrentGUI(Stage primaryStage) {
        BorderPane emptyPane = new BorderPane();
        Scene emptyScene = new Scene(emptyPane, 400, 300);
        primaryStage.setScene(emptyScene);
    }

    /**
     * 
     */
    public void backButton(Stage primaryStage) {
        clearCurrentGUI(primaryStage);
        start(primaryStage);
    }

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        OddsBackend back = new OddsBackend(oddsList);

        OddsFrontEnd frontend = new OddsFrontEnd(back); // Use the constructor
        launch(args);
    }

}
