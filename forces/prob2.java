import java.util.*;
import java.io.*;
public class prob2{

    public static Scanner scn=new Scanner(new InputStreamReader(System.in));
    public static void main(String[]args){
        int t=scn.nextInt();
        while(t-->0){
            int n=scn.nextInt();
            int[]arr=new int[n];

            String s=scn.next();
            for(int i=0;i<n;i++){
                arr[i]=s.charAt(i)-'0';
            }
            
             s=scn.next();
            int[]fin=new int[n];
            for(int i=0;i<n;i++){
                fin[i]=s.charAt(i)-'0';
            }

            int place=0,unplace=0;
            for(int i=0;i<n;i++){
                if(arr[i]==1 && fin[i]==0)
                unplace++;
                else if(arr[i]==0 && fin[i]==1)
                place++;
            }

            int min=Math.min(unplace,place);

            int sum=min+Math.abs(place-unplace);
            System.out.println(sum);
        }
    }
}