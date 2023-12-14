import java.util.Arrays;
public class prob4{


    //nqueen basic
    public static int nQueenBasic(int [][]arr, int queens, String ans, int index){
        if(queens==0){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        int places= arr.length * arr[0].length;
        for(int i=index;i<places;i++){
            int row= i/arr.length;
            int col= i%arr.length;
            if(safeToPlace(arr, row, col)){
                arr[row][col]=1;
                count+= nQueenBasic(arr, queens-1, ans+"("+row+","+col+")", i+1);
                arr[row][col]=0;
            }
        }
        return count;
    }
    public static boolean safeToPlace(int[][]arr, int row, int col){
        if(safeToPlaceRow(arr, row, col) && safeToPlaceCol(arr, row, col) && safeToPlaceDiag(arr, row, col) && safeToPlaceAntiDiag(arr, row, col))
        return true;
        return false;
    }
    public static boolean safeToPlaceRow(int[][]arr, int row, int col){
        while(col>=0){
            if(arr[row][col]==1)
            return false;
            col--;
        }
        return true;
    }
    public static boolean safeToPlaceCol(int[][]arr, int row, int col){
        while(row>=0){
            if(arr[row][col]==1)
            return false;
            row--;
        }
        return true;
    }
    public static boolean safeToPlaceDiag(int[][]arr, int row, int col){
        while(col>=0 && row>=0){
            if(arr[row][col]==1)
            return false;
            col--;
            row--;
        }
        return true;
    }
    public static boolean safeToPlaceAntiDiag(int[][]arr, int row, int col){
        while(row>=0 && col<arr.length){
            if(arr[row][col]==1)
            return false;
            col++;
            row--;
        }
        return true;
    }



    //nqueen solution using arrays.
    //make 4 arrays: row, col, diag, anti-diag

    public static int nQueenShadow(int[][]arr, int queens, String ans, int index, int[]r, int[]c, int[]d, int[]ad){
        if(queens==0){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        for(int i=index;i<arr.length*arr[0].length;i++){
            int row= i/arr.length;
            int col= i%arr.length;
            if(isSafeToPlace(arr, row, col, r, c, d, ad)){
                arr[col][row]=1;
                r[row]=1;
                c[col]=1;
                d[row-col+arr.length-1]=1;
                ad[row+col]=1;
                count+=nQueenShadow(arr, queens-1, ans+"("+row+","+col+")", i+1, r, c, d, ad);
                //the commented code gives same TC as nQueenShadowBetter where we put every queen to work from next row only.
                // count+=nQueenShadow(arr, queens-1, ans+"("+row+","+col+")", (row+1)*arr.length, r, c, d, ad);
                arr[col][row]=0;
                r[row]=0;
                c[col]=0;
                d[row-col+arr.length-1]=0;
                ad[row+col]=0;
            }
        }
        return count;
    }
    public static int nQueenShadowBetter(int[][]arr, int queens, String ans, int indexRow, int[]r, int[]c, int[]d, int[]ad){
        if(queens==0){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        for(int i=indexRow; i<arr[0].length; i++){
            for(int j=0; j<arr.length; j++){
                int row= i;
                int col= j;
                if(isSafeToPlace(arr, row, col, r, c, d, ad)){
                    arr[col][row]=1;
                    r[row]=1;
                    c[col]=1;
                    d[row-col+arr.length-1]=1;
                    ad[row+col]=1;
                    count+=nQueenShadowBetter(arr, queens-1, ans+"("+row+","+col+")", i+1, r, c, d, ad);
                    arr[col][row]=0;
                    r[row]=0;
                    c[col]=0;
                    d[row-col+arr.length-1]=0;
                    ad[row+col]=0;
                }
            }
        }
        return count;
    }

    public static int nQueenShadowBetterSubsequence(int[][]arr, int queens, String ans, int indexRow, int indexCol, int[]r, int[]c, int[]d, int[]ad){
        if(queens==0){
            System.out.println(ans);
            return 1;
        }
        if(indexCol>=arr.length)
        return 0;

        int count=0;
        for(int i=indexRow; i<arr[0].length; i++){
                int row= i;
                int col= indexCol;
                if(isSafeToPlace(arr, row, col, r, c, d, ad)){
                    arr[col][row]=1;
                    r[row]=1;
                    c[col]=1;
                    d[row-col+arr.length-1]=1;
                    ad[row+col]=1;
                    count+=nQueenShadowBetterSubsequence(arr, queens-1, ans+"("+row+","+col+")", i+1, 0, r, c, d, ad); //queen placed.
                    arr[col][row]=0;
                    r[row]=0;
                    c[col]=0;
                    d[row-col+arr.length-1]=0;
                    ad[row+col]=0;
                }
                count+=nQueenShadowBetterSubsequence(arr, queens, ans, i, col+1, r, c, d, ad); //queen is not placed.(in this column)
                count+=nQueenShadowBetterSubsequence(arr, queens, ans, i+1, col, r, c, d, ad); //queen is not placed.(in this row).
        }
        return count;
    }

    public static int nQueenShadowBetterSubsequenceBetter(int[][]arr, int queens, String ans, int indexRow, int[]r, int[]c, int[]d, int[]ad){
        if(queens==0){
            System.out.println(ans);
            return 1;
        }
        if(indexRow>=arr[0].length)
        return 0;

        int count=0;
        for(int i=0; i<arr.length; i++){
                int row= indexRow;
                int col= i;
                if(isSafeToPlace(arr, row, col, r, c, d, ad)){
                    arr[col][row]=1;
                    r[row]=1;
                    c[col]=1;
                    d[row-col+arr.length-1]=1;
                    ad[row+col]=1;
                    count+=nQueenShadowBetterSubsequenceBetter(arr, queens-1, ans+"("+row+","+col+")", indexRow+1, r, c, d, ad); //queen placed.
                    arr[col][row]=0;
                    r[row]=0;
                    c[col]=0;
                    d[row-col+arr.length-1]=0;
                    ad[row+col]=0;
                }
                
        }
        count+=nQueenShadowBetterSubsequenceBetter(arr, queens, ans, indexRow+1, r, c, d, ad); //queen is not placed.
        return count;
    }
    public static boolean isSafeToPlace(int [][]arr, int row, int col, int []r, int []c, int []d, int []ad){
        if(r[row]!=1 && c[col]!=1 && d[row-col + arr.length-1]!=1 && ad[row+col]!=1)
        return true;
        return false;
    }

    public static void main(String[]args){
        int queens= 4;
        int[][]arr=new int[10][10];
        // int ans= nQueenBasic(arr, queens, "", 0);
        int []r= new int[arr[0].length];
        int []c= new int[arr.length];
        int []d= new int[arr.length + arr[0].length -1];
        int []ad= new int[arr.length + arr[0].length -1];
        // int ans= nQueenShadow(arr, queens, "", 0, r, c, d, ad);
        // int ans= nQueenShadowBetter(arr, queens, "", 0, r, c, d, ad);
        // int ans= nQueenShadowBetterSubsequence(arr, queens, "", 0, 0, r, c, d, ad);
        int ans= nQueenShadowBetterSubsequenceBetter(arr, queens, "", 0, r, c, d, ad);
        System.out.println(ans);
    }
}