public class prob3{                 //bits

    public static int onOnOffOn(int num, int k){
        int mask= (1<<k);
        int ans= num|(mask);
        return ans;
    }
    public static int OffOffOnOff(int num, int k){
        int mask= (1<<k);
        int ans= (num&(~(mask)));
        return ans;
    }
    public static int noOfBits(int num){
        int count=0;
        int mask=(1<<0);
        while(num!=0){
            if((num&mask)!=0)
            count++;
            num=num>>>1;
        }
        return count;
    }
    public static int noOfBitsAdvanced(int num){
        //much faster as loop runs only equal to no. of times a set bit is present in num.
        //for details see page 9 of notes!
        int count=0;
        while(num!=0){
            num=(num&(num-1));
            count++;
        }
        return count;
    }
    public static void main(String[]args){
        int num=7,k=4;
    //    int ans= onOnOffOn(num,k);
    //    int ans= OffOffOnOff(num, k);
    // int ans= noOfBits(num);
    int ans= noOfBitsAdvanced(num);
       System.out.println(ans);
    }
}