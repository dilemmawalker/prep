import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class prob2{
    public static ArrayList<Integer>[]graph;

    public static void constructGraph(){
        graph= new ArrayList [8];
        for(int i=0;i<graph.length;i++){
            graph[i]=new ArrayList<>();
        }
        addEdge(1,0);
        addEdge(3,1);
        addEdge(2,1);
        addEdge(5,2);
        addEdge(5,4);
        addEdge(6,3);
        addEdge(6,4);
        addEdge(7,5);
        addEdge(7,6);
    }

    public static void addEdge(int vertex1, int vertex2){ //adds Edge from vertex1 to vertex2
        graph[vertex1].add(vertex2);
    }

    public static void displayGraph(){
        for(int i=0;i<graph.length;i++){
            System.out.println(i+" -> "+ graph[i]);
        }
    }

    public static void topologicalSort(){
        boolean[]vis= new boolean[graph.length];
        ArrayList<Integer>ans=new ArrayList<>();
        for(int i=0;i<graph.length;i++){
            if(!vis[i]){
                topoSort(i, vis, ans);
            }
        }
        Collections.reverse(ans);  //reverse arrayList
        for(int e: ans)
        System.out.print(e+" ");
    }

    public static void topoSort(int index, boolean[]vis, ArrayList<Integer>arr){
        vis[index]=true;

        for(int neighbour: graph[index]){
            if(!vis[index]){
                topoSort(neighbour, vis, arr);
            }
        }
        arr.add(index);
    }

    public static boolean khansAlgo(){
        int[]indegree= new int[graph.length];
        for(int vertex=0;vertex<graph.length;vertex++){
            for(int e: graph[vertex]){
                indegree[e]++;
            }
        }

        LinkedList<Integer> queue= new LinkedList<>();
        for(int index=0; index<graph.length; index++){
            if(indegree[index]==0)
            queue.addLast(index);
        }

        ArrayList<Integer> poppedElement= new ArrayList<>();

        int level=0; 
        while(queue.size()!=0){
            int size=queue.size();
            while(size-->0){
                int cvertex= queue.removeFirst();
                poppedElement.add(cvertex);

                for(int neighbour: graph[cvertex]){
                    indegree[neighbour]--;
                    if(indegree[neighbour]==0)
                    queue.addLast(neighbour);
                }
            }
        }
        if(poppedElement.size()==graph.length)
        return true;
        return false;
    }

    //leetcode: 207. Course Schedule

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        graph=new ArrayList[numCourses];

        for(int vertex=0; vertex<graph.length; vertex++){
            graph[vertex]= new ArrayList<>();
        }

        for(int i=0;i<prerequisites.length;i++){
            int a= prerequisites[i][0];
            int b= prerequisites[i][1];

            graph[a].add(b);
        }

        int[]indegree=new int[graph.length];
        LinkedList<Integer>queue= new LinkedList<>();
        LinkedList<Integer>length= new LinkedList<>();

        for(int i=0;i<graph.length;i++){
            for(int ele: graph[i]){
                indegree[ele]++;
            }
        }

        for(int i=0;i<indegree.length;i++)
        if(indegree[i]==0)
        queue.addLast(i);

        while(queue.size()!=0){
            int size=queue.size();
            while(size-->0){
                int cvertex= queue.removeFirst();
                length.addLast(cvertex);

                for(int ele: graph[cvertex]){
                    indegree[ele]--;
                    if(indegree[ele]==0){
                        queue.push(ele);
                    }
                }
            }
        }
        if(length.size()==graph.length)
        return true;
        return false;
    }


    //leetcode 329: Longest Increasing Path in a Matrix

    public static int longestIncreasingPath(int[][] matrix) {

        int[][]dir=new int[][]{{0,-1},{0,1},{1,0},{-1,0}};
        LinkedList<Integer> queue= new LinkedList<>();

        int[][]indegree= new int[matrix.length][matrix[0].length];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                int value= matrix[i][j];
                for(int di=0;di<dir.length;di++){
                    int row= i+dir[di][0];
                    int col= j+dir[di][1];
                    if(row>=0 && col>=0 && row<matrix.length && col<matrix[0].length && matrix[row][col]<value)
                    indegree[i][j]++;
                }
            }
        }

        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(indegree[i][j]==0)
                queue.addLast(i*matrix[0].length + j);
            }
        }

        int level=0;
        while(queue.size()!=0){
            int size=queue.size();
            level++;
            // System.out.println("comint here");
            while(size-->0){
                int ele= queue.removeFirst();
                int r= ele/matrix[0].length;
                int c= ele%matrix[0].length;


                for(int i=0;i<dir.length;i++){
                    int row= r+dir[i][0];
                    int col= c+dir[i][1];
                    if(row>=0 && col>=0 && row<matrix.length && col<matrix[0].length && matrix[row][col]>matrix[r][c]){
                    indegree[row][col]--;
                    if(indegree[row][col]==0){
                        queue.addLast(row*matrix[0].length + col);
                    }
                }
                }
            }
        }
        return level;
    }

    public static void main(String[]args){
        constructGraph();
        displayGraph();
        topologicalSort();
       boolean res= khansAlgo();
       System.out.println(res);
    }
}