public class prob2{

    //Leetcode  876. Middle of the Linked List
public ListNode middleNode(ListNode head) {
        ListNode slow=head,fast=head;

        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    //Leetcode 206. Reverse Linked List

    public ListNode reverseList(ListNode head) {
        if(head==null || head.next==null)
        return head;

        ListNode prev=head;
        ListNode curr=head.next;
        ListNode next=null;
        if(curr!=null)
        next=curr.next;

        prev.next=null;

        while(next!=null){
            curr.next=prev;
            ListNode temp=curr;
            prev=curr;
            curr=next;
            next=next.next;
        }
        curr.next=prev;

        return curr;
    }

    //Leetcode 234. Palindrome Linked List

    public boolean isPalindrome(ListNode head) {
        ListNode slow=head,fast=head,prev=slow;

        while(fast!=null && fast.next!=null){
            prev=slow;
            slow=slow.next;
            fast=fast.next.next;
            System.out.println("working");
        }

        prev.next=null;

        ListNode newHead=slow;
        System.out.println(newHead.val);

        System.out.println("dfghjkl");

        newHead= reverse(newHead);
        ListNode temp=newHead;
        // while(temp!=null){
        // System.out.println(temp.val);
        // temp=temp.next;
        // }

        while(newHead!=null && head!=null){
            if(newHead.val!=head.val)
            return false;
            newHead=newHead.next;
            head=head.next;
        }
        return true;
    }


    public ListNode reverse(ListNode head){
        if(head==null || head.next==null)
        return head;

        ListNode prev=head;
        ListNode curr=head.next;
        ListNode next=head.next.next;

        while(next!=null){
            curr.next=prev;
            prev=curr;
            curr=next;
            next=next.next;
            
        }

        System.out.println("i worked");

        curr.next=prev;
        return curr;
    }

    //  203. Remove Linked List Elements

     public ListNode removeElements(ListNode head, int val) {
        if(head==null)
        return head;
        if(head.next==null){
            if(head.val==val)
            return null;
            else
            return head;
        }
        ListNode temp=head;
        ListNode prev=findNext(head,val);
        System.out.println(prev!=null?prev.val:"null");
        if(prev==null)
        return prev;
        temp=prev;
        ListNode curr=prev.next;

        while(curr!=null){
            if(curr.val==val){
                prev.next=findNext(curr,val);
            }
            prev=curr;
            curr=curr.next;
        }

        return temp;
    }
    public ListNode findNext(ListNode head, int val){
        while(head!=null && head.val==val){
            head=head.next;
        }
        return head;
    }

    // 19. Remove Nth Node From End of List

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int size=size(head)-n;

        if(size==0){
            if(head==null)
            return head;
            return head.next;
        }

        ListNode temp=head;

        ListNode prev=head,curr=head.next;
        while(curr!=null && size!=1){
            prev=curr;
            curr=curr.next;

            size--;
        }
        if(curr==null || curr.next==null)
        prev.next=null;
        else
        prev.next=curr.next;

        return temp;
    }

    public int size(ListNode head){
        int si=0;
        while(head!=null){
            si++;
            head=head.next;
        }
        return si;
    }

    //

    public void reorderList(ListNode head) {
        if(head==null || head.next==null || head.next.next==null)
        return ;

        ListNode slow=head,fast=head,prev=head;
        while(fast!=null && fast.next!=null){
            prev=slow;
            slow=slow.next;
            fast=fast.next.next;
        }
        prev=slow;
        slow=slow.next;


        prev.next=null;
        ListNode nHead=slow;

        nHead=reverse(nHead);

        // prev=head;
        // ListNode curr=head.next;
        // while(curr!=null && nHead!=null){
        //     prev.next=nHead;
        //     nHead=nHead.next;
        //     prev=prev.next;
        //     prev.next=curr;
        //     System.out.println(curr.val);
        //     curr=curr.next;
        //     // System.out.println(curr.val);
        //     prev=prev.next;
        // }


        // if(curr!=null){
        // prev.next=curr;
        // }
        // if(nHead!=null){
        // prev.next=nHead;
        // while(prev.next!=null)
        // prev=prev.next;
        // }

        // prev=prev.next;
        // if(prev!=null && prev.next!=null)
        // prev.next=null;

        ListNode te=nHead;
        nHead=head;
        head=te;

        while(head!=null && nHead!=null){
            ListNode next=head.next;
            head.next=nHead;
            head=next;

            next=nHead.next;
            nHead.next=head;
            nHead=nHead.next;
        }

    }
    public ListNode reverse(ListNode head){
        if(head==null || head.next==null)
        return head;

        ListNode prev=head,curr=head.next,next=curr.next;
        while(next!=null){
            curr.next=prev;
            prev=curr;
            curr=next;
            next=next.next;
        }
        curr.next=prev;
        return curr;
    }

    // 328. Odd Even Linked List

    public ListNode oddEvenList(ListNode head) {
        if(head==null || head.next==null || head.next.next==null)
        return head;

        ListNode first=head;
        ListNode second=head.next;

        ListNode last=first,temp2=second,original=head;

        while(first!=null && second!=null){
            last=first;

            first.next=first.next.next;
            first=first.next;
            if(first==null){
                second.next=null;
                break;
            }
            else if(second.next.next==null){
                second.next=null;
                break;
            }
            second.next=second.next.next;
            second=second.next;
        }
        if(first!=null)
        last=first;

        last.next=temp2;

        return original;
    }
}