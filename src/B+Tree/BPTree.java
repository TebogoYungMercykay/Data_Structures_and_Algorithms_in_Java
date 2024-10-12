/**
 * Class for a B+ tree
 * Since the structures and behaviours between internal nodes and leaf nodes are different, 
 * there are different classes for each kind of node. However, both classes share attributes in
 * a common parent node class.
 * @param <TKey> the data type of the key
 * @param <TValue> the data type of the value
 */
public class BPTree<TKey extends Comparable<TKey>, TValue> {
	
	private BPTreeNode<TKey, TValue> root;
	private int debug;
	
	public BPTree(int order) {
		this.root = new BPTreeLeafNode<TKey, TValue>(order);
		this.debug = 0;
	}


	public String debug()
    	{  
		String res = "";
		if (root != null) 
		{ 
			res = res + debug;
		}
		return res;
	}


	/**
	 * Print all keys of the B+ tree
	 */
    	public void print()
    	{  
		if (root != null) 
		{ 
			root.print(root);
			System.out.println();
		}
	}


	/**
	 * Insert a new key and its associated value into the B+ tree.
	 */
	public void insert(TKey key, TValue value) {
		
		if (root != null) 
		{ 
			root = root.insert(key, value);
		}
	}	
	

	/**
	 * Search a key value on the B+ tree and return its associated value.
	 */
	public TValue search(TKey key) {
		if (root != null) 
		{ 
			debug++;
			return root.search(key);
		}
		else
			return null;
	}
	

	/**
	 * Delete a key and its associated value from the B+ tree.
	 */
	public void delete(TKey key) {

		if (root != null) 
		{ 
			root = root.delete(key);
		}
	}


	/**
	 * Return all associated key values on the B+ tree in ascending key order.
	 */
	public TValue[] values() {
		if (root != null) 
		{ 
			return root.values();
		}
		else {
			return null;
		}
	}	

}
