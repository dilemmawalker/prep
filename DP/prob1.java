import java.io.*;

public class prob1{

    //classic DP - Memorization
    public static int fibo(int n, int[]dp){
        if(n==1 || n==0)
        return dp[n]=n; //just 1st statement

        if(dp[n]!=-1) //just 2nd statement, & dp is done :)
        return dp[n]; 

        int sum=0;
        sum=fibo(n-1, dp)+fibo(n-2,dp);
        return dp[n]=sum;
    }

    public static void main(String[]args){
        int n=4000;
        int[]dp=new int[n+1];
        for(int i=0;i<=n;i++)
        dp[i]=-1;
        int ans= fibo(n, dp);
        System.out.println(ans);
    }
}