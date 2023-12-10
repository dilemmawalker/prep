import java.util.Scanner;
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

    public static void main(String args[]){
        //printIncreasing(1,10);
        printDecreasing(1,10);
    }
}