
/**
 * Noel Powell : 620128757
 * Tyreke McLean : 620129957
 * Michael Layne : 620106364
 * Richard Ebanks : 620120063
 * Donald Berry : 620130142
 */
public class Event
{   //
    /**
     * method that returns process information
     * @return processName
     * @return startTime
     * @return finishTime
     */
    private final int processName;
    private final int startTime;
    private int finishTime;
    
    public Event(int i, int startTime, int finishTime)
    {
        this.processName = i;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }
    
    public int getProcessName()
    {
        /**
         * Returns the name of the process 
         * @return processName 
         */
        return processName;
    }
    
    public int getStartTime()
    {
        /**
         * Returns the start time of the process 
         * @return startTime 
         */   
        return startTime;
    }
    
    public int getFinishTime()
    {
        /**
         * Returns the finish time of the process 
         * @return finishTime 
         */
        return finishTime;
    }
    
    public void setFinishTime(int finishTime)
    {
        this.finishTime = finishTime;
    }
}
