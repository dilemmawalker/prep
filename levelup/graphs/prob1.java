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
        // addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 4, 6, 3);
        addEdge(graph, 5, 6, 8);
        // addEdge(graph, 2, 5, 2);
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

    //====================================DFS=============================================================

    public static String hasPath(ArrayList<Edge>[]graph, int start, int dest, boolean[]check){

        if(start == dest){
            return start+"";
        }

        check[start] = true;
        ArrayList<Edge>al = graph[start];
        String ans = "";
        for(int i=0; i<al.size(); i++){
            int v = al.get(i).v;
            if(!check[v]){
                ans =  hasPath(graph, v, dest, check) +" "+ start;
            }
        }

        //not marking false -> we don't need all paths, rather just get any path.

        return ans;
    }

    public static int allPath(ArrayList<Edge>[]graph, int start, int dest, boolean[]check, String ans, int sum){

        if(start == dest){
            System.out.println(ans+" "+start + " @ "+sum);
            return 1;
        }

        check[start] = true;
        ArrayList<Edge>al = graph[start];
        int count = 0;
        for(int i=0; i<al.size(); i++){
            int v = al.get(i).v;
            if(!check[v]){
                count +=  allPath(graph, v, dest, check, ans + " " + start, sum+al.get(i).w);
            }
        }

        check[start]=false;

        return count;
    }

    public static int ceil(ArrayList<Edge>[]graph, int start, int dest, boolean[]check, String ans, int sum, int weight){//solve through pair class by storing all weights of paths against ans.

        if(start == dest){
            System.out.println(ans+" "+start + " @ "+sum);
            return sum;
        }

        check[start] = true;
        ArrayList<Edge>al = graph[start];
        int count = 0;
        for(int i=0; i<al.size(); i++){
            int v = al.get(i).v;
            if(!check[v]){
                count +=  allPath(graph, v, dest, check, ans + " " + start, sum+al.get(i).w);
            }
        }

        check[start]=false;

        return count;
    }

    public static int countOfHamiltonianCycles(ArrayList<Edge>[]graph, int start, int ostart, boolean[]check, String ans){
        if(check[start]){
            if(start == ostart){
                //hamiltonian edge
                System.out.println(ans);
               return 1;
            }
            return 0;
        }

        check[start]=true;
        int count = 0;
        for(Edge edge: graph[start]){
            // if(check[edge.v])
            // continue;

        
        count += countOfHamiltonianCycles(graph, edge.v, ostart, check, ans+" "+edge.v);
        }
        check[start]=false;

        return count;
    }

    //leetcode : 130 -> Surrounded Regions
    //TBD



    public static void gcc(ArrayList<Edge>[]graph, int start, String ans, boolean[]check){

        System.out.print(start+" ");

        check[start]=true;

        for(Edge edge: graph[start]){
            if(!check[edge.v]){
                gcc(graph, edge.v, ans+" "+edge.v, check);
            }
        }
        
        return;
    }

    public static int find_gcc(ArrayList<Edge>[]graph, int start, String ans, boolean[]check){
        int count = 0;

        for(int i=start; i<graph.length; i++){
            if(!check[i]){
                count++;
                gcc(graph, i, "", check);
                System.out.println();
            }
        }
        return count;
    }

    public static void main(String[]args){
        int n = 7;
        ArrayList<Edge>[]graph = new ArrayList[n];
        constructGraph(graph);
        // removeEdge(graph, 3, 4);
        // removeNode(graph, 1);
        // printGraph(graph);
        // String ans = hasPath(graph, 0, 5, new boolean[n]);
        // System.out.println(allPath(graph, 0, 5, new boolean[n],"",0));
        // System.out.println(ans.rever);
        // int hamiltonianGraphsCount = countOfHamiltonianCycles(graph, 0, 0, new boolean[n], "");
        // System.out.println("count of hamiltonian graphs: "+ hamiltonianGraphsCount);

        int val=find_gcc(graph, 0, "", new boolean[n]);
        System.out.println("count of gcc: "+ val);
    }
}