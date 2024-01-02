import java.util.ArrayList;
import java.util.LinkedList;

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

    //View =============================================

    public static void leftView(Node root){
        if(root==null)
        return;

        LinkedList<Node>queue= new LinkedList<>();
        queue.add(root);
        while(queue.size()!=0){
            int size=queue.size();
            Node temp= queue.getFirst();
            System.out.println(temp.data);
            while(size-->0){
                Node t= queue.removeFirst();

                if(t.left!=null)
                queue.addLast(t.left);
                if(t.right!=null)
                queue.addLast(t.right);
            }
        }
    }

    public static void rightView(Node root){
        if(root==null)
        return;

        LinkedList<Node>queue= new LinkedList<>();
        queue.add(root);
        while(queue.size()!=0){
            int size=queue.size();
            Node temp= queue.getLast();
            System.out.println(temp.data);
            while(size-->0){
                Node t= queue.removeFirst();

                if(t.left!=null)
                queue.addLast(t.left);
                if(t.right!=null)
                queue.addLast(t.right);
            }
        }
    }

    public static void extreme(Node root, pair p){
        LinkedList<TNode>queue= new LinkedList<>();
        queue.addLast(new TNode(root,p));

        while(queue.size()!=0){
            int size=queue.size();
            while(size-->0){

                TNode temp= queue.removeFirst();
                p.left= Math.min(p.left,temp.p.left);
                p.right= Math.max(p.right,temp.p.right);

                if(temp.node.left!=null)
                queue.addLast(new TNode(temp.node.left,new pair(temp.p.left-1,temp.p.right)));
                if(temp.node.right!=null)
                queue.addLast(new TNode(temp.node.right,new pair(temp.p.left,temp.p.right+1)));
                
            }
        }
    }
    
    public static class TNode{
        Node node=null;
        pair p;
        TNode(Node node, pair p){
            this.node=node;
            this.p=p;
        }
    }

    public static class pair{
        int left=0;
        int right=0;
        pair(int left, int right){
            this.left=left;
            this.right=right;
        }
    }

    //using dfs; -> wrong solution for such cases, only use bfs
    public static void verticalOrder(Node node, ArrayList<Integer>[]g, int index){
       if(node==null)
       return;

        g[index].add(node.data);

        verticalOrder(node.left, g, index-1);
        verticalOrder(node.right, g, index+1);
    }

    //using bfs;
    public static void verticalOrderBFS(Node node, ArrayList<Integer>[]g, int index){  //only using left in pair class.
        LinkedList<TNode>queue= new LinkedList<>();

        queue.addLast(new TNode(node, new pair(index,0)));

        while(queue.size()!=0){
            int size=queue.size();
            while(size-->0){
                TNode temp= queue.removeFirst();

                g[temp.p.left].add(temp.node.data);

                if(temp.node.left!=null)
                queue.addLast(new TNode(temp.node.left, new pair(temp.p.left-1,0)));
                if(temp.node.right!=null)
                queue.addLast(new TNode(temp.node.right, new pair(temp.p.left+1,0)));
            }
        }
    }

    //diagonal view
    public static void diagonalView(Node node, int index, ArrayList<Integer>[]g){
        if(node==null)
        return;

        LinkedList<TNode>queue= new LinkedList<>();
        queue.addLast(new TNode(node, new pair(index,0)));

        while(queue.size()!=0){
            int size=queue.size();
            while(size-->0){
                TNode n= queue.removeFirst();

                g[n.p.left].add(n.node.data);

                if(n.node.left!=null)
                queue.addLast(new TNode(n.node.left, new pair(n.p.left-1,0)));
                if(n.node.right!=null)
                queue.addLast(new TNode(n.node.right, new pair(n.p.left,0)));
            }
        }
    }

    //linearize
    // public static Node linearize(Node root){
    //     if(root==null)
    //     return null;

    //     if(root.left!=null)
    //     Node left= linearize(root.left);
    //     if(root.right!=null){
    //     Node right= linearize(root.right);
    //     left.left=right;
    //     root.right=null;
    //     }

    //     return root;
    // }



    //DLL(Doubly Linked List)
    static Node prev=null;static Node head= null;
    public static void dll(Node node){
        if(node==null)
        return;

        dll(node.left);

        if(head==null){
            head=node;
        }
        else{
            prev.right= node;
            node.left= prev;
        }
        prev=node;

        dll(node.right);
    }

    public static void main(String[]args){
        int[]arr=new int[]{10, 20, 40, -1, -1, 50, 80, -1, -1, 90, -1, -1, 30, 60, 100, -1, -1, -1, 70, 110, -1, -1, 120, -1, -1};
        Node root= constructTrees(arr);
        // printTreePreorder(root);
        // printLeftRight(root);
        // int size= size(root);
        // int height= height(root);
        // int max= maximum(root);
        // boolean ans= find(root, 101);
        // System.out.print("Size is :" + ans);
        // postOrder(root);
        // ArrayList<Integer>ar= new ArrayList<>();
        // rootToNodePath(root, ar, 100);
        // System.out.println(ar);
        // rightView(root);
        pair p = new pair(0,0);
        extreme(root, p);
        // System.out.println(p.left+" "+p.right);
        int range= p.right-p.left;
        // System.out.println("range: "+range);
        ArrayList<Integer>[]g= new ArrayList[p.left*(-1) + 1];
        for(int i=0;i<g.length;i++)
        g[i]=new ArrayList<Integer>();
        // verticalOrder(root, g, (p.left)*(-1));
        // verticalOrderBFS(root, g, (p.left)*(-1));

        // diagonalView(root, p.left * (-1), g);
        // for(int i=0;i<g.length;i++)
        // System.out.println(g[i]);
        dll(root);
        while(head!=null){
        System.out.println(head.data);
        head=head.right;
        }
    }
}