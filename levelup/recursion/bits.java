public class bits{

    public static int finalOn(int no, int k){
        int mask = (1<<k);
        int ans = no | mask;
        return ans;
    }

    public static int finalOff(int no, int k){
        int mask = (~(1<<k));
        int ans = no & mask;
        return ans;
    }

    public static int countOnBits(int no){
        int count = 0;

        while(no!=0){
            no=(no&(no-1));
            count++;
        }
        return count;
    }

    public static int findOnceNo(int[]arr){
        int ans = 0;

        for(int i=0; i<arr.length; i++){
            int val = arr[i];
            ans^=val;
        }

        return ans;
    }

    public static int findOnceRepeatK(int[]arr, int k){
        int ans = 0;
        int[]bits = new int[32];

        for(int i=0; i<arr.length; i++){
            int val = arr[i];

            for(int j=0; j<32; j++){
                int mask = (1<<j);
                if((mask & val)!=0){
                    bits[j]++;
                }
            }
        }

        for(int i=0; i<32; i++){
            bits[i]=bits[i]%k;
        }

        return convertBinaryToDecimal(bits);
    }

    public static int convertBinaryToDecimal(int[]arr){
        int ans = 0;
        for(int i=0; i<arr.length; i++){
            int val = (int)(Math.pow(2,i));
            ans+=val*arr[i];
        }
        return ans;
    }


    public static void main(String args[]){
        int no = 122; 
        // System.out.println("Initial no: "+ no);
        // System.out.println(finalOn(no, 2));
        // System.out.println(finalOff(no, 4));
        // int count = countOnBits(no);
        // System.out.println("count of open bits: "+ count);
        int[]arr = {2,3,2,5,3,5,2,11,5,3};
        // System.out.println(findOnceNo(arr));
        System.out.println(findOnceRepeatK(arr, 3));
    }
}