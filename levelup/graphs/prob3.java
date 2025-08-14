public class prob3{

    public static int []parent;
    public static int []size;

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

    //===========================================================================================

    public static int findParent(int v){
        if(parent[v]==v)
        return v;

        return parent[v]=findParent(parent[v]); //optimizes from O(N) to O(1)
    }

    public static int mergeSet(int v1, int v2){
        int n1 = size[v1];
        int n2 = size[v2];
        if(n1>=n2){
            size[n1]=n1+n2;
            parent[v2]=v1;
        } else{
            size[n2]=n1+n2;
            parent[v1]=v2;
        }
    }

    public static int construct(int n){
        for(int i=0; i<n; i++){
            size[i]=1;
            parent[i]=i;
        }
    }

    public static int unionFind(){
        // int n = graph.length;
        // for(int i=0; i<graph.length; i++){
        //     ArrayList<Edge> edge = graph[i];
        //     int u = edge.
        // }
    }

    public static int printParent(){
        int n= parent.length;
        for(int i=0; i<n; i++){
            System.out.println(i+": "+parent[i]);
        }
    }

    public static int printSize(){
        int n= size.length;
        for(int i=0; i<n; i++){
            System.out.println(i+": "+size[i]);
        }
    }

    public static void main(String[]args){
        int n = 7;
        parent = new int[n];
        size = new int[n];
        construct(n);
        unionFind();
        printParent(); printSize();
    }
}