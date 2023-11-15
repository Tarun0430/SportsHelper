import java.util.ArrayList;
public interface OddsBackendInterface {

    /**
     * Gets a string that returns the percentages of the prop over and unders
     * @param underOdds
     * @param overOdds
     * @return String containing both prop information
     */
    public String[] getPropPercentages(double underOdds, double overOdds);

    /**
     * Adds highest prop odds to arraylist
     * @param propList
     * @param highestOdd
     */
    public void saveHighestPropOdds(ArrayList propList, String highestOdd);

    /**
     * 
     * @param propList
     */
    public void sortedList(ArrayList propList);

    /**
     * Clears the array list
     * @param propList
     */
    public void clearList(ArrayList propList);
}