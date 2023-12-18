import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
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
    public static int[][] constructSudoku(){
        int[][]arr={{5,3,0,0,7,0,0,0,0},
                    {6,0,0,1,9,5,0,0,0},
                    {0,9,8,0,0,0,0,6,0},
                    {8,0,0,0,6,0,0,0,3},
                    {4,0,0,8,0,3,0,0,1},
                    {7,0,0,0,2,0,0,0,6},
                    {0,6,0,0,0,0,2,8,0},
                    {0,0,0,4,1,9,0,0,5},
                    {0,0,0,0,8,0,0,7,9}};
        
        return arr;
    }

    public static boolean sudoku(int [][]arr, int index, int[] r, int[] c, int[] box, int nos){
        //not 100% accurate by logic, but must be a small thing, check if time permits!
        if(nos==0){
            printArr(arr);
            return true;
        }
        if(index==arr.length * arr[0].length){
            return false;
        }

        boolean call= false;

        // for(int i=sr;i<arr[0].length;i++){
        //     for(int j=sc;j<arr.length;j++){
                int i=index/9,j=index%9;
                for(int num=1;num<10;num++){
                    if(arr[i][j]!=0)
                    call= call || sudoku(arr, index+1, r, c, box, nos);
                    if(sudokuSafeToPlace(arr, num, i, j, r, c, box)){
                        arr[i][j]=num;
                        r[i]|=(1<<num);
                        c[j]|=(1<<num);
                        box[((i/3)*3)+(j/3)]|=(1<<num);
                        call= call || sudoku(arr, index+1, r, c, box, nos-1);
                        arr[i][j]=0;
                        r[i]&=(~(1<<num));
                        c[j]&=(~(1<<num));
                        box[((i/3)*3)+(j/3)]&=(~(1<<num));
                    }
                }
            // }
        // }
        return call;
    }

    public static void printArr(int[][]arr){
        for(int []ele1:arr){
            for(int ele: ele1){
                System.out.print(ele+" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
    public static boolean sudokuSafeToPlace(int[][]arr, int num, int row, int col, int[]r, int[]c, int []box){
        int mask=(1<<num);
        if(arr[row][col]==0 && ((r[row]& mask)==0) && ((c[col]& mask)==0) && ((box[((row/3)*3)+(col/3)] & mask)==0))
        return true;
        return false;
    }

    public static boolean wordBreak(String s, List<String> wordDict, int index) {
        if(s.length()==0){
            return true;
        }

        if(index>s.length())
        return false;

        // System.out.println(s.length());

        boolean flag=false;
        String temp= s.substring(0,index);
        System.out.println(temp+" index:"+index);
        if(checkSubstringInDictionary(temp, wordDict)){
            flag= flag || wordBreak(s.substring(index), wordDict, 1);
        }
        flag= flag || wordBreak(s, wordDict, index+1);

        return flag;
    }
    public static boolean checkSubstringInDictionary(String str, List<String> wordDict){
        for(String s: wordDict){
            if(s.equals(str))
            return true;
        }
        return false;
    }

    public static boolean cryptarithmetic(String str1, String str2, String str3, String unique, int index, HashMap<Character,Integer>map, int[]used){
        if(index==unique.length()){
            if(checkSumCryptarithmetic(str1, str2, str3, map)){
            printFunction(unique, map);
            return true;
            }
            else
            return false;
        }
        if(index>=unique.length())
        return false;

        boolean flag= false;
        char ch= unique.charAt(index);
        for(int i=0;i<10;i++){
                if(used[i]==0){
                    used[i]=1;
                    map.put(ch,i);
                    flag= flag || cryptarithmetic(str1, str2, str3, unique, index+1, map, used);
                    map.remove(ch);
                    used[i]=0;
                }

        }
        return flag;
    }
    public static boolean checkSumCryptarithmetic(String str1, String str2, String str3, HashMap<Character,Integer>map){
        long no1=0, no2=0, no3=0;
        no1= convertStringToNo(str1,map);
        no2= convertStringToNo(str2,map);
        no3= convertStringToNo(str3,map);

        if((no1+no2)==no3)
        return true;
        return false;
    }
    public static long convertStringToNo(String str, HashMap<Character, Integer> map){
        long no=0;
        long conversion=1;
        for(int i=0;i<str.length();i++){
            char ch= str.charAt(i);
            no=no*conversion + map.get(ch);
            conversion*=10;
        }
        return no;
    }
    public static void printFunction(String unique, HashMap<Character,Integer>map){
        for(int i=0;i<unique.length();i++){
            char ch=unique.charAt(i);
            System.out.print(ch+" : "+map.get(ch)+"  ");
        }
    }
    public static String uniqueString(String str1, String str2, String str3){
        String s=str1+str2+str3;
        int[]arr=new int[26];
        for(int i=0;i<s.length();i++){
            char ch= s.charAt(i);
            arr[ch-'a']++;
        }
        String unique="";
        for(int i=0;i<26;i++){
            int val= arr[i];
            if(val!=0)
            unique+=(char)(97+i);
        }
        return unique;
    }
    public static void laxigraphicalOrder(int s, int e){
        if(s>e)
        return;

        System.out.println(s);
        for(int i=0;i<10;i++){
            int no= s*10 + i;
            if(no<e){
                laxigraphicalOrder(no, e);
            }
            else break;
        }

        //this call is for other starting no.s like 2, 3, 4, ... 9
        //not necessary that recursion has to be called from one function only, can be called from same function itself.
        if(s<10)
        laxigraphicalOrder(s+1, e);
    }

    //leetcode 1079 
    public static int numTilePossibilities(String tiles, int index, String ans){
        if(index==tiles.length()){
            return 0;
        }

        int count=0;
        int[]arr=new int[26];
        for(int i=0;i<tiles.length();i++){
            char ch= tiles.charAt(i);
            int no= ch-'A';
            if(arr[no]==0){
                arr[no]=1;
                count+=numTilePossibilities(tiles.substring(0,i)+tiles.substring(i+1), index, ans+ch) + 1;
                // arr[no]=0;
            }
        }
        return count;
    }
    public static long factorial(int no){
        if(no==0)
        return 1;

        long ans=1;
        for(int i=1;i<=no;i++){
            ans*=i;
        }
        return ans;
    }

    public static List<String> generateParenthesis(int n, int open, int close) {
        if(open==n && close==n){
            List<String>base=new ArrayList<>();
            base.add("");
            return base;
        }

        List<String>ans=new ArrayList<>();
        List<String>tempAnsOpen=new ArrayList<>();
        List<String>tempAnsClose=new ArrayList<>();
        if(open+1<=n){
            tempAnsOpen= generateParenthesis(n, open+1, close);
        }
        if(close<open){
            tempAnsClose= generateParenthesis(n, open, close+1);
        }
        for(String s: tempAnsOpen){
            ans.add("("+s);
        }
        for(String s: tempAnsClose){
            ans.add(")"+s);
        }
        return ans;
    }


    public static void main(String[]args){
        int queens= 4;
        // int[][]arr=new int[10][10];
        // int ans= nQueenBasic(arr, queens, "", 0);
        // int []r= new int[arr[0].length];
        // int []c= new int[arr.length];
        // int []d= new int[arr.length + arr[0].length -1];
        // int []ad= new int[arr.length + arr[0].length -1];
        // int ans= nQueenShadow(arr, queens, "", 0, r, c, d, ad);
        // int ans= nQueenShadowBetter(arr, queens, "", 0, r, c, d, ad);
        // int ans= nQueenShadowBetterSubsequence(arr, queens, "", 0, 0, r, c, d, ad);
        // int ans= nQueenShadowBetterSubsequenceBetter(arr, queens, "", 0, r, c, d, ad);
        int[][] arr=constructSudoku();
        int count=0;
        for(int []ele1:arr){
            for(int ele: ele1){
                if(ele==0)
                count++;
            }
        }
        // boolean call= sudoku(arr, 0, new int[9],  new int[9], new int[9], count);
        // System.out.println(ans);

        List<String>wordDict= new ArrayList<>();
        wordDict.add("cat");
         wordDict.add("cats");
          wordDict.add("and");
           wordDict.add("dog");
            wordDict.add("dogs");
             wordDict.add("og");
        // List<String>wordDict={"cats","dog","sand","and","cat"};
        // boolean res= wordBreak("catsanddog", wordDict, 0);
        HashMap<Character, Integer>map=new HashMap<>();
        String str1= "send"; String str2= "more"; String str3= "money";
        // String unique= uniqueString(str1, str2, str3);
        // System.out.println(unique);
        // boolean res= cryptarithmetic(str1, str2, str3, unique, 0, map, new int[10]);
        // System.out.println(res);
        // laxigraphicalOrder(1, 1000);
        // int res= numTilePossibilities("AAB",0,"");
        List<String> res= generateParenthesis(3,0,0);
        System.out.println(res);
    }
}