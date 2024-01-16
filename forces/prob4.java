import java.util.*;
import java.io.*;
public class prob4{

    public static Scanner scn=new Scanner(new InputStreamReader(System.in));
    public static void main(String[]args){
        int t=scn.nextInt();
        while(t-->0){
            int n=scn.nextInt();
            int m=scn.nextInt();
            
            int[]arr1=new int[n];
            int[]arr2=new int[m];


            for(int i=0;i<n;i++){
                arr1[i]=scn.nextInt();
            }
            for(int i=0;i<m;i++){
                arr2[i]=scn.nextInt();
            }
            
            Arrays.sort(arr1);
            Arrays.sort(arr2);

            int p1=0,p2=m-1;
            int sum=0;

            //recursion

            int anss= maxSum(arr1, arr2, 0, m-1, 0);

            // for(int i=0;i<n;i++){
            //     int val=arr1[i];

            //     if(Math.abs(arr2[p1]-val)>=Math.abs(arr2[p2]-val)){
            //         sum+=Math.abs(arr2[p1]-val);
            //         p1++;
            //     }
            //     else{
            //         sum+=Math.abs(arr2[p2]-val);
            //         p2--;
            //     }
            // }

            
            System.out.println(anss);
        }
    }
    public static int maxSum(int[]arr1, int []arr2, int p1, int p2, int index){
        if(index==arr1.length || p1>p2)
        return 0;

        int a= maxSum(arr1, arr2, p1+1, p2, index+1)+(Math.abs(arr2[p1]-arr1[index]));
        int b= maxSum(arr1, arr2, p1, p2-1, index+1)+(Math.abs(arr2[p2]-arr1[index]));

        return Math.max(a,b);
    }
}