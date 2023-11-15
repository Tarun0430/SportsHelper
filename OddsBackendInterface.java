import java.util.ArrayList;
public interface OddsBackendInterface {

    /**
     * Gets a string that returns the percentages of the prop over and unders
     * @param underOdds
     * @param overOdds
     * @return String containing both prop information
     */
    public String[] getPropPercentages(double underOdds, double overOdds, double propLine, double propType, String playerName);

    /**
     * Adds highest prop odds to arraylist
     * @param propList
     * @param highestOdd
     */
    public void saveHighestPropOdds(ArrayList<String> oddsList, String highestOdds);

    /**
     * 
     * @param propList
     */
    public void sortedList(ArrayList<String> resultList);

    /**
     * Clears the array list
     * @param propList
     */
    public void clearList(ArrayList<String> oddsList);
}