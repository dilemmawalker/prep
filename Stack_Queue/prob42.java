// 42. Trapping Rain Water

class Solution {
    public int trap(int[] height) {
        Stack<pair>st=new Stack<>();
        if(height.length>=1)
        st.push(new pair(height[0],-1));
        int ans=0;

        for(int i=0;i<height.length;i++){
            int val=height[i];
            while(st.peek().val<val && st.peek().index!=-1){
                pair temp= st.pop();
                int a= (Math.min(val,st.peek().val)-temp.val)*(i-st.peek().index-1);
                ans+=a>0?a:0;
            }

            st.push(new pair(val,i));
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