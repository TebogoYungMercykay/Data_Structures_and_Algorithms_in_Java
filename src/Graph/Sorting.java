public abstract class Sorting {
    public String name = "Unknown";
    public abstract String[] sortAcs(Vertex[] array) throws CycleException; 
    public abstract String[] sortDsc(Vertex[] array) throws CycleException;
    protected String[] vertexToString(Vertex[] array){
        String[] res = new String[array.length];
        for(int i=0; i < res.length; i++)
        {
            res[i] = array[i].getVName();
        }
        return res;
    }

    protected void printArr(String[] arr)
    {
        String line = "";
        for(int i=0; i < arr.length; i++)
        {
            line += arr[i] + ";";
        }
        line = line.substring(0, line.length()-1);
        System.out.println(line);
    }
}
