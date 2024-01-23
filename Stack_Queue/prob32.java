
// 32. Longest Valid Parentheses

class Solution {
    public int longestValidParentheses(String s) {
        Stack<pair>st=new Stack<>();
        int ans=0;
        st.push(new pair(')',-1));

        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(ch=='('){
                st.push(new pair('(',i));
            }
            else{
                if(st.size()!=0 && st.peek().ch=='('){
                    st.pop();
                    ans=Math.max(ans,(i-st.peek().index));
                }
                else{
                    st.push(new pair(')',i));
                }
            }
        }
        return ans;
    }
    public class pair{
        char ch;
        int index;
        pair(char ch, int index){
            this.ch=ch;
            this.index=index;
        }
    }
}