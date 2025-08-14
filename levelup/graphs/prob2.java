import java.util.ArrayList;
import java.util.LinkedList;

public class prob2{

    public static class Edge{
        int v=0;
        int w=0;

        Edge(int v, int w){
            this.v=v;
            this.w=w;
        }
    }

    public static void initialize(ArrayList<Edge>[]graph){
        int n = graph.length;
        for(int i=0; i<n; i++){
            graph[i] = new ArrayList<Edge>();
        }
    }

    public static void addEdge(ArrayList<Edge>[]graph, int v1, int v2, int w){
        graph[v1].add(new Edge(v2, w));
        graph[v2].add(new Edge(v1, w));
    }

    public static void removeSingleEdge(ArrayList<Edge>[]graph, int v1, int v2){
        ArrayList<Edge>temp = graph[v1];
        for(int i=0; i<temp.size(); i++){
            Edge e = temp.get(i);
            if(e.v == v2){
                temp.remove(i);
                break;
            }
        }
    }

    public static void removeEdge(ArrayList<Edge>[]graph, int v1, int v2){
        removeSingleEdge(graph, v1, v2);
        removeSingleEdge(graph, v2, v1);
    }

    public static void removeNode(ArrayList<Edge>[]graph, int v){
        for(int i=0; i<graph[v].size(); i++){
            Edge e = graph[v].get(i);
            removeSingleEdge(graph, e.v, v);
        }
        graph[v]= new ArrayList<Edge>();
    }

    public static void constructGraph(ArrayList<Edge>[]graph){
        initialize(graph);

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 3, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 4, 6, 3);
        addEdge(graph, 5, 6, 8);
        addEdge(graph, 2, 5, 2);
    }

    public static void printGraph(ArrayList<Edge>[]graph){
        int n = graph.length;
        for(int i=0; i<n; i++){
            ArrayList<Edge> al = graph[i];
            int size = al.size();
            System.out.print(i+" -> ");
            for(int j=0; j<size; j++){
                Edge edge = al.get(j);
                System.out.print("("+ edge.v+": "+edge.w+"), ");
            }
            System.out.println();
        }
    }

    //====================================BFS=============================================================

    public static void shortestPath(ArrayList<Edge>[]graph, int initialVertex){

        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(initialVertex);
        int level = 0;
        int cycle = 0;
        boolean[]check = new boolean[graph.length];
        // check[initialVertex]=true;

        while(queue.size()!=0){
            int size = queue.size();

            while(size-->0){
                int edge = queue.removeFirst();
                if(check[edge]){
                    cycle++;
                    continue;
                }
                check[edge]=true;
                for(Edge neighbour: graph[edge]){

                    if(!check[neighbour.v]){
                        // check[neighbour.v]=true;
                        queue.addLast(neighbour.v);
                    }
                }
            }
            level++;
        }

        System.out.println("cycle count: "+ cycle);
        System.out.println("levels: "+ level);
    }

    //===================leetcode 286==========================
    //do later

    //=========================Bipartitie graph====================
    //in notes

    //=========================leetcode 329(longes increasing path in a matrix)    ====================

    //=========================kosa raju algo & bus stop leetcode    ====================




    public static void main(String[]args){
        int n = 7;
        ArrayList<Edge>[]graph = new ArrayList[n];
        constructGraph(graph);
        
        shortestPath(graph, 0);
    }
}