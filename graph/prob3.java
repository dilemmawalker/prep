public class prob3{

    public static int[]size;
    public static int[]parent;

    public static int mergeSet(int vertex1, int vertex2){
        if(size[vertex1]<=size[vertex2]){
            parent[vertex1]=vertex2;
            size[vertex2]+=size[vertex1];
            return vertex2;
        }
        else{
            parent[vertex2]=vertex1;
            size[vertex1]+=size[vertex2];
            return vertex1;
        }

    }

    public static int findParent(int vertex){
        if(parent[vertex]==vertex)
        return vertex;

        return parent[vertex]= findParent(parent[vertex]);
    }

    public static void construct(){
        size=new int[11];
        parent= new int[11];
        parent[1]=1;
        parent[2]=1;
        parent[9]=2;
        parent[10]=1;
        parent[3]=1;
        parent[6]=3;
        parent[4]=3;
        parent[8]=4;
        parent[5]=4;

        size[1]=4;
        size[2]=1;
        size[3]=2;
        size[4]=2;
    }

    public static void printParent(){
        for(int index=0;index<parent.length;index++){
            System.out.print(parent[index]+" ");
        }
        System.out.println();
    }


    //leetcode: 684 Redundant Connection

    public int[] findRedundantConnection(int[][] edges) {
        int index=-1;
        for(int i=0;i<edges.length;i++){
            index=Math.max(index, edges[i][0]);
            index=Math.max(index, edges[i][1]);
        }
        index++;
        int[]ans=new int[]{-1,-1};

        int[]size=new int[index];
        int[]parent= new int[index];

        for(int i=0;i<index;i++){
            parent[i]=i;
            size[i]=1;
        }    

        for(int i=0;i<index;i++){
            int e1= edges[i][0];
            int e2= edges[i][1];
            int p1=findParent(e1, size, parent);
            int p2= findParent(e2, size, parent);

            if(p1==p2){
                ans[0]=e1;
                ans[1]=e2;
                return ans;
            }
            mergeSet(p1,p2, size, parent);
        }
        return ans;
    }

    public int findParent(int vertex, int[]size, int[]parent){
        if(parent[vertex]==vertex)
        return vertex;

        return parent[vertex]= findParent(parent[vertex], size, parent);
    }

    public int mergeSet(int s1 , int s2, int[]size, int[]parent){
        if(size[s1]>=size[s2]){
            size[s1]+=size[s2];
            parent[s2]=s1;
            return s1;
        }
        else{
            size[s2]+=size[s1];
            parent[s1]=s2;
            return s2;
        }
    }

    //leetcode 547: Number of Provinces

    

    public static void main(String[]args){
        construct();
        printParent();
        findParent(5);
        printParent();
    }
}