// 987. Vertical Order Traversal of a Binary Tree

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
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>>ans=new ArrayList<>();
        pair p= new pair(0,0);
        extreme(root, p, 0);

        int range= p.right - p.left;
        List<Integer>[]pq= new ArrayList[range+1]; //array made of Queue with multiple sets of PQ. or just simply make a PQ & add elements of current stage in it at last
        // PriorityQueue<Integer>[]pq= new PriorityQueue[range+1];
        for(int i=0;i<pq.length;i++){
            // pq[i]=new PriorityQueue<>((int a, int b)->{
            //     return a-b;
            // });
            pq[i]= new ArrayList<>();
        }

        travel(root, pq, p.left*(-1));

        for(int i=0;i<range+1;i++){
            List<Integer>temp= new ArrayList<>();

            for(int ele: pq[i])
            temp.add(ele);

            ans.add(temp);
        }
        return ans;
    }


        //bfs
    public void travel(TreeNode root, List<Integer>[]pq, int index){
        PriorityQueue<TNode>queue= new PriorityQueue<>((TNode a, TNode b)->{
            return a.node.val-b.node.val;
        });
        queue.add(new TNode(root, index));

        while(queue.size()!=0){
            int size=queue.size();
            
            ArrayList<TNode>temp= new ArrayList<>();
            while(size-->0){
                TNode val= queue.remove();
                pq[val.index].add(val.node.val);

                if(val.node.left!=null)
                temp.add(new TNode(val.node.left, val.index-1));
                if(val.node.right!=null)
                temp.add(new TNode(val.node.right, val.index+1));
            }
            queue.addAll(temp);//not sure if it works in queue.
        }
    }

    public class TNode{
        TreeNode node=null;
        int index=0;
        TNode(TreeNode node, int index){
            this.node=node;
            this.index=index;
        }
    }


    public void extreme(TreeNode root, pair p, int index){
        if(root==null)
        return;

        p.left=Math.min(index, p.left);
        p.right=Math.max(index, p.right);

        extreme(root.left, p, index-1);
        extreme(root.right, p, index+1);
    }

    public class pair{
        int left=0;
        int right=0;
        pair(int l, int r){
            this.left=l;
            this.right=r;
        }
    }
}