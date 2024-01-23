//  84. Largest Rectangle in Histogram

class Solution {
    public int largestRectangleArea(int[] arr) {
        Stack<pair>st=new Stack<>();
        int ans=0;
        st.push(new pair(-1,-1));

        for(int i=0;i<arr.length;i++){
            int val=arr[i];
            if(st.peek().val>=val){ //pop state
                while(st.peek().val>=val){
                    pair temp=st.pop();
                    ans=Math.max(ans,((i-st.peek().index-1)*temp.val));
                    System.out.println(temp.val+" "+ans);
                }
                st.push(new pair(val,i));
            }
            else{                   //push state
                st.push(new pair(val,i));
            }
        }

        while(st.peek().val!=-1){
            pair temp=st.pop();
            ans=Math.max(ans,((arr.length-st.peek().index-1))*temp.val);
        }
        return ans;
    }

    public class pair{
        int val=0;
        int index=0;
        pair(int val, int index){
            this.val=val;
            this.index=index;
        }
    }
}