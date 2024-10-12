public class SelectionSort extends Sorting {
    
    public SelectionSort(){
        this.name = "SelectionSort";
    }

    @Override
    public String[] sortAcs(Vertex[] array) {
        for(int i_iterator=0,k_iterator,temp_var;i_iterator<array.length;i_iterator+=1) {
            for(k_iterator = i_iterator+1, temp_var=i_iterator;k_iterator<array.length;k_iterator+=1) {
                if(array[k_iterator].getVName().compareTo(array[temp_var].getVName())<0){
                    temp_var = k_iterator;
                }
            }
            Vertex temp_store=array[temp_var];
            array[temp_var]=array[i_iterator];
            array[i_iterator]=temp_store;
            printArr(vertexToString(array));
        }
        return vertexToString(array);
    }

    @Override
    public String[] sortDsc(Vertex[] array) {
        for(int i_iterator=0,k_iterator,temp_var;i_iterator<array.length;i_iterator+=1) {
            for(k_iterator = i_iterator+1, temp_var=i_iterator;k_iterator<array.length;k_iterator+=1) {
                if(array[k_iterator].getVName().compareTo(array[temp_var].getVName())>0){
                    temp_var = k_iterator;
                }
            }
            Vertex temp_store=array[temp_var];
            array[temp_var]=array[i_iterator];
            array[i_iterator]=temp_store;
            printArr(vertexToString(array));
        }
        return vertexToString(array);
    }
}
