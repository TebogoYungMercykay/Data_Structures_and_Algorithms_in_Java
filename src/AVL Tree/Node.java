public class Node<T extends Comparable<T>> {
   public T data;
   public int height;
   public Node<T> left;
   public Node<T> right;

    /** Used to indicate whether the right / left pointer is a normal
    pointer or a pointer to inorder successor.
     **/
    boolean rightThread;
    boolean leftThread;

    public Node(T item) {
        data = item;
        left = right = null;
    }
}
