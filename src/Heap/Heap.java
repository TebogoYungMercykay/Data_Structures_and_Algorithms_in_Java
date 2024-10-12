@SuppressWarnings("unchecked")
public abstract class Heap<T extends Comparable<T>> {

    int capacity;
    Comparable<T> mH[];
    int currentSize;

    public Heap(int capacity) {
        this.capacity = capacity;
        mH = new Comparable[capacity+1];
        currentSize = 0;
    }

    protected int getCapacity(){
        return capacity;
    }

    protected int getCurrentSize(){
        return currentSize;
    }

    public void display() {
        for(int i = 1; i <= currentSize; i++) {
            System.out.print(mH[i] + " ");
        }
        System.out.println("");
    }

    protected boolean isEmpty() {
        return (getCurrentSize()==0);
    }

    public abstract void insert(T elem);
}