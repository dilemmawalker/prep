/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<TreeNode>ar= new ArrayList<>();
        lca(root, p, q, ar);
        return ar.get(0);
    }
    public boolean lca(TreeNode root, TreeNode p, TreeNode q, ArrayList<TreeNode>ar){
        if(root==null)
        return false;

        boolean flag1=false,flag2=false;
        flag1=lca(root.left, p, q, ar);
        flag2=lca(root.right, p, q, ar);

        if(flag1 && flag2){
        ar.add(root);
        return true;
        }

        if(root.val==p.val || root.val==q.val){
            if(flag1 || flag2){
                ar.add(root);
            }
            return true;
        }

        return flag1||flag2;
    }
}