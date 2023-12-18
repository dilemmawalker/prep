import java.util.ArrayList;

public class prob1{
    public static ArrayList<Edge>[]graph;

    public static class Edge{
        int v=0;
        int w=0;
        Edge(int v, int w){
            this.v=v;
            this.w=w;
        }
    }
    public static void constructGraph(){
        int size=7;
        graph= new ArrayList[size];
        for(int i=0;i<size;i++){
            graph[i]= new ArrayList<Edge>();
        }

        addEdge(0,1,10);
        addEdge(0,3,10);
        addEdge(1,0,10);
        addEdge(1,2,10);
        addEdge(2,1,10);
        addEdge(2,3,40);
        addEdge(3,0,10);
        addEdge(3,2,40);
        addEdge(3,4,2);
        addEdge(4,3,2);
        addEdge(4,5,2);
        addEdge(4,6,3);
        addEdge(5,4,2);
        addEdge(5,6,8);
        addEdge(6,5,8);
        addEdge(6,4,3);

    }

    public static void addEdge(int vertex1, int vertex2, int weight){
        graph[vertex1].add(new Edge(vertex2, weight));
    }

    public static void displayGraph(){
        for(int edge=0; edge<graph.length; edge++){
            ArrayList<Edge> tempArr= graph[edge];
            for(Edge value: tempArr){
                System.out.println(edge+" -> "+"index: "+value.v + ", " + "weight: "+ value.w);
            }
        }
    }

    public static void removeEdge(int vertex1, int vertex2){
        int positionVertex2InVertex1= positionOfVertex(vertex1, vertex2);
        int positionVertex1InVertex2= positionOfVertex(vertex2, vertex1);

        graph[vertex1].remove(positionVertex2InVertex1);
        graph[vertex2].remove(positionVertex1InVertex2);

    }
    public static int positionOfVertex(int vertex1, int vertex2){
        ArrayList<Edge> vertex1List= graph[vertex1];
        int positionVertex1= -1;
        for(int vertex=0; vertex<vertex1List.size(); vertex++){
            if(vertex1List.get(vertex).v==vertex2){
                positionVertex1 = vertex;
                break;
            }
        }
        return positionVertex1;
    }

    public static void removeVertex(int vertex){
        ArrayList<Edge> vertexNeighbourList= graph[vertex];
        for(Edge e: vertexNeighbourList){
            int neighbourVertex= e.v;
            int positionVertex= positionOfVertex(neighbourVertex, vertex);
            graph[neighbourVertex].remove(positionVertex);
        }
        graph[vertex]= new ArrayList<Edge>();
    }

    public static boolean hasPath(int src, int dest, boolean[]vis, String ans){
        if(src==dest){
            System.out.println(ans);
            return true;
        }
        
        vis[src]=true;
        boolean flag= false;
            ArrayList<Edge> neighbours= graph[src];
            for(Edge e: neighbours){

                if(!vis[e.v]){
                // vis[e.v]= true;
                flag= flag || hasPath(e.v, dest, vis, ans+" "+e.v);
                // vis[e.v]= false;
                }
            }

            //not necessary to mark false.
            vis[src]=false;
        return flag;
    }
    public static int allPath(int src, int dest, boolean[]vis, String ans, int weight){
        if(src==dest){
            System.out.println(ans+" @ "+weight);
            return 1;
        }
        
        vis[src]=true;
        int flag= 0;
            ArrayList<Edge> neighbours= graph[src];
            for(Edge e: neighbours){
                if(!vis[e.v]){
                flag+= allPath(e.v, dest, vis, ans+" "+e.v, weight+e.w);
                }
            }

        vis[src]=false;
        return flag;
    }

    public static int allSolution(int src, int dest, boolean[]vis, String ans, int weight, display values, int n){
        if(src==dest){
            System.out.println(ans+" @ "+weight);
            values.heaviest=Math.max(values.heaviest, weight);
            values.lightest=Math.min(values.lightest, weight);
            values.floor= Math.min(values.floor, n-weight>=0?weight:values.floor);
            values.ceil= Math.min(values.ceil, weight-n>=0?weight:values.ceil);
            return 1;
        }
        
        vis[src]=true;
        int flag= 0;
            ArrayList<Edge> neighbours= graph[src];
            for(Edge e: neighbours){
                if(!vis[e.v]){
                flag+= allSolution(e.v, dest, vis, ans+" "+e.v, weight+e.w, values, n);
                }
            }

        vis[src]=false;
        return flag;
    }
    public static class display{
        int heaviest=0;
        int lightest=100000;
        int floor=100000;
        int ceil=100000;
        display(int heaviest, int lightest, int floor, int ceil){
            heaviest= this.heaviest;
            lightest= this.lightest;
            floor= this.floor;
            ceil= this.floor;
        }
        display(){
            heaviest= 0;
            lightest= 1000;
            floor= 1000;
            ceil= 1000;
        }
    }


    public static void main(String[]args){
        constructGraph();
        // removeEdge(1,2);
        // removeVertex(3);
        displayGraph();
        // boolean res= hasPath(0,6,new boolean[graph.length], "0");
        // int res= allPath(0,6,new boolean[graph.length], "0", 0);
        // int ceil=1000, floor=10000, heaviest=0, lightest=1000;
        display values= new display();
        int res= allSolution(0,6,new boolean[graph.length], "0", 0, values, 20);
        System.out.println(res);
        //Remember:
        // Primitive data type(int) like heaviest, lightest, etc. cannot be used as variable because their value is passed by value in java always.
        // this holds true for primitive data types
        //whereas for non-primitive data types, values are passed by reference.
        System.out.println("Ceiling: "+values.ceil + ",        Floor:"+ values.floor);
        System.out.println("Heaviest: "+ values.heaviest + ",        Lightest: "+ values.lightest);

    }
}