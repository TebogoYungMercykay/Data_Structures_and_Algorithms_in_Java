@SuppressWarnings("unchecked")
class BTreeNode<T extends Comparable<T>> {
	boolean leaf;
	int keyTally;
	Comparable<T> keys[];
	BTreeNode<T> references[];
	int m;
	static int level = 0;
	// Constructor for BTreeNode class
	public BTreeNode(int order, boolean leaf1){
		m = order;
		leaf = leaf1;
		keys =  new Comparable[2*m-1];
		references = new BTreeNode[2*m];
		keyTally = 0;
	}

	public void print(){
		level++;
		if (this != null) {
			System.out.print("Level " + level + " ");
			this.printKeys();
			System.out.println();
        	if (!this.leaf){	
				for (int j = 0; j < 2*m; j++){
					if (this.references[j] != null)
					this.references[j].print();
    			}
			}
		}
		level--;
	}
	private void printKeys()
	{
		System.out.print("[");
    		for (int i = 0; i < this.keyTally; i++){
        		System.out.print(" " + this.keys[i]);
    		}
 		System.out.print("]");
	}
	public String toString() {
		String out = "[";
		for (int i = 1; i <= (this.keyTally-1); i++)	out += keys[i-1] + ",";
		out += keys[keyTally-1] + "] ";
		return out;
	}
	public BTreeNode<T> insert(T key){
        BTreeNode<T> root = this; 
		if (root.keyTally == 2*m-1) 
		{ 
			BTreeNode<T> s = new BTreeNode<T>(m, false);
			s.references[0] = root;
			s.splitChild(0, root);
			int i = 0; 
			if (s.keys[0].compareTo(key) < 0) 
				i++; 
			s.references[i].insertNonFull(key);
			root = s; 
		} 
		else  
			root.insertNonFull(key); 
			 
		return root;
	}
    public BTreeNode<T> search(T key)
    {  
		int i = 0; 
		while (i < keyTally && keys[i].compareTo((key)) < 0) 
			i++; 
		if (i != keyTally)
		if (keys[i] == key) 
			return this; 
		if (leaf == true) 
			return null;
		return references[i].search(key); 
	}
	public void traverse()
	{
		int i=0;
		for (i = 0; i < keyTally; i++)
		   {
			if(leaf == false)
				references[i].traverse();
		   System.out.print(keys[i]);
			
		   }
		if(leaf == false)
			references[i].traverse();
	}
	void insertNonFull(T k) { 
    int i = keyTally-1; 
    if (leaf == true) 
    { 
        while (i >= 0 && keys[i].compareTo(k) > 0) 
        { 
            keys[i+1] = keys[i]; 
            i--; 
        } 
        keys[i+1] = k; 
        keyTally = keyTally+1; 
    } 
    else 
    { 
        while (i >= 0 && keys[i].compareTo(k) > 0) 
            i--; 
        if (references[i+1].keyTally == 2*m-1) 
        { 
            splitChild(i+1, references[i+1]);
            if (keys[i+1].compareTo(k) < 0) 
                i++; 
        } 
        references[i+1].insertNonFull(k); 
    } 
	} 
	void splitChild(int i, BTreeNode<T> y) 
	{ 
		BTreeNode<T> z = new BTreeNode<T>(y.m, y.leaf); 
		z.keyTally = m - 1; 
		for (int j = 0; j < m-1; j++) 
			z.keys[j] = y.keys[j+m]; 
		if (y.leaf == false) 
		{ 
			for (int j = 0; j < m; j++) 
			{
				z.references[j] = y.references[j+m]; 
				y.references[j+m] = null;
			}
		} 
		y.keyTally = m - 1; 
		for (int j = keyTally ; j >= i+1; j--) 
			references[j+1] = references[j]; 
		references[i+1] = z;
		for (int j = keyTally-1; j >= i; j--) 
			keys[j+1] = keys[j]; 
		keys[i] = y.keys[m-1]; 
		keyTally = keyTally + 1;
	} 
}