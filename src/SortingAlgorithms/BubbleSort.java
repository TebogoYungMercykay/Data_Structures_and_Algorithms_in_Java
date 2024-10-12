public class BubbleSort extends Sorting {
        public BubbleSort(){
                this.name = "BubbleSort";
        }

        @Override
        public String[] sortAcs(Vertex[] array) {
                for(int i_iterator=0,n_iterator = array.length; i_iterator <= n_iterator-2; i_iterator+=1){  
                        for(int j_iterator=n_iterator-1; j_iterator >= i_iterator+1; j_iterator-=1){  
                                if(array[j_iterator].getVName().compareTo(array[j_iterator-1].getVName())<0){
                                        Vertex temp = array[j_iterator];  
                                        array[j_iterator] = array[j_iterator-1];  
                                        array[j_iterator-1] = temp;  
                                }  
                                
                        } 
                        printArr(vertexToString(array));
                }
                return vertexToString(array);
        }
        @Override
        public String[] sortDsc(Vertex[] array) {
                for(int i_iterator=0,n_iterator = array.length; i_iterator <= n_iterator-2; i_iterator+=1){  
                        for(int j_iterator=n_iterator-1; j_iterator >= i_iterator+1; j_iterator-=1){  
                                if(array[j_iterator].getVName().compareTo(array[j_iterator-1].getVName())>0){
                                        Vertex temp = array[j_iterator];  
                                        array[j_iterator] = array[j_iterator-1];  
                                        array[j_iterator-1] = temp;  
                                }  
                                
                        } 
                        printArr(vertexToString(array));
                }
                return vertexToString(array);
        }
}
