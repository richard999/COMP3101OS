   /** 
     * 
     * Noel Powell- 620128757, Tyreke McLean-620129957, Donald Berry-620130142, Richard Ebanks-620120063, Michael Layne-620106364
     */

import java.util.ArrayList;
import java.util.List;

public abstract class CPUScheduler
{
 
    private final List<Row> rows;
    private final List<Event> timeline;
    private int timeQuantum;

      /** 
     * End of Variables declaration 
     */
    
       /** 
     * CPUScheduler Constructor
     */
    public CPUScheduler()
    {
        rows = new ArrayList();
        timeline = new ArrayList();
        timeQuantum = 1;
    }
     /** 
     *   End of CPUScheduler Constructor
     */
    
      /** 
     * Method to add Row, Returns a boolean
     */
    public boolean add(Row row)
    {
        return rows.add(row);
    }
    
     /** 
     * This method sets the time quantum 
     */
    public void setTimeQuantum(int timeQuantum)
    {
        this.timeQuantum = timeQuantum;
    }
     /** 
     * This access and return the time quantum as an int
     */
    public int getTimeQuantum()
    {
        return timeQuantum;
    }
     /** 
     * Code to calculate the average waiting time 
     */
    public double getAverageWaitingTime()
    {
        double avg = 0.0;
        
        for (Row row : rows)
        {
            avg += row.getWaitingTime();
        }
        
        return avg / rows.size();
    }
     /** 
     * Code to calculate the averare turn around time 
     */
    public double getAverageTurnAroundTime()
    {
        double avg = 0.0;
        
        for (Row row : rows)
        {
            avg += row.getTurnaroundTime();
        }
        
        return avg / rows.size();
    }
     /** 
     * this returns the Event object/objects if the process name in the row provided as the argument  is equivalent a processname in the timeline
     */
    public Event getEvent(Row row)
    {
        for (Event event : timeline)
        {
            if (row.getProcessName()==(event.getProcessName()))
            {
                return event;
            }
        }
        
        return null;
    }
     /** 
     * Method outputs the row object if the row is present in the array of rows
     */
    public Row getRow(int process)
    {
        for (Row row : rows)
        {
            if (row.getProcessName()==(process))
            {
                return row;
            }
        }
        
        return null;
    }
    
     /** 
     * This method returns a list of the rows
     */
    public List<Row> getRows()
    {
        return rows;
    }
    
     /** 
     * This method returns a list of the events in the timeline
     */
    public List<Event> getTimeline()
    {
        return timeline;
    }
    
    public abstract void process();
}
