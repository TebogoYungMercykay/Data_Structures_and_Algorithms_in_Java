public class Node<T extends Comparable<T>>{
    public Node<T> next;
    public Node<T> prev;
    public int accessCount;
    public T data;

    public Node(T data){
        //TODO: Implement the function
        this.data = data;
        this.next = null;
        this.prev = null;
        this.accessCount = 0;
    }

    @Override
    public String toString() {
        return "(" + data.toString() + "[" + accessCount + "]" + ")";
    }
}
