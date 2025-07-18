public class prob5{

    public static int mazepath_2d(int sr, int sc, int er, int ec, String ans){
        if(sr == er && sc == ec){
            System.out.println(ans);
            return 1;
        }

        if(sr>er || sc>ec){
            return 0;
        }

        int count = 0;
        count+= mazepath_2d(sr+1, sc, er, ec, ans+" row:"+ sr);
        count+= mazepath_2d(sr, sc+1, er, ec, ans+" col:"+ sc);

        return count;
    }

    public static int mazepath_2d_4dir(int sr, int sc, int er, int ec, String ans, boolean[][]check){
        if(sr == er-1 && sc == ec-1){
            System.out.println(ans + " row:"+ sr + " col:"+ sc);
            return 1;
        }

        if(sr>=er || sc>=ec){
            return 0;

        }

        // System.out.println(" sr: "+ sr + " sc: "+sc);

        int count = 0;
            if(sr+1<er && !check[sr+1][sc]){
                check[sr+1][sc] = true;
                // System.out.println(" sr: "+ sr);
                count+= mazepath_2d_4dir(sr+1, sc, er, ec, ans+" row:"+ sr, check);
                check[sr+1][sc] = false;
            }
            if(sr-1<er && sr-1>=0 && !check[sr-1][sc]){
                check[sr-1][sc] = true;
                // System.out.println(" sr: "+ sr);
                count+= mazepath_2d_4dir(sr-1, sc, er, ec, ans+" row:"+ sr, check);
                check[sr-1][sc] = false;
            }
            if(sc+1<ec && !check[sr][sc+1]){
                check[sr][sc+1] = true;
                // System.out.println(" sc: "+ sc);
                count+= mazepath_2d_4dir(sr, sc+1, er, ec, ans+" col:"+ sc, check);
                check[sr][sc+1] = false;
            }
            if(sc-1<ec && sc-1>=0 && !check[sr][sc-1]){
                check[sr][sc-1] = true;
                // System.out.println(" sc: "+ sc);
                count+= mazepath_2d_4dir(sr, sc-1, er, ec, ans+" col:"+ sc, check);
                check[sr][sc-1] = false;
            }

        return count;
    }

    //=================leetcode 200=================================================================================
    public void findIsland(char[][]grid, int sr, int sc, int er, int ec){
        // if(sr == er-1 && sc == ec-1){
        //     grid[sr][sc]='2';
        //     System.out.println("reaching");
        //     return ;
        // }

        grid[sr][sc]='2';

        if(sr+1<er && grid[sr+1][sc]=='1'){
            grid[sr+1][sc]='2';
            findIsland(grid, sr+1, sc, er, ec);
            // grid[sr+1][sc]='0';
        }
        if(sc+1<ec && grid[sr][sc+1]=='1'){
            grid[sr][sc+1]='2';
            findIsland(grid, sr, sc+1, er, ec);
            // grid[sr][sc+1]='0';
        }
        if(sr-1<er && sr-1>=0 && grid[sr-1][sc]=='1'){
            grid[sr-1][sc]='2';
            findIsland(grid, sr-1, sc, er, ec);
            // grid[sr+1][sc]='0';
        }
        if(sc-1<ec && sc-1>=0 && grid[sr][sc-1]=='1'){
            grid[sr][sc-1]='2';
            findIsland(grid, sr, sc-1, er, ec);
            // grid[sr][sc+1]='0';
        }
    }

    public static void main(String[]args){
        System.out.println(mazepath_2d_4dir(0, 0, 4, 4, "", new boolean[4][4]));
    }
}