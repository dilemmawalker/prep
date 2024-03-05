import java.lang.Thread;
public class prob1 extends Thread{

    int start=0;
    int end=0;

    prob1(int start, int end){ //constructor
        this.start= start;
        this.end= end;
    }
        public void run(){ //function
            try{
                for(int i=start;i<=end;i++)
                System.out.print(i+" ");
                // System.out.println("     Thread: "+ Thread.currentThread().getId());
                // Thread.sleep(2000);
            }
            catch(Exception e){
                System.out.println("error");
                // throw exception;
            }
        }
}