import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Noel Powell : 620128757
 * Tyreke McLean : 620129957
 * Michael Layne : 620106364
 * Richard Ebanks : 620120063
 * Donald Berry : 620130142
 */
public class ShortestJobFirst extends CPUScheduler{
    private int process, burst, arrival;
/**
 * Method constructor for ShortestJobFirst
 * @param pro Process ID
 * @param burs Burst Time
 * @param arriv Arrival Time
 */
    public ShortestJobFirst(int pro, int burs, int arriv){
        this.process=pro;
        this.burst=burs;
        this.arrival=arriv;
    }

    public ShortestJobFirst() {
}

    /**
     * Calculate the waiting time 
     * @param proces
     * @param i
     * @param wait
    */
    public static void Waiting_Time (ShortestJobFirst proces[], int i, int wait[]){
        int rule[]= new int[i];
        for (int o=0; o<i; o++){
            rule[o]= proces [o].burst;
        }
        
        int shortest=0, fTime, c=0, t=0, min=Integer.MAX_VALUE;
        boolean check=false;

        while (c !=i) {
            for (int p=0; p<i;p++){
                if ((proces [p].arrival <= t) && (rule [p] < min) && (rule[p] > 0)){
                    min=rule[p];
                    shortest=p;
                    check=true;
                }

            }
            
            if (check != true){
                t++;
                continue;
            }

            rule[shortest]-- ;
            min=rule[shortest];

            if (min ==0){
                min=Integer.MAX_VALUE;

            }

            if (rule[shortest]== 0){
                c++;
                check=false;
                fTime= t+1;
                wait [shortest] = fTime- proces[shortest].burst- proces[shortest].arrival;

                if (wait[shortest] < 0){
                    wait[shortest]=0;
                }
            }
            t++;
        }    
    }

    /**
     * Calcutes the turn around 
     * @param proces
     * @param i
     * @param wait
     * @param turnT
     */
    public static void TurnAround (ShortestJobFirst proces[], int i, int wait[], int turnT[]){
        int n=0;
        while (n<i){
            turnT[n]= proces[n].burst + wait[n]; 
            n++;
            continue;
        }
        
}

    /**
     * Calculates the Average Time
     * @param proces
     * @param i
     */
    public static void AverageTime(ShortestJobFirst proces [], int i){
        int wait[]= new int[i], turnT[]=new int[i], totalWait=0, totalturn=0;
        
        Waiting_Time (proces, i, wait); 

        TurnAround(proces, i, wait, turnT);

        int n=0;
        while (n<i) {
            totalWait = totalWait + wait[n];

            totalturn = totalturn + turnT[n];
            int b=0;

            System.out.println("Process #" + "\t " + proces[n].process + "\t"+ proces[n].burst + "\t" + wait[n] + "\t" + turnT[n]);
            n++;
            continue;
        }
      
        System.out.println("Average waiting time = " + (float)totalWait / (float)i);

        System.out.println("Average turn around time = " +(float)totalturn / (float)i);


    }

    /**
     * The main constuctor for working out the shortest job first
     * @param args
     */
    public static void main(String[] args)

    {

         ShortestJobFirst proc[] = { new ShortestJobFirst(1, 6, 1),new ShortestJobFirst(2, 8, 1), new ShortestJobFirst(3,7, 2), new ShortestJobFirst(4, 3, 3)};

        AverageTime(proc, proc.length);

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
            this.getTimeline().add(new Event(row.getProcessName(), time, time + row.getBurstTime()));
            time += row.getBurstTime();
            
            for (int i = 0; i < rows.size(); i++)
            {
                if (rows.get(i).getProcessName()==(row.getProcessName()))
                {
                    rows.remove(i);
                    break;
                }
            }
        }
        
        for (Row row : this.getRows())
        {
            row.setWaitingTime(this.getEvent(row).getStartTime() - row.getArrivalTime());
            row.setTurnaroundTime(row.getWaitingTime() + row.getBurstTime());
        }
    }
    
    
}
