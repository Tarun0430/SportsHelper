import java.util.ArrayList;

public class OddsBackend implements OddsBackendInterface {
    /**
     * 
     */
    private ArrayList<String> propList;

    /**
     * 
     */
    public OddsBackend() {
       this.propList = new ArrayList<>();
    }

     /**
     * Gets a string that returns the percentages of the prop over and unders
     * @param underOdds
     * @param overOdds
     * @return String containing both prop information
     */
    public String[] getPropPercentages(double underOdds, double overOdds, double propLine, String propType, String playerName){
        String[] resultStrings = new String[2];
        double underOddsPercent = 0;
        double overOddsPercent = 0;
        String higherOddsType = "";
        String lowerOddsType = "";
        if (overOdds < underOdds) {
            higherOddsType = "over";
            lowerOddsType = "under";
          } else if (underOdds < overOdds) {
            higherOddsType = "under";
            lowerOddsType = "over";
          } else if (underOdds == overOdds) {
            higherOddsType = "under";
            lowerOddsType = "over";
          }

        if (overOdds < 0) {
            overOddsPercent = NegativeOddsPercent(overOdds);
            // Test System.out.println(odds1Percent);
          } else if (overOdds > 0) {
            overOddsPercent = PositiveOddsPercent(overOdds);
            // Test System.out.println(odds1Percent);
          }
           if (underOdds < 0) {
            underOddsPercent = NegativeOddsPercent(overOdds);
            // Test System.out.println(odds1Percent);
          } else if (overOdds > 0) {
            underOddsPercent = PositiveOddsPercent(overOdds);
            // Test System.out.println(odds1Percent);
          }
          double trueHighOdds = truePercent(overOddsPercent, underOddsPercent);
          double trueLowOdds = 100 - trueHighOdds;
          trueHighOdds = Math.round(trueHighOdds * 100.0) / 100.0;
          trueLowOdds = Math.round(trueLowOdds * 100.0) / 100.0;

          resultStrings[0] = "There is a " + trueHighOdds + "% chance for " + playerName + " going "
          + higherOddsType + " " + propLine + " " + propType;
          resultStrings[1] = "There is a " + trueLowOdds + "% chance for " + playerName + " going "
          + lowerOddsType + " " + propLine + " " + propType;

        return resultStrings;
    }

    /**
     * Adds highest prop odds to arraylist
     * @param propList
     * @param highestOdd
     */
    public void saveHighestPropOdds(ArrayList<String> oddsList, String highestOdds) {
        oddsList.add(highestOdds);
        if (oddsList.size() > 1) {
            sortedList(oddsList);
          }

    }

    /**
     * 
     * @param resultList
     */
    public void sortedList(ArrayList<String> resultList) {
        int n = resultList.size();
        for (int i = 0; i < n - 1; i++) {
          for (int j = 0; j < n - i - 1; j++) {
            String result1 = resultList.get(j);
            String result2 = resultList.get(j + 1);
            double percentage1 = Double.parseDouble(result1.split(" ")[3].replace("%", ""));;
            double percentage2 = Double.parseDouble(result2.split(" ")[3].replace("%", ""));
            if (percentage1 < percentage2) {
              resultList.set(j, result2);
              resultList.set(j + 1, result1);
            }
          }
        }
      }


    /**
     * Clears the array list
     * @param oddsList
     */
    public void clearList(ArrayList<String> oddsList) {
        oddsList.clear();
    }
    
    public double NegativeOddsPercent(double negNumber) {
        double negValue = Math.abs((double) negNumber) / (100 + Math.abs((double) negNumber));
        return negValue;
      }

      public double PositiveOddsPercent(double posNumber) {
        double posValue = 100 / (100 + (double) posNumber);
        return posValue;
      }

      public static double truePercent(double lowerPercent, double higherPercent) {
        double truehigherPercent = 0.0;
        if (lowerPercent < higherPercent) {
          truehigherPercent = higherPercent / (higherPercent + lowerPercent);
          truehigherPercent = truehigherPercent * 100;
          return truehigherPercent;
        } else {
          truehigherPercent = lowerPercent / (higherPercent + lowerPercent);
          truehigherPercent = truehigherPercent * 100;
          return truehigherPercent;
        }
    
      }

}
