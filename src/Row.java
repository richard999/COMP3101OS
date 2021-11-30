
public class Row
{
    /**
     * Declaration of varibles
     */
    private int processName;
    private int arrivalTime;
    private int burstTime;
    private int priorityLevel;
    private int waitingTime;
    private int turnaroundTime;
    
    private Row(int processName, int arrivalTime, int burstTime, int priorityLevel, int waitingTime, int turnaroundTime)
    {
        /**
         * declaration for creating list 
         * 
         * @param processName
         * @param arrivalTime
         * @param burstTime
         * @param priorityLevel
         * @param waitingTime
         * @param turnaroundTime
         */
        this.processName = processName;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priorityLevel = priorityLevel;
        this.waitingTime = waitingTime;
        this.turnaroundTime = turnaroundTime;
    }
    
    public Row(int processName, int arrivalTime, int burstTime, int priorityLevel)
    {
        this (processName, arrivalTime, burstTime, priorityLevel, 0, 0);
    }
    
    public Row(int processName, int arrivalTime, int burstTime)
    {
        this(processName, arrivalTime, burstTime, 0, 0, 0);
    }
    
    public void setBurstTime(int burstTime)
    {
        this.burstTime = burstTime;
    }
    
    public void setWaitingTime(int waitingTime)
    {
        this.waitingTime = waitingTime;
    }
    
    public void setTurnaroundTime(int turnaroundTime)
    {
        this.turnaroundTime = turnaroundTime;
    }
    
    public int getProcessName()
    {
        /**
         * Returns the process name
         * @return processName 
         */
        return this.processName;
    }
    
    public int getArrivalTime()
    {
        /**
         * Returns the arrival time for process
         * @return arrivalTime 
         */
        return this.arrivalTime;
    }
    
    public int getBurstTime()
    {
        /**
         * Returns the burst time for process
         * @return burstTime 
         */
        return this.burstTime;
    }
    
    public int getPriorityLevel()
    {
        /**
         * Returns the priority level for process
         * @return priorityLevel 
         */
        return this.priorityLevel;
    }
    
    public int getWaitingTime()
    {
        /**
         * Returns the waiting time for process
         * @return waitingTime 
         */
        return this.waitingTime;
    }
    
    public int getTurnaroundTime()
    {
        /**
         * Returns the turn around time for process
         * @return turnaroundTime 
         */
        return this.turnaroundTime;
    }
}
