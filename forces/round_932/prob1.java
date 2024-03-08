import java.util.*;
import java.io.*;
public class prob1{

    public static Scanner scn=new Scanner(new InputStreamReader(System.in));
    public static void main(String[]args){
        int t=scn.nextInt();
        while(t-->0){
            int n= scn.nextInt();
            String s= scn.next();

            n=2;
            String temp= reverse(s);
            // System.out.println(temp);

            String s1= s;
            String s2= s+temp;
            // System.out.println(s1);
            // System.out.println(s2);
            String s3= temp+s;
            // System.out.println(s3);

            String ans=compare(s1,s2);
            ans=compare(ans,s3);

            System.out.println(ans);
            
        }
    }
    public static String reverse(String s){
        int n=s.length();
        String temp="";

        for(int i=0;i<n;i++){
            temp=s.charAt(i)+temp;
        }
        return temp;
    }

    public static String compare(String s1, String s2){
        int n=Math.min(s1.length(),s2.length());

        for(int i=0;i<n;i++){
            if(s1.charAt(i)==s2.charAt(i))
            continue;

            if(s1.charAt(i)<s2.charAt(i))
            return s1;
            else
            return s2;
        }

        return s1;
    }
}