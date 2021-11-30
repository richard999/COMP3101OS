
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
//Use a class to keep track of the current processes status.
public class RoundRobin extends CPUScheduler{
    public int burstTime;
    public int arrivalTime;
    public int completionTime;
    public int remainingRunTime;

    // Initialize processes with an arrival time and burst time
    public RoundRobin(int arrivalTimeValue, int burstTimeValue) {
        burstTime = burstTimeValue;
        arrivalTime = arrivalTimeValue;
        completionTime = -1;
        remainingRunTime = burstTime;
    }
    public RoundRobin() {
    }
    public class Main {
        // Use a class to keep track of the current processes status.
        
    
        public static void main(String[] args) {
            System.out.println("Average Waiting time");
    
            float waity;
            int arrival[] = { 0, 0 };
            int burst[] = { 4, 4 };
            waity = roundRobin(arrival, burst, 3);
    
            System.out.println(waity);
    
            // waity = roundRobin({},{},3);
    
            // System.out.println(waity);
    
        }
    
        static public float roundRobin(int[] arrivalTimes, int[] burstTimes, int quantumTime) {
            // Avoid divide by zero
            if (arrivalTimes.length == 0)
                return 0;
    
            // processes can be either arriving, running or finished
            List<RoundRobin> arrivingProcesses = new ArrayList<RoundRobin>();
            Queue<RoundRobin> runningProcesses = new LinkedList<RoundRobin>();
            List<RoundRobin> finishedProcesses = new ArrayList<RoundRobin>();
    
            // Create all processes in arriving
            for (int i = 0; i < arrivalTimes.length; i++) {
                arrivingProcesses.add(new RoundRobin(arrivalTimes[i], burstTimes[i]));
            }
            // I assume the arrays already list the processes based on priority.
            // If there is another way you want to choose priority then you should sort
            // arrivingProcesses
    
            int currentTime = 0;
    
            // Simulate time until the processes are all finished
            while (!(arrivingProcesses.isEmpty() && runningProcesses.isEmpty())) {
    
                // First add any arriving processes to the queue
                for (int i = arrivingProcesses.size() - 1; i >= 0; i--) {
                    if (arrivingProcesses.get(i).arrivalTime <= i) {
                        runningProcesses.add(arrivingProcesses.get(i));
                        arrivingProcesses.remove(i);
                    }
                }
    
                // Run the first item in the queue
                if (!runningProcesses.isEmpty())
                    runningProcesses.peek().remainingRunTime--;
    
                currentTime++;
    
                // finish process if run time is 0
                if (runningProcesses.peek().remainingRunTime == 0) {
                    runningProcesses.peek().completionTime = currentTime;
                    finishedProcesses.add(runningProcesses.remove());
                }
    
                // if the quantum time is reached, put the process in the back
                if (currentTime % quantumTime == 0 && !runningProcesses.isEmpty()) {
                    runningProcesses.add(runningProcesses.remove());
                }
    
            }
    
            // Calculate total waiting time
            float totalWaitTime = 0;
    
            for (RoundRobin checkProcess : finishedProcesses) {
                totalWaitTime += (checkProcess.completionTime - (checkProcess.arrivalTime + checkProcess.burstTime));
            }
    
            // return the average
            return totalWaitTime / arrivalTimes.length;
    
        }
    
    }
    @Override
    public void process() {
        Collections.sort(this.getRows(), (Object o1, Object o2) -> {
            if (((Row) o1).getArrivalTime() == ((Row) o2).getArrivalTime())
            {
                return 0;
            }
            else if (((Row) o1).getArrivalTime() < ((Row) o2).getArrivalTime())
            {
                return -1;
            }
            else
            {
                return 1;
            }
        });
        
        List<Row> rows = Utility.deepCopy(this.getRows());
        int time = rows.get(0).getArrivalTime();
        int timeQuantum = this.getTimeQuantum();
        
        while (!rows.isEmpty())
        {
            Row row = rows.get(0);
            int bt = (row.getBurstTime() < timeQuantum ? row.getBurstTime() : timeQuantum);
            this.getTimeline().add(new Event(row.getProcessName(), time, time + bt));
            time += bt;
            rows.remove(0);
            
            if (row.getBurstTime() > timeQuantum)
            {
                row.setBurstTime(row.getBurstTime() - timeQuantum);
                
                for (int i = 0; i < rows.size(); i++)
                {
                    if (rows.get(i).getArrivalTime() > time)
                    {
                        rows.add(i, row);
                        break;
                    }
                    else if (i == rows.size() - 1)
                    {
                        rows.add(row);
                        break;
                    }
                }
            }
        }
        
        Map map = new HashMap();
        
        for (Row row : this.getRows())
        {
            map.clear();
            
            for (Event event : this.getTimeline())
            {
                if (event.getProcessName()==(row.getProcessName()))
                {
                    if (map.containsKey(event.getProcessName()))
                    {
                        int w = event.getStartTime() - (int) map.get(event.getProcessName());
                        row.setWaitingTime(row.getWaitingTime() + w);
                    }
                    else
                    {
                        row.setWaitingTime(event.getStartTime() - row.getArrivalTime());
                    }
                    
                    map.put(event.getProcessName(), event.getFinishTime());
                }
            }
            
            row.setTurnaroundTime(row.getWaitingTime() + row.getBurstTime());
        }
    }
 
}