import java.util.ArrayList;

class prob6{
    static ArrayList<Integer>[]graph;
    static int[]discover;
    static int[]low;
    public static void construct(){
        graph=new ArrayList[7];
        discover=new int[graph.length];
        low= new int[graph.length];
        int count=0;


        for(int i=0;i<graph.length;i++){
            graph[i]=new ArrayList<>();
            // low[i]=discover[i]=count++;
        }

        addEdge(0,3);
        addEdge(0,1);
        addEdge(3,2);
        addEdge(1,2);
        addEdge(2,4);
        addEdge(4,5);
        addEdge(4,6);
        addEdge(5,6);

    }

    public static void addEdge(int u, int v){
        graph[u].add(v);
        graph[v].add(u);
    }


        //dry run more to see why not working!!!

        //also run for articulation edge.
    public static int articulationPoint(pair vertex, ArrayList<Integer>ans, int[]vis, int index){/
        if(vis[vertex.v]==1){
            return discover[vertex.v];
        }

        vis[vertex.v]=1;//parent
        int min=Integer.MAX_VALUE;
        discover[vertex.v]=index;
        low[vertex.v]=index;

        for(int i=0; i<graph[vertex.v].size(); i++){
            int v= graph[vertex.v].get(i);//child
            if(vertex.v==v)
            continue;

            min= Math.min(min,articulationPoint(new pair(v,vertex.v), ans, vis, index+1)); 
            if(index!=0 && discover[vertex.v]<=min){
                ans.add(vertex.v);
            }
        }
        if(min>=discover[vertex.v]){
            //if(index!=0)
            //ans.add(vertex.v);
            return low[vertex.v];
        }
        else
        return low[vertex.v]=min;
    }

    public static class pair{
        int v=0;
        int p=0;
        pair(int v, int p){
            this.v=v;
            this.p=p;
        }
    }
    public static void printGraph(){
        for(int i=0;i<graph.length;i++){
            System.out.print(i+" -> ");
            for(int j=0;j<graph[i].size();j++){
                System.out.print(graph[i].get(j)+", ");
            }
            System.out.println();
        }
    }

    public static void main(String[]args){
        construct();
        printGraph();
        ArrayList<Integer>ans= new ArrayList<Integer>();
        articulationPoint(new pair(0,-1), ans, new int[graph.length], 0);
        System.out.print(ans);
    }
}