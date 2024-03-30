public class prob4{

    //Memorization
    public static int fibonacci(int n, int[]dp){
        if(n==1)
        return dp[n]=1;
        else if(n==0)
        return dp[n]=0;

        if(dp[n]!=0)
        return dp[n];
        
        int c=0;
        c+= fibonacci(n-1, dp) + fibonacci(n-2, dp);

        return dp[n]=c;
    }

    //Tabulation
    public static int fibonacci_tabu(int n, int[]dp){
        for(int i=0;i<=n;i++){

            if(i==1){
             dp[i]=1; // do a continue whereever we do return in memorization.
             continue;
            }
            else if(i==0){
             dp[i]=0;
            continue;
            }

            //remove the DP part from tabulation
            
            int c=0;
            c+= dp[i-1] + dp[i-2]; // take data from array rather than recursion calls.

             dp[i]=c;
        }
        return dp[n];
    }

    public static int mazepath(int sr, int sc, int er, int ec, int[][]dp){
        if(sr==er && sc==ec)
        return dp[sr][sc]=1;

        if(sr>er || sc>ec)
        return 0;

        if(dp[sr][sc]!=0)
        return dp[sr][sc];

        int c=0;
        c+=mazepath(sr+1, sc, er, ec, dp);
        c+=mazepath(sr+1, sc+1, er, ec, dp);
        c+=mazepath(sr, sc+1, er, ec, dp);

        return dp[sr][sc]=c;
    }

    public static int mazepath_tabulation(int SR, int SC, int er, int ec, int[][]dp){
        for(int sr= er; sr>=SR; sr--){
            for(int sc= ec; sc>=SC; sc--){
                if(sr==er && sc==ec){
                dp[sr][sc]=1;
                continue;
                }

                // if(sr>er || sc>ec){
                // dp[sr][sc]= 0;
                // continue;
                // }

                int c=0;
                if(sr+1<=er)
                c+=dp[sr+1][sc];
                if(sr+1<=er && sc+1<=ec)
                c+=dp[sr+1][sc+1];
                if(sc+1<=ec)
                c+=dp[sr][sc+1];

                dp[sr][sc]=c;
            }
        }
        return dp[0][0];
    }

    public static void main(String[]args){
        int n=13;
        int[]arr=new int[n+1];
        // int ans= fibonacci(n, arr);
        // int ans= fibonacci_tabu(n, arr);
        int[][]dp= new int[n+1][n+1];
        int ans= mazepath_tabulation(0, 0, n, n, dp);
        System.out.println(ans);
    }
}