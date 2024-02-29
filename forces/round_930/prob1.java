import java.util.*;
import java.io.*;

public class prob1{

    public static Scanner scn=new Scanner(new InputStreamReader(System.in));
    public static void main(String[]args){

        int t=scn.nextInt();
        while(t-->0){
            int n=scn.nextInt();
            long ans=1l;

            while(ans<=n){
                ans*=2;
            }
            ans/=2;

            System.out.println(ans);
        }
    }
}