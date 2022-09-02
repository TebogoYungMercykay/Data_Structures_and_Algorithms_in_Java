/**
 * A B+ tree generic node
 * Abstract class with common methods and data. Each kind of node implements this class.
 * @param <TKey> the data type of the key
 * @param <TValue> the data type of the value
 */
abstract class BPTreeNode<TKey extends Comparable<TKey>, TValue> {
	protected Object[] keys;
	protected int keyTally;
	protected int m;
	protected BPTreeNode<TKey, TValue> parentNode;
	protected BPTreeNode<TKey, TValue> leftSibling;
	protected BPTreeNode<TKey, TValue> rightSibling;
	protected static int level=0; // do not modify this variable's value as it is used for printing purposes. You can create another variable with a different name if you need to store the level.
	public BPTreeNode<TKey, TValue> root;
	private Object[] values;
	protected BPTreeNode(){
		this.keyTally=0;
		this.parentNode=null;
		this.leftSibling=null;
		this.rightSibling=null;
		this.values=new Object[m];
	}
	public int getKeyCount() 
	{
		return this.keyTally;
	}
	
	@SuppressWarnings("unchecked")
	public TKey getKey(int index) 
	{
		return (TKey)this.keys[index];
	}

	public void setKey(int index, TKey key) 
	{
		this.keys[index] = key;
	}

	public BPTreeNode<TKey, TValue> getParent() 
	{
		return this.parentNode;
	}

	public void setParent(BPTreeNode<TKey, TValue> parent) 
	{
		this.parentNode = parent;
	}	
	
	public abstract boolean isLeaf();
	/**
	 * Print all nodes in a subtree rooted with this node
	 */
	@SuppressWarnings("unchecked")
	public void print(BPTreeNode<TKey, TValue> node)
	{
		level++;
		if (node != null) {
			System.out.print("Level " + level + " ");
			node.printKeys();
			System.out.println();

			// If this node is not a leaf, then 
        		// print all the subtrees rooted with this node.
        		if (!node.isLeaf())
			{	BPTreeInnerNode inner = (BPTreeInnerNode<TKey, TValue>)node;
				for (int j = 0; j < (node.m); j++)
    				{
        				this.print((BPTreeNode<TKey, TValue>)inner.references[j]);
    				}
			}
		}
		level--;
	}
	// Xander's Code starts here
	protected int findWithinNode(TKey key)
	{
		if (key==null)
		{
			return -1;
		}
		for (int i=0;i<keyTally;i++)
		{
			if ((keys[i]).equals(key))
			{
				return i;
			}
		}
		return -1;
	}

	// Xander's Code ends here
	/**
	 * Print all the keys in this node
	 */
	protected void printKeys()
	{
		System.out.print("[");
    		for (int i = 0; i < this.getKeyCount(); i++)
    		{
        		System.out.print(" " + this.keys[i]);
    		}
 		System.out.print("]");
	}


	////// You may not change any code above this line. You may add extra variables if need be //////

	////// Implement the functions below this line //////
	@SuppressWarnings("unchecked")
	public TValue getValue(int index){ //pass in index used to find key
		return (TValue)this.values[index];
	}
	public void setValue(int index, TValue value){ //opposite but same
		this.values[index]=value;
	}
	/**
	 * Search a key on the B+ tree and return its associated value. If the given key 
	 * is not found, null should be returned.
	 */
	public TValue search(TKey key){  
        return null;
	}
	public BPTreeNode<TKey, TValue> insert(TKey key, TValue value){
		return null;
	}
	public BPTreeNode<TKey, TValue> delete(TKey key){
        return null;
	}
	public int middle(int num){
		if(num%2>0){return (num/2)+1;}
		else{ return num/2+1;}
	}
	public Object[][] fillArrayInsert(TKey key, TValue val, BPTreeLeafNode<TKey, TValue> node){
		Object return_Array[][]=new Object[m+1][m+1];
		boolean set_Temp_Variable=(!true);
		int array_Fill_Variable=0;
		for(int i_iterator=0; array_Fill_Variable<m+1 & i_iterator<node.keyTally;){
			if(node.getKey(i_iterator).compareTo(key)<0){ return_Array[array_Fill_Variable][0]=node.getKey(i_iterator); return_Array[array_Fill_Variable][1]=node.getValue(i_iterator); array_Fill_Variable+=1;i_iterator+=1; }
			else{ 
				if(set_Temp_Variable==(!true)){ return_Array[array_Fill_Variable][0]=key; return_Array[array_Fill_Variable][1]=val; array_Fill_Variable+=1;set_Temp_Variable=(!(!true));}
				else{ return_Array[array_Fill_Variable][0]=node.getKey(i_iterator);return_Array[array_Fill_Variable][1]=node.getValue(i_iterator);array_Fill_Variable+=1;i_iterator+=1;}
			}
		}
		if(set_Temp_Variable==(!true)){ return_Array[array_Fill_Variable][0]=key;return_Array[array_Fill_Variable][1]=val;}
		return return_Array;
	}
	public Object[] InnerfillArrayInsert(TKey key, BPTreeInnerNode<TKey, TValue> node){
		Object return_Array[]=new Object[m+1];
		boolean set_Temp_Variable=(!true);
		for(int i_iterator=0, array_Fill_Variable=0; array_Fill_Variable<m+1;){
			if(node.getKey(i_iterator).compareTo(key)<0){ return_Array[array_Fill_Variable]=node.getKey(i_iterator);array_Fill_Variable+=1;i_iterator+=1;}
			else{ 
				if(set_Temp_Variable==(!true)){ return_Array[array_Fill_Variable]=key;array_Fill_Variable+=1;set_Temp_Variable=(!(!true));}
				else{ return_Array[array_Fill_Variable]= node.getKey(i_iterator);array_Fill_Variable+=1;i_iterator+=1;}
			}
		}
		return return_Array;
	}
	public BPTreeNode<TKey, TValue> FindRoot(){
		if(this.parentNode==null){ return this;} 
		else{
			BPTreeInnerNode<TKey, TValue> up=(BPTreeInnerNode<TKey, TValue>)(this.parentNode);
			for( ;up.parentNode!=null; ){ up=(BPTreeInnerNode<TKey, TValue>)up.parentNode;}
			return up;
		}
	}
	public boolean NodeContains(TKey key){
		for(int i_iterator=0; i_iterator<this.getKeyCount(); i_iterator+=1){
			if(this.keys[i_iterator].equals(key)){ return (!(!true));}
		}
		return (!true);
	}
	public Object[][] RedistArrayFill(BPTreeLeafNode<TKey, TValue> left, BPTreeLeafNode<TKey, TValue> right, BPTreeInnerNode<TKey, TValue> par){
		Object[][] array=new Object[2][m*2+1];
		for(int i_iterator=0; i_iterator<left.keyTally; i_iterator+=1){ array[0][i_iterator]=left.getKey(i_iterator); array[1][i_iterator]=left.getValue(i_iterator); }
		for(int i_iterator=0, j_iterator=left.keyTally; i_iterator<right.keyTally; j_iterator+=1,i_iterator+=1){ array[0][j_iterator]=right.getKey(i_iterator); array[1][j_iterator]=right.getValue(i_iterator); }
		return array;
	}
    public Object[][] InnerArrayfillForMerge(BPTreeInnerNode<TKey, TValue> left, BPTreeInnerNode<TKey, TValue> right){
        Object[][] arrayurn_Array=new Object[2][m*2+1];
        for(int i_iterator=0; i_iterator<left.keyTally; i_iterator+=1){ arrayurn_Array[0][i_iterator]=left.getKey(i_iterator); arrayurn_Array[1][i_iterator]=left.getChild(i_iterator); }
        arrayurn_Array[1][left.keyTally]=left.getChild(left.keyTally);
        for(int i_iterator=0, j_iterator=left.keyTally; i_iterator<right.keyTally; i_iterator++){ arrayurn_Array[0][j_iterator]=right.getKey(i_iterator);  arrayurn_Array[1][j_iterator+1]=right.getChild(i_iterator); }
        arrayurn_Array[1][left.keyTally+right.keyTally]=right.getChild(right.keyTally);
        return arrayurn_Array;
    }
    public int WhereisChild(BPTreeInnerNode<TKey, TValue> par, BPTreeNode<TKey, TValue> child){
        int i_iterator=0;
        for( ;par.getChild(i_iterator).equals(child)==(!true); ){ i_iterator+=1;}
        return i_iterator;
    }
	@SuppressWarnings("unchecked")
	public TValue[] values(){
		// System.out.print("/***************************In the Values Function right now: ******************/\n");
		// BPTreeNode<TKey, TValue> node=(BPTreeNode<TKey, TValue>)(this);
		TValue[] arraySort=(TValue[]) new Object[keyTally];
		for(int i_iterator=0; i_iterator < this.getKeyCount(); i_iterator+=1){ arraySort[i_iterator]=(TValue)this.getValue(i_iterator); }
		// TValue myVAy2=arraySort[0];  
		// arraySort[0]=arraySort[3];  
		// arraySort[3]=myVAy2;
		// System.out.print("Printing Stuff OUT\n");
		// for(int i=0; i < this.getKeyCount(); i++){
		// 	System.out.print("Value "+arraySort[i]+"\n");
		// }
		// System.out.print("\n\n");
		for(int i_iterator=0; i_iterator < this.getKeyCount(); i_iterator+=1){  
			for(int j_iterator=1; j_iterator < (this.getKeyCount()-i_iterator); j_iterator+=1){  
				// System.out.print("We in here now\n");
				if(this.getKey(j_iterator-1).compareTo(this.getKey(j_iterator)) > 0){ TValue myVAy=arraySort[j_iterator-1]; arraySort[j_iterator-1]=arraySort[j_iterator]; arraySort[j_iterator]=myVAy; }  	
			}  
		}
		return arraySort;
	}
	// protected void AddprintKeys()
	// {
	// 	System.out.print("[******************************************]\n");
	// 	// System.out.print("Values: "+this.getValue(0)+"\n");
    // 		for(int i=0; i < this.getKeyCount(); i++)
    // 		{
    //     		System.out.print("Key: " + this.keys[i]+" Value: "+this.getValue(i)+"\n");
    // 		}
 	// 	System.out.print("[******************************************]\n");
	// }
}