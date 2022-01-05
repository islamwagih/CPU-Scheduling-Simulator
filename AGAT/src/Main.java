import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Process> p = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("enter number of processes");
        int n = sc.nextInt();

        System.out.println("enter process name, arrival time, burst time, priority, quantum");
        for (int i = 0; i<n; i++) {
            String name = sc.next();
            int arr = sc.nextInt();
            int burst = sc.nextInt();
            int priority = sc.nextInt();
            int quantum = sc.nextInt();

            p.add(new Process(name, arr, burst, priority, quantum));
        }

        AGAT g = new AGAT();
        g.start(p);
    }
}
