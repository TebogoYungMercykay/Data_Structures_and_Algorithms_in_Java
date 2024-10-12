@SuppressWarnings("unchecked")
public class minDHeap<T extends Comparable<T>> {
    private int d;
    private T[] nodes;

    @Override
    public String toString() {
        if (nodes.length == 0) {
            return "";
        }

        return "[" + nodes[0] + "]\n" + toStringRec(0, "");
    }
    // Instead, you must create a Comparable[] and then cast this to a T[].•
    public String toStringRec(int i, String pre) {
        if (i >= nodes.length) {
            return "";
        }
        String res = "";
        for (int k = 0; k < d; k++) {
            int c = d * i + k + 1;
            if (c < nodes.length) {
                if (k == d - 1 || c + 1 >= nodes.length) {
                    res += pre + "└── " + "[" + nodes[c] + "]\n" + toStringRec(c, pre + "    ");
                } else {
                    res += pre + "├── " + "[" + nodes[c] + "]\n" + toStringRec(c, pre + "│   ");
                }
            }
        }
        return res;
    }

    public T[] getNodes() {
        return nodes;
    }

    /*
     * Don't change anything above this line
     */

    public minDHeap(int d) {
        this.d = d;
        nodes = (T[]) new Comparable[0];
    }

    public void resize_array(int newL) {
        if(newL <= 0) {
            minDHeap<T> heap = new minDHeap<>(d);
            nodes = heap.getNodes();
        }
        else {
            T[] copy = (T[]) new Comparable[newL];
            for (int i = 0; i < newL; copy[i] = null, i++);
            for (int i = 0; i < Math.min(nodes.length, newL); copy[i] = nodes[i], i++);
            nodes = copy;
        }
    }

    public void insert(T val) {
        if(d >= 1) {
            if (nodes == null || nodes.length == 0) {
                nodes = (T[]) new Comparable[nodes.length + 1];
                nodes[nodes.length - 1] = val;
            }
            else {
                if(nodes.length > 0) {
                    resize_array(nodes.length + 1);
                    nodes[nodes.length - 1] = val;
                    int index = nodes.length - 1;
                    while (index > 0 && nodes[index].compareTo(nodes[parent(index)]) < 0) {
                        T temp = nodes[index];
                        nodes[index] = nodes[parent(index)];
                        nodes[parent(index)] = temp;
                        index = parent(index);
                    }
                }
            }
        }
    }

    public void remove(T val) {
        int min_node = 0;
        int index = find(val);
        if (index == -1) {
            return;
        }
        else {
            nodes[index] = nodes[nodes.length - 1];
            nodes[nodes.length - 1] = null;
            resize_array(nodes.length - 1);
            while (true) {
                min_node = index * d + 1;
                if (min_node >= nodes.length) {
                    break;
                }
                for (int j = 2; j <= d; j++) {
                    if (index * d + j >= nodes.length) {
                        break;
                    }
                    if (nodes[index * d + j].compareTo(nodes[min_node]) < 0) {
                        min_node = index * d + j;
                    }
                }
                if (nodes[min_node].compareTo(nodes[index]) < 0) {
                    T temp = nodes[index];
                    nodes[index] = nodes[min_node];
                    nodes[min_node] = temp;
                    index = min_node;
                }
                else {
                    break;
                }
            }
        }
    }

    public void changeD(int newD) {
        for (int i = (nodes.length - 2)/newD; i >= 0; i--) {
            while (true) {
                int min_node = i * newD + 1;
                if (min_node >= nodes.length) {
                    break;
                }
                for (int j = 2; j <= newD; j++) {
                    if (i * newD + j >= nodes.length) {
                        break;
                    }
                    if (nodes[i * newD + j].compareTo(nodes[min_node]) < 0) {
                        min_node = i * newD + j;
                    }
                }
                if (nodes[min_node].compareTo(nodes[i]) < 0) {
                    T temp = nodes[i];
                    nodes[i] = nodes[min_node];
                    nodes[min_node] = temp;
                    i = min_node;
                }
                else {
                    break;
                }
            }
            d = newD;
        }
    }

    public boolean no_spouses(int i) {
        int[] spouses = spouses(i);
        int k = 0;
        for( ; k < spouses.length && spouses[k] == -1; k++);
        return (k == spouses.length);
    }

    public int[] spouses(int i) {
        int[] spouseArray = new int[d];
        for(int k = 0; k < d; k++) {
            spouseArray[k] = -1;
        }
        int st_spouse = d * i + 1;
        for (int j = 0; j < d; j++) {
            int index = st_spouse + j;
            if (index < nodes.length) {
                spouseArray[j] = index;
            }
        }
        return spouseArray;
    }

    public T min(int h) {
        if (h >= nodes.length || h < 0) {
            return null;
        }
        T min = nodes[h];
        int[] spouses = spouses(h);
        for (int spouse : spouses) {
            if (spouse < nodes.length && spouse != -1) {
                T smaller = min(spouse);
                if (smaller != null){
                    if (smaller.compareTo(min) < 0){
                        min = smaller;
                    }
                }
            }
        }
        return min;
    }

    public T max(int h)
    {
        if (h >= nodes.length || h < 0) {
            return null;
        }
        T max = nodes[h];
        for (int i = 1; i <= d; i++) {
            int spouse = d * h + i;
            if (spouse < nodes.length) {
                T larger = max(spouse);
                if (larger != null){
                    if (larger.compareTo(max) > 0){
                        max = larger;
                    }
                }
            }
        }
        return max;
    }

    public String pathToRoot(T val) {
        String temp = "";
        int index = find(val);
        if (index < 0 || index >= nodes.length) {
            return temp;
        }
        temp += "[" + (nodes[index]) +"]";
        while (index > 0) {
            index = parent(index);
            temp += "[" + (nodes[index]) +"]";
        }
        return temp;
    }

    public int parent(int i) {
        return (i - 1) / d;
    }

    public int find(T val) {
        if(nodes.length != 0 && nodes != null && nodes.length != 0) {
            for (int i = 0; i < nodes.length; i++) {
                if (val.equals(nodes[i]) == true) {
                    return i;
                }
            }
        }
        return -1;
    }
}