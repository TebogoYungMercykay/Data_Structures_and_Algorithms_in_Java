class BTree<T extends Comparable<T>> {
	BTreeNode<T> root;
    	int m;
    	BTree(int order)
    	{  
		root = null; 
		m = order; 
	}
    	public void print()
    	{  
		if (root != null) 
		{ 
			root.print();
			System.out.println();
		} 
	}
	public void insert(T key)
	{
    		if (root == null)
    		{
        		root = new BTreeNode<T>(m, true);
        		root.keys[0] = key;
        		root.keyTally = 1;
    		}
    		else 
    		{
        		root = root.insert(key);
    		}
	}
    public BTreeNode<T> search(T key){  
		if (root != null) 
			return root.search(key);
		else
			return null;
	}
    public void traverse(){  
		if (root != null) 
		{ 
			root.traverse();
			System.out.println();
		} 
	}
}