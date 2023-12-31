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
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<Integer>ar= new ArrayList<>();
        List<List<Integer>>ans = new ArrayList<>();
        path(root, targetSum, 0, ar, ans);
        return ans;
    }
    public void path(TreeNode root, int targetSum, int sum, List<Integer>ar, List<List<Integer>>ans){
        if(root==null)
        return ;

        if(sum+root.val==targetSum && root.left==null && root.right==null){
            List<Integer>temp= new ArrayList<>(ar);
            // for(int ele: ar)
            // temp.add(ele);
            // temp.addAll(ar);
            temp.add(root.val);
            // ar.add(root.val);
            ans.add(temp);
        }
        ar.add(root.val);
        path(root.left, targetSum, sum+root.val, ar, ans);
        path(root.right, targetSum, sum+root.val, ar, ans);
        ar.remove(ar.size()-1);
    }
}