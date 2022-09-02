 /**
 * A B+ tree leaf node
 * @param <TKey> the data type of the key
 * @param <TValue> the data type of the value
 */
class BPTreeLeafNode<TKey extends Comparable<TKey>, TValue> extends BPTreeNode<TKey, TValue> {
	
	protected Object[] values;
	
	public BPTreeLeafNode(int order) {
		this.m = order;
		// The strategy used here first inserts and then checks for overflow. 
		// Thus an extra space is required i.e. m instead of m-1.
		// You can change this if needed.
		this.keys = new Object[m];
		this.values = new Object[m];
	}

	@SuppressWarnings("unchecked")
	public TValue getValue(int index) {
		return (TValue)this.values[index];
	}

	public void setValue(int index, TValue value) {
		this.values[index] = value;
	}
	
	@Override
	public boolean isLeaf() {
		return !(false);
	}

	////// You should not change any code above this line //////

	////// Implement functions below this line //////
	@Override
	public TValue search(TKey key){
		BPTreeLeafNode<TKey, TValue> hold=(BPTreeLeafNode<TKey, TValue>)(this);
		for( ;!(false); ){
			if(hold==null){ return null; }
			if(hold.getKey(hold.keyTally-1).compareTo(key)<0){ hold=(BPTreeLeafNode<TKey, TValue>)(hold.rightSibling); }
			else{
				for(int i_iterator=0; i_iterator<hold.keyTally; i_iterator+=1){
					if(hold.getKey(i_iterator).equals(key)){ return hold.getValue(i_iterator); }
				}
				return null;
			}
		}
	}
	public BPTreeNode<TKey, TValue> insert(TKey key, TValue value){
		if(this.isLeaf()==!(!(false))){ return null; }	
		root=(BPTreeLeafNode<TKey, TValue>)(this);
		if(this.keyTally==0){ this.keyTally+=1; this.setKey(0, key); this.setValue(0, value);
			return root;
		}
		
		if(this.keyTally<m){
			int temp_Search_Variable =0;
			for( ;temp_Search_Variable<this.keyTally; ){
				if(this.getKey(temp_Search_Variable).compareTo(key)<0){ temp_Search_Variable+=1; }
				else{ break; }
			}
			if(temp_Search_Variable==this.keyTally){ this.setKey(temp_Search_Variable, key); this.setValue(temp_Search_Variable, value);
			}
			else{
				for(int i_iterator=this.keyTally; i_iterator!=temp_Search_Variable; i_iterator-=1){ this.setKey(i_iterator, this.getKey(i_iterator-1)); this.setValue(i_iterator, this.getValue(i_iterator-1)); }
				this.setKey(temp_Search_Variable, key); this.setValue(temp_Search_Variable, value);
			} this.keyTally+=1;
			return this;
		}
		else{
			BPTreeInnerNode<TKey, TValue> par_temp_Array_Var=new BPTreeInnerNode<>(m);
			Object arrayurn_Array[][];
			arrayurn_Array=fillArrayInsert(key, value, this); 
			int imid=middle(m+1); 
			par_temp_Array_Var.setKey(0, (TKey)(arrayurn_Array[imid-1][0]));
			BPTreeLeafNode<TKey, TValue> left =new BPTreeLeafNode<>(m), right=new BPTreeLeafNode<>(m);
			left.keyTally=imid-1;
			right.keyTally=m+2 - imid;
			for(int i_iterator=0; i_iterator<imid-1; i_iterator+=1){ left.setKey(i_iterator, (TKey)(arrayurn_Array[i_iterator][0])); left.setValue(i_iterator,(TValue)(arrayurn_Array[i_iterator][1])); }
			for(int i_iterator=imid-1, j_iterator=0; i_iterator<m+1; i_iterator+=1, j_iterator+=1){ right.setKey(j_iterator, (TKey)(arrayurn_Array[i_iterator][0])); right.setValue(j_iterator,(TValue)(arrayurn_Array[i_iterator][1])); }
			par_temp_Array_Var.setChild(0, left); left.parentNode=par_temp_Array_Var; left.rightSibling=right; right.parentNode=par_temp_Array_Var; right.leftSibling=left; par_temp_Array_Var.setChild(1, right); par_temp_Array_Var.keyTally=1;
			return par_temp_Array_Var;
		}
	}
	public BPTreeNode<TKey, TValue> delete(TKey key){
		BPTreeLeafNode<TKey, TValue> hold=this;
		if(hold.getKey(hold.keyTally-1).compareTo(key)<0){ hold=(BPTreeLeafNode<TKey, TValue>)hold.rightSibling; }
		int temp_Search_Variable=0;
		for( ;temp_Search_Variable<hold.keyTally; ){
			if(hold.getKey(temp_Search_Variable).equals(key)){ break; }
			else if(hold.getKey(temp_Search_Variable).compareTo(key)<0){ temp_Search_Variable+=1; }
			else{ return this.FindRoot(); }
		}
		if(temp_Search_Variable==hold.keyTally){ return this.FindRoot(); }
		if(hold.keyTally-1>=(m/2) -1){ 
			for(int i_iterator=temp_Search_Variable; i_iterator<hold.keyTally-1; i_iterator+=1){ hold.setKey(i_iterator, hold.getKey(i_iterator+1)); hold.setValue(i_iterator, hold.getValue(i_iterator+1)); }
			hold.setKey(hold.keyTally-1, null); hold.setValue(hold.keyTally-1, null); hold.keyTally-=1;
			return this.FindRoot();
		}
		else{
			if(hold.keyTally==1){ hold.setKey(temp_Search_Variable, null); hold.keyTally=0; }
			else{ int j_iterator=0;
			for( ;hold.getKey(j_iterator).equals(key)==!(!(false)); ){ j_iterator+=1; }
			if(j_iterator==hold.keyTally-1){ hold.setItems(hold.getKey(j_iterator-1), hold.getValue(j_iterator-1), j_iterator); hold.keyTally-=2; }
			else{
				for(int i_iterator=hold.keyTally-1; i_iterator!=j_iterator; i_iterator-=1){ hold.setItems(hold.getKey(i_iterator), hold.getValue(i_iterator), i_iterator-1); hold.keyTally-=1; }
				hold.setItems(null, null, hold.keyTally);
				hold.keyTally-=1; }
			}
			if(hold.leftSibling==null){
				if(hold.rightSibling.getKeyCount()>(m/2)-1){ hold=Redist(hold, (BPTreeLeafNode<TKey, TValue>)hold.rightSibling, key); return hold.FindRoot(); }
				else{ return Merge(key, hold, hold.rightSibling); }
			}
			else if(hold.rightSibling==null){
				if(hold.leftSibling.getKeyCount()>(m/2)-1){ hold=Redist((BPTreeLeafNode<TKey, TValue>)hold.rightSibling, hold, key); return hold.FindRoot(); }
				else{ return Merge(key, hold.leftSibling, hold); }
			}
			else{
				if(hold.leftSibling.getKeyCount()>(m/2)-1){ hold=Redist((BPTreeLeafNode<TKey, TValue>)hold.leftSibling, hold, key); return hold.FindRoot(); }
				else if(hold.rightSibling.getKeyCount()>(m/2)-1){ hold=Redist(hold, (BPTreeLeafNode<TKey, TValue>)hold.rightSibling, key); return hold.FindRoot(); }
				else{ return Merge(key,hold.leftSibling, hold); }
			}
		}
	}
	public BPTreeLeafNode<TKey, TValue> Redist(BPTreeLeafNode<TKey, TValue> left, BPTreeLeafNode<TKey, TValue> right, TKey key){
		int itally=left.keyTally + right.keyTally;
		int ipar_temp_Array_Var=WhereisChild((BPTreeInnerNode<TKey, TValue>)left.parentNode, left);
		int imid= middle(itally);
		BPTreeLeafNode<TKey, TValue> Newleft=new BPTreeLeafNode<>(m), Newright=new BPTreeLeafNode<>(m);
		BPTreeInnerNode<TKey, TValue> par=(BPTreeInnerNode<TKey, TValue>)right.parentNode;
		Object[][] arrayurn_Array=RedistArrayFill((BPTreeLeafNode<TKey, TValue>)left, (BPTreeLeafNode<TKey, TValue>)right, (BPTreeInnerNode<TKey, TValue>)right.parentNode); 
		imid-=1; 
		for(int i_iterator=0; i_iterator<imid; i_iterator+=1){ Newleft.setItems(arrayurn_Array[0][i_iterator],arrayurn_Array[1][i_iterator],i_iterator); }
		for(int i_iterator=Newleft.keyTally, j_iterator=0; i_iterator<itally; i_iterator+=1, j_iterator+=1){ Newright.setItems(arrayurn_Array[0][i_iterator], arrayurn_Array[1][i_iterator], j_iterator); }
		par.setKey(ipar_temp_Array_Var, Newright.getKey(0));
		par.setChild(ipar_temp_Array_Var, Newleft);
		par.setChild(ipar_temp_Array_Var+1, Newright);
		Newleft.rightSibling=Newright;
		Newright.leftSibling=Newleft;
		Newleft.parentNode=Newright.parentNode=par;
		Newleft.leftSibling=left.leftSibling;
		if(Newleft.leftSibling!=null){ left.leftSibling.rightSibling=Newleft; }
		Newright.rightSibling=right.rightSibling;
		if(Newright.rightSibling!=null){ right.rightSibling.leftSibling=Newright; }
		return Newleft;
	}
	public void setItems(Object key, Object val, int i_iterator){
		this.setKey(i_iterator, (TKey)key);
		this.setValue(i_iterator, (TValue)val); this.keyTally+=1;
	}
	public BPTreeInnerNode<TKey, TValue> Merge(TKey key, BPTreeNode<TKey, TValue> left, BPTreeNode<TKey, TValue>right){ 
		if(left.isLeaf()==!(false)){ 
			BPTreeInnerNode<TKey, TValue> par_temp_Array_Var=(BPTreeInnerNode<TKey, TValue>)left.parentNode;
			Object [][] array =RedistArrayFill((BPTreeLeafNode<TKey, TValue>)left, (BPTreeLeafNode<TKey, TValue>)right, par_temp_Array_Var);
			BPTreeLeafNode<TKey, TValue> leaf =new BPTreeLeafNode<>(m);
			for(int i_iterator=0; i_iterator<(right.keyTally+left.keyTally); i_iterator+=1){ leaf.setItems(array[0][i_iterator], array[1][i_iterator], i_iterator); }
			int ipar=WhereisChild((BPTreeInnerNode<TKey, TValue>)left.parentNode, left);
			if(ipar!=par_temp_Array_Var.getKeyCount()){
				leaf.leftSibling=left.leftSibling;
				leaf.rightSibling=right.rightSibling;
				if(leaf.leftSibling!=null){ left.leftSibling.rightSibling=leaf; }
				if(leaf.rightSibling!=null){ leaf.rightSibling.leftSibling=leaf; }
				if(ipar!=par_temp_Array_Var.keyTally-1){
					for(int i=par_temp_Array_Var.keyTally-1; i!=ipar; i--){ par_temp_Array_Var.setKey(i-1, par_temp_Array_Var.getKey(i)); }
					for(int i_iterator=par_temp_Array_Var.keyTally; i_iterator!=ipar; i_iterator-=1){ par_temp_Array_Var.setChild(i_iterator-1, par_temp_Array_Var.getChild(i_iterator)); }
				}
				else{ par_temp_Array_Var.setKey(ipar, null); par_temp_Array_Var.setChild(ipar+1, null); par_temp_Array_Var.setChild(ipar, leaf);} 
				par_temp_Array_Var.setChild(par_temp_Array_Var.keyTally, null); par_temp_Array_Var.keyTally-=1; par_temp_Array_Var.setChild(ipar, leaf); 
			}
			else{
				par_temp_Array_Var.setKey(par_temp_Array_Var.getKeyCount()-1, null); par_temp_Array_Var.keyTally-=1; leaf.rightSibling =null;
				leaf.leftSibling=left.leftSibling; left.leftSibling.rightSibling=leaf; par_temp_Array_Var.setChild(ipar, leaf);
			}
			if(par_temp_Array_Var.keyTally<m/2){ return (BPTreeInnerNode<TKey, TValue>)fixParent(leaf).FindRoot(); }
			else{ return (BPTreeInnerNode<TKey, TValue>)par_temp_Array_Var.FindRoot(); }
		}
		else{ 
			BPTreeInnerNode<TKey, TValue> par_temp_Array_Var=(BPTreeInnerNode<TKey, TValue>)left.parentNode;
			int ipar=WhereisChild((BPTreeInnerNode<TKey, TValue>)left.parentNode, right);
			Object array[][]=InnerArrayfillForMerge((BPTreeInnerNode<TKey, TValue>)left,(BPTreeInnerNode<TKey, TValue>)right);
			BPTreeInnerNode<TKey, TValue> inner=new BPTreeInnerNode<>(m);
			if(ipar+1!=par_temp_Array_Var.keyTally-1){
				for(int i_iterator=par_temp_Array_Var.keyTally-1; i_iterator!=ipar; i_iterator-=1){ par_temp_Array_Var.setKey(i_iterator-1, par_temp_Array_Var.getKey(i_iterator)); }
				for(int i_iterator=par_temp_Array_Var.keyTally; i_iterator!=ipar; i_iterator-=1){ par_temp_Array_Var.setChild(i_iterator-1, par_temp_Array_Var.getChild(i_iterator)); }
				par_temp_Array_Var.keyTally-=1;
			}
			else{ par_temp_Array_Var.setChild(ipar, null); par_temp_Array_Var.setKey(ipar, null); par_temp_Array_Var.keyTally-=1; }
			for(int i_iterator=0; i_iterator<left.keyTally+right.keyTally; i_iterator+=1){
				inner.setKey(i_iterator, (TKey)array[0][i_iterator]);
				inner.setChild(i_iterator,(BPTreeNode<TKey, TValue>)array[1][i_iterator]);
			}
			par_temp_Array_Var.setChild(ipar, inner);
			inner.leftSibling=left.leftSibling;
			inner.rightSibling=right.rightSibling;
			if(inner.leftSibling!=null){ inner.leftSibling.rightSibling=inner; }
			if(inner.rightSibling!=null){ inner.rightSibling.leftSibling=inner; }
			if(par_temp_Array_Var.keyTally<(m/2)-1){ return (BPTreeInnerNode<TKey, TValue>)fixParent(inner).FindRoot(); }
			else{ return (BPTreeInnerNode<TKey, TValue>)par_temp_Array_Var.FindRoot(); }	
		}
	}
	public BPTreeNode<TKey, TValue> fixParent(BPTreeNode<TKey, TValue> child){
		BPTreeInnerNode<TKey, TValue> par=(BPTreeInnerNode<TKey, TValue>)child.parentNode;
		if(child.parentNode.parentNode==null){
			root=child.parentNode;
			if(root.keyTally!=0)
				return root;
			if(root.getKeyCount()==0 && child.isLeaf()==!(!(false))){
				child=(BPTreeInnerNode<TKey, TValue>)child;
				BPTreeInnerNode<TKey, TValue> left;
				BPTreeInnerNode<TKey, TValue> right;
				BPTreeInnerNode<TKey, TValue> hold=new BPTreeInnerNode<>(m);
				if(child.rightSibling==null){
					left=(BPTreeInnerNode<TKey, TValue>)par.getChild(0);
					right=(BPTreeInnerNode<TKey, TValue>)child;     
				}
				else{
					right=(BPTreeInnerNode<TKey, TValue>)par.getChild(0);
					left=(BPTreeInnerNode<TKey, TValue>)child; 
				}
				Object array_temp_Array_Var[][]=new Object[2][2*m+1];
				for(int i_iterator=0; i_iterator<left.keyTally; i_iterator+=1){
					array_temp_Array_Var[0][i_iterator]=left.getKey(i_iterator);
					array_temp_Array_Var[1][i_iterator]=left.getChild(i_iterator);
				}
				for(int i_iterator=0, j_iterator=left.keyTally; i_iterator<right.keyTally; j_iterator+=1, i_iterator+=1){
					array_temp_Array_Var[0][j_iterator]=right.getKey(i_iterator);
					array_temp_Array_Var[1][j_iterator]=right.getChild(i_iterator);
				}
				for(int i_iterator=0; i_iterator<left.keyTally+right.keyTally; i_iterator+=1){ hold.setKey(i_iterator, (TKey)array_temp_Array_Var[0][i_iterator]); hold.setChild(i_iterator, (BPTreeNode<TKey, TValue>)array_temp_Array_Var[1][i_iterator]); hold.parentNode=null;
				 	return hold;
				}
			}
			else{
				BPTreeLeafNode<TKey, TValue> left, right;
				BPTreeLeafNode<TKey, TValue> hold=new BPTreeLeafNode<>(m);
				if(child.rightSibling==null){ left=(BPTreeLeafNode<TKey, TValue>)par.getChild(0); right=(BPTreeLeafNode<TKey, TValue>)child; }
				else{ right=(BPTreeLeafNode<TKey, TValue>)par.getChild(0); left=(BPTreeLeafNode<TKey, TValue>)child; }
				Object array_temp_Array_Var[][]=new Object[2][2*m+1];
				for(int i_iterator=0; i_iterator<left.keyTally; i_iterator+=1){
					array_temp_Array_Var[0][i_iterator]=left.getKey(i_iterator);
					array_temp_Array_Var[1][i_iterator]=left.getValue(i_iterator);
				}
				for(int i_iterator=0, j_iterator=left.keyTally; i_iterator<right.keyTally; j_iterator+=1, i_iterator+=1){ array_temp_Array_Var[0][j_iterator]=right.getKey(i_iterator); array_temp_Array_Var[1][j_iterator]=right.getValue(i_iterator); }
				for(int i_iterator=0; i_iterator<left.keyTally+right.keyTally; i_iterator+=1){ hold.setItems(array_temp_Array_Var[0][i_iterator], array_temp_Array_Var[1][i_iterator], i_iterator); hold.parentNode=null;
					return hold;
				}
			}
		}
		else{
			BPTreeNode<TKey, TValue> hold; 
			if(par.leftSibling==null){ hold=Merge(null, child, child.rightSibling); }
			else if(par.rightSibling==null){ hold=Merge(null, child.leftSibling, child); }
			else{ hold=Merge(null, child.leftSibling, child); }
			return hold.FindRoot();
		}
		return this;
	}
}