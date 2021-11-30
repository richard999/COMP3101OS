import java.util.*;
/**
 * 
 */
public class FirstComeFirstServe extends CPUScheduler
{
//method to calculate the waiting time
/**
 * 
 * @param processes
 * @param n
 * @param burstTime
 * @param waitingTime
 */
public static void findWaitingTime(int processes[], int n, int burstTime[], int waitingTime[])
{
waitingTime[0] = 0;
for (int i = 1; i < n; i++)
waitingTime[i] = burstTime[i - 1] + waitingTime[i - 1];
}
  
//method to calculate the turn around time
/**
 * 
 * @param processes
 * @param n
 * @param burstTime
 * @param waitingTime
 * @param turnAroundTime
 */
public static void findTurnAroundTime(int processes[], int n, int burstTime[], int waitingTime[], int turnAroundTime[])
{
for (int i = 0; i < n; i++)
turnAroundTime[i] = burstTime[i] + waitingTime[i];
}

/**
 * 
 * @param args
 */

public static void main(String[] args)
   {
/**
 * variable declaration and initialization
 */

int numProcess = 5;
  
/**
 * array declaration
 */
int processId[] = new int[] {0, 1, 2, 3, 4};
int arrivalTime[] = new int[] {0, 1, 4, 5, 10};
int burstTime[] = new int[] {10, 2, 4, 1, 3};
  
/**
 * array declaration
 */
int waitingTime[] = new int[numProcess];
int turnAroundTime[] = new int[numProcess];
double totalWaitintTime = 0.0, totalTurnAroundTime = 0.0;
  
/**
 * /method calling
 */
findWaitingTime(processId, numProcess, burstTime, waitingTime);
  
for(int i=0; i<numProcess; i++)
{
if(waitingTime[i] - arrivalTime[i]>0)
waitingTime[i] = waitingTime[i] - arrivalTime[i];
else
waitingTime[i] = 0;
}
  
/**
 * method calling
 */
findTurnAroundTime(processId, numProcess, burstTime, waitingTime, turnAroundTime);
  
/**
 * display waiting time of each process
 */
for(int i=0; i<numProcess; i++)
{
System.out.println("Process "+processId[i]+" waiting time = "+waitingTime[i]);
totalWaitintTime = totalWaitintTime + waitingTime[i];
}
  
System.out.println();
  
/**
 * display trun around time of each process
 */
for(int i=0; i<numProcess; i++)
{
System.out.println("Process "+processId[i]+" turn around time = "+turnAroundTime[i]);
totalTurnAroundTime = totalTurnAroundTime + turnAroundTime[i];
}
  
/**
 * display average turn around time
 */
System.out.println("\n\nAverage turn around time = "+(double)totalTurnAroundTime/numProcess);
  
/**
 * display average waiting time
 */
System.out.println("\nAverage waiting time = "+(double)totalWaitintTime/numProcess);
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
  
  List<Event> timeline = this.getTimeline();
  
  for (Row row : this.getRows())
  {
      if (timeline.isEmpty())
      {
          timeline.add(new Event(row.getProcessName(), row.getArrivalTime(), row.getArrivalTime() + row.getBurstTime()));
      }
      else
      {
          Event event = timeline.get(timeline.size() - 1);
          timeline.add(new Event(row.getProcessName(), event.getFinishTime(), event.getFinishTime() + row.getBurstTime()));
      }
  }
  
  for (Row row : this.getRows())
  {
      row.setWaitingTime(this.getEvent(row).getStartTime() - row.getArrivalTime());
      row.setTurnaroundTime(row.getWaitingTime() + row.getBurstTime());
  }
}
}
