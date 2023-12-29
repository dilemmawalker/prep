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
        if(index==arr.length || arr[index]==null){
            return null;
        }

        Node node= new Node(arr[index++]);
        node.left= constructTrees(arr);
        node.right= constructTrees(arr);

        return node;
    }

    public static printTree(Node node){
        if(node==null)
        return;

        
    }

    public static void main(String[]args){

    }
}