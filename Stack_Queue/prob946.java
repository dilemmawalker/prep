// 946. Validate Stack Sequences

class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer>st=new Stack<>();
        
        int p1=0,p2=0;
        while(p1<pushed.length && p2<popped.length){
            if(st.size()==0 || (popped[p2]!=st.peek())){
                st.push(pushed[p1]);
                p1++;
            }
            else if(st.size()!=0 && (popped[p2]==st.peek())){
                st.pop();
                p2++;
            }
        }

        while(p2<popped.length && st.size()!=0 && (popped[p2]==st.peek())){
            st.pop();
            p2++;
        }

        if(p2==popped.length)
        return true;

        return false;
    }
}