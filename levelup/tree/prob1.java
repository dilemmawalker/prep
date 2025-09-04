import java.util.ArrayList;

public class prob1{

    public static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
        }

        Node(int data, Node left, Node right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static int idx = 0;
    public static Node construct(int[]arr){
        if(idx == arr.length || arr[idx]==-1){
            idx++;
            return null;
        }

        Node currentNode = new Node(arr[idx++]);
        currentNode.left = construct(arr);
        currentNode.right = construct(arr);

        return currentNode;
    }

    public static void printTreePreOrder(Node node){
        if(node == null){
            System.out.println(".");
            return;
        }

        System.out.println(node.data);
        printTreePreOrder(node.left);
        printTreePreOrder(node.right);
    }

    public static void printTreeInOrder(Node node){
        if(node == null)
        return;

        printTreeInOrder(node.left);
        System.out.println(node.data);
        printTreeInOrder(node.right);
    }

    public static void printTreePostOrder(Node node){
        if(node == null)
        return;

        printTreePostOrder(node.left);
        printTreePostOrder(node.right);
        System.out.println(node.data);
    }

    public static int size(Node node){
        if(node == null){
            return 0;
        }

        int size1 = size(node.left);
        int size2 = size(node.right);

        return size1+size2+1;
    }

    public static int height(Node node){
        if(node == null){
            return 0;
        }

        int size1 = height(node.left);
        int size2 = height(node.right);
        int ans = Math.max(size1, size2);
        return ans + 1;
    }

    public static int maximum(Node node){

        if(node == null){
            return 0;
        }

        int size1 = maximum(node.left);
        int size2 = maximum(node.right);
        int ans = Math.max(Math.max(node.data, size1), size2);
        return ans;
    }

    public static int minimum(Node node){

        if(node == null){
            return 100000;
        }

        int size1 = minimum(node.left);
        int size2 = minimum(node.right);
        int ans = Math.min(Math.min(node.data, size1), size2);
        return ans;
    }

    public static boolean find(Node node, int val){
        if(node == null){
            return false;
        }

        if(node.data == val){
            return true;
        }
        return (find(node.left, val) || find(node.right, val));
    }

    public static boolean rootToNodePath(Node node, int val, ArrayList<Integer>arr){
        if(node == null){
            return false;
        }
        if(node.data == val){
            arr.add(node.data);
            return true;
        }

        boolean l = rootToNodePath(node.left, val, arr);
        boolean r = rootToNodePath(node.right, val, arr);
        if(l || r){
            arr.add(node.data);
            return true;
        }

        return false;
    }


    public static boolean LCA_2(Node node, int val1, int val2, ArrayList<Integer>arr){
        if(node == null){
            return false;
        }

        boolean self = false;

        if((node.data == val1) || (node.data == val2)){
            self = true;
        }

        boolean l = LCA_2(node.left, val1, val2, arr);
        boolean r = LCA_2(node.right, val1, val2, arr);
        // System.out.println(node.data +" :"+ l+" , "+r +" , "+self);

        if((l && r) || (l && self) || (r && self)){
            arr.add(node.data);
            // ansNode = node;
        }

        return (l || r || self);
    }

    //===============================all nodes k away=========================

    //===============================diameter of a tree=========================

    public static void main(String[]args){
        int[]arr = new int[] {10, 20, 40, -1, -1, 50, 50, -1, -1, 20, -1, -1, 30, 60, 100, -1, -1
        , -1, 70, 110, -1, -1, 120, -1, -1};

        Node parentNode = construct(arr);
        // printTreePreOrder(parentNode);
        // int ans = minimum(parentNode);
        ArrayList<Integer>al = new ArrayList<>();
        boolean ans = LCA_2(parentNode, 100, 60, al);
        System.out.println(ans);
        System.out.println(al);
    }
}