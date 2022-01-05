public class Jop {
    private int ID;
    private int arrival_time;
    private int burst_time;
    private int remaining_time;
    private int end_time;
    private boolean complete=false;
    public int counter=0;

    public Jop(int ID, int arrival_time, int brust_time) {
        this.ID=ID;
        this.arrival_time=arrival_time;
        this.burst_time=brust_time;
        this.remaining_time=brust_time;
    }
    public Jop(int ID, int arrival_time) {
        this.ID=ID;
        this.arrival_time=arrival_time;
        this.burst_time=getRand();
        this.remaining_time=burst_time;
    }
    private int getRand()
    {
        int min=1;
        int max=30;
        return (int)Math.floor(Math.random()*(max-min+1)+min);
    }
    public int getID(){return ID;}
    public int getArrival_time(){return arrival_time;}
    public int getBurst_time(){return burst_time;}
    public int getRemaining_time(){return remaining_time;}
    public void updateRemaining_time(int worked_time){remaining_time-=worked_time;}
    public void setEnd_time(int end_time){
        this.end_time=end_time;
        remaining_time=0;
        complete=true;
    }
    public int getEnd_time(){return end_time;}
    public int getWaiting_time(){return (end_time-arrival_time)-burst_time;}
    public boolean isComplete(){return complete;}
}
