import java.io.*;

public class prob2{
    public static void main(String[]args){
        System.out.println("                       abcde"); 
        for(int i=1;i<24;i*=2){
            for(int j=12;j>=i;j/=2){
                System.out.print("     ");
            }
            for(int j=1;j<=i;j*=2){
                System.out.print("abcde ");
            }
            for(int j=1;j<=i;j*=2){
                System.out.print("abcde ");
            }
            System.out.println();
        }
    }
}