import java.util.ArrayList;
import java.util.Scanner;

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
        ArrayList<String>ans = new ArrayList<>();
        // for(String a: ans)
        // System.out.println(a);
        // subsequence_void("abc", 0, ans, "");
        // System.out.println(permutation_withduplication("abc", 0));
        System.out.println(permutation_without_duplication("aba", ans, ""));
        System.out.println(ans);
    }
}