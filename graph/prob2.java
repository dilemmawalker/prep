import java.util.ArrayList;
import java.util.Collections;

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

    public static void main(String[]args){
        constructGraph();
        displayGraph();
        topologicalSort();
    }
}