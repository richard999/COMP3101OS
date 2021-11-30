
import java.util.ArrayList;
import java.util.List;

/**
 * Noel Powell : 620128757
 * Tyreke McLean : 620129957
 * Michael Layne : 620106364
 * Richard Ebanks : 620120063
 * Donald Berry : 620130142
 */
public class Utility
{
    public static List<Row> deepCopy(List<Row> oldList)
    {
        List<Row> newList = new ArrayList();
        
        for (Row row : oldList)
        {
            newList.add(new Row(row.getProcessName(), row.getArrivalTime(), row.getBurstTime(), row.getPriorityLevel()));
        }
        
        return newList;
    }
}
