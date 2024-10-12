public class Edge implements Comparable<Edge> {
    public Vertex vertexA, vertexB;
    public int weight;

    public Edge(Vertex a, Vertex b, int w) {
        weight = w;
        if (a.compareTo(b) < 0) {
            vertexA = a;
            vertexB = b;
        } else {
            vertexA = b;
            vertexB = a;
        }
    }

    @Override
    public int compareTo(Edge o) {
        int a = vertexA.compareTo(o.vertexA);
        if (a != 0) {
            return a;
        }
        return vertexB.compareTo(o.vertexB);
    }

    @Override
    public String toString() {
        return vertexA + "->" + vertexB + "{" + weight + "}";
    }
}
