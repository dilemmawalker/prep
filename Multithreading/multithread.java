public class multithread{
        public static void main(String[]args){
            // for(int i=0;i<10;i++){
                prob1 obj= new prob1(0,5); //making a new thread
                obj.start();

                try{
                    obj.join();
                }
                catch(Exception e){
                    System.out.println("error");
                }

                

                prob1 obj2= new prob1(10,15); //making another thread
                obj2.start();

                try{
                    obj2.join();
                }
                catch(Exception e){
                    System.out.println("error");
                }

            // }
        }
    }