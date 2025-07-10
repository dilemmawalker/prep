public class prob4{

    public static int cans = 0;

    public static int laxicographical(int no, int ans, int ansRequired){
        if(ans > no)
        return 0;

        if(cans > ansRequired)
        return 0;

        System.out.println(ans);
        cans ++;
        int count = 0;
        
        for(int i=0; i<=9; i++){
            int val = ans * 10 + i;
            count+=laxicographical(no, val, ansRequired);
        }
        
        if(ans<10){
        count += laxicographical(no, ans+1, ansRequired);
        }
        return count;
    }

    public static void main(String[]args){
        // for(int i=1; i<=9;i++){
            laxicographical(10000, 1, 5000);
        // }
    }
}