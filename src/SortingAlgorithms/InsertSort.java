public class InsertSort extends Sorting {

    public InsertSort(){
        this.name = "InsertSort";
    }

    @Override
    public String[] sortAcs(Vertex[] array) {
        for (int i_iterator=1,n_iterator = array.length; i_iterator<n_iterator; ++i_iterator){
            Vertex key=array[i_iterator];
            int j_iterator=i_iterator-1;
            while (j_iterator>=0 && array[j_iterator].getVName().compareTo(key.getVName())>0){
                array[j_iterator+1]=array[j_iterator];
                j_iterator=j_iterator-1;
            }
            array[j_iterator+1]=key;
            printArr(vertexToString(array));
        }
        return vertexToString(array);
    }

    @Override
    public String[] sortDsc(Vertex[] array) {
        for (int i_iterator=1,n_iterator = array.length; i_iterator<n_iterator; ++i_iterator){
            Vertex key=array[i_iterator];
            int j_iterator=i_iterator-1;
            while (j_iterator>=0 && array[j_iterator].getVName().compareTo(key.getVName())<0){
                array[j_iterator+1]=array[j_iterator];
                j_iterator=j_iterator-1;
            }
            array[j_iterator+1]=key;
            printArr(vertexToString(array));
        }
        return vertexToString(array);
    }
}
