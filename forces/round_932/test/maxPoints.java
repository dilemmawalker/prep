public class maxPoints{

    public static getPointsValue(ArrayList<ArrayList<Integer>> points){
        int n=points.size();

        // already sorted; 

        

        int max=0;

        for(int index=0; index<n; index++){ //finding max length required
            ArrayList<Integer> temp= points.get(index);

            int start = temp.get(0);
            int end = temp.get(1);

            int max=Math.max(max, end);

        }

        //max containing the last end point;

        int[] count= new int[max+1]; // construing array

        for(int index=1; index<=n; index++){ // overall update for all arraylist elements
            ArrayList<Integer> temp= points.get(index);

            int start = temp.get(0);
            int end = temp.get(1);

            while(start<=end){ // try to simplify    // start to end update array count
                count[start]++;
            }
        }

        int maximum=0;

        for(int index=1; index<=max; index++){ //getting max value from all points.
            maximum= Math.max(maximum, count[index]);
        }

        System.out.println(maximum);
    }

    public static void main(String[]args){
        ArrayList<ArrayList<Integer>> points= new ArrayList<>();
        // data add;

        getPointsValue(points);
    }
    
}