import java.util.ArrayList;

public class prob1{

    public static class Node{
        int data;
        Node left=null;
        Node right=null;
        Node(int data, Node left, Node right){
            this.data=data;
            this.left=left;
            this.right=right;
        }
        Node(int data){
            this.data=data;
        }
    }


    static int index=0;
    public static Node constructTrees(int[]arr){
        if(index==arr.length || arr[index]==-1){
            index++;
            return null;
        }

        Node node= new Node(arr[index++]);
        // System.out.print(node.data+" ");
        node.left= constructTrees(arr);
        node.right= constructTrees(arr);
        

        return node;
    }

    public static void printTreePreorder(Node node){
        if(node==null)
        return;

        System.out.print(node.data+"  ");
        printTreePreorder(node.left);
        printTreePreorder(node.right);
    }

    public static void printLeftRight(Node node){
        if(node==null)
        return;

        System.out.println((node.left==null?" ":node.left.data)+" <- "+ node.data +" -> "+(node.right==null?" ":node.right.data));
        printLeftRight(node.left);
        printLeftRight(node.right);
    }


    //Basic==============================================

    public static int size(Node node){
        if(node==null)
        return 0;

        int count=0;
        count+=size(node.left);
        count+=size(node.right);
        return count+1;
    }

    public static int height(Node node){
        if(node==null)
        return 0;

        int max=0;
        max=Math.max(max,height(node.left));
        max=Math.max(max,height(node.right));
        return max+1;
    }
    public static int maximum(Node node){
        if(node==null)
        return Integer.MIN_VALUE;

        // int ans= Integer.MIN_VALUE;
        // ans=Math.max(ans,maximum(node.left));
        // ans=Math.max(ans,maximum(node.right));
        // ans=Math.max(node.data,ans);
        int ans=Math.max(Math.max(maximum(node.left),maximum(node.right)),node.data);
        return ans;
    }

    public static boolean find(Node node, int val){
        if(node==null)
        return false;

        boolean flag=false;
        if(node.data==val)
        return true;

        flag= flag||find(node.left,val);
        flag= flag||find(node.right,val);

        return flag;
    }



    //Traversal =======================================

    public static void preOrder(Node node){
        if(node==null)
        return;

        System.out.println(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }

    public static void inOrder(Node node){
        if(node==null)
        return;

        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);
    }

    public static void postOrder(Node node){
        if(node==null)
        return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.data);
    }

    public static boolean rootToNodePath(Node node, ArrayList<Integer>ans, int value){
        if(node==null)//base case 1
        return false;

        if(node.data==value){ //base case 2
            ans.add(node.data);
            return true;
        }

        boolean flag= rootToNodePath(node.left, ans, value) || rootToNodePath(node.right, ans, value);
        if(flag)
        ans.add(node.data);

        return flag;
    }

    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
 
      }
  
 
    static int ans=-1;
     public static int diameterOfBinaryTree(TreeNode root) {
         dia(root);
         return ans;
    }
    public static int dia(TreeNode root){
        if(root==null)
        return 0;

        int left=dia(root.left);
        int right= dia(root.right);

        ans=Math.max(ans,(left+right)); //calculate diameter
        return Math.max(left,right)+1; //return height
    }

    public static void main(String[]args){
        int[]arr=new int[]{10, 20, 40, -1, -1, 50, 80, -1, -1, 90, -1, -1, 30, 60, 100, -1, -1, -1, 70, 110, -1, -1, 120, -1, -1};
        Node root= constructTrees(arr);
        // printTreePreorder(root);
        // printLeftRight(root);
        int size= size(root);
        int height= height(root);
        int max= maximum(root);
        boolean ans= find(root, 101);
        // System.out.print("Size is :" + ans);
        // postOrder(root);
        ArrayList<Integer>ar= new ArrayList<>();
        rootToNodePath(root, ar, 100);
        System.out.println(ar);
    }
}