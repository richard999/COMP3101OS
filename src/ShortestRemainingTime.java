
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Noel Powell : 620128757
 * Tyreke McLean : 620129957
 * Michael Layne : 620106364
 * Richard Ebanks : 620120063
 * Donald Berry : 620130142
 */
public class ShortestRemainingTime extends CPUScheduler
{
    @Override
    public void process()
    {
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
        
        while (!rows.isEmpty())
        {
            List<Row> availableRows = new ArrayList();
            
            for (Row row : rows)
            {
                if (row.getArrivalTime() <= time)
                {
                    availableRows.add(row);
                }
            }
            
            Collections.sort(availableRows, (Object o1, Object o2) -> {
                if (((Row) o1).getBurstTime() == ((Row) o2).getBurstTime())
                {
                    return 0;
                }
                else if (((Row) o1).getBurstTime() < ((Row) o2).getBurstTime())
                {
                    return -1;
                }
                else
                {
                    return 1;
                }
            });
            
            Row row = availableRows.get(0);
            this.getTimeline().add(new Event(row.getProcessName(), time, ++time));
            row.setBurstTime(row.getBurstTime() - 1);
            
            if (row.getBurstTime() == 0)
            {
                for (int i = 0; i < rows.size(); i++)
                {
                    if (rows.get(i).getProcessName() ==row.getProcessName())
                    {
                        rows.remove(i);
                        break;
                    }
                }
            }
        }
        
        
        for (int i = this.getTimeline().size() - 1; i > 0; i--)
        {
            List<Event> timeline = this.getTimeline();
            
            if (timeline.get(i - 1).getProcessName()==(timeline.get(i).getProcessName()))
            {
                timeline.get(i - 1).setFinishTime(timeline.get(i).getFinishTime());
                timeline.remove(i);
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

    public static void main(String[] args)
    {
        
       
        shortest_remaining_time();
        
    }
    
    
    public static void shortest_remaining_time()
    {
        CPUScheduler srt = new ShortestRemainingTime();
        srt.add(new Row(1, 8, 1));
        srt.add(new Row(2, 5, 1));
        srt.add(new Row(3, 2, 7));
        srt.add(new Row(4, 4, 3));
        srt.add(new Row(5, 2, 8));
        
        srt.process();
        display(srt);
    }
    
    
    
    
    public static void display(CPUScheduler object)
    {
        System.out.println("Process\tAT\tBT\tWT\tTAT");

        for (Row row : object.getRows())
        {
            System.out.println(row.getProcessName() + "\t" + row.getArrivalTime() + "\t" + row.getBurstTime() + "\t" + row.getWaitingTime() + "\t" + row.getTurnaroundTime());
        }
        
        System.out.println();
        
        for (int n = 0; n < object.getTimeline().size(); n++)
        {
            List<Event> timeline = object.getTimeline();
            //System.out.print(timeline.get(n).getStartTime() + "(" + timeline.get(n).getProcessName() + ")");
            
            if (n == object.getTimeline().size() - 1)
            {
                System.out.println("Finish Time");
                System.out.print(timeline.get(n).getFinishTime());
            }
        }
        
        System.out.println("\n\nAverage WaitingTime: " + object.getAverageWaitingTime() + "\nAverage TurnAroundTime: " + object.getAverageTurnAroundTime());
    }
}
