/*
    A node holds a value that is being indexed by a key. 
    All ValueNodes in the same linked list have the same key in the BPlusTree but refer to different rows in a database table
 */
public class ValueNode<TValue> {
	TValue value; /* The value that this node refers to. In this case: the row ID in a database table */
	ValueNode<TValue> next;

	public ValueNode(TValue rowId) {
		this.value = rowId;
		this.next = null;
	}

}
