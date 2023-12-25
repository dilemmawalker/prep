import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class prob4{
    public static ArrayList<Integer>[]graph;
    public static int[]parent;

    public static void kruskalAlgo(ArrayList<ArrayList<Integer>>input){
        
        int edgesCount=0;
        for(int i=0;i<input.size();i++){
            edgesCount= Math.max(edgesCount, Math.max(input.get(i).get(0), input.get(i).get(1)));
            // graph[i]= new ArrayList<Integer>();
        }
        edgesCount++;

        graph= new ArrayList [edgesCount];
        parent= new int[edgesCount];

        for(int i=0;i<graph.length;i++){
            graph[i]=new ArrayList<Integer>();
            parent[i]=i;
        }

        for(int i=0;i<input.size();i++){
            int v1= input.get(i).get(0);
            int v2= input.get(i).get(1);

            int p1= findParent(v1);
            int p2= findParent(v2);

            if(p1!=p2){
                addEdge(v1,v2);
            }
        }
    }

    public static void addEdge(int v1, int v2){
        ArrayList<Integer>temp1= graph[v1];
        temp1.add(v2);
        graph[v1]=temp1;

        ArrayList<Integer>temp2= graph[v2];
        temp2.add(v1);
        graph[v2]=temp2;
    }

    public static void printGraph(){
        for(int i=0;i<graph.length;i++){
            System.out.print(i+" -> ");
            for(int j=0;j<graph[0].size();j++){
                System.out.print(graph[i].get(j)+", ");
            }
            System.out.println();
        }
    }

    public static int findParent(int vertex){
        if(parent[vertex]==vertex)
        return vertex;

        return parent[vertex]=findParent(parent[vertex]);
    }

    public static void constructarr(ArrayList<ArrayList<Integer>>arr){
        addEdgeinArr(0,1,4,arr);
        addEdgeinArr(0,7,8,arr);
        addEdgeinArr(1,7,11,arr);
        addEdgeinArr(1,2,8,arr);
        addEdgeinArr(7,8,7,arr);
        addEdgeinArr(7,6,1,arr);
        addEdgeinArr(6,8,6,arr);
        addEdgeinArr(2,8,2,arr);
        addEdgeinArr(2,5,4,arr);
        addEdgeinArr(6,5,2,arr);
        addEdgeinArr(2,3,7,arr);
        addEdgeinArr(3,5,14,arr);
        addEdgeinArr(3,4,9,arr);
        addEdgeinArr(5,4,10,arr);    

        Collections.sort(arr, (ArrayList<Integer> a, ArrayList<Integer> b)->{   //default(increasing) =   this - other
            return a.get(2) - b.get(2);
        });
        // Arrays.sort(arr, (int[] a, int[] b)->{   //default(increasing) =   this - other
        //     return a[2] - b[2];
        // });
        System.out.println(arr);
    }
    public static void addEdgeinArr(int v1, int v2, int weight, ArrayList<ArrayList<Integer>>arr){
        ArrayList<Integer>temp= new ArrayList<Integer>();
        temp.add(v1);
        temp.add(v2);
        temp.add(weight);
        arr.add(temp);
    }

    // 1168 minimum cost to supply water
    static int[]par;
    public static int supplyWater(int n, int k, int[] wells, int[][] pipes) {
        par=new int[n+1];
        for(int i=0;i<par.length;i++){
            par[i]=i;
        }

        int wt=0;
        int[][]newpipes= new int[pipes.length + n][3];
        for(int i=0;i<pipes.length;i++){
            newpipes[i][0]=pipes[i][0];
            newpipes[i][1]=pipes[i][1];
            newpipes[i][2]=pipes[i][2];
        }
        int index=1;
        for(int i=index;i<=n ; i++){
            newpipes[i+pipes.length-1-1]= new int[]{0,i,wells[i-1]};
        }

        Arrays.sort(newpipes, (int[]a, int[]b)->{
            return a[2]-b[2];
        });

        for(int i=0;i<newpipes.length;i++){
            int v1= newpipes[i][0];
            int v2= newpipes[i][1];

            int p1= findPar(v1);
            int p2= findPar(v2);

            if(p1!=p2){
                par[p1]=p2;
                wt+=newpipes[i][2];
            }
        }

        return wt;
    }

    public static int findPar(int v){
        if(par[v]==v)
        return v;

        return par[v]=findPar(par[v]);
    }

    // hackerrank- journey to moon 

    public static int journeyToMoon(int n, List<List<Integer>> astronaut) {
    // Write your code here
    int[]parent=new int[n];
    int[]size= new int[n];
    for(int i=0;i<n;i++){
        parent[i]=i;
    }
    
    for(int i=0;i<astronaut.size();i++){
        int v1= astronaut.get(i).get(0);
        int v2= astronaut.get(i).get(1);

        int p1= Parent(v1, parent);
        int p2= Parent(v2, parent);
    }

    int value=0, totalValue=0;
    for(int i=0;i<parent.length;i++){
        size[parent[i]]++;
        totalValue+=size[i];
    }
    
    // for(int i=0;i<parent.length;i++){
    //     for(int j=i+1;j<parent.length;j++){
    //         value+= size[i] * size[j];
    //     }
    // }

    for(int i=0;i<parent.length;i++){
        totalValue-= size[i];
        value+= size[i]*(totalValue);
    }
    
    return value;
    }
    public static int Parent(int v, int[]parent){
        if(parent[v]==v)
        return v;

        return parent[v]=Parent(parent[v], parent);
    }

    //mr. president haackerearth 

    public static mrPresident() {
        /* Sample code to perform I/O:
         * Use either of these methods for input

        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();                // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        //Scanner*/
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();                 // Reading input from STDIN
        // System.out.println("Hi, " + name + ".");    // Writing output to STDOUT
        int m= s.nextInt();
        int k= s.nextInt();

        // Write your code here
        ArrayList<ArrayList<Integer>>input= new ArrayList<ArrayList<Integer>>();

        for(int i=0;i<m;i++){
            int a=s.nextInt();
            int b=s.nextInt();
            int c=s.nextInt();

            ArrayList<Integer>temp= new ArrayList<>();
            temp.add(a);temp.add(b);temp.add(c);
            input.add(temp);
        }

        Collections.sort(input, (ArrayList<Integer> a, ArrayList<Integer> b)->{
            return a.get(2)-b.get(2);
        });

        int[]parent=new int[n+1];

        for(int i=0;i<parent.length;i++){
            parent[i]=i;
        }

        int ans=0, count=0;
        for(int i=0;i<m;i++){
            int a= input.get(i).get(0);
            int b= input.get(i).get(1);
            int wt= input.get(i).get(2);

            int p1= findParent(a, parent);
            int p2= findParent(b, parent);

            if(p1!=p2){
                parent[p1]=Math.min(p1,p2);
                parent[p2]=Math.min(p1,p2);
                ans+=wt;
                if(ans>k){
                    count++;
                }
            }
        }

        // for(int i=0;i<=n;i++){
        //     int a= i;
        //     ArrayList<
        //     int b= input.get(i).get(0);
        // }
        System.out.print(count);
    }
    public static int findParent(int v, int[]parent){
        if(parent[v]==v)
        return v;

        return parent[v]=findParent(parent[v],parent);
    }
    public static class Edge{
        int v1=0;
        int v2=0;
        int w=0;
        Edge(int v1, int v2, int w){
            this.v1=v1;
            this.v2=v2;
            this.w=w;
        }
    }

    public static void main(String[]args){
        ArrayList<ArrayList<Integer>>arr= new ArrayList<ArrayList<Integer>>();
        constructarr(arr);
        kruskalAlgo(arr);
        printGraph();
    }
}