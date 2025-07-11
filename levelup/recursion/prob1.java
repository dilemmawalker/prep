import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class prob1{

    public static Scanner scn = new Scanner(System.in);

    public static void printIncreasing(int start, int end){
        if(start == end){
            System.out.println(start);
            return;
        }

        System.out.println(start);
        printIncreasing(start+1, end);
    }

    public static void printDecreasing(int start, int end){
        if(start == end+1){
            return;
        }

        printDecreasing(start+1, end);
        System.out.println(start);
    }

    public static void printIncreasingDecreasing(int start, int end){
        //base-case
        if(start ==end){
            return;
        }

        //pre-area
        if(start%2==0)
        System.out.print(start + " ");

        printIncreasingDecreasing(start+1, end);

        //post-area
        if(start%2!=0)
        System.out.print(start + " ");
    }

    public static int factorial(int value, int end){
        if(value == end){
            return value;
        }

        int ans = 1;
        ans*= factorial(value+1, end) * value;
        return ans;
    }

    public static int factorial2(int value){
        if(value==0)
            return 1;

//        int ans = 1;
//        ans *= factorial2(value-1) * value;
//
//        return ans;

        return factorial2(value-1) * value;
    }

    public static int power(int value, int pow){
        if(pow==1)
            return value;

        return power(value, pow-1) * value;
    }

    public static int power2(int value, int pow){
        if(pow==1)
            return value;

        int n = power(value, pow/2);
        if(pow%2==0)
            return n*n;
        else
            return n*n*value;
    }

    public static boolean find(int value, int[]arr, int vidx){
        if(vidx == arr.length)
            return false;

        if(value == arr[vidx])
            return true;



        boolean ans = false;
        System.out.println("Pre: "+ vidx+ " "+  ans);
        ans = ans || find(value, arr, vidx+1);
        System.out.println("Post: "+ vidx + " " + ans);

        return ans;
    }

    public static int maximum(int []arr, int vidx){
        if(vidx == arr.length)
            return Integer.MIN_VALUE;

        return Math.max(maximum(arr, vidx+1), arr[vidx]);
    }

    public static int minimum(int[]arr, int vidx){
        if(vidx == arr.length)
        return Integer.MAX_VALUE;

        int value = minimum(arr, vidx+1);
        return Math.min(value, arr[vidx]);
    }


  //  ================================================================================================================


    public static ArrayList<String> subsequence_return(String str, int vidx){
        if(vidx == str.length()){
            ArrayList<String> temp = new ArrayList<>();
            temp.add("");
            return temp;
        }

        ArrayList<String>ans = new ArrayList<>();
        ArrayList<String> temp = subsequence_return(str, vidx+1);

        // System.out.println(temp);

        char ch = str.charAt(vidx);
        for(String s: temp){
            ans.add(ch+""+s+"");
        }
        for(String s: temp){
            ans.add(s);
        }
        // System.out.println("hello");

        return ans;
        
    }

    public static void subsequence_void(String str, int vidx, ArrayList<String> ans, String tans){
        if(vidx == str.length()){
            ans.add(tans);
            return;
        }

        subsequence_void(str, vidx+1, ans, tans+str.charAt(vidx));
        subsequence_void(str, vidx+1, ans, tans);
    }

    //permutation with repetition allowed
    public static ArrayList<String> permutation_withduplication(String str, int idx){
        if(idx == str.length()-1){
            ArrayList<String> temp = new ArrayList<>();
            temp.add(str.charAt(idx)+"");
            return temp;
        }

        ArrayList<String> ans = new ArrayList<>();
        ArrayList<String> temp = permutation_withduplication(str, idx+1);
        // System.out.println(temp);
        for(String s: temp){
            for(int i=0;i<=s.length();i++){
                String start = s.substring(0,i);
                String end = s.substring(i);

                ans.add(start + "" + str.charAt(idx) + "" + end);
            }
        }

        return ans;
    }

    public static int permutation_without_duplication(String str, ArrayList<String>ans, String tans){
        if(str.length()==0){
            ans.add(tans);
            return 1;
        }

        int count = 0;
        boolean[]arr = new boolean[26];
        
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            int val = ch-'a';

            if(arr[val])
            continue;

            arr[val]=true;
            count += permutation_without_duplication(str.substring(0,i) + str.substring(i+1), ans, tans+ch+"");
            // arr[val]=false;
        }

        return count;

    }

    public static HashMap<Integer,String> getMapForKeypad(){
        HashMap<Integer, String> map = new HashMap<>();

        map.put(1, "abc");
        map.put(2, "def");
        map.put(3, "ghk");
        map.put(11, "uvw");
        map.put(10, "xyz");

        return map;
    }

    public static int keypad_void(String no, String ans, int vidx, HashMap<Integer,String>map){
        if(vidx == no.length()){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        String str = map.get(no.charAt(vidx)-'0');
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);

            count+=keypad_void(no, ans+""+ch, vidx+1, map);

        }

        if(no.charAt(vidx)=='1' && vidx+1<no.length() && (no.charAt(vidx+1)=='0'||no.charAt(vidx+1)=='1')){
            String ss = map.get(10+(no.charAt(vidx+1)-'0'));
            for(int i=0;i<ss.length();i++){
                char ch = ss.charAt(i);

                 count+=keypad_void(no, ans+""+ch, vidx+1, map);
            }
        }

        return count;
        
    }

    public static ArrayList<String> keypad_return(String no, int idx, HashMap<Integer, String>map){
        if(idx>=no.length()){
            ArrayList<String> temp = new ArrayList<>();
            temp.add("");
            return temp;
        }

        ArrayList<String>ans = new ArrayList<>();
        int ch = no.charAt(idx)-'0';
        String str = map.get(ch);

        ArrayList<String> temp = keypad_return(no, idx+1, map);
        if(ch==1 && idx+1<no.length() && (no.charAt(idx+1)=='0' || no.charAt(idx+1)=='1')){
            // System.out.println("coming in 2nd case");
            ArrayList<String> temp2 = keypad_return(no, idx+2, map);

            int ch2 = 10+(no.charAt(idx+1) - '0');
            // System.out.println("coming in 2nd case: " + ch2);
            String str2 = map.get(ch2);

            for(int i=0;i<str2.length();i++){
                char tempCh = str2.charAt(i);
                for(String s: temp2){
                    ans.add(tempCh + ""+s);
            }
        }
        }

        for(int i=0;i<str.length();i++){
            char tempCh = str.charAt(i);
            for(String s: temp){
                ans.add(tempCh+""+s);
            }
        }

        return ans;
        
    }
    
    public static void combination_multi_coin(int[]arr, int target, int idx, String ans){
        if(target == 0){
            System.out.println(ans);
            return;
        }

        if(idx == arr.length){
            return;
        }


        for(int i=idx; i<arr.length; i++){
            int val = arr[i];
            if(target-val >=0){
                combination_multi_coin(arr, target-val, i, ans+" "+val);
            }
        }
    }

    public static void permutation_multi_coin(int[]arr, int target, int idx, String ans){
        if(target == 0){
            System.out.println(ans);
            return;
        }

        if(idx == arr.length){
            return;
        }


        for(int i=0; i<arr.length; i++){
            int val = arr[i];
            if(target-val >=0){
                permutation_multi_coin(arr, target-val, i, ans+" "+val);
            }
        }
    }

    public static void permutation_single_coin(int[]arr, int target, String ans, boolean[]check){
        if(target == 0){
            System.out.println(ans);
            return;
        }

        
        for(int i=0; i<arr.length; i++){
            int val = arr[i];
            if(target-val >= 0 && !check[i]){
                check[i]=true;
                permutation_single_coin(arr, target-val, ans+" "+val, check);
                check[i]=false;
            }
        }
    }

    public static void combination_single_coin(int[]arr, int target, String ans, int idx){
        if(target == 0){
            System.out.println(ans);
            return;     
        }

        for(int i=idx; i<arr.length; i++){
            int val = arr[i];

            if(target-val >=0)
            combination_single_coin(arr, target-val, ans+" "+val, i+1);
            
        }
    }

    public static int three_queen_1D(int no, String ans, int start, int end){
        if(no==0){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        for(int i=start; i<end; i++){
            count+= three_queen_1D(no-1, ans+" "+i, i+1, end);
        }

        return count;
    }

    public static int three_queen_1D_subseq(int no, String ans, int start, int end){
        if(no==0){
            System.out.println(ans);
            return 1;
        }
        if(start == end)
        return 0;

        int count = 0;
        
        count+=three_queen_1D_subseq(no-1, ans+" "+start, start+1, end);
        count+=three_queen_1D_subseq(no, ans, start+1, end);

        return count;
    }

    public static int three_queen_2D(int no, String ans, int start, int end, int row, int col, int qplaced){
        if(qplaced == no){
            System.out.println(ans);
            return 1;
        }

        if(start == end){
            return 0;
        }


        int count = 0;
        for(int i=start; i<end; i++){
            count+=three_queen_2D(no, ans, i+1, end, row, col, qplaced);
            count+=three_queen_2D(no, ans+" queen:"+qplaced+" pointer:"+(i/col)+" "+(i%col), i+1, end, row, col, qplaced+1);
        }

        return count;
    }

    public static void main(String[]args){
//        printIncreasing(1,10);
//        printDecreasing(1,10);
//        printIncreasingDecreasing(1, 11);
//        System.out.println(factorial(1,5));
//        System.out.println(factorial2(4));
//        System.out.println(power(10,5));
//        System.out.println(power2(2,5));
        // int[]arr = {4, 5, 10, 15, 6, 8, 3};
//        System.out.println(find(15, arr, 0));
//        System.out.println(maximum(arr, 0));
        // System.out.println(minimum(arr, 0));
        // ArrayList<String>ans = new ArrayList<>();
        // for(String a: ans)
        // System.out.println(a);
        // subsequence_void("abc", 0, ans, "");
        // System.out.println(permutation_withduplication("abc", 0));
        // System.out.println(permutation_without_duplication("aba", ans, ""));
        // HashMap<Integer,String>map = getMapForKeypad();
        // System.out.println(keypad_void("1123", "", 0, map));
        // System.out.println(keypad_return("1123", 0, map));
        // System.out.println(ans);
        // int[]arr = {2, 3, 5, 7};
        // permutation_multi_coin(arr, 10, 0, "");
        // permutation_single_coin(arr, 10, "", new boolean[arr.length]);
        // combination_single_coin(arr, 10, "", 0);
        // three_queen_1D(3, "", 0, 5);
        // three_queen_1D_subseq(3 ,"", 0, 5);

        int row= 3, col=3;
        int count = three_queen_2D(3, "", 0, row*col, row, col, 0);
        System.out.println(count);
    }
}