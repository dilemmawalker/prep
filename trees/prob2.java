import java.util.ArrayList;

public class prob2{
    public static class Node{
        int val=0;
        Node left=null;
        Node right=null;
        Node(int val, Node left, Node right){
            this.val=val;
            this.left=left;
            this.right=right;
        }
        Node(int val){
            this.val=val;
            left=null;
            right=null;
        }
    }

    public static Node construct(int[]arr, int si, int ei){
        if(si>ei)
        return null;

        int mid=(si+ei)/2;
        Node node= new Node(arr[mid]);

        // System.out.println(node.val);

        node.left=construct(arr, si, mid-1);
        node.right=construct(arr, mid+1, ei);

        return node;
    }

    public static void print(Node root){
        if(root==null)
        return;

        System.out.println((root.left!=null?root.left.val:".") + " <- "+root.val +" -> "+(root.right!=null?root.right.val:"."));

        print(root.left);
        print(root.right);
    }

    public static boolean find(Node root, int val){
        if(root==null)
        return false;

        if(root.val==val)
        return true;

        boolean flag=false;
        if(val<root.val)
        flag=flag||find(root.left,val);
        else
        flag=flag||find(root.right,val);

        return flag;
    }

    public static boolean rootToNodePath(Node root, int val, ArrayList<Integer>ar){
        if(root==null)
        return false;

        if(root.val==val){
            ar.add(root.val);
            return true;
        }

        boolean flag=false;
        if(root.val>val)
        flag=flag||rootToNodePath(root.left, val, ar);
        else
        flag=flag||rootToNodePath(root.right, val, ar);

        if(flag)
        ar.add(root.val);

        return flag;
    }

    public static boolean lca(Node root, int val1, int val2){
        if(root==null)
        return false;

        // if(root.val==val1 || root.val==val2){
        //     System.out.print(root.val);
        //     return true;
        // }
        boolean flag=false;
        if(root.val>val1 && root.val>val2)
        flag=flag||lca(root.left, val1, val2);
        else if(root.val<val1 && root.val<val2)
        flag=flag||lca(root.right, val1, val2);
        else{
            System.out.println("lca: "+root.val);
        return true;
        }

        return flag;
    }

    public static void main(String[]args){
        int[]arr=new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        Node root= construct(arr, 0, arr.length-1);
        // System.out.println("node val: "+root.val);
        // print(root);
        // boolean flag= find(root, 91);
        ArrayList<Integer>ar=new ArrayList<>();
        // boolean flag= rootToNodePath(root, 90, ar);
        lca(root, 20, 40);
        // System.out.println(ar);
    }
}