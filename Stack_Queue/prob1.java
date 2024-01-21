import java.util.Scanner;

public class prob1{

    public static class Stack{
        public static int[]stack =new int[1];
        public static int pointer= -1;

        public int size(){
            return pointer+1;
        }

        public boolean empty(){
            if(pointer==-1)
            return true;
            return false;
        }

        public void push(int val){
            if(pointer+1>=stack.length)
            newStack(stack.length*2);

                pointer++;
                stack[pointer]= val;
        }

        public int pop(){
            if(pointer!=-1)
            return stack[pointer--];
            else
            return -1;
            
        }

        public int top(){
            if(pointer!=-1)
            return stack[pointer];
            else
            return -1;
        }

        public void newStack(int size){
            int[]arr= new int[size];
            int index=0;
            for(int ele: stack)
                arr[index++]=ele;

            stack=new int[size];
            for(int i=0;i<size/2+1;i++)
                stack[i]=arr[i];
        }
    }

    public static void main(String[]args){
        Stack stack= new Stack();
        int size= stack.size();
        // System.out.println(size);
        stack.push(4);
        stack.push(5);
        stack.push(9);
        int size2= stack.size();
        System.out.println(size2);
        int val= stack.pop();
        int val2= stack.top();
        System.out.println(val);
        System.out.println(val2);
        int val3= stack.pop();
        System.out.println(val3);
        int val4=stack.top();
        System.out.println(val4);
    }
}