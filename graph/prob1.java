import java.util.ArrayList;
import java.util.LinkedList;

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
        addEdge(2,5,5);
        addEdge(3,0,10);
        addEdge(3,2,40);
        addEdge(3,4,2);
        addEdge(4,3,2);
        addEdge(4,5,2);
        addEdge(4,6,3);
        addEdge(5,4,2);
        addEdge(5,2,5);
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

    public static int hamiltonianPath(int startVertex, String ans, int[]vis, int noOfVis, int originalStartVertex){
        if(noOfVis==graph.length){
            if(positionOfVertex(startVertex, originalStartVertex)!=-1){
                System.out.println("Hamiltonian Cycle: "+ ans);
                return 1;
            }
            else{
                System.out.println("Hamiltonian Path: "+ ans);
                return 0;  
            }
        }

        int count=0;
        vis[startVertex]=1;
        ArrayList<Edge>neighbours= graph[startVertex];
        for(int i=0; i<neighbours.size(); i++){
            int singleNeighbour= neighbours.get(i).v;
            if(vis[singleNeighbour]==0){
                vis[singleNeighbour]=1;
                count+= hamiltonianPath(singleNeighbour, ans+""+singleNeighbour, vis, noOfVis+1, originalStartVertex);
                vis[singleNeighbour]=0;
            }
        }
        return count;
    }

    public static void surroundedRegions(char[][] board) {
       ArrayList<Integer> startPoints = new ArrayList<>();
        for(int col=0; col<board[0].length; col++){
            if(board[0][col]=='O')
            startPoints.add(col);
            if(board[board.length-1][col]=='O')
            startPoints.add((board.length-1)*board[0].length +col);
        }
        for(int row=0; row<board.length; row++){
            if(board[row][0]=='O')
            startPoints.add(board[0].length * row);
            if(board[row][board[0].length-1]=='O')
            startPoints.add(board[0].length * row + board[0].length-1);
        }
        System.out.println("staret points are: "+startPoints);
        int[][]dir=new int[4][2];
        dir[0]=new int[]{0,-1};
        dir[1]=new int[]{0,1};
        dir[2]=new int[]{-1,0};
        dir[3]=new int[]{1,0};
        for(Integer value: startPoints){
            int row= value/board[0].length;
            int col= value%board[0].length;
            if(board[row][col]!='A')
            surroundedRegionsDfs(board, value, new int[board.length][board[0].length], dir);  //returns a board with unsurrounded from all sides 0 turned to 'A';
            print2DArray(board);
            // break;
        }
         for(int row=0; row<board.length; row++){
            for(int col=0; col<board[0].length; col++){
                if(board[row][col]=='O')
                board[row][col]='X';
                else if(board[row][col]=='A')
                board[row][col]='O';
            }
        }
    }
    public static void surroundedRegionsDfs(char[][]board, int index, int[][]vis, int[][]dir){
       int row= index/board[0].length;
        int col= index%board[0].length;

        System.out.println("row: "+row + "   col: "+col);
        vis[row][col]=1;
        board[row][col]='A';
        for(int i=0;i<4;i++){
            int nrow=row+dir[i][0];
            int ncol=col+dir[i][1];
            if(nrow>=0 && nrow<board.length && ncol>=0 && ncol<board[0].length && board[nrow][ncol]=='O'){
                int nindex= nrow*board.length + ncol;
                if(vis[nrow][ncol]==0){
                    vis[nrow][ncol]=1;
                    surroundedRegionsDfs(board, nindex, vis, dir);
                    vis[nrow][ncol]=0;
                }
            }
        }
    }
    public static void print2DArray(char[][]board){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static int getConnectedComponents(){
        int[]vis= new int[graph.length];
        int count=0;
        for(int vertex=0; vertex<graph.length; vertex++){
            if(vis[vertex]==0){
                count++;
                GCC(vertex, vis);
            }
        }
        return count;
    }
    public static void GCC(int vertex, int[]vis){
        vis[vertex]=1;

        ArrayList<Edge> neighbours= graph[vertex];
        for(Edge e: neighbours){
            int neighbour= e.v;
            if(vis[neighbour]==0){
                GCC(neighbour, vis);
            }
        }
    }

    public static class path{
        int vertex=0;
        String route="";
        path(int vertex, String route){
            this.vertex= vertex;
            this.route= route;
        }
    }

    public static void BFS(int vertex, int[]vis, int dest){
        LinkedList<path> queue= new LinkedList<>();
        queue.addLast(new path(vertex,vertex+""));
        int level=0;
        while(queue.size()!=0){ //overall
            int size=queue.size();
            while(size-->0){ //level wise.
                path cvtx= queue.removeFirst();
                // System.out.println("removed vertex: "+ cvtx.vertex);
                // System.out.println("removed route: "+ cvtx.route);

                if(cvtx.vertex==dest)
                System.out.println("destination: "+cvtx.route);

                if(vis[cvtx.vertex]==1){
                    System.out.println("cycle: "+ cvtx.route);
                    continue;
                }

                vis[cvtx.vertex]=1;


                for(Edge e: graph[cvtx.vertex]){
                    if(vis[e.v]==0){
                        // vis[e.v]=1;
                        // System.out.print("printing: "+ e.v);
                        queue.addLast(new path(e.v, cvtx.route+""+e.v));
                        // System.out.println("    route: "+ cvtx.route+""+e.v);
                    }
                }
            }
            level++;
        }
        System.out.println("levels total: "+level);
    }

    public static void wallsAndGates(int[][]arr){
        ArrayList<Integer>index= new ArrayList<>();

        for(int row=0;row<arr.length;row++){
            for(int col=0;col<arr[0].length;col++){
                // System.out.println("row: "+row + "   col: "+col);
                if(arr[row][col]==0)
                index.add(row*arr[0].length + col); //contains all the gates.
            }
        }

        DFSWallsAndGates(arr, index, new int[arr.length][arr[0].length]);
        //already mark true the 2 vertices before this call.

        for(int row=0;row<arr.length;row++){
            for(int col=0;col<arr[0].length;col++){
                System.out.print(arr[row][col]+"  ");
            }
            System.out.println();
        }
    }
    
    public static class indexDistance{
        int row=0;
        int col=0;
        int distance=0;
        indexDistance(int row, int col, int distance){
            this.row=row;
            this.col=col;
            this.distance=distance;
        }
    }

    public static void DFSWallsAndGates(int[][]arr, ArrayList<Integer>index, int[][]vis){
        int level=1;
        LinkedList<indexDistance> queue= new LinkedList<>();
        int[][]dir=new int[4][2];
        dir[0]=new int[]{0,-1};
        dir[1]=new int[]{0,1};
        dir[2]=new int[]{-1,0};
        dir[3]=new int[]{1,0};

        for(int value: index){
            int row= value/arr[0].length;
            int col= value%arr[0].length;
            // System.out.println("row: "+row + "    col: "+col);
            queue.addLast(new indexDistance(row,col,0));
            // System.out.println("queue: "+queue.getFirst().row+",   " + queue.getFirst().col +".   +" + queue.getFirst().distance);
        }

        while(queue.size()!=0){
            int size=queue.size();
            while(size-->0){
                indexDistance value= queue.removeFirst();
                int row= value.row;
                int col= value.col;
                int distance= value.distance;

                vis[row][col]=1;
                arr[row][col]=distance;

                for(int i=0;i<4;i++){
                    int nrow= row+dir[i][0];
                    int ncol= col+dir[i][1];
                    if(nrow>=0 && ncol>=0 && nrow<arr.length && ncol<arr[0].length && vis[nrow][ncol]==0 && arr[nrow][ncol]==1000000){
                        vis[nrow][ncol]=1;
                        queue.addLast(new indexDistance(nrow,ncol,level));
                    }
                }
            }
            level++;
        }
    }


    public static void main(String[]args){
        constructGraph();
        removeEdge(2,5);
        // removeEdge(3,4);
        // removeVertex(3);
        // displayGraph(); 
        // display values= new display();
        // int res= allSolution(0,6,new boolean[graph.length], "0", 0, values, 20);
        // System.out.println(res);
        //Remember:
        // Primitive data type(int) like heaviest, lightest, etc. cannot be used as variable because their value is passed by value in java always.
        // this holds true for primitive data types
        //whereas for non-primitive data types, values are passed by reference.
        // System.out.println("Ceiling: "+values.ceil + ",        Floor:"+ values.floor);
        // System.out.println("Heaviest: "+ values.heaviest + ",        Lightest: "+ values.lightest);
        // int res= hamiltonianPath(2, "2", new int[graph.length], 1, 2);
        // System.out.println(res);
        char[][]board= new char[4][2];
        board[0]=new char[]{'X','X','X','X'};
        board[1]=new char[]{'X','0','0','X'};
        board[2]=new char[]{'X','0','0','X'};
        board[3]=new char[]{'X','0','X','X'};
        // surroundedRegions(board);
        // board[0]=new char[]{'X','X'};
        // board[1]=new char[]{'X','X'};
        // board[2]=new char[]{'X','X'};
        // board[3]=new char[]{'X','X'};
        // int res= getConnectedComponents();
        // System.out.println(res);
        // print2DArray(board);
        // BFS(0, new int[graph.length], 6);
        int[][]arr=new int[4][4];
        arr[0]=new int[]{1000000, -1, 0, 1000000};
        arr[1]= new int[]{1000000, 1000000, 1000000, -1};
        arr[2]= new int[]{1000000, -1, 1000000, -1};
        arr[3]= new int[]{0, -1, 1000000, 1000000};
        wallsAndGates(arr);
    }
}