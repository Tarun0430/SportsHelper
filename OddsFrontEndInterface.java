import java.util.ArrayList;
import javafx.stage.Stage;

public interface OddsFrontEndInterface {

    /**
     * Constructor
     * @param backend
     * @param scanner
     */
     //public OddsFrontend(OddsBackEnd backend, Scanner scanner) {
       // this.backend = backend;
        //this.scanner = scanner;
    //}


    /**
     * Sets up the stage/display and calls on the following methods when needed
     * @param primaryStage
     */
    public void start(Stage primaryStage);

    /**
     * Sets the display for if Add prop is selected
     */
    public void addPropSelected(Stage primaryStage);

    /**
     * Sets the display and calls necessary backend methods to save prop
     */
    public void savePropSelected(ArrayList<String> oddsList, String highestOdds, Stage primaryStage);

    /**
     * Sets the display and calls necessary backend methods to see saved props
     */
    public void showSavedPropsSelected(ArrayList<String> oddsList, Stage primaryStage);

    /**
     * Sets the display and calls necessary backend methods to clear saved props
     */
    public void clearSavedPropsSelected(ArrayList<String> oddsList, Stage primaryStage);

    /**
     * Takes user back to main menu
     */
    public void backButton(Stage primaryStage);

    /**
     * Closes app (close stage)
     */
    public void exitButton(Stage primaryStage);




    
}