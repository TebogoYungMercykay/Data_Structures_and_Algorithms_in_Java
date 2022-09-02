public class Edge {
    public Vertex pointA;
    public Vertex pointB;
    private float value;
    private String eName;
    public Edge(float value, String eName){
        this.value = value;
        this.eName = eName;
    }
    public float getValue(){
        return value;
    }
    public String getEName(){
        return eName;
    }
    public String toString(){
        String res = "";
        if(pointA == null)
            res += "[]";
        else 
            res += "[" + pointA.getVName() + "]";

        res += "={" + value + "}=";

        if(pointB == null)
            res += "[]";
        else 
            res += "[" + pointB.getVName() + "]";
        
        return res;
    } 
}
