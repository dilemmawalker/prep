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
                int ccol=i%r;
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

    public static boolean checkForUnplacedBits(int row, int col, int diag, int adiag, int idx, int r){
        int crow = idx/r;
        int ccol = idx%r;

        if(((row & (1<<crow))==0) && ((col & (1<<ccol))==0) && ((diag & (1<<(crow-ccol+r-1)))==0) && ((adiag & (1<<(crow+ccol)))==0)){
            return true;
        } else {
            return false;
        }
    }

    public static int nQueenBits(int no, int[][]arr, String ans, int placedQueen, int idx, int row, int col, int diag, int adiag){
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
            if(checkForUnplacedBits(row, col, diag, adiag, i, r)){
                // System.out.println("executed for: "+ i);
                int crow=i/r;
                int ccol=i%r;
                row = row|(1<<crow);
                col = col|(1<<ccol);
                diag = diag|(1<<(crow-ccol+r-1));
                adiag = adiag|(1<<(crow+ccol));
                count+=nQueenBits(no, arr, ans+"("+crow+","+ccol+") ", placedQueen+1, i+1, row, col, diag, adiag);
                row = row&(~(1<<crow));
                col = col&(~(1<<ccol));
                diag = diag&(~(1<<(crow-ccol+r-1)));
                adiag = adiag&(~(1<<(crow+ccol)));
            }
        }

        return count;
    }

    public static int nQueenBitsSubSeq(int no, int[][]arr, String ans, int placedQueen, int idx, int row, int col, int diag, int adiag){
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
        
            if(checkForUnplacedBits(row, col, diag, adiag, idx, r)){
                // System.out.println("executed for: "+ i);
                int crow=idx/r;
                int ccol=idx%r;
                row = row|(1<<crow);
                col = col|(1<<ccol);
                diag = diag|(1<<(crow-ccol+r-1));
                adiag = adiag|(1<<(crow+ccol));
                count+=nQueenBitsSubSeq(no, arr, ans+"("+crow+","+ccol+") ", placedQueen+1, idx+1, row, col, diag, adiag);
                row = row&(~(1<<crow));
                col = col&(~(1<<ccol));
                diag = diag&(~(1<<(crow-ccol+r-1)));
                adiag = adiag&(~(1<<(crow+ccol)));
            }
            count+=nQueenBitsSubSeq(no, arr, ans, placedQueen, idx+1, row, col, diag, adiag);
        

        return count;
    }

    public static boolean sudokuCheck(int[][]arr, int idx, int []row, int[]col, int[]box, int no){
        int n = arr.length;
        int m = arr[0].length;

        int r = idx/n;
        int c = idx%n;
        // System.out.println("coming: "+ idx +" "+no);
        // printSudoku(arr);
        int mask = (1<<no);
        // System.out.println("verify");
        // System.out.println((row[r] & mask)+ " "+ (col[c] & mask) +" "+ (box[((r/3)*3 + (c/3))] & mask));

        if(((row[r] & mask) == 0) && ((col[c] & mask) == 0) && ((box[((r/3)*3 + (c/3))] & mask) == 0)){
            // System.out.println("true returned: "+ idx);
            return true;
        } else {
            return false;
        }
    }

    public static int sudoku(int[][]arr, int idx, int []row, int[]col, int[]box){
        int n = arr.length;
        int m = arr[0].length;
        if(idx == n*m){
            printSudoku(arr);
            return 1;
        }

        int count = 0;
        
            int r = idx/n;
            int c = idx%n;
            // System.out.println("checking2: "+ idx);
            if(arr[r][c]!=0){
                count += sudoku(arr, idx+1, row, col, box);
            }
            else {
                for(int j=1; j<=9; j++){
                    // System.out.println("checking2: "+ idx +" "+ j);
                    if(sudokuCheck(arr, idx, row, col, box, j)){
                        // System.out.println("checking: "+ idx +" "+ j);
                        int mask = (1<<j);
                        row[r] = row[r]^mask;
                        col[c] = col[c]^mask;
                        box[((r/3)*3 + (c/3))] = box[((r/3)*3 + (c/3))]^mask;
                        arr[r][c] = j;
                        count += sudoku(arr, idx+1, row, col, box);
                        arr[r][c] = 0;
                        row[r] = row[r]^mask;
                        col[c] = col[c]^mask;
                        box[((r/3)*3 + (c/3))] = box[((r/3)*3 + (c/3))]^mask;
                    }
                }
            }

        return count;
    }

    public static int[][] constructSudoku(){
        int[][]arr={{8,0,0,0,0,0,0,0,0},
                    {0,0,3,6,0,0,0,0,0},
                    {0,7,0,0,9,0,2,0,0},
                    {0,5,0,0,0,7,0,0,0},
                    {0,0,0,0,4,5,7,0,0},
                    {0,0,0,1,0,0,0,3,0},
                    {0,0,1,0,0,0,0,6,8},
                    {0,0,8,5,0,0,0,1,0},
                    {0,9,0,0,0,0,4,0,0}};
        
        return arr;
    }

    public static int getEmptyBoxCount(int[][]arr){
        int count = 0;
        for(int i=0; i<arr.length; i++){
            for (int j=0; j<arr[0].length; j++){
                if(arr[i][j]==0)
                count++;
            }
        }
        return count;
    }
    public static void print1DArray(int[]arr){
        for(int i=0; i<arr.length; i++)
        System.out.print(arr[i]+" ");
        System.out.println();
    }

    public static void setInitialForSudoku(int []row, int[]col, int[]box, int [][]arr){
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[0].length; j++){
                int val = arr[i][j];
                if(val!=0){
                    row[i] = row[i]^(1<<val);
                    col[j] = col[j]^(1<<val);
                    box[(i/3)*3 + (j/3)] = box[(i/3)*3 + (j/3)]^(1<<val);
                }
            }
        }
    } 

    public static void printSudoku(int[][]arr){
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[0].length; j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[]args){
        int r=4,c=4;
        // int[][]arr = new int[r][c];
        // int []row = new int[r];
        // int []col = new int[c];
        // int []diag = new int[r+c-1];
        // int []adiag = new int[r+c-1];
        // nQueen(4, arr, "", 0, 0, row, col, diag, adiag);
        // nQueenBits(4, arr, "", 0, 0, 0, 0, 0, 0);
        // nQueenBitsSubSeq(4, arr, "", 0, 0, 0, 0, 0, 0);
        int []row = new int[10];
        int []col = new int[10];
        int []box = new int[10];

        int[][]sudokuArray = constructSudoku();
        setInitialForSudoku(row, col, box, sudokuArray);
        // printSudoku(sudokuArray);
        // print1DArray(row);
        // print1DArray(col);
        // print1DArray(box);

        System.out.println(sudoku(sudokuArray, 0, row, col, box));
    }
}