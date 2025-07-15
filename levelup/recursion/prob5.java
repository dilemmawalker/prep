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
        if(sr == er && sc == ec){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        if(sr+1<=er && sc+1<=ec){
            if(!check[sr+1][sc]){
                check[sr+1][sc] = true;
                count+= mazepath_2d_4dir(sr+1, sc, er, ec, ans+" row:"+ sr, check);
                check[sr+1][sc] = false;
            }
            else if(!check[sr][sc+1]){
                check[sr+1][sc] = true;
                count+= mazepath_2d_4dir(sr, sc+1, er, ec, ans+" col:"+ sc, check);
                check[sr+1][sc] = false;
            }
        }

        return count;
    }

    public static void main(String[]args){
        System.out.println(mazepath_2d_4dir(0, 0, 3, 3, "", new boolean[4][4]));
    }
}