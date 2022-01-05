import java.util.*;
import java.lang.Math;

public class AGAT {
        public ArrayList <Process> allProcesses;
        //if arrivalTime = clock enter ready queue
        public ArrayList<Process> readyQueue = new ArrayList<>();
        //finish its job(dead process)
        public ArrayList<Process> deadProcesses = new ArrayList<>();
        //has a job
        public ArrayList<Integer> quantumleft = new ArrayList<>();
        //factors
        public ArrayList<Integer> AGATfactor = new ArrayList<>();
        public double v1 , v2;


        public void start(ArrayList<Process> allProcesses) {
            this.allProcesses = new ArrayList<Process>(allProcesses);
            sortProcesses();
            calculateV1();

            int clock = 0;
            int index = 0;
            int pclk = 0;

            while ((index < allProcesses.size() || !readyQueue.isEmpty())){

                for (; index < allProcesses.size(); index++) {
                    if (allProcesses.get(index).getArrivalTime() <= clock) {
                        readyQueue.add(allProcesses.get(index));
                        quantumleft.add(allProcesses.get(index).getQuantum());
                    } else if (allProcesses.get(index).getArrivalTime() > clock) {
                        break;
                    }
                }

                if (readyQueue.isEmpty()) {
                    clock++;
                    continue;
                }

                calculateV2(readyQueue);


                if (readyQueue.get(0).getBurstTime() == 0) {

                    System.out.print(readyQueue.get(0).getName());
                    System.out.println(" finishing executing");
                    System.out.println("start time: " + pclk);
                    System.out.println("end time: " + clock);
                    System.out.println("burst time: " + readyQueue.get(0).getBurstTime());
                    System.out.println("arrival time: " + readyQueue.get(0).getArrivalTime());
                    System.out.println("**********************************");

                    pclk = clock;

                    readyQueue.get(0).setQuantum(0);
                    deadProcesses.add(readyQueue.get(0));

                    readyQueue.remove(0);
                    quantumleft.remove(0);

                }

                if (readyQueue.isEmpty()) {
                    clock++;
                    continue;
                }

                int factor = calculateAGATFactor(readyQueue);
                //if quantum exceeds 40% then see our cases
                if (readyQueue.get(0).getQuantum() - quantumleft.get(0) >= Math.round(readyQueue.get(0).getQuantum() * 0.4)) {

                    if (readyQueue.get(0).getBurstTime() != 0 && quantumleft.get(0) == 0) {

                        System.out.print(readyQueue.get(0).getName());
                        System.out.println(" finished it’s quantum");
                        System.out.println("start time: " + pclk);
                        System.out.println("end time: " + clock);
                        System.out.println("burst time: " + readyQueue.get(0).getBurstTime());
                        System.out.println("arrival time: " + readyQueue.get(0).getArrivalTime());
                        System.out.println("**********************************");


                        pclk = clock;

                        Process p = readyQueue.get(0);
                        //update the quantum
                        p.setQuantum(p.getQuantum() + 2);
                        readyQueue.remove(0);
                        quantumleft.remove(0);
                        readyQueue.add(p);
                        quantumleft.add(p.getQuantum());

                    } else if (factor != 0) {

                        System.out.print(readyQueue.get(0).getName());
                        System.out.println(" get swapped");
                        System.out.println("start time: " + pclk);
                        System.out.println("end time: " + clock);
                        System.out.println("burst time: " + readyQueue.get(0).getBurstTime());
                        System.out.println("arrival time: " + readyQueue.get(0).getArrivalTime());
                        System.out.println("**********************************");

                        pclk = clock;

                        readyQueue.get(0).setQuantum(readyQueue.get(0).getQuantum() + quantumleft.get(0));
                        quantumleft.set(0, readyQueue.get(0).getQuantum());

                        Collections.swap(readyQueue, 0, factor);
                        Collections.swap(quantumleft, 0, factor);

                    }
                }
                readyQueue.get(0).setBurstTime(readyQueue.get(0).getBurstTime()-1);
                quantumleft.set(0, quantumleft.get(0) - 1);
                clock++;
            }
        }

        public void sortProcesses () {
            Collections.sort(allProcesses, Comparator.comparingInt(Process::getArrivalTime));
        }

        public int getMaxArrivalTime () {
            int lastIndex = allProcesses.size() - 1;
            return allProcesses.get(lastIndex).getArrivalTime();
        }

        public int getMaxBurstTime (ArrayList<Process> temp) {
            int max = 0;
            for (Process p: temp){
                if (p.getBurstTime() > max){
                    max = p.getBurstTime();
                }
            }
            return max;
        }

        public void  calculateV1 () {
            int max = getMaxArrivalTime();
            if (max > 10){
                v1 = max/10.0;
            }
            else {
                v1 = 1;
            }

        }

        public double calculateV2 (ArrayList<Process> temp) {
            int max = getMaxBurstTime(temp);
            if (max > 10){
                v2 = max/10.0;
            }
            else {
                v2 = 1;
            }
            return v2;
        }


        public int calculateAGATFactor (ArrayList<Process> temp) {
            AGATfactor.clear();

            for (Process p: temp){
                double value = (10-p.getPriority()) + (Math.ceil(p.getArrivalTime()/v1)) +
                               (Math.ceil(p.getBurstTime()/v2));
                AGATfactor.add((int) value);
            }

            int minIndex = 0;
            for (int i = 0; i < AGATfactor.size(); i++) {
                if (AGATfactor.get(i) <= AGATfactor.get(minIndex)) {
                    minIndex = i;
                }
            }

            return minIndex;
        }


}
