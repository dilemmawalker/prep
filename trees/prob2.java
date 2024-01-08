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

    public static void predecessorSuccessor(Node node){
        if(node==null)
        return;

        Node pre=node;
        if(node.left!=null){
            pre=pre.left;

            while(pre.right!=null){
                pre=pre.right;
            }
        }

        Node succ=node;
        if(node.right!=null){
            succ=succ.right;

            while(succ.left!=null){
                succ=succ.left;
            }
        }

        System.out.println(pre.val +" "+ succ.val);

        return;

    }

    //construct BST!!!
    public static int indexPre=0;
    public static Node constructBSTPreorder(int left, int right, int[]arr){
        if(indexPre>=arr.length || right<arr[indexPre] || left>arr[indexPre])
        return null;

        int val= arr[indexPre];
        Node temp= new Node(val);
        indexPre++;

        if(val<right && val>left ){
            
            temp.left= constructBSTPreorder(left, val, arr);
        }
        if(val<right && val>left ){
            // indexPre++;
            temp.right= constructBSTPreorder(val, right, arr);
        }

        return temp;
    }

    public static int heightBSTPreorder(int left, int right, int[]arr){
        if(indexPre>=arr.length || right<arr[indexPre] || left>arr[indexPre])
        return 0;

        int val= arr[indexPre];
        // Node temp= new Node(val);
        indexPre++;
        int height=0;
            
            height= Math.max(height,heightBSTPreorder(left, val, arr));
            height= Math.max(height,heightBSTPreorder(val, right, arr));

        return height+1;
    }

    public static void main(String[]args){
        // int[]arr=new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        // Node root= construct(arr, 0, arr.length-1);
        // System.out.println("node val: "+root.val);
        // print(root);
        // boolean flag= find(root, 91);
        ArrayList<Integer>ar=new ArrayList<>();
        // boolean flag= rootToNodePath(root, 90, ar);
        // lca(root, 20, 40);
        // System.out.println(ar);
        // predecessorSuccessor(root);
        int[]arr=new int[]{50, 17, 9, 14, 12, 23, 19, 76, 54, 72, 67};
        // Node ans= constructBSTPreorder(Integer.MIN_VALUE, Integer.MAX_VALUE, arr);
        int h= heightBSTPreorder(Integer.MIN_VALUE, Integer.MAX_VALUE, arr);
        // print(h);
        System.out.println(h);

    }
}