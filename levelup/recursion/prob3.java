import java.util.HashSet;

public class prob3{

    public static int wordBreak(String str, String ans, String fans, HashSet<String>set, int idx){
        
        if(idx==str.length()){
            if(set.contains(ans))
            fans+=" "+ans;

            if(fans != ""){
                System.out.println(fans);
                return 1;
            }   
            return 0;
        }


        int count = 0;
        // System.out.println(ans +" "+ fans +" "+idx);
        if(set.contains(ans)){
            count += wordBreak(str, ""+str.charAt(idx), fans + " " + ans, set, idx+1);
        }
        count += wordBreak(str, ans+str.charAt(idx)+"", fans, set, idx+1);

        return count;
    }

    public static boolean checkCryptographic(String str1, String str2, String str3, int[]ans){
        int val1 = stringToNumber(str1, ans);
        int val2 = stringToNumber(str2, ans);
        int val3 = stringToNumber(str3, ans);

        if((val1 + val2) == val3)
        return true;

        return false;
    }

    public static int stringToNumber(String str, int[]ans){
        int n = str.length();
        int fans = 0;

        for(int i=0; i<n; i++){
            char ch = str.charAt(i);
            int val = ans[ch-'a'];
            fans = fans*10 + val;
        }
        return fans;
    }

    public static int cryptographic(String str1, String str2, String str3, int[]ans, String str, int idx, boolean[]check){
        //some silly mistake, see why we're trying all cases for idx even when check contains false
        if(idx == str.length()){
            // System.out.println("hereeeeee "+ idx);
            if(checkCryptographic(str1, str2, str3, ans)){
                // System.out.println("trueeeeee "+ idx);
                for(int i=0; i<26; i++){
                    System.out.print(ans[i]+" ");
                }
                System.out.println();
                return 1;
            }
            return 0;
        }

        // System.out.println("idx value: "+ idx );

        int count = 0;
        // cryptographic(str1, str2, str3, ans, str, idx+1, check);

        for(int i=0; i<=9; i++){
            char ch = str.charAt(idx);
            int val = ch-'a';

            System.out.println("for loop:"+ (idx) + " "+i);

            if(check[i])
            continue;

            if(ans[val]!=-1){
                //assigned
                // System.out.println("for loop:"+ (val) + " "+i);
                count += cryptographic(str1, str2, str3, ans, str, idx+1, check);
            } else {
                //un-assigned
                // System.out.println("tyuiogvhujik: "+ (val)+" "+idx);
                // System.out.println("check[i]dfdfd: "+ (val) + " "+i);
                if(check[i]==false){
                    // System.out.println("check[i]: "+ (val) + " "+i);
                    check[i] = true;
                    ans[val] = i;
                    count += cryptographic(str1, str2, str3, ans, str, idx+1, check);
                    ans[val] = -1;
                    check[i] = false;
                }
            }
        }

        return count;
    }

    public static void main(String[]args){
        // HashSet<String>set = new HashSet<>();
        // set.add("i");
        // set.add("like");
        // set.add("sam");
        // set.add("sung");
        // set.add("samsung");
        // System.out.println(wordBreak("ilikesamsung", "", "", set, 0));
        int[]ans = new int[26];
        for(int i=0; i<26; i++){
            ans[i]=-1;
        }

        System.out.println(cryptographic("send", "more", "money", ans, "sendmoremoney", 0, new boolean[10]));
    }
}