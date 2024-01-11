import java.util.Stack;
public class prob3{

    public static class tNode{
        int val=0;
        tNode left=null;
        tNode right=null;
        boolean sd=false;
        boolean ld=false;
        boolean rd=false;

        tNode(int val){
            this.val=val;
        }
        tNode(int val ,boolean sd, boolean ld, boolean rd){
            this.val=val;
            this.sd=sd;
            this.ld=ld;
            this.rd=rd;
        }
        tNode(int val, tNode left, tNode right){
            this.val=val;
            this.left=left;
            this.right=right;
        }
    }

    public static int index=0;
    public static tNode constructTreePreorder(int[]arr){
        if(index>arr.length || arr[index]==-1){
            index++;
            return null;
        }

        tNode node=new tNode(arr[index]);
        index++;
        node.left=constructTreePreorder(arr);
        node.right=constructTreePreorder(arr);

        return node;
    }

    public static void print(tNode root){
        if(root==null){
            return;
        }

        System.out.println((root.left!=null?root.left.val:".") + " <- "+ root.val+ " -> "+(root.right!=null?root.right.val:"."));
        print(root.left);
        print(root.right);
    }

    // Iterative PreOrder
    public static void iteratePrintPreorder(tNode root){
        Stack<tNode>st= new Stack<>();

        st.push(root);
        while(st.size()!=0){
            tNode temp= st.pop();

            if(temp.ld==false){
                System.out.println(temp.val);
                temp.sd=true;
                temp.ld=true;
                st.push(temp);
                if(temp.left!=null)
                    st.push(new tNode(temp.left.val, temp.left.left, temp.left.right));
            }
            else if(temp.rd==false){
                temp.rd=true;
                if(temp.right!=null)
                    st.push(new tNode(temp.right.val, temp.right.left, temp.right.right));
            }
        }
    }

    //Iterative InOrder
    public static void iteratePrintInorder(tNode root){
        Stack<tNode>st= new Stack<>();

        st.push(root);
        while(st.size()!=0){
            tNode temp= st.pop();

            if(temp.ld==false){
                temp.sd=true;
                temp.ld=true;
                st.push(temp);
                if(temp.left!=null)
                    st.push(new tNode(temp.left.val, temp.left.left, temp.left.right));
            }
            else if(temp.rd==false){
                System.out.println(temp.val);
                temp.rd=true;
                if(temp.right!=null)
                    st.push(new tNode(temp.right.val, temp.right.left, temp.right.right));
            }
        }
    }

    //Iterative PostOrder
    public static void iteratePrintPostorder(tNode root){
        Stack<tNode>st= new Stack<>();

        st.push(root);
        while(st.size()!=0){
            tNode temp= st.pop();

            if(temp.ld==false){
                temp.sd=true;
                temp.ld=true;
                st.push(temp);
                if(temp.left!=null)
                    st.push(new tNode(temp.left.val, temp.left.left, temp.left.right));
            }
            else if(temp.rd==false){
                temp.rd=true;
                st.push(temp);
                if(temp.right!=null)
                    st.push(new tNode(temp.right.val, temp.right.left, temp.right.right));
            }
            else if(temp.rd==true){
                System.out.println(temp.val);
            }
        }
    }

    public static void main(String[]args){
        int[]arr=new int[]{10, 20, 40, -1, -1, 50, 80, -1, -1, 90, -1, -1, 30, 60, 100, -1, -1, -1, 70, 110, -1, -1, 120, -1, -1};
        tNode root= constructTreePreorder(arr);
        // print(root);
        iteratePrintPostorder(root);

    }
}