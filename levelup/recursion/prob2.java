public class prob2{

    public static boolean unplaced(int[]row, int[]col, int[]diag, int[]adiag, int idx, int r){
        int ro = idx/r;
        int co = idx%r;

        if(row[ro]==0 && col[co]==0 && diag[ro-co+r-1]==0 && adiag[ro+co]==0){
            return true;
        } else {
            return false;
        }
    }

    public static int nQueen(int no, int[][]arr, String ans, int placedQueen, int idx, int []row, int[]col, int[]diag, int[]adiag){
        if(no == placedQueen){
            System.out.println(ans);
            return 1;
        }

        if( idx == arr.length * arr[0].length){
            return 0;
        }

        int count = 0;
        int r = arr.length;
        int c = arr[0].length;
        for(int i=idx; i<r*c; i++){
            if(unplaced(row, col, diag, adiag, i, r)){
                // System.out.println("executed for: "+ i);
                int crow=i/r;
                int ccol=i%c;
                row[crow]=1;
                col[ccol]=1;
                diag[crow-ccol+arr.length-1]=1;
                adiag[crow+ccol]=1;
                count+=nQueen(no, arr, ans+"("+crow+","+ccol+") ", placedQueen+1, i+1, row, col, diag, adiag);
                row[crow]=0;
                col[ccol]=0;
                diag[crow-ccol+arr.length-1]=0;
                adiag[crow+ccol]=0;
            }
        }

        return count;
    }

    public static void main(String[]args){
        int r=4,c=4;
        int[][]arr = new int[r][c];
        int []row = new int[r];
        int []col = new int[c];
        int []diag = new int[r+c-1];
        int []adiag = new int[r+c-1];
        nQueen(4, arr, "", 0, 0, row, col, diag, adiag);
    }
}