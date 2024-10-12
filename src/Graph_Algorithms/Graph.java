public class Graph {
    public Vertex[] vertices = new Vertex[0];
    public Edge[] edges = new Edge[0];

    public int[][] unionFind()
    {
        int[][] properties = new int[4][vertices.length];
        setup(properties);
        int indexRt = 0;
        int exchange;

        for (Edge edge : edges)
        {
            int vertexV = IndexOfVertexInArray(edge.vertexA);
            int vertexU = IndexOfVertexInArray(edge.vertexB);

            if (properties[0][vertexU]  ==  properties[0][vertexV])
            {
                for (int iterator = 0; iterator < vertices.length; iterator += 1)
                {
                    properties[3][iterator] = 1;
                }
            }
            else if (properties[2][properties[0][vertexV]] < properties[2][properties[0][vertexU]])
            {
                indexRt = properties[0][vertexV];
                properties[2][properties[0][vertexU]] += properties[2][indexRt];
                properties[0][indexRt] = properties[0][vertexU];

                for (int j = properties[1][indexRt]; j != indexRt; j = properties[1][j])
                {
                    properties[0][j] = properties[0][vertexU];
                }

                exchange = properties[1][properties[0][vertexU]];
                properties[1][properties[0][vertexU]] = properties[1][indexRt];
                properties[1][indexRt] = exchange;
            }
            else
            {
                exchange = vertexU;
                vertexU = vertexV;
                vertexV = exchange;
                indexRt = properties[0][vertexV];
                properties[2][properties[0][vertexU]] += properties[2][indexRt];
                properties[0][indexRt] = properties[0][vertexU];

                for (int j = properties[1][indexRt]; j != indexRt; j = properties[1][j])
                {
                    properties[0][j] = properties[0][vertexU];
                }

                exchange = properties[1][properties[0][vertexU]];
                properties[1][properties[0][vertexU]] = properties[1][indexRt];
                properties[1][indexRt] = exchange;
            }
        }
        return properties;
    }

    public boolean cycle() {
        if(edges.length  ==  0 || vertices.length  ==  0)
        {
            return false;
        }
        return unionFind()[3][0]  ==  1;
    }

    public void addVertex(String v)
    {
        if (searchForVertex(v) != null)
        {
            return;
        }

        Vertex[] arr = new Vertex[vertices.length + 1];
        int ctrl = 0;
        Vertex alt = new Vertex(v);

        for (; ctrl < vertices.length; ctrl += 1)
        {
            if (alt.compareTo(vertices[ctrl]) < 0)
            {
                break;
            }
        }

        int insertionIndex = ctrl;

        for (int i = 0, vertex = 0; i < arr.length; i += 1)
        {
            if (i != insertionIndex)
            {
                if (vertex >= vertices.length)
                {
                    break;
                }
                arr[i] = vertices[vertex++];
            }
            else
            {
                arr[i] = new Vertex(v);
            }
        }

        vertices = arr;
    }

    public void removeVertex(String v) {
        if(searchForVertex(v) == null){
            return;
        }
        Vertex vww = new Vertex(v);
        int skip = IndexOfVertexInArray(vww);
        vertices = HelperShrink(skip);
    }

    public Vertex[] HelperShrink(int skip)
    {
        Vertex[] tempp = new Vertex[vertices.length - 1];
        int vertex = 0;
        for(int vv = 0; vv < tempp.length; vv += 1)
        {
            if(vv == skip)
            {
                tempp[vv] = vertices[++vertex];
            }
            else
            {
                tempp[vv] = vertices[vertex];
            }
            vertex += 1;
        }
        return tempp;
    }

    public Graph minSpanningTree()
    {
        if (vertices.length  ==  0)
        {
            return new Graph();
        }
        if (edges.length  ==  0)
        {
            return this;
        }

        Graph spanningTree = new Graph();
        Graph temp = new Graph();

        for (Vertex vertex : vertices)
        {
            spanningTree.addVertex(vertex.name);
            temp.addVertex(vertex.name);
        }

        for (Edge edge : edges)
        {
            temp.addEdge(true, edge.vertexA.name, edge.vertexB.name, edge.weight);
        }

        Edge[] sorted = temp.edges;
        for (Edge edge : sorted)
        {
            spanningTree.addEdge(edge.vertexA.name, edge.vertexB.name, edge.weight);
            boolean h = false;

            if (spanningTree.edges.length  ==  0 || spanningTree.vertices.length  ==  0)
            {
                h = false;
            }
            else
            {
                h = (spanningTree.unionFind()[3][0]  ==  1);
            }
            if (h)
            {
                spanningTree.removeEdge(edge.vertexA.name, edge.vertexB.name);
            }
        }
        return spanningTree;
    }

    public int CountEdges(Vertex vertex)
    {
        if (searchForVertex(vertex.name)  ==  null)
        {
            return 0;
        }
        int count = 0;
        for (Edge edge : edges)
        {
            if (IsEqual(vertex, edge.vertexA) || IsEqual(vertex, edge.vertexB))
            {
                count += 1;
            }
        }
        return count;
    }

    public Edge searchForEdge(Edge e)
    {
        for (Edge edge : edges)
        {
            if (e.compareTo(edge)  ==  0)
            {
                return edge;
            }
        }
        return null;
    }

    public Vertex searchForVertex(String VN)
    {
        for (Vertex vertex : vertices)
        {
            if (IsEqual(vertex, new Vertex(VN)))
            {
                return vertex;
            }
        }
        return null;
    }

    public int IndexOfVertexInArray(Vertex alt)
    {
        int ctrl = 0;
        for (Vertex vertex : vertices)
        {
            if (IsEqual(alt, vertex))
            {
                return ctrl;
            }
            ctrl += 1;
        }
        return -1;
    }

    // TODO: This IS A HELPER FUNCTION.
    public boolean IsEqual(Vertex alt, Vertex v2)
    {
        if (alt.toString().equals(v2.toString()))
        {
            return true;
        }
        else
        {
            if (alt.name.equals(v2.name))
            {
                return true;
            }
            else
            {
                if (alt.compareTo(v2)  ==  0)
                {
                    return true;
                }
                else
                {
                    if (alt.name.contains(v2.name) || v2.name.contains(alt.name))
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
            }
        }
    }

    public void addEdge(String a, String b, int w)
    {
        Edge edge1 = new Edge(new Vertex(a), new Vertex(b), w);
        Edge edge2 = new Edge(new Vertex(b), new Vertex(a), w);

        if (searchForEdge(edge1) != null || searchForEdge(edge2) != null || searchForVertex(a)  ==  null || searchForVertex(b)  ==  null)
        {
            return;
        }

        Edge[] arr = new Edge[edges.length + 1];
        int ctrl = 0;

        for (Edge edge : edges)
        {
            if (edge1.compareTo(edge) < 0)
            {
                break;
            }
            ctrl += 1;
        }

        int insertionIndex = ctrl;
        arr[insertionIndex] = edge1;
        int i = 0;
        int edge = 0;

        for (; i < arr.length; i += 1)
        {
            if (i != insertionIndex)
            {
                if (edge >= edges.length)
                {
                    break;
                }
                arr[i] = edges[edge++];
            }
        }

        edges = arr;
    }

    public void removeEdge(String a, String b)
    {
        Edge edge1 = new Edge(new Vertex(b), new Vertex(a), 0);
        Edge edge2 = new Edge(new Vertex(a), new Vertex(b), 0);

        if (searchForEdge(edge1)  ==  null && searchForEdge(edge2)  ==  null)
        {
            return;
        }
        if (searchForVertex(b)  ==  null || searchForVertex(a)  ==  null)
        {
            return;
        }

        int ctrl = 0;
        boolean found = false;

        for (Edge edge : edges)
        {
            if (edge.compareTo(edge1)  ==  0)
            {
                found = true;
                break;
            }
            ctrl += 1;
        }

        if(found  ==  false)
        {
            ctrl = -1;
        }
        if (ctrl  ==  -1)
        {
            ctrl = 0;
            found = false;
            for (Edge edge : edges)
            {
                if (edge.compareTo(edge2)  ==  0)
                {
                    found = true;
                    break;
                }
                ctrl += 1;
            }

            if(found  ==  false)
            {
                ctrl = -1;
            }
        }
        if (ctrl != -1)
        {
            Edge[] Earr = new Edge[edges.length - 1];
            int edge = 0;
            int error = 0;

            for (; error < ctrl; error += 1, edge += 1)
            {
                Earr[error] = edges[edge];
            }

            for (; error < Earr.length; error += 1, edge += 1)
            {
                Earr[error] = edges[edge + 1];
            }
            edges = Earr;
        }
    }

    // TODO: This IS A HELPER FUNCTION.
    public void addEdge(Boolean add, String a, String b, int w)
    {
        Edge edge1 = new Edge(new Vertex(a), new Vertex(b), w);
        Edge edge2 = new Edge(new Vertex(b), new Vertex(a), w);

        if (searchForEdge(edge1) != null || searchForEdge(edge2) != null || searchForVertex(a)  ==  null || searchForVertex(b)  ==  null)
        {
            return;
        }

        Edge[] arr = new Edge[edges.length + 1];
        int ctrl = 0;

        for (Edge edge : edges)
        {
            if (edge1.weight < edge.weight)
            {
                break;
            }
            ctrl += 1;
        }

        int Insertion = ctrl;
        arr[Insertion] = edge1;
        int edge = 0;

        for (int i = 0; i < arr.length; i += 1)
        {
            if (i != Insertion)
            {
                if (edge >= edges.length)
                {
                    break;
                }
                arr[i] = edges[edge++];
            }
        }
        edges = arr;
    }

    public void setup(int[][] arr)
    {
        for (int iterator = 0; iterator < vertices.length; iterator += 1)
        {
            arr[0][iterator] = iterator;
            arr[1][iterator] = iterator;
            arr[3][iterator] = 0;
            arr[2][iterator] = 1;
        }
    }

    public int FindColour(Boolean add, int[][] hold, Vertex v)
    {
        int ne = vertices.length + 5;
        for (Edge edge : edges)
        {
            if (edge.vertexA.equals(v) || IsEqual(v, edge.vertexA))
            {
                int nIndex = IndexOfVertexInArray(edge.vertexB);
                if (hold[nIndex][2] < ne)
                {
                    ne = hold[nIndex][2];
                }
            }
            else if (edge.vertexB.equals(v) || IsEqual(v, edge.vertexB))
            {
                int nIndex = IndexOfVertexInArray(edge.vertexA);
                if (hold[nIndex][2] < ne)
                {
                    ne = hold[nIndex][2];
                }
            }
        }
        if (ne > 0)
        {
            return 0;
        }
        else
        {
            int c = 0;
            while (c < vertices.length)
            {
                boolean k = true;
                for (int ee = 0; ee < edges.length; ee += 1)
                {
                    if (edges[ee].vertexA.compareTo(v)  ==  0)
                    {
                        if (hold[IndexOfVertexInArray(edges[ee].vertexB)][2]  ==  c)
                        {
                            k = false;
                        }
                    }
                    else if (edges[ee].vertexB.compareTo(v)  ==  0)
                    {
                        if (hold[IndexOfVertexInArray(edges[ee].vertexA)][2]  ==  c)
                        {
                            k = false;
                        }
                    }
                }
                if (k)
                {
                    return c;
                }
                c += 1;
            }
        }
        return -1;
    }

    public Vertex[][] brelazColouring()
    {
        if (vertices.length  ==  1)
        {
            Vertex[][] one = new Vertex[1][1];
            one[0][0] = vertices[0];
            return one;
        }
        if (vertices.length  ==  0)
        {
            return new Vertex[0][0];
        }

        int[][] hold = new int[vertices.length][5];
        initializeHold(hold);
        Vertex vertex;
        int colour;
        int ctrl = Integer.MIN_VALUE;

        for (int jj = 0; jj < vertices.length; jj += 1)
        {
            if (hold[jj][2]  ==  Integer.MIN_VALUE)
            {
                ctrl = jj;
            }
        }

        while (ctrl != Integer.MIN_VALUE)
        {
            int H_Degree = Integer.MIN_VALUE;
            Vertex ver = null;
            int largest = Integer.MIN_VALUE;

            for (int[] row : hold)
            {
                if (row[0] > largest)
                {
                    largest = row[0];
                }
            }

            int H = largest;
            for (int bb = 0; bb < vertices.length; bb += 1)
            {
                if (hold[bb][0]  ==  H && hold[bb][1] > H_Degree)
                {
                    H_Degree = hold[bb][1];
                    ver = vertices[bb];
                }
            }

            vertex = ver;
            colour = FindColour(true, hold, vertex);
            for (Edge edge : edges)
            {
                if (edge.vertexA.equals(vertex) && hold[IndexOfVertexInArray(edge.vertexB)][2]  ==  Integer.MIN_VALUE)
                {
                    hold[IndexOfVertexInArray(edge.vertexB)][0] += 1;
                    hold[IndexOfVertexInArray(edge.vertexB)][1]--;
                }
                else if (edge.vertexB.equals(vertex) && hold[IndexOfVertexInArray(edge.vertexA)][2]  ==  Integer.MIN_VALUE)
                {
                    hold[IndexOfVertexInArray(edge.vertexA)][0] += 1;
                    hold[IndexOfVertexInArray(edge.vertexA)][1]--;
                }
            }

            hold[IndexOfVertexInArray(vertex)][2] = colour;
            hold[IndexOfVertexInArray(vertex)][0] = Integer.MIN_VALUE;
            ctrl = Integer.MIN_VALUE;

            for (int jj = 0; jj < vertices.length; jj += 1)
            {
                if (hold[jj][2]  ==  Integer.MIN_VALUE)
                {
                    ctrl = jj;
                }
            }
        }
        return getArr(hold, true);
    }

    private void initializeHold(int[][] hold)
    {
        for (int hh = 0; hh < vertices.length; hh += 1)
        {
            hold[hh][0] = 0;
            hold[hh][1] = CountEdges(vertices[hh]);
            hold[hh][2] = Integer.MIN_VALUE;
            hold[hh][3] = Integer.MAX_VALUE;
            hold[hh][4] = Integer.SIZE;
        }
    }

    public Vertex[][] getArr(int[][] hold, boolean get)
    {
        if (!get)
        {
            return new Vertex[0][0];
        }
        int row = -1;
        for (int oo = 0; oo < vertices.length; oo += 1)
        {
            if (hold[oo][2] > row)
            {
                row = hold[oo][2];
            }
        }
        row = row + 1;
        if (row  ==  -1)
        {
            row = 0;
        }
        Vertex[][] arr = new Vertex[row][];
        for (int r = 0; r < row; r += 1)
        {
            int fg = 0, col = 0;
            for (int error = 0; error < vertices.length; error += 1)
            {
                if (hold[error][2]  ==  r)
                {
                    fg += 1;
                }
            }
            if (fg > vertices.length)
            {
                col = 0;
            }
            col = fg;
            arr[r] = new Vertex[col];

            for (int c = 0; c < col; c += 1)
            {
                int ctrl = -1;
                for (int mm = 0; mm < vertices.length; mm += 1)
                {
                    if (hold[mm][2]  ==  r)
                    {
                        hold[mm][2] = Integer.MAX_VALUE;
                        ctrl = mm;
                        break;
                    }
                }
                arr[r][c] = vertices[ctrl];
            }
        }
        return arr;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        if (vertices.length != 0)
        {
            for (Vertex vertex : vertices)
            {
                sb.append("\t").append(vertex.name);
            }
            sb.append("\n");
            for (int rr = 0; rr < vertices.length; rr += 1)
            {
                if (vertices[rr] != null)
                {
                    sb.append(vertices[rr].name);
                }
                for (int cc = 0; cc < vertices.length; cc += 1)
                {
                    Edge edge = searchForEdge(new Edge(vertices[rr], vertices[cc], cc));
                    if (edge != null)
                    {
                        sb.append("\t").append(edge.weight);
                    }
                    else
                    {
                        sb.append("\t0");
                    }
                }
                if (rr < vertices.length - 1)
                {
                    sb.append("\n");
                }
            }
        }
        return sb.toString();
    }
}