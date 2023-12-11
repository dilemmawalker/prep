import java.util.Scanner;
import java.util.ArrayList;
public class prob1{
    public static void printIncreasing(int a, int b){
        if(a>b)
        return;

        System.out.println(a);
        printIncreasing(a+1,b);
       // System.out.println("I am printing");
     //   return;
    }
    public static void printDecreasing(int a, int b){
        if(a>b)
        return;

        printDecreasing(a+1,b);
        System.out.println(a);
    }
    public static void printEvenOdd(int a, int b){
        if(a>b)
        return;

        if(a%2==0)
        System.out.println(a);
        printEvenOdd(a+1,b);
        if(a%2!=0)
        System.out.println(a);
    }
    public static int factorial(int num){
        if(num==0)
        return 1;

        int oneNumberLessFactorial= factorial(num-1);
        int currentNumberFactorial= oneNumberLessFactorial*num;
        return currentNumberFactorial;
    }
    public static int power(int num1,int num2){
        if(num2<=0)
        return 1;

        return power(num1,num2-1)*num1;
    }
    public static int power2(int num1,int num2){
        if(num2==0)
        return 1;

        int returnPower= power2(num1,num2/2);
        return num2%2==0?returnPower*returnPower:returnPower*returnPower*num1;
    }
    public static void displayArray(int[]arr, int vidx){
        if(vidx==arr.length)
        return;

        System.out.println(arr[vidx]);
        displayArray(arr,vidx+1);
    }
    public static boolean findNo(int []arr, int index, int number){
        if(arr.length==index)
        return false;

        if(arr[index]==number)
        return true;

        return findNo(arr, index+1, number);
    }
   // static int max=-1;
    public static int fingLargest(int[]arr, int index){
        if(arr.length==index)
        return arr[index-1];

        return Math.max(fingLargest(arr,index+1), arr[index]);
    }








    ///////////////////////////////////////////next day!!!!

    public static void subsequence(String str, int index, String ans){
        if(str.length()==index){
            System.out.println(ans);
            return;
        }

        char ch=str.charAt(index);

        subsequence(str,index+1,ans+ch);
        subsequence(str,index+1,ans);
    }
    public static int subsequenceCount(String str, int index, String ans){
        if(str.length()==index){
            System.out.println(ans);
            return 1;
        }

        char ch=str.charAt(index);
        int count=0;

        count+=subsequenceCount(str,index+1,ans+ch);
        count+=subsequenceCount(str,index+1,ans);

        return count;
    }
    public static ArrayList<String> subsequenceReturnType(String str, int index){
        if(str.length()==index){
            ArrayList<String> arrn=new ArrayList<>();
            arrn.add("");
            return arrn;
        }

        char ch= str.charAt(index);
        ArrayList<String> arr=new ArrayList<>();
        ArrayList<String> ans=new ArrayList<>();

        arr= subsequenceReturnType(str, index+1);

        // for(int i=0;i<arr.size();i++){
        //     ans.add(arr.get(i));
        //     ans.add(ch+arr.get(i));
        // }
        ans.addAll(arr);
        for(String s: arr){
            ans.add(ch+s);
        }

       return ans;
    }

    public static void main(String args[]){
        //printIncreasing(1,10);
        //printDecreasing(1,10);
        //printEvenOdd(1,10);
        //int ans= factorial(5);
        //int ans= power(2,4);
        //int ans= power2(2,5);
        int []arr={2,3,5,8,11,16,12};
        //displayArray(arr, 0);
        //boolean ans= findNo(arr, 0, 8);
        // int ans= fingLargest(arr, 0);
        // System.out.println(ans);
        String str="abc";
        //subsequence(str,0,"");
        // ArrayList<String> ar= subsequenceReturnType(str, 0);
        // System.out.println(ar);
        int ans=subsequenceCount(str,0,"");
        System.out.println("total strings are "+ans);
    }
}