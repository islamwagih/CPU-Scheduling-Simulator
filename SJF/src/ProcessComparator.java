import java.util.Comparator;
public class ProcessComparator implements Comparator<Process> {
    @Override
    public int compare(Process x, Process y) {
        if(x.getArrivalTime() != y.getArrivalTime()) return Integer.compare(x.getArrivalTime(), y.getArrivalTime());
        if(x.getBurstTime() != y.getBurstTime()) return Integer.compare(x.getBurstTime(), y.getBurstTime());
        if(x.getPriority() != y.getPriority()) return Integer.compare(x.getPriority(), y.getPriority());
        return Integer.compare(x.getId(), y.getId());
    }
}
