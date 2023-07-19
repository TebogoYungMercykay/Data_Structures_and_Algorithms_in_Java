abstract class Traverser<T extends Comparable<T>>{
    protected SelfOrderingList<T> list;

    public abstract String toString();

    public abstract boolean contains(T data);

    public abstract int size();

    public abstract SelfOrderingList<T> reverseList();

    public abstract Node<T> find(T data);

    public abstract Node<T> get(int pos);

    public abstract SelfOrderingList<T> clone(SelfOrderingList<T> otherList);

    public void setList(SelfOrderingList<T> otherList){
        //TODO: Implement the function
        this.list = otherList;
    }
}
