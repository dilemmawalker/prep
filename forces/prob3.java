import java.util.*;
import java.io.*;
public class prob3{

    public static Scanner scn=new Scanner(new InputStreamReader(System.in));
    public static void main(String[]args){
        int t=scn.nextInt();
        while(t-->0){
            int n=scn.nextInt();
            long initial=scn.nextLong();
            long a=scn.nextLong();
            long b=scn.nextLong();
            long[]arr=new long[n];

            for(int i=0;i<n;i++){
                arr[i]=scn.nextLong();
            }
            
             long prev=0;
             for(int i=0;i<n;i++){

                long small= Math.min((arr[i]-prev)*a,b);

                initial-=small;

                prev=arr[i];
             }
             String ans="YES";
             if(initial<=0)
             ans="NO";

             System.out.println(ans);
        }
    }
}