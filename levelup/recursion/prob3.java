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

    public static int cryptographic(String str1, String str2, String str3, int[]ans){
        HashSet<Character>set = new HashSet<>();
        String fans = "";

        
    }

    public static int cryptographicRecursion(String str, int[]ans){

    }

    public static void main(String[]args){
        HashSet<String>set = new HashSet<>();
        set.add("i");
        set.add("like");
        set.add("sam");
        set.add("sung");
        set.add("samsung");
        System.out.println(wordBreak("ilikesamsung", "", "", set, 0));
    }
}