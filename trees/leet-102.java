//. 102. Binary Tree Level Order Traversal



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
    public List<List<Integer>> levelOrder(TreeNode root) {
        LinkedList<TreeNode>queue= new LinkedList<>();
        queue.addLast(root);

        List<List<Integer>>ans= new ArrayList<>();
        while(queue.size()!=0){
            int size=queue.size();
            List<Integer>ar = new ArrayList<>();
            while(size-->0){
                TreeNode node= queue.removeFirst();
                if(node==null)
                return ans;

                ar.add(node.val);

                if(node.left!=null)
                queue.addLast(node.left);
                if(node.right!=null)
                queue.addLast(node.right);
            }
            List<Integer>temp = new ArrayList<>();
            temp.addAll(ar);
            ans.add(temp);
        }
        return ans;
    }
}