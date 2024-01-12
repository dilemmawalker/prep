public class prob1{

    public static class linkedList{
        public class Node{
            int data=0;
            Node next=null;

            Node(int data){
                this.data=data;
            }
        }

        Node head=null;
        Node tail=null; 
        int size=0;

        public int size(){
            return size;
        }

        public boolean isEmpty(){
            return size==0;
        }

        @Override
        public String toString(){ //in built function of java which we are oveririding here.
            // return "Hi";
            Node curr=head;
            String ans="";
            while(curr!=null){
                ans+=curr.data+" -> ";
                curr=curr.next;
            }
            return ans;
        }

        //add function============================

        public void addFirst(int val){
            Node node=new Node(val);
            addFirstNode(node);
            size++;
        }

        private void addFirstNode(Node node){
            if(head!=null){
                node.next=head;
            }
            head=node;

            if(tail==null){
                tail=node;
            }
        }

        public void addLast(int val){
            Node node=new Node(val);
            addLastNode(node);
            size++;
        }

        public void addLastNode(Node node){
            if(tail==null){
                head=node;
                tail=node;
            }
            else{
                tail.next=node;
                tail=tail.next;
            }
        }

        // get function============================

        public int getFirst(){
            if(size==0){
                System.out.println("size is 0");
                return -1;
            }

            return head.data;
        }

        public int getLast(){
            if(size==0){
                System.out.println("size is 0");
                return -1;
            }

            return tail.data;
        }

        public int getAt(int pos){
            if(pos>size){
                System.out.println("size is less than position");
                return -1;
            }

            return getPositionNode(pos).data;
        }

        //remove function ==============================

        public int removeFirst(){
            if(size==0){
                System.out.println("size is 0");
                return -1;
            }

            Node temp=head;
            head=head.next;
            size--;
            return temp.data;
        }

        public int removeLast(){
            if(size==0){
                System.out.println("size is 0");
                return -1;
            }

            Node temp=tail;
            Node secondLast = getPositionNode(size-1);
            tail=secondLast;
            size--;
            return temp.data;
        }

        public int removeAt(int pos){
            if(pos>size){
                System.out.println("size is less than position");
                return -1;
            }

            Node before= getPositionNode(pos-1);
            Node after= getPositionNode(pos+1);
            Node temp= getPositionNode(pos);

            before.next=after;
            size--;
            return temp.data;
        }

        private Node getPositionNode(int pos){
            if(pos>size){
                return new Node(-1);
            }

            Node temp=head;
            int val=1;
            while(val++<pos){
                temp=temp.next;
            }
            return temp;
        }


    }

    
    public static void main(String[]args){
        linkedList ll=new linkedList();
        for(int i=10;i<=100;i+=10){
            ll.addFirst(i);
        }
        System.out.println(ll);
        ll.removeAt(3);
        System.out.println(ll);
    }
}