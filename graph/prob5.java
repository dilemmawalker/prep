import java.util.ArrayList;
import java.util.PriorityQueue;

public class prob5{

    static ArrayList<graphEdge>[]graph;
    static ArrayList<graphEdge>[]newGraph;
    public static void construct(){
        graph= new ArrayList [7];
        for(int i=0;i<graph.length;i++){
            graph[i]=new ArrayList<graphEdge>();
        }
        addEdge(0,1,10);
        addEdge(0,3,20);
        addEdge(1,2,10);
        addEdge(2,3,40);
        addEdge(3,4,2);
        addEdge(4,5,3);
        addEdge(4,6,8);
        addEdge(5,6,2);
    }

    public static void addEdge(int u, int v, int w){
        graph[u].add(new graphEdge(v,w));
        graph[v].add(new graphEdge(u,w));
    }

    public static void dijkstra(int source){
        edge e= new edge(source, -1, 0, 0);   // src, parent, weight, wsf(weight so far).
        PriorityQueue<edge>pq=new PriorityQueue<>((edge a, edge b)->{
            return a.wsf-b.wsf;     //default in PQ
        });
        boolean []vis=new boolean[graph.length];
        newGraph=new ArrayList [graph.length];

        for(int i=0;i<graph.length;i++){
            newGraph[i]=new ArrayList<graphEdge>();
        }

        pq.add(e);

        while(pq.size()!=0){
            int size=pq.size();
            while(size-->0){

                edge ed= pq.remove();
                if(vis[ed.src]) //cycle
                continue;

                vis[ed.src]=true;
                if(ed.parent!=-1){
                newGraph[ed.parent].add(new graphEdge(ed.src, ed.wsf));
                newGraph[ed.src].add(new graphEdge(ed.parent, ed.wsf));
                }

                    for(graphEdge gh: graph[ed.src]){
                        int v= gh.v;
                        int w= gh.w;

                        if(!vis[v]){
                            pq.add(new edge(v, ed.src, w, w+ed.wsf));
                        }
                    }
            }
        }
    }

    public static class edge{
        int src=0;
        int parent=0;
        int weight=0;
        int wsf=0;
        edge(int src, int parent, int weight, int wsf){
            this.src=src;
            this.parent=parent;
            this.weight=weight;
            this.wsf=wsf;
        }
    }

    public static class graphEdge{
        int v=0;
        int w=0;
        graphEdge(int v, int w){
            this.v=v;
            this.w=w;
        }
    }

    public static void printGraph(){
        for(int i=0;i<newGraph.length;i++){
            System.out.print(i+" -> ");
            for(int j=0;j<newGraph[i].size();j++){
                graphEdge e= newGraph[i].get(j);
                System.out.print("( "+e.v+" "+e.w+")  ");
            }
            System.out.println();
        }
    }


    //leetcode 924 Malware Spread

    public int minMalwareSpread(int[][] graph, int[] initial) {
        int[]parent=new int[graph.length];
        int []size=new int[graph.length];

        for(int i=0;i<graph.length;i++){
            parent[i]=i;
            size[i]=1;
        }

        int minMalware=-1;
        for(int i=0;i<initial.length;i++){
            minMalware= Math.min(initial[i],minMalware);
        }

        for(int i=0;i<graph.length;i++){
            for(int j=0;j<graph[i].length;j++){
                int v1=i;
                int v2=j;
                
                int p1=findParent(v1);
                int p2=findParent(v2);

                mergeSet(p1,p2);
            }
        }

        HashSet<Integer>map= new HashSet<>();
        for(int initi: initial)
        map.add(initi);

        HashMap<Integer,Integer>noAgainstIndex= new HashMap<>(); 
        int noOfSets=0;
        for(int i=0;i<parent.length;i++){
            if(parent[i]==i){
                noAgainstIndex.add(i,noOfSets);
                noOfSets++;
            }
        }

        storage[] spread= new storage[noOfSets];
        int index=0;
        for(int i=0;i<noOfSets;i++){
            spread[i]=new storage();
        }

        for(int i=0;i<parent.length;i++){
            int value= parent[i];
            // if(noAgainstIndex.containsKey(value)){
                int val= noAgainstIndex.get(value);
                storage t= spread[val];
                t.setSize= t.setSize + 1;
                t.parent= value;
                t.selfEdge= i;

                if(map.contains(i))
                t.malwareCount= t.malwareCount + 1;
            // }
        }

        Arrays.sort(spread, (storage a, storage b)->{
            return b.setSize - a.setSize;
        });

        for(int i=0;i<spread.length;i++){
            storage value= spread[i];
            if(value.malwareCount==1){
                return value.selfEdge;
            }
        }

        return minMalware;

    }

    public class storage{
        int setSize=0;
        int parent=0;
        int malwareCount=0;
        int selfEdge=0;
        storage(int setSize, int parent, int malwareCount, int selfEdge){
            this.setSize= setSize;
            this.parent= parent;
            this.malwareCount= malwareCount;
            this.selfEdge= selfEdge;
        }
        storage(){
            this.setSize=0;
            this.parent=0;
            this.malwareCount= 0;
            this.selfEdge= 0;
        }
    }

    //. 743. Network Delay Time

    public int networkDelayTime(int[][] times, int n, int k) {
        PriorityQueue<store>pq= new PriorityQueue<>((store a, store b)->{
            return a.wsf-b.wsf;
        });
        ArrayList<edge>[]graph= new ArrayList[n+1];
        for(int i=0;i<=n;i++)
        graph[i]=new ArrayList<edge>();

        construct(graph, times);

        //print graph
        // for(int i=0;i<graph.length;i++){
        //     System.out.print(i+" -> ");
        //     for(int j=0;j<graph[i].size();j++){
        //         System.out.print(graph[i].get(j)+", ");
        //     }
        //     System.out.println();
        // }

        int minTime=0;
        pq.add(new store(k, -1, 0, 0));
        boolean[]vis=new boolean[n+1];

        while(pq.size()!=0){
            int size=pq.size();
            while(size-->0){
                store vertex= pq.remove();

                if(vis[vertex.node])
                continue;

                minTime=Math.max(minTime,vertex.wsf);
                vis[vertex.node]=true;

                for(int i=0;i<graph[vertex.node].size();i++){
                    edge ele= graph[vertex.node].get(i);
                    if(!vis[ele.v]){
                        // System.out.println("worked for edge: "+ ele.v +" "+(ele.w+vertex.wsf));
                        pq.add(new store(ele.v, vertex.node, ele.w, vertex.wsf+ele.w));
                    }
                }

            }
        }

        for(int i=1;i<vis.length;i++){
            if(!vis[i])
            return -1;
        }

        return minTime;

    }

    public void construct(ArrayList<edge>[]graph, int[][]times){
        for(int i=0;i<times.length;i++){
                int u= times[i][0];
                int v= times[i][1];
                int w= times[i][2];

                addEdge(graph,u,v,w);
            
        }
    }
    public void addEdge(ArrayList<edge>[]graph, int u, int v, int w){
        graph[u].add(new edge(v,w));
    }
    public class store{
        int node=0;
        int parent=0;
        int weight=0;
        int wsf=0;
        store(int node, int parent, int weight, int wsf){
            this.node=node;
            this.parent=parent;
            this.weight=weight;
            this.wsf=wsf;
        }
    }
    public class edge{
        int v=0;
        int w=0;
        edge(int v, int w){
            this.v=v;
            this.w=w;
        }
    }



    //. 787. Cheapest Flights Within K Stops

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        ArrayList<edge>[]graph= new ArrayList[n+1];
        int[]vis=new int[n];

        for(int i=0;i<graph.length;i++)
        graph[i]=new ArrayList<edge>();
        construct(graph, flights);

        //print graph
        printg(graph);

        PriorityQueue<store>pq= new PriorityQueue<>((store a, store b)->{
            return a.wsf-b.wsf;
        });

        int min=Integer.MAX_VALUE;

        pq.add(new store(src, -1, 0, 0, 0));

        while(pq.size()!=0){
            store vertex= pq.remove();
            if(vertex.v==dst && vertex.stops<=k+1){
                return vertex.wsf;
            }

            vis[vertex.v]=1;
            
            for(int i=0;i<graph[vertex.v].size();i++){
                int v= graph[vertex.v].get(i).v;
                int w= graph[vertex.v].get(i).w;

                if((vis[v]==0) && vertex.stops+1<=k+1){
                    pq.add(new store(v, vertex.v, w, w+vertex.wsf, vertex.stops+1));
                }
            }
            vis[vertex.v]=0;
        }
        if(min!=Integer.MAX_VALUE)
        return min;
        return -1;
    }


    public void printg(ArrayList<edge>[]graph){
        for(int i=0;i<graph.length;i++){
            System.out.print(i+" -> ");
            for(int j=0;j<graph[i].size();j++){
                System.out.print("("+graph[i].get(j).v+","+graph[i].get(j).w+"), ");
            }
            System.out.println();
        }
    }

    public void construct(ArrayList<edge>[]graph, int[][]flights){
        for(int i=0;i<flights.length;i++){
            int u= flights[i][0];
            int v= flights[i][1];
            int w= flights[i][2];

            graph[u].add(new edge(v,w));
        }
    }
    public class edge{
        int v=0;
        int w=0;
        edge(int v, int w){
            this.v=v;
            this.w=w;
        }
    }
    public class store{
        int v=0;
        int p=0;
        int w=0;
        int wsf=0;
        int stops=0;
        store(int v, int p, int w, int wsf, int stops){
            this.v=v;
            this.p=p;
            this.w=w;
            this.wsf=wsf;
            this.stops=stops;
        }
    }

    public static void main(String[]args){
        construct();
        dijkstra(0);
        printGraph();
    }
}