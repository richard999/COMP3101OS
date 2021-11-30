
public class Event
{
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
        return processName;
    }
    
    public int getStartTime()
    {
        return startTime;
    }
    
    public int getFinishTime()
    {
        return finishTime;
    }
    
    public void setFinishTime(int finishTime)
    {
        this.finishTime = finishTime;
    }
}
