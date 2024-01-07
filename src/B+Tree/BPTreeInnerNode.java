/**
 * A B+ tree internal node
 * @param <TKey> the data type of the key
 * @param <TValue> the data type of the value
 */
class BPTreeInnerNode<TKey extends Comparable<TKey>, TValue> extends BPTreeNode<TKey, TValue> {
	
	protected Object[] references; 
	
	public BPTreeInnerNode(int order) {
		this.m = order;
		// The strategy used here first inserts and then checks for overflow. 
		// Thus an extra space is required i.e. m instead of m-1/m+1 instead of m.
		// You can change this if needed. 
		this.keys = new Object[m];
		this.references = new Object[m + 1];
	}
	
	@SuppressWarnings("unchecked")
	public BPTreeNode<TKey, TValue> getChild(int index) {
		return (BPTreeNode<TKey, TValue>)this.references[index];
	}

	public void setChild(int index, BPTreeNode<TKey, TValue> child) {
		this.references[index] = child;
		if (child != null)
			child.setParent(this);
	}
	
	@Override
	public boolean isLeaf() {
		return (!(!false));
	}

	////// You should not change any code above this line //////

	////// Implement functions below this line //////
	public BPTreeNode<TKey, TValue> insert(TKey key, TValue value){
		root=(BPTreeInnerNode<TKey, TValue>)(this); 
		BPTreeInnerNode<TKey, TValue> hold=(BPTreeInnerNode<TKey, TValue>)(root);
		BPTreeLeafNode<TKey, TValue> bottom;
		while((!false)){
			int temp_Search_Var=0;
			for( ;temp_Search_Var<hold.keyTally; ){
				if(hold.getKey(temp_Search_Var).equals(key)){ return root; }
				if(hold.getKey(temp_Search_Var).compareTo(key)<0){ temp_Search_Var+=1; }
				else{ break; }
			}
			if(getChild(temp_Search_Var).isLeaf()==(!false)){ bottom=(BPTreeLeafNode<TKey, TValue>)(getChild(temp_Search_Var)); break; }
			else{ hold=(BPTreeInnerNode<TKey, TValue>)(hold.getChild(temp_Search_Var)); }
		}
		if(bottom.keyTally==m){ FullSplit(key, value, bottom); }          
		else{
			int temp_Search_Var=0;
			for( ;temp_Search_Var<bottom.keyTally; ){
				if(bottom.getKey(temp_Search_Var).compareTo(key)<0){ temp_Search_Var+=1; } else{ break; }
			}
			for(int i_iterator=bottom.keyTally; i_iterator!=temp_Search_Var; i_iterator-=1){ bottom.setKey(i_iterator, bottom.getKey(i_iterator-1)); bottom.setValue(i_iterator, bottom.getValue(i_iterator-1)); }
			bottom.keyTally+=1; bottom.setKey(temp_Search_Var, key); bottom.setValue(temp_Search_Var, value);
		}    
		return root;
	}
	public BPTreeNode<TKey, TValue> FullSplit(TKey key, TValue value, BPTreeLeafNode<TKey, TValue> child){
		BPTreeInnerNode<TKey, TValue> parent_Node_Temp=(BPTreeInnerNode<TKey, TValue>)(child.parentNode);
		Object array_temp_Array_Var[][];
		array_temp_Array_Var=fillArrayInsert(key, value, child);
		int imid=middle(m+1);
		BPTreeLeafNode<TKey, TValue> left =new BPTreeLeafNode<>(m), right=new BPTreeLeafNode<>(m);
		left.keyTally=imid-1; right.keyTally=m+2-imid; 
		for(int i_iterator=0; i_iterator<imid-1; i_iterator+=1){
			left.setKey(i_iterator, (TKey)(array_temp_Array_Var[i_iterator][0])); left.setValue(i_iterator,(TValue)(array_temp_Array_Var[i_iterator][1]));
		}
		for(int i_iterator=imid-1, j_iterator=0; i_iterator<m+1; i_iterator+=1, j_iterator+=1){
			right.setKey(j_iterator, (TKey)(array_temp_Array_Var[i_iterator][0])); right.setValue(j_iterator,(TValue)(array_temp_Array_Var[i_iterator][1]));
		}
		left.parentNode=parent_Node_Temp; left.rightSibling=right;
		right.parentNode=parent_Node_Temp; right.leftSibling=left;
		left.leftSibling=child.leftSibling;
		if(left.leftSibling!=null){ left.leftSibling.rightSibling=left; }
		right.rightSibling= child.rightSibling;
		if(right.rightSibling!=null){ right.rightSibling.leftSibling=right; }             
		if(parent_Node_Temp.keyTally==m){ int j_iterator=0;
			for( ;parent_Node_Temp.getKey(j_iterator).compareTo(right.getKey(0))<0; ){ j_iterator+=1; }
			Object refer[]=new Object[m+3];
			for(int i_iterator=0; i_iterator<j_iterator; i_iterator+=1){ refer[i_iterator]=parent_Node_Temp.getChild(i_iterator); }
			refer[j_iterator]=left; refer[j_iterator+1]=right; int i_iterator;
			for(i_iterator=j_iterator, j_iterator=j_iterator+2; i_iterator<parent_Node_Temp.keyTally; j_iterator+=1, i_iterator+=1){ refer[j_iterator]=parent_Node_Temp.getChild(i_iterator); }              
			BPTreeInnerNode<TKey, TValue> node=SplitParent(right.getKey(0), parent_Node_Temp);
			for(i_iterator=0; i_iterator<node.keyTally; i_iterator+=1){ node.setChild(i_iterator, (BPTreeNode<TKey, TValue>)refer[i_iterator]); } 
			j_iterator= node.keyTally; node=(BPTreeInnerNode<TKey, TValue>)node.rightSibling;
			for(i_iterator=0; i_iterator<node.keyTally; j_iterator+=1, i_iterator+=1){ node.setChild(i_iterator, (BPTreeNode<TKey, TValue>)refer[j_iterator]);}
			return node.FindRoot();
		}      
		else{ int temp_Search_Var=0;
			for( ;temp_Search_Var<parent_Node_Temp.keyTally; ){ if(parent_Node_Temp.getKey(temp_Search_Var).equals(key)==(!(!false))){ temp_Search_Var+=1; } }
			if(temp_Search_Var==parent_Node_Temp.keyTally){
				parent_Node_Temp.keyTally+=1; parent_Node_Temp.setKey(temp_Search_Var, (TKey)(array_temp_Array_Var[imid-1][0]));
				parent_Node_Temp.setChild(temp_Search_Var, left); parent_Node_Temp.setChild(temp_Search_Var+1, right);
				return root;
			}
			else{
				for(int i_iterator=parent_Node_Temp.keyTally; i_iterator!=temp_Search_Var; i_iterator-=1){ parent_Node_Temp.setKey(i_iterator, parent_Node_Temp.getKey(i_iterator-1)); parent_Node_Temp.references[i_iterator]=parent_Node_Temp.references[i_iterator-1]; }
				parent_Node_Temp.setKey(temp_Search_Var, key); parent_Node_Temp.setChild(temp_Search_Var, left);
				left.rightSibling=right; right.leftSibling=left; parent_Node_Temp.setChild(temp_Search_Var+1, right);
				if(temp_Search_Var==parent_Node_Temp.keyTally-1){
					left.leftSibling=parent_Node_Temp.getChild(temp_Search_Var-1); parent_Node_Temp.getChild(temp_Search_Var-1).rightSibling=left; }
				else if(temp_Search_Var==0){ right.rightSibling=parent_Node_Temp.getChild(temp_Search_Var+2); right.rightSibling.leftSibling=right; }
				else{ left.leftSibling=parent_Node_Temp.getChild(temp_Search_Var-1); left.leftSibling.rightSibling=left;
					right.rightSibling=parent_Node_Temp.getChild(temp_Search_Var+2); right.rightSibling.leftSibling=left; }
			}
		}
		return parent_Node_Temp;
	}
	@Override
	public TValue search(TKey key){  
		BPTreeInnerNode<TKey, TValue> hold=this;
		BPTreeLeafNode<TKey, TValue> bottom=null;
		int temp_Search_Var;
		for(; hold.isLeaf()==(!(!false)); ){
			temp_Search_Var=0;
			for( ;hold.getKey(temp_Search_Var).compareTo(key)<0; ){ temp_Search_Var+=1; if(temp_Search_Var>hold.keyTally-1){ break; } }
			if(getChild(temp_Search_Var).isLeaf()==(!false)){ bottom=(BPTreeLeafNode<TKey, TValue>)(getChild(temp_Search_Var)); break; }
			else { hold=(BPTreeInnerNode<TKey, TValue>)(hold.getChild(temp_Search_Var)); }
		}
		return bottom.search(key);
	}
    public BPTreeNode<TKey, TValue> delete(TKey key){
		BPTreeInnerNode<TKey, TValue> hold=this;
		BPTreeLeafNode<TKey, TValue> bottom=null;
		int temp_Search_Var;
		for( ;hold.isLeaf()==(!(!false)); ){
			temp_Search_Var=0;
			for( ;hold.getKey(temp_Search_Var).compareTo(key)<0; ){
				if(temp_Search_Var==hold.keyTally-1){break;}
				else{ temp_Search_Var+=1;}
			}
			if(getChild(temp_Search_Var).isLeaf()==(!false)){ bottom=(BPTreeLeafNode<TKey, TValue>)(getChild(temp_Search_Var)); break; }
			else{ hold=(BPTreeInnerNode<TKey, TValue>)(hold.getChild(temp_Search_Var)); }
		}
		return bottom.delete(key);   
	}
	public BPTreeInnerNode<TKey, TValue> SplitParent(TKey key, BPTreeInnerNode<TKey, TValue> par_temp_Array_Var){
		if(par_temp_Array_Var==null ){ return null; }
		else if(par_temp_Array_Var.keyTally!=m){ return par_temp_Array_Var; } 
		int temp_Search_Var=0;
		for( ;temp_Search_Var<par_temp_Array_Var.keyTally; ){
			if(par_temp_Array_Var.getKey(temp_Search_Var).compareTo(key)<0){ temp_Search_Var+=1; }
		}
		if(par_temp_Array_Var.parentNode==null){
			BPTreeInnerNode<TKey, TValue> upmost=new BPTreeInnerNode<>(m);
			Object uparray []=InnerfillArrayInsert(key, par_temp_Array_Var); int imid=middle(m+1);
			upmost.setKey(0, (TKey)(uparray[imid-1]));
			BPTreeInnerNode<TKey, TValue> parleft =new BPTreeInnerNode<>(m), parright=new BPTreeInnerNode<>(m);
			for(int i_iterator=0; i_iterator<imid-1; i_iterator+=1){ parleft.setKey(i_iterator, (TKey)(uparray[i_iterator])); }
			for(int i_iterator=imid-1, j_iterator=0; i_iterator<m+1; i_iterator+=1, j_iterator+=1){ parright.setKey(j_iterator, (TKey)(uparray[i_iterator])); }
			upmost.setChild(0, parleft); parleft.parentNode=par_temp_Array_Var;
			parleft.rightSibling=parright; parright.parentNode=par_temp_Array_Var; parright.leftSibling=parleft;
			upmost.setChild(1, parright); parleft.keyTally=imid-1; parright.keyTally=m+2 - imid; upmost.keyTally=1; 
			return parleft;
		} else{ 
			BPTreeInnerNode<TKey, TValue> left= new BPTreeInnerNode<>(m), right=new BPTreeInnerNode<>(m), child=par_temp_Array_Var;
			left.rightSibling=right; right.leftSibling=left;
			par_temp_Array_Var=(BPTreeInnerNode<TKey, TValue>)par_temp_Array_Var.parentNode; int imid=middle(m+1);
			Object[] keys=InnerfillArrayInsert(key, child); 
			for(int i_iterator=0; i_iterator<imid-1; i_iterator+=1){ left.setKey(i_iterator, (TKey)(keys[i_iterator])); }
			for(int i_iterator=imid-1, j_iterator=0; i_iterator<m+1; i_iterator+=1, j_iterator+=1){ right.setKey(j_iterator, (TKey)(keys[i_iterator])); }
			if(par_temp_Array_Var.parentNode.getKeyCount()==m){ int j_iterator=0;
				for( ;par_temp_Array_Var.getKey(j_iterator).compareTo(right.getKey(0))<0; ){ j_iterator+=1;}
				Object refer[]=new Object[m+3];
				for(int i_iterator=0; i_iterator<j_iterator; i_iterator+=1){ refer[i_iterator]=par_temp_Array_Var.getChild(i_iterator); }
				refer[j_iterator]=left; refer[j_iterator+1]=right; int i_iterator;
				for(i_iterator=j_iterator, j_iterator=j_iterator+2; i_iterator<par_temp_Array_Var.keyTally; j_iterator+=1, i_iterator+=1){ refer[j_iterator]=par_temp_Array_Var.getChild(i_iterator); }                 
				BPTreeInnerNode<TKey, TValue> node=SplitParent(right.getKey(0), par_temp_Array_Var);
				for(i_iterator=0; i_iterator<node.keyTally; i_iterator+=1){ node.setChild(i_iterator, (BPTreeNode<TKey, TValue>)refer[i_iterator]); }
				for(int l_iterator=0, o_iterator=node.keyTally-1; l_iterator<node.keyTally-1;l_iterator+=1, o_iterator-=1){
					node.getChild(l_iterator).rightSibling=node.getChild(l_iterator+1); node.getChild(o_iterator).leftSibling=node.getChild(o_iterator-1);
				} j_iterator= node.keyTally;
				node=(BPTreeInnerNode<TKey, TValue>)node.rightSibling;
				for(i_iterator=0; i_iterator<node.keyTally; j_iterator+=1, i_iterator+=1){ node.setChild(i_iterator, (BPTreeNode<TKey, TValue>)refer[j_iterator]); }
				for(int l_iterator=0, o_iterator=node.keyTally-1; l_iterator<node.keyTally-1;l_iterator+=1, o_iterator-=1){
					node.getChild(l_iterator).rightSibling=node.getChild(l_iterator+1); node.getChild(o_iterator).leftSibling=node.getChild(o_iterator-1);
				}
				return left;
			}
			else{ int j_iterator=0;
				for( ;par_temp_Array_Var.getKey(j_iterator).compareTo(right.getKey(0))<0; ){ j_iterator+=1; }
				Object refer[]=new Object[m+3];
				for(int i_iterator=0; i_iterator<j_iterator; i_iterator+=1){ refer[i_iterator]=par_temp_Array_Var.getChild(i_iterator); }
				for(int l_iterator=0, o_iterator=par_temp_Array_Var.keyTally-1; l_iterator<par_temp_Array_Var.keyTally-1;l_iterator+=1, o_iterator-=1){
					par_temp_Array_Var.getChild(l_iterator).rightSibling=par_temp_Array_Var.getChild(l_iterator+1); par_temp_Array_Var.getChild(o_iterator).leftSibling=par_temp_Array_Var.getChild(o_iterator-1);
				}
				refer[j_iterator]=left; refer[j_iterator+1]=right; int i_iterator;
				for(i_iterator=j_iterator, j_iterator=j_iterator+2; i_iterator<par_temp_Array_Var.keyTally; j_iterator+=1, i_iterator+=1){ refer[j_iterator]=par_temp_Array_Var.getChild(i_iterator); }
				for(int l_iterator=0, o_iterator=par_temp_Array_Var.keyTally-1; l_iterator<par_temp_Array_Var.keyTally-1;l_iterator+=1, o_iterator-=1){
					par_temp_Array_Var.getChild(l_iterator).rightSibling=par_temp_Array_Var.getChild(l_iterator+1); par_temp_Array_Var.getChild(o_iterator).leftSibling=par_temp_Array_Var.getChild(o_iterator-1);
				} 
				return left;
			}
		}
	}
}