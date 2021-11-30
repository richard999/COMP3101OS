
import java.util.ArrayList;
import java.util.List;

public class Utility
{
    /**
     * 
     */
    public static List<Row> deepCopy(List<Row> oldList)
    {
        /**
         * constructor for creating a list to show process name, arrival time, burst time and priority level
         * 
         */
        List<Row> newList = new ArrayList();
        
        for (Row row : oldList)
        {
            newList.add(new Row(row.getProcessName(), row.getArrivalTime(), row.getBurstTime(), row.getPriorityLevel()));
        }
        
        /**
         * Returns a list showing the process name, arrival time, burst time and priority level of the process
         * @return newList 
         */
        return newList;
    }
}
