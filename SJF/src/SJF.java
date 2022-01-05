import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class SJF {
    public void run(){

        Comparator<Process> arrivalBurstComparator = new ProcessComparator();
        Comparator<Process> burstComparator = new ProcessBurstComparator();
        PriorityQueue<Process> processesArrivalBurstQueue = new PriorityQueue<>(arrivalBurstComparator);
        PriorityQueue<Process> processesBurstQueue = new PriorityQueue<>(burstComparator);
        ArrayList<Process> finalResult = new ArrayList<>();
        System.out.print("Enter number of processes : ");
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt(), elapsedTime = Integer.MAX_VALUE;
        System.out.println("Enter process arrival time and burst time");
        for(int i=1;i<=n;i++){
            int arrivalTime = scan.nextInt();
            int burstTime = scan.nextInt();
            elapsedTime = Math.min(elapsedTime, arrivalTime);
            /*
             System.out.println("Enter process priority");
             int priority = scan.nextInt();
             processesArrivalBurstQueue.add(new Process(arrivalTime, burstTime, i, priority));
            */
            processesArrivalBurstQueue.add(new Process(arrivalTime, burstTime, i));
        }

        while (processesArrivalBurstQueue.size() > 0 || processesBurstQueue.size() > 0) {
            while (processesArrivalBurstQueue.size() > 0 && processesArrivalBurstQueue.peek().getArrivalTime() <= elapsedTime) {
                Process currProcess = processesArrivalBurstQueue.poll();
                processesBurstQueue.add(currProcess);
                if(processesArrivalBurstQueue.size() > 0){
                    PriorityQueue<Process> helper = new PriorityQueue<>(arrivalBurstComparator);
                    while(processesArrivalBurstQueue.size() > 0) helper.add(processesArrivalBurstQueue.poll());
                    while(helper.size() > 0){
                        Process p = helper.poll();
                        p.setPriority(p.getPriority()+2);
                        processesArrivalBurstQueue.add(p);
                    }
                }
            }
            if(processesBurstQueue.size() > 0) {
                Process currProcess = processesBurstQueue.poll();
                currProcess.setStartTime(elapsedTime);
                elapsedTime += currProcess.getBurstTime();
                currProcess.setEndTime(elapsedTime);
                finalResult.add(currProcess);
            }else {
                if(processesArrivalBurstQueue.size() > 0) elapsedTime+=processesArrivalBurstQueue.peek().getArrivalTime()-elapsedTime;
            }
        }

        int totalWaitingTime = 0, totalTurnaroundTime = 0;
        for(Process p:finalResult){
            p.info();
            int turnaroundTime = p.getEndTime()-p.getArrivalTime();
            int waitingTime = turnaroundTime-p.getBurstTime();
            totalTurnaroundTime += turnaroundTime;
            totalWaitingTime += waitingTime;
        }

        System.out.println("Avg waiting time = "+(totalWaitingTime/(double)finalResult.size()));
        System.out.println("Avg turnaround time = "+(totalTurnaroundTime/(double)finalResult.size()));
    }

}
