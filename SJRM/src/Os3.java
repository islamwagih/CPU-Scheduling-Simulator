import java.util.Scanner;
public class Os3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Scanner input=new Scanner(System.in);
        System.out.println("enter the number of Processes");
        int n=input.nextInt();
        Jop[] jops= new Jop[n];
        System.out.println("do you want random brust time (0 for yes)");
        int rand=input.nextInt();
        if(rand==0)
        {
            for(int i=0;i<n;i++)
            {
                System.out.println("enter the arrival time for the process "+ (i+1));
                int arrival_time=input.nextInt();
                jops[i]=new Jop(i+1,arrival_time);
            }
        }
        else
        {
            for(int i=0;i<n;i++)
            {
                System.out.println("enter the arrival and the brust time for the process "+ (i+1));
                int arrival_time=input.nextInt();
                int brust_time=input.nextInt();
                jops[i]=new Jop(i+1,arrival_time,brust_time);
            }
        }
        System.out.println("enter the max waiting time for the process to take turn ");
        int maxWaiting_time=input.nextInt();
        SRTF srtf=new SRTF(jops,maxWaiting_time);
    }
    
}
