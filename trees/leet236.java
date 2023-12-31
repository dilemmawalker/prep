//LCA - 236. Lowest Common Ancestor of a Binary Tree

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<TreeNode>ar1= new ArrayList<>();
        ArrayList<TreeNode>ar2= new ArrayList<>();

        TreeNode root1= new TreeNode(root.val);
        root1.left= root.left;
        root1.right= root.right;

        lca(root, p, ar1);
        lca(root1, q, ar2);

        int max= Math.min(ar1.size(),ar2.size());
        int p1=ar1.size()-1;
        int p2= ar2.size()-1;
        TreeNode ans=ar1.get(p1);
        while(p1>=0 && p2>=0){
            if(ar1.get(p1).val==ar2.get(p2).val){
                ans=ar1.get(p1);
            }
            p1--;p2--;
        }
        return ans;
    }

    public void printar(ArrayList<TreeNode>ar){
        for(TreeNode n: ar)
        System.out.print(n.val+" ");

        System.out.println();
    }

    public boolean lca(TreeNode root, TreeNode val, ArrayList<TreeNode>ar){
        if(root==null)
        return false;
        if(root.val==val.val){
            ar.add(root);
            return true;
        }

        boolean flag= lca(root.left, val, ar) || lca(root.right, val, ar);
        if(flag)
        ar.add(root);

        return flag;
    }