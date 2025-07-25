import java.util.ArrayList;

public class prob1{

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
    }

    public static void printGraph(ArrayList<Edge>[]graph){
        int n = graph.length;
        for(int i=0; i<n; i++){
            ArrayList<Edge> al = graph[i];
            int size = al.size();
            System.out.print(i+" -> ");
            for(int j=0; j<size; j++){
                Edge edge = al.get(j);
                System.out.print(edge.v+": "+edge.w+", ");
            }
            System.out.println();
        }
    }

    public static void main(String[]args){
        int n = 7;
        ArrayList<Edge>[]graph = new ArrayList[n];
        constructGraph(graph);
        printGraph(graph);
    }
}