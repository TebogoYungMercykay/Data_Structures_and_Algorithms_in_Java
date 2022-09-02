public class Graph {
    private Vertex[] vertices;
    public Graph(int numVertex){
        vertices = new Vertex[numVertex];
    }

    public boolean addVertex(String nName, int numVertices){
        int openPos = -1;
        for(int i=0; i < vertices.length; i++)
        {
            if(vertices[i] == null){
                if(openPos == -1)
                    openPos = i;
            } else {
                if(vertices[i].getVName().equals(nName)){
                    return false;
                }
            }
        }
        if(openPos == -1)
            return false;

        vertices[openPos] = new Vertex(nName, numVertices);
        return true;
    }

    public Vertex getVertex(String nName){
        for(int i=0; i < vertices.length; i++){
            if(vertices[i] != null && vertices[i].getVName().equals(nName)){
                return vertices[i];
            }
        }
        return null;
    }

    public boolean addEdge(String pointA, String pointB, float value, String vName){
        Vertex pA = getVertex(pointA);
        Vertex pB = getVertex(pointB);
        if(pA == null || pB == null)
            return false;

        Edge v = new Edge(value, vName);
        v.pointA = pA;
        v.pointB = pB;
        pA.addEdge(v);
        return true;
    }

    public void printSort(Sorting sortAlgo, Vertex[] vArray, boolean Acs) throws CycleException{
        System.out.println("Sorted using: " + sortAlgo.name);
        String[] sortedArray;
        if(Acs)
            sortedArray = sortAlgo.sortAcs(vArray);
        else 
            sortedArray = sortAlgo.sortDsc(vArray);
        for(int i=0; i < sortedArray.length; i++)
        {
            System.out.print(sortedArray[i] + "|");
        }
        System.out.println();
    }   

    public void printSort(Sorting sortAlgo, Graph graph, boolean Acs) throws CycleException{
        Graph g = graph.clone();
        printSort(sortAlgo, g.vertices, Acs);
    }

    public Graph clone(){
        Graph result = new Graph(vertices.length);
        for(int i=0; i < vertices.length; i++)
        {
            if(vertices[i] != null){
                result.addVertex(vertices[i].getVName(), vertices[i].getEdges().length);
            }
        }

        for(int i=0; i < vertices.length; i++)
        {
            if(vertices[i] != null){
                for(int j=0; j < vertices[i].getEdges().length; j++)
                {
                    if(vertices[i].getEdges()[j] != null && vertices[i].getEdges()[j].pointB!= null)
                    {
                        result.addEdge(vertices[i].getVName(), 
                        vertices[i].getEdges()[j].pointB.getVName(), 
                        vertices[i].getEdges()[j].getValue(), 
                        vertices[i].getEdges()[j].getEName());
                    }
                }
            }
        }

        return result;
    }
}
