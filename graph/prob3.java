public class prob3{

    public static int[]size;
    public static int[]parent;

    // public static int mergeSet(int vertex1, int vertex2){
    //     if(size[vertex1]<=size[vertex2]){
    //         parent[vertex1]=vertex2;
    //         size[vertex2]+=size[vertex1];
    //         return vertex2;
    //     }
    //     else{
    //         parent[vertex2]=vertex1;
    //         size[vertex1]+=size[vertex2];
    //         return vertex1;
    //     }

    // }

    public static int findParent(int vertex){
        if(parent[vertex]==vertex)
        return vertex;

        return parent[vertex]= findParent(parent[vertex]);
    }

    public static void construct(){
        size=new int[11];
        parent= new int[11];
        parent[1]=1;
        parent[2]=1;
        parent[9]=2;
        parent[10]=1;
        parent[3]=1;
        parent[6]=3;
        parent[4]=3;
        parent[8]=4;
        parent[5]=4;

        size[1]=4;
        size[2]=1;
        size[3]=2;
        size[4]=2;
    }

    public static void printParent(){
        for(int index=0;index<parent.length;index++){
            System.out.print(parent[index]+" ");
        }
        System.out.println();
    }


    //leetcode: 684 Redundant Connection

    public int[] findRedundantConnection(int[][] edges) {
        int index=-1;
        for(int i=0;i<edges.length;i++){
            index=Math.max(index, edges[i][0]);
            index=Math.max(index, edges[i][1]);
        }
        index++;
        int[]ans=new int[]{-1,-1};

        int[]size=new int[index];
        int[]parent= new int[index];

        for(int i=0;i<index;i++){
            parent[i]=i;
            size[i]=1;
        }    

        for(int i=0;i<index;i++){
            int e1= edges[i][0];
            int e2= edges[i][1];
            int p1=findParent(e1, size, parent);
            int p2= findParent(e2, size, parent);

            if(p1==p2){
                ans[0]=e1;
                ans[1]=e2;
                return ans;
            }
            mergeSet(p1,p2, size, parent);
        }
        return ans;
    }

    public int findParent(int vertex, int[]size, int[]parent){
        if(parent[vertex]==vertex)
        return vertex;

        return parent[vertex]= findParent(parent[vertex], size, parent);
    }

    public int mergeSet(int s1 , int s2, int[]size, int[]parent){
        if(size[s1]>=size[s2]){
            size[s1]+=size[s2];
            parent[s2]=s1;
            return s1;
        }
        else{
            size[s2]+=size[s1];
            parent[s1]=s2;
            return s2;
        }
    }

    //leetcode 547: Number of Provinces
    public int findCircleNum(int[][] isConnected) {
        int maxVertices= isConnected.length;
        size= new int[maxVertices];
        parent= new int[maxVertices];

        for(int i=0;i<maxVertices;i++){
            size[i]=1;
            parent[i]=i;
        }

        for(int i=0;i<maxVertices;i++){
            int vertex1= i;
            for(int j=i+1;j<isConnected[i].length;j++){

                int vertex2= j;
                if(isConnected[i][j]==0 || i==j)
                continue;

                int p1= findPar(vertex1);
                int p2= findPar(vertex2);
                System.out.println("vertices:  "+i+"  " +j);
                mergeSet(p1,p2);
            }
        }
        for(int i=0;i<maxVertices;i++){
            findParent(i);
        }
        int ans=0;
        for(int i=0;i<size.length;i++){
            if(parent[i]==i)
            ans++;
        }

        return ans;
    }
    public int findPar(int vertex){
        if(parent[vertex]==vertex)
        return vertex;

        return parent[vertex]=findPar(parent[vertex]);
    }
    public void mergeSet(int s1, int s2){
        System.out.println("size of set 1: "+size[s1]+ "    size of set 2: "+size[s2]);
        if(size[s1]>=size[s2]){
            size[s1]+=size[s2];
            parent[s2]=s1;
        }
        else{
            size[s2]+=size[s1];
            parent[s1]=s2;
        }
    }

    // 1061. Lexicographically Smallest Equivalent String
        public char []par;
     public String smallestEquivalentString(String s1, String s2, String baseStr) {
        // size= new int[s1.length()];
        par= new char[26];

        for(int index=0; index<par.length; index++){
            // size[index]= 1;
            par[index]= (char)('a'+index);
        }

        for(int index=0; index<s1.length(); index++){
            char string1ch= s1.charAt(index);
            char string2ch= s2.charAt(index);

            char string1Parent= findP(string1ch);
            char string2Parent= findP(string2ch);

            mergeS(string1Parent, string2Parent);
        }

        for(int i=0;i<26;i++){
            System.out.print(par[i]+" ");
        }

            String ans="";
            for(int index=0;index<baseStr.length();index++){
                char ch= baseStr.charAt(index);
                ans+=findP(ch);
            }
        return ans;
    }

    public char findP(char str){
        if(par[str-'a']==str)
        return str;

        return par[str-'a']=findP(par[str-'a']);
    }
    public void mergeS(char p1, char p2){
        if(p1<=p2){
            par[p2-'a']=p1;
        }
        else{
            par[p1-'a']=p2;
        }
    }



    // 200. Number of Islands by Union Find

    int[][]size;
    int[][]parent;
    public int numIslands(char[][] grid) {
        size=new int[grid.length][grid[0].length];
        parent= new int[grid.length][grid[0].length];

        for(int i=0;i<grid.length;i++){
            for(int j=0;j< grid[0].length;j++){
                if(grid[i][j]=='0')
                continue; 

                size[i][j]=1;
                parent[i][j]=i*grid[0].length + j;
            }
        }

        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                

                if(j+1<grid[0].length && grid[i][j+1]=='1' && grid[i][j]=='1'){
                    int parent1= findParent(parent[i][j], grid);
                    int parent2= findParent(parent[i][j+1], grid);
                    mergeSet(parent1, parent2, grid);
                    System.out.println("j+1: "+parent2);
                }

                if(i+1<grid.length && grid[i+1][j]=='1' && grid[i][j]=='1'){
                    int parent1= findParent(parent[i][j], grid);
                    int parent2= findParent(parent[i+1][j], grid);
                    mergeSet(parent1, parent2, grid);
                    System.out.println("i+1: "+parent2);
                }
            }
        }

        int count=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    // parent[i][j]=findParent(parent[i][j], grid);
                    if(parent[i][j]==(i*grid[0].length + j))
                    count++;
                }
                System.out.print(parent[i][j]+"  ");
            }
            System.out.println();
        }
        return count;
    }
    public int findParent(int index, char[][]grid){
        if(parent[index/grid[0].length][index%grid[0].length]==index)
        return index;

        return parent[index/grid[0].length][index%grid[0].length]= findParent(parent[index/grid[0].length][index%grid[0].length], grid);
    }
    public void mergeSet(int s1, int s2, char[][]grid){
        int n=grid.length, m=grid[0].length;
        if(size[s1/m][s1%m]>=size[s2/m][s2%m]){
            size[s1/m][s1%m]+=size[s2/m][s2%m];
            parent[s2/m][s2%m]=s1;
            System.out.println("mergeSet: "+s1);
        }
        else{
             size[s2/m][s2%m]+=size[s1/m][s1%m];
            parent[s1/m][s1%m]=s2;
            System.out.println("mergeSet: "+s2);
        }
    }


    //839. Similar String Groups

    public int numSimilarGroups(String[] strs) {
        int[]parent=new int[strs.length];
        int[]size=new int[strs.length];
        for(int i=0;i<strs.length;i++){
            parent[i]=i;
            size[i]=1;
        }

        for(int i=0;i<strs.length;i++){
            for(int j=i+1;j<strs.length;j++){
                String a= strs[i];
                String b= strs[j];

                int p1=findParent(i, parent);
                int p2=findParent(j, parent);

                if(isSimilar(a,b)){
                    size[p1]+=size[p2];
                    parent[p2]=p1;
                }
            }
        }

        int count=0;
        for(int i=0;i<strs.length;i++){
            if(parent[i]==i)
            count++;
        }
        return count;
    }
    public int findParent(int i, int[]parent){
        if(parent[i]==i)
        return i;

        return parent[i]=findParent(parent[i], parent);
    }
    public boolean isSimilar(String s1, String s2){
        int count=0;
        for(int i=0;i<s1.length();i++){
            if(s1.charAt(i)!=s2.charAt(i)){
                count++;
            }
        }
        if(count>2)
        return false;
        return true;
    }

    public static void main(String[]args){
        construct();
        // printParent();
        findParent(5);
        printParent();
    }
}