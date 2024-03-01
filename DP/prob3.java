public class prob3{
    public static int possibleWays(String a, String b, String ans, int index){
        if(index==a.length()){
            if(ans.equals(b)){
                return 1;
            }
            else{
                return 0;
            }
        }

        int count=0;
        count+= possibleWays(a, b, ans+a.charAt(index), index++);
        count+= possibleWays(a, b, ans, index++);

        return count;
    }

    public static void main(String[]args){
        String a= "rabbbit";
        String b= "rabbit";

        possibleWays(a,b,"",0);
    }
}