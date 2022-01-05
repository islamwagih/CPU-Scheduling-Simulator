public class Process{
    private int arrivalTime, burstTime, startTime, endTime, id, priority;

    public Process(){
        this(0);
    }

    public Process(int burstTime){
        this(0, burstTime, 0);
    }

    public Process(int arrivalTime, int burstTime, int id){
        this(arrivalTime, burstTime, (int)(Math.floor(Math.random()*(10-1+1)+1)), id);
    }

    public Process(int arrivalTime, int burstTime, int priority, int id){
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public int getArrivalTime() {
        return this.arrivalTime;
    }

    public int getBurstTime() {
        return this.burstTime;
    }

    public int getEndTime() {
        return this.endTime;
    }

    public int getStartTime() {
        return this.startTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void info(){
        System.out.println("==============================");
        System.out.println("Process id : "+getId());
        System.out.println("arrivalTime = "+getArrivalTime()+", burst Time = "+getBurstTime());
        int turnaroundTime = getEndTime()-getArrivalTime();
        int waitingTime = turnaroundTime-getBurstTime();
        System.out.println("completion time = "+getEndTime()+", turnaround time = "+(turnaroundTime)+", waiting time = "+(waitingTime)+", priority = "+getPriority());
        System.out.println("==============================");
    }
}
