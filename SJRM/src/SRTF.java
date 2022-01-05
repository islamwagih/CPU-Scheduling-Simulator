public class SRTF {
    int clock=0;
    Jop jops[];

    public SRTF(Jop jops[],int maxWaiting_time) {
        this.jops=jops;
        while(!SRTComplete())
        {
            Jop minBurstJop = null;
            int minBurst = Integer.MAX_VALUE;
            
            for(Jop j : jops) {
                if(!j.isComplete() && j.getArrival_time() <= clock && ((j.counter++)==maxWaiting_time|| j.getRemaining_time()< minBurst)) {
                    
                        minBurst = j.getRemaining_time();
                        minBurstJop = j;
                        if(j.counter==maxWaiting_time)
                        {
                            break;
                        }
                }
            }
            clock+=1;
            if(minBurstJop != null) {
                System.out.print("p"+minBurstJop.getID()+" ");
                minBurstJop.updateRemaining_time(1);
            
                if(minBurstJop.getRemaining_time()== 0) {
                    minBurstJop.setEnd_time(clock);
                }
            }
        }
        System.out.println(" ");
        print();
    }
    private boolean SRTComplete()
    {
        for(Jop j : jops) {
            if(!j.isComplete()) {
                return false;
            }
        }
        return true;
    }
    private void print()
    {
        int totalWaiting=0;
        System.out.println("prosses     Burst time      Arrival time        Ending Time     waiting time");
        for(Jop j :jops)
        {
            System.out.print(j.getID()+"           ");
            System.out.print(j.getBurst_time()+"               ");
            System.out.print(j.getArrival_time()+"                   ");
            System.out.print(j.getEnd_time()+"                 ");
            System.out.println(j.getWaiting_time());
            totalWaiting+=j.getWaiting_time();
        }
        System.out.println("Average waiting time : "+(float)totalWaiting/(float)jops.length);
    }
    
    
    
    
}
