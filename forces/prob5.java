import java.util.*;
import java.io.*;
public class prob5{

    public static Scanner scn=new Scanner(new InputStreamReader(System.in));
    public static void main(String[]args){
        int t=scn.nextInt();
        while(t-->0){
            int n=scn.nextInt();
            int q=scn.nextInt();
            
            int[]arr=new int[n];

            for(int i=0;i<n;i++){
                arr[i]=scn.nextInt();
            }
            
            for(int i=0;i<q;i++){
                int s=scn.nextInt()-1;
                int d=scn.nextInt();
                int k=scn.nextInt();

                int val=1;
                long sum=0l;
                while(k>0 && s<n){
                    sum+=arr[s]*val;
                    k--;
                    s=s+d;
                    val++;
                }
                System.out.print(sum+" ");
            }
            System.out.println();
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