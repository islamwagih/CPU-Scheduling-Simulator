import java.util.ArrayList;
import java.util.Scanner;

class Process {
    int proc;
    int priority;
    int ArrivalTime;
    int BurstTime;
    int waitingtime = 0;
    int turnaroundtime = 0;

    Process(int proc, int ArrivalTime, int BurstTime, int Priority){
        this.proc = proc;
        this.ArrivalTime = ArrivalTime;
        this.BurstTime = BurstTime;
        this.priority = Priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}

public class Main {
    static float averagewaitingtime = 0;
    static float averageturnaroundtime= 0;
    static int currentTime = 0;


    static ArrayList<Process> sortArrivalTime(ArrayList<Process> processes) {
        Process temp = null;
        for(int i=0; i<processes.size(); i++) {
            for(int j=i+1; j<processes.size(); j++) {
                if(processes.get(i).ArrivalTime > processes.get(j).ArrivalTime) {
                    temp = processes.get(i);
                    processes.set(i, processes.get(j));
                    processes.set(j, temp);
                }
            }
        }
        return processes;
    }

    static ArrayList<Process> sortPriority(ArrayList<Process> processes) {
        Process temp = null;
        for(int i=0; i<processes.size(); i++) {
            for(int j=i+1; j<processes.size(); j++) {
                if(processes.get(i).priority > processes.get(j).priority && currentTime>=processes.get(i).ArrivalTime) {
                    temp = processes.get(i);
                    processes.set(i, processes.get(j));
                    processes.set(j, temp);
                }

            }
        }
        return processes;
    }

    static ArrayList<Process> increasePriority(ArrayList<Process> processes){
        for(int i=0;i<processes.size();i++){
            processes.get(i).setPriority(processes.get(i).getPriority()+2);
        }
        return processes;
    }


    static void priorityschedual(ArrayList<Process> processes) {
        float total_w_t = 0, total_t_t = 0, numproc = processes.size();

        System.out.println("Process\tArrivalTime\tBurstTime\tWaitingTime\tTurnaroundTime");

        while(!processes.isEmpty()) {
            processes = sortArrivalTime(processes);
            processes = sortPriority(processes);

            Process process = processes.get(0);

            if(currentTime >= process.ArrivalTime) {
                currentTime += process.BurstTime;
                process.turnaroundtime = currentTime - process.ArrivalTime;
                process.waitingtime = process.turnaroundtime - process.BurstTime;
                total_w_t += process.waitingtime;
                total_t_t += process.turnaroundtime;
                System.out.println("   "+process.proc+ "\t\t" + process.ArrivalTime+ "\t\t\t" + process.BurstTime +"\t\t\t" + process.waitingtime + "\t\t\t" + process.turnaroundtime);
                processes.remove(0);
            }
            else {
                currentTime++;
            }
            //avoid starvation problem before adding new process
            //processes = increasePriority(processes);


        }
        averagewaitingtime = total_w_t / numproc;
        averageturnaroundtime = total_t_t / numproc;
        System.out.println("Average Wait Time : " + averagewaitingtime);
        System.out.println("Average Turn Around Time : " + averageturnaroundtime);

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Process> processes = new ArrayList<Process>();

        System.out.println("Enter the number of Process : ");
        int numproc = input.nextInt();
        System.out.println("Enter Arrival Time, Burst Time, Priority \n");
        for(int i=0; i<numproc; i++) {
            System.out.print("P[" + (i + 1) + "]:");
            int ArrivalTime = input.nextInt();
            int BurstTime = input.nextInt();
            int priority = input.nextInt();
            int proc = i + 1;
            Process process = new Process(proc, ArrivalTime, BurstTime, priority);
            processes.add(process);
        }
        priorityschedual(processes);
        input.close();
    }
}