/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int ans=Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        call(root);
        return ans;
    }
    public int call(TreeNode root){
        if(root==null)return 0;

        int left= call(root.left);
        int right= call(root.right);

        ans=Math.max(ans,Math.max((root.val+left+right), Math.max((left+root.val),Math.max((right+root.val),root.val))));
        return Math.max(Math.max(left,right)+root.val, root.val);
    }
}