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
        if(node == null)
        return;

        System.out.println(node.data);
        printTreePreOrder(node.left);
        printTreePreOrder(node.right);
    }
    public static void main(String[]args){
        int[]arr = new int[] {10, 20, 40, -1, -1, 50, 50, -1, -1, 20, -1, -1, 30, 60, 100, -1, -1
        , -1, 70, 110, -1, -1, 120, -1, -1};

        Node parentNode = construct(arr);
        printTreePreOrder(parentNode);
    }
}