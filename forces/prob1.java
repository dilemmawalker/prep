import java.util.*;
import java.io.*;
public class prob1{

    public static Scanner scn=new Scanner(new InputStreamReader(System.in));
    public static void main(String[]args){
        int t=scn.nextInt();
        while(t-->0){
            int x1=scn.nextInt();
            int y1=scn.nextInt();
            int x2=scn.nextInt();
            int y2=scn.nextInt();


            int x3=scn.nextInt();
            int y3=scn.nextInt();
            int x4=scn.nextInt();
            int y4=scn.nextInt();

            int x=0,y=0;

            // if(x1==x2)
            // x=Math.abs(y2-y1);
            // else if(x1==x3)
            // x=Math.abs(y2-y1);
            // else
            // x=Math.abs(y3-y1);

            // System.out.println("this is x:"+x);

            if(y1==y2)
            y=Math.abs(x2-x1);
            else if(y1==y3)
            y=Math.abs(x3-x1);
            else 
            y=Math.abs(x4-x1);

            // System.out.println("this is y:"+y);

            System.out.println(y*y);
        }
    }
}