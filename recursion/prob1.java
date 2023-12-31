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
    public static void permutationVoidType(String str, int index, String ans){
        if(str.length()==index){
            System.out.println(ans);
            return;
        }

        for(int idx=0; idx<=ans.length(); idx++){
            String p1= ans.substring(0,idx);
            String p2= ans.substring(idx);
            String tempA= p1+str.charAt(index)+p2;
            // System.out.println("up");
            permutationVoidType(str, index+1, tempA);
            // System.out.println("down");
        }
    }
    public static ArrayList<String> permutationReturnType(String str, int index){
        if(str.length()-1==index){
            ArrayList<String> base=new ArrayList <>();
            base.add(str.charAt(index)+"");
            return base;
        }
        ArrayList<String>aans= new ArrayList<>();

        ArrayList<String>tempAns= permutationReturnType(str, index+1);
        char ch= str.charAt(index);
        for(String s: tempAns){
            for(int idx=0; idx<=s.length(); idx++){
            String p1= s.substring(0,idx);
            String p2= s.substring(idx);
            String tempA= p1+ch+p2;
            //System.out.println("up");
            aans.add(tempA);
            //System.out.println("down");
            }
        }
        return aans;
    }
    public static int permutationWithoutDuplicates(String str, String ans){
        if(str.length()==0){
            System.out.println(ans);
            return 1;
        }

        int[]vis= new int[26];
        int count=0;

        for(int i=0; i<str.length(); i++){
            char ch=str.charAt(i);
            String p1= str.substring(0,i);
            String p2= str.substring(i+1);
            String tempStr= p1+p2;
            if(vis[ch-'a']==0){
                vis[ch-'a']=1;
                count+=permutationWithoutDuplicates(tempStr, ans+ch);
            }
        }
        return count;
    }
    public static int keypadBasic(String number, int index, ArrayList<Character>[]arr, String ans){
        if(number.length()==index){
            System.out.println(ans);
            return 1;
        }

        char ch=number.charAt(index);
        int num= ch-'0';
        int count=0;
        for(char c: arr[num]){
            count+=keypadBasic(number, index+1, arr, ans+c);
        }
        return count;
    }
    public static ArrayList<String> keypadBasicReturnType(String number, int index, ArrayList<Character>[]arr){
        if(index==number.length()){
            ArrayList<String> base= new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String>tempAns= keypadBasicReturnType(number, index+1, arr);
        ArrayList<String>ans = new ArrayList<>();
        char ch= number.charAt(index);
        int num= ch-'0';
        for(char c: arr[num]){
            for(String s: tempAns){
                ans.add(c+s);
            }
        }
        return ans;
    }
    public static int keypadAdvancedVoidType(String number, int index, ArrayList<Character>[]arr, String ans){
        if(index==number.length()){
            System.out.println(ans);
            return 1;
        }

        char ch1=number.charAt(index);
        int count=0;
        if(ch1=='1' && index+1<number.length()){
            char ch2=number.charAt(index+1);
            if(ch2=='0'||ch2=='1'){
                // String temp= ch1+ch2+"";
                for(char c:arr[10+(ch2-'0')]){
                count+=keypadAdvancedVoidType(number, index+2, arr, ans+c);
                }
            }
        }
        for(char c:arr[ch1-'0']){
            count+=keypadAdvancedVoidType(number, index+1, arr, ans+c);
        }
        return count;
    }

    public static ArrayList<String> keypadAdvancedReturnType(String number, int index, ArrayList<Character>[]arr){
        if(index==number.length()){
            ArrayList<String>base= new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> temp1=new ArrayList<>();
        ArrayList<String> temp2=new ArrayList<>();
        ArrayList<String> ans=new ArrayList<>();
        temp1= keypadAdvancedReturnType(number, index+1, arr);
        char c1= number.charAt(index);
        for(char c: arr[c1-'0']){
            for(String s: temp1){
                ans.add(c+s);
            }
        }
        if(c1=='1' && index+1<number.length()){
            char c2=number.charAt(index+1);
            if(c2=='0'||c2=='1'){
                temp2= keypadAdvancedReturnType(number, index+2, arr);
                for(char c: arr[10+(c2-'0')]){
                    for(String s: temp2){
                    ans.add(c+s);
                    }
                }
            }
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
        // int []arr={2,3,5,8,11,16,12};
        //displayArray(arr, 0);
        //boolean ans= findNo(arr, 0, 8);
        // int ans= fingLargest(arr, 0);
        // System.out.println(ans);
        String str="aba";
        //subsequence(str,0,"");
        // ArrayList<String> ar= subsequenceReturnType(str, 0);
        // System.out.println(ar);
        // int ans=subsequenceCount(str,0,"");
        // System.out.println("total strings are "+ans);
        // permutationVoidType(str, 0, "");
        // ArrayList<String> ans= permutationReturnType(str, 0);
        // System.out.println(ans);
        //recursion-2 -> 47:50
        // int c= permutationWithoutDuplicates(str, "");
        // System.out.println(c);
        ArrayList<Character>[]arr= new ArrayList[12];
        for(int i=0;i<12;i++){
            arr[i]=new ArrayList<Character>();
        }
        arr[0].add('y');
        arr[0].add('z');
        arr[1].add(':');
        arr[1].add(';');
        arr[1].add(',');
        arr[2].add('a');
        arr[2].add('b');
        arr[2].add('c');
        arr[3].add('d');
        arr[3].add('e');
        arr[3].add('f');
        arr[4].add('g');
        arr[4].add('h');
        arr[4].add('i');
        arr[5].add('j');
        arr[5].add('k');
        arr[5].add('l');
        arr[6].add('m');
        arr[6].add('n');
        arr[6].add('o');
        arr[7].add('p');
        arr[7].add('q');
        arr[7].add('r');
        arr[7].add('s');
        arr[8].add('t');
        arr[8].add('u');
        arr[8].add('v');
        arr[9].add('w');
        arr[9].add('x');
        arr[10].add('*');
        arr[10].add('#');
        arr[10].add('@');
        arr[11].add('+');
        arr[11].add('-');
        arr[11].add('x');

        int num=1146;
        // int res=keypadBasic(num+"", 0, arr, "");
        // ArrayList<String> res= keypadBasicReturnType(num+"", 0, arr);
        int res= keypadAdvancedVoidType(num+"", 0, arr, "");
        // ArrayList<String> res= keypadAdvancedReturnType(num+"", 0, arr);
        System.out.println("keypad combinations: "+ res);
    }
}