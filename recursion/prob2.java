import java.util.ArrayList;
public class prob2{

    public static ArrayList<String> mazepath2D(int sr, int sc, int er, int ec){
        if(sr==er && sc==ec){
            ArrayList<String> base=new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> temp1=new ArrayList<>();
        ArrayList<String> temp2=new ArrayList<>();
        ArrayList<String> ans=new ArrayList<>();
        if(sr+1<=er){
            temp1= mazepath2D(sr+1, sc, er, ec);
        }
        if(sc+1<=ec){
            temp2= mazepath2D(sr, sc+1, er, ec);
        }

        for(String s:temp1){
            ans.add("H"+s);
        }
        for(String s:temp2){
            ans.add("V"+s);
        }
        return ans;
    }

    public static ArrayList<String> mazepath3D(int sr, int sc, int er, int ec){
        if(sr==er && sc==ec){
            ArrayList<String> base=new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> temp1=new ArrayList<>();
        ArrayList<String> temp2=new ArrayList<>();
        ArrayList<String> temp3=new ArrayList<>();
        ArrayList<String> ans=new ArrayList<>();
        if(sr+1<=er){
            temp1= mazepath2D(sr+1, sc, er, ec);
        }
        if(sc+1<=ec){
            temp2= mazepath2D(sr, sc+1, er, ec);
        }
        if(sr+1<=er && sc+1<=ec){
            temp3= mazepath3D(sr+1, sc+1, er, ec);
        }

        for(String s:temp1){
            ans.add("H"+s);
        }
        for(String s:temp2){
            ans.add("V"+s);
        }
        for(String s:temp3){
            ans.add("D"+s);
        }
        return ans;
    }
    public static ArrayList<String> mazepath3DMultiMove(int sr, int sc, int er, int ec){
        if(sr==er && sc==ec){
            ArrayList<String> base=new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> temp1=new ArrayList<>();
        ArrayList<String> temp2=new ArrayList<>();
        ArrayList<String> temp3=new ArrayList<>();
        ArrayList<String> ans=new ArrayList<>();
        for(int i=1;i<=er-sr;i++){
            temp1= mazepath3DMultiMove(sr+i, sc, er, ec);
            for(String s:temp1){
                ans.add(i+"V"+s);
            }
        }
        for(int i=1;i<=ec-sc;i++){
            temp2= mazepath3DMultiMove(sr, sc+i, er, ec);
            for(String s:temp2){
                ans.add(i+"H"+s);
            }
        }
        for(int i=1;i<=er-sr&&i<=ec-sc;i++){
            temp3= mazepath3DMultiMove(sr+i, sc+i, er, ec);
            for(String s:temp3){
                ans.add(i+"D"+s);
            }
        }
        
        return ans;
    }
    public static int mazepath3DMultiMoveVoidType(int sr, int sc, int er, int ec, String ans){
        if(sr==er && sc==ec){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        for(int i=1;i<=er-sr;i++){
            count+= mazepath3DMultiMoveVoidType(sr+i, sc, er, ec, ans+i+"V");
        }
        for(int i=1;i<=ec-sc;i++){
            count+= mazepath3DMultiMoveVoidType(sr, sc+i, er, ec, ans+i+"H");
        }
        for(int i=1;i<=er-sr&&i<=ec-sc;i++){
            count+= mazepath3DMultiMoveVoidType(sr+i, sc+i, er, ec, ans+i+"D");
        }
        
        return count;
    }
    public static int mazepath4D(int sr, int sc, int er, int ec, String ans, int[][]vis){
        if(sr==er && sc==ec){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        if(sr+1<vis[0].length && vis[sr+1][sc]==0){
            vis[sr+1][sc]=1;
            count+= mazepath4D(sr+1, sc, er, ec, ans+"S", vis);
            vis[sr+1][sc]=0;
        }
        if(sc+1<vis[0].length && vis[sr][sc+1]==0){
            vis[sr][sc+1]=1;
            count+= mazepath4D(sr, sc+1, er, ec, ans+"E", vis);
            vis[sr][sc+1]=0;
        }
        if(sr-1>=0 && vis[sr-1][sc]==0){
            vis[sr-1][sc]=1;
            count+= mazepath4D(sr-1, sc, er, ec, ans+"N", vis);
            vis[sr-1][sc]=0;
        }
        if(sc-1>=0 && vis[sr][sc-1]==0){
            vis[sr][sc-1]=1;
            count+= mazepath4D(sr, sc-1, er, ec, ans+"W", vis);
            vis[sr][sc-1]=0;
        }
        return count;
    }
    public static int mazepath4D_diff(int sr, int sc, int er, int ec, String ans, int[][]vis){
        if(sr==er && sc==ec){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        vis[sr][sc]=1;
        if(sr+1<vis[0].length && vis[sr+1][sc]==0){
            count+= mazepath4D_diff(sr+1, sc, er, ec, ans+"S", vis);
        }
        if(sc+1<vis.length && vis[sr][sc+1]==0){
            count+= mazepath4D_diff(sr, sc+1, er, ec, ans+"E", vis);
        }
        if(sr-1>=0 && vis[sr-1][sc]==0){
            count+= mazepath4D_diff(sr-1, sc, er, ec, ans+"N", vis);
        }
        if(sc-1>=0 && vis[sr][sc-1]==0){
            count+= mazepath4D_diff(sr, sc-1, er, ec, ans+"W", vis);
        }
        vis[sr][sc]=0;
        return count;
    }
    public static boolean checkFloodFill(int sr, int sc, int[][]vis){
        if(sr<0 || sr>=vis.length || sc<0 || sc>=vis[0].length || vis[sr][sc]==1)
        return false;
        return true;
    }
    public static int floodfill(int sr, int sc, int er, int ec, int[][]vis, String ans, int[][]dir, String[]dirN){
        if(sr==er && sc==ec){
            System.out.println(ans);
            return 1;
        }
        int count=0;
        if(!checkFloodFill(sr, sc, vis))
        return 0;

        vis[sr][sc]=1;
        for(int i=0;i<dir.length;i++){
            //just put another for loop multiplying by radius for sr & sc increasing at every step.
            count+=floodfill(sr+dir[i][0], sc+dir[i][1], er, ec, vis, ans+dirN[i], dir, dirN);
        }
        vis[sr][sc]=0;
        return count;
    }


    //important, revise this!!!
    public static class pair{
        String str="";
        int length=0;
        pair(String str, int length){
            this.str=str;
            this.length=length;
        }
    }

    public static int coinChangePermutationInfinite(int[]coins, int tar, String ans){
        if(tar==0){
            System.out.println(ans);
            return 1;
        }
        if(tar<0)
        return 0;

        int count=0;
        for(int value: coins){
            count+=coinChangePermutationInfinite(coins, tar-value, ans+value);
        }
        return count;
    }
    public static int coinChangeCombinationInfinite(int[]coins, int tar, String ans, int index){
        if(tar==0){
            System.out.println(ans);
            return 1;
        }
        if(tar<0)
        return 0;

        int count=0;
        for(int i=index;i<coins.length;i++){
            count+=coinChangeCombinationInfinite(coins, tar-coins[i], ans+coins[i], i);
        }
        return count;
    }
    public static int coinChangeCombinationOneCoin(int[]coins, int tar, String ans, int index){
        if(tar==0){
            System.out.println(ans);
            return 1;
        }
        if(tar<0)
        return 0;

        int count=0;

        for(int i=index;i<coins.length;i++){
            count+=coinChangeCombinationOneCoin(coins, tar-coins[i], ans+coins[i], i+1);
        }
        return count;
    }
    
    public static int coinChangePermutationOneCoin(int[]coins, int tar, String ans){
        if(tar==0){
            System.out.println(ans);
            return 1;
        }
        if(tar<0)
        return 0;

        int count=0;

        for(int i=0;i<coins.length;i++){
            int value= coins[i];
            if(value!=-1){
            coins[i]=-1;
            count+=coinChangePermutationOneCoin(coins, tar-value, ans+value);
            coins[i]=value;
            }
        }
        return count;
    }


    public static void main(String[]args){
        // ArrayList<String> c= mazepath2D(0,0,2,2);
        // ArrayList<String> c= mazepath3D(0,0,2,2);
        // ArrayList<String> c= mazepath3DMultiMove(0,0,2,2);
        // int c= mazepath3DMultiMoveVoidType(0,0,2,2,"");
        int er=3,ec=3;
        int[][]vis=new int[er+1][ec+1];
        // vis[0][0]=1;
        int[][]dir=new int[8][2];
        dir[0][0]= 0;
        dir[0][1]= 1;
        dir[1][0]= 1;
        dir[1][1]= 1;
        dir[2][0]= 1;
        dir[2][1]= 0;
        dir[3][0]= 1;
        dir[3][1]= -1;
        dir[4][0]= 0;
        dir[4][1]= -1;
        dir[5][0]= -1;
        dir[5][1]= -1;
        dir[6][0]= -1;
        dir[6][1]= 0;
        dir[7][0]= -1;
        dir[7][1]= 1;

        String[]dirN={"E","se","S","sw","W","nw","N","ne"};
        // int c= mazepath4D_diff(0,0,2,2,"",vis, dir, dirN);
        // int c= floodfill(0,0,er,ec,vis,"",dir,dirN);
        // System.out.println(c);
        int[]coins={2,3,5,7};
        // int res= coinChangePermutationInfinite(coins, 10, "");
        // int res= coinChangeCombinationInfinite(coins, 10, "", 0);
        // int res= coinChangeCombinationOneCoin(coins, 10, "", 0);
        int res= coinChangePermutationOneCoin(coins, 10, "");
        System.out.println(res);
    }
}