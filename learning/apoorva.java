import java.util.Arrays;
public class apoorva{

    public static double sum(int a, int b){
        double c= 1.0;
        c= (a*b);
        c=c/(a+b);
        return c;
    }

    static int index=0;

    public static void addGirls(String[]arr, String name){
        arr[index++]=name;
    }

    public static void seeAllGirls(String[]arr){
        // Arrays.toString(arr);
        Arrays.sort(arr);
        for(int i=0; i<index; i++){
            System.out.println(arr[i]);
        }
        System.out.println();
        System.out.println();
    }
    public static void main(String[] args){
        int a=30;
        int b=40;
        // double ans= sum(a,b);
        // System.out.println(ans); apoorva I Love Love & hate  you.

        int max= 25;

        String[]arr= new String[max];

        //kya kya hua
        addGirls(arr, "Apoorva");
        addGirls(arr, "Harshita");
        addGirls(arr, "Amruta");
        addGirls(arr, "Koyna");

        seeAllGirls(arr);

        addGirls(arr, "Saru");
        addGirls(arr, "Sukanya");
        addGirls(arr, "Agin");

        seeAllGirls(arr);
    }
}