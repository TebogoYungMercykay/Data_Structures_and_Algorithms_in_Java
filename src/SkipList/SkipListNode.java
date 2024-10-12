// This is used to remove the warnings which occur when using generics
@SuppressWarnings("unchecked")
// SkipListNode class
public class SkipListNode<T extends Comparable<T>> {

    public T key;
    public SkipListNode<T>[] next;

    public SkipListNode(T key, int levels){
        this.key = key;
        this.next = new SkipListNode[levels];
        for(int i_counter = 0; i_counter < levels; i_counter++){
            next[i_counter] = null;
        }
    }

    @Override
    public String toString(){
        return "[" + this.key.toString() + "]";
    }

    public String emptyString(){
        String temp_string = "";
        int temp_length = this.toString().length();
        for(int i_counter = 0; i_counter < temp_length; i_counter++){
            temp_string += "-";
        }
        return temp_string;
    }
}