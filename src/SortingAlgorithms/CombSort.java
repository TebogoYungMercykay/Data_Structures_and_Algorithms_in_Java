public class CombSort extends Sorting {
    public CombSort(){
        this.name = "CombSort";
    }
    @Override
    public String[] sortAcs(Vertex[] array){
        int n_iterator = array.length;
        int gap_iterator = n_iterator;
        boolean swapped = false;
        while (gap_iterator != 1 || swapped == true){
            swapped = false;
            if (gap_iterator>1){
                gap_iterator=(int)(gap_iterator/1.3);
            }
            for (int i_iterator=0; i_iterator+gap_iterator<n_iterator; i_iterator++){
                if (array[i_iterator].getVName().compareTo(array[i_iterator+gap_iterator].getVName())>0){
                    Vertex temp = array[i_iterator];
                    array[i_iterator] = array[i_iterator+gap_iterator];
                    array[i_iterator+gap_iterator] = temp;
                    swapped = true;
                }
            }
            printArr(vertexToString(array));
            System.out.println("Gap: "+gap_iterator);
        }
        return vertexToString(array);
    }
    @Override
    public String[] sortDsc(Vertex[] array) {
        int n_iterator = array.length;
        int gap_iterator = n_iterator;
        boolean swapped = false;
        while (gap_iterator != 1 || swapped == true){
            swapped = false;
            if (gap_iterator>1){
                gap_iterator=(int)(gap_iterator/1.3);
            }
            for (int i_iterator=0; i_iterator+gap_iterator<n_iterator; i_iterator++){
                if (array[i_iterator].getVName().compareTo(array[i_iterator+gap_iterator].getVName())<0){
                    Vertex temp = array[i_iterator];
                    array[i_iterator] = array[i_iterator+gap_iterator];
                    array[i_iterator+gap_iterator] = temp;
                    swapped = true;
                }
            }
            printArr(vertexToString(array));
            System.out.println("Gap: "+gap_iterator);
        }
        return vertexToString(array);
    }
}
