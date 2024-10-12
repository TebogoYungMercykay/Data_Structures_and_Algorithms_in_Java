public class StandardBinaryTree<T extends Comparable<T>> extends BinaryTree<T> {

    @Override
    public void insert(T data){
        super.insert(data, true);
    }

    @Override
    public int height(){
        //TODO: Implement this function
        Leaf<T> tempRoot = this.root;
        return HeightHelper(tempRoot) - 1;
    }

    @Override
    public boolean contains(T data){
        //TODO: Implement this function
        Leaf<T> tempRoot = this.root;
        return ContainsHelper(tempRoot, data);
    }

    @Override
    public Leaf<T> find(T data){
        //TODO: Implement this function
        Leaf<T> tempRoot = this.root;
        return FindHelper(tempRoot, data);
    }

    @Override
    public void depthFirstTraversal(){
        //TODO: Implement this function
        Leaf<T> tempRoot = this.root;
        DFST_Inoder(tempRoot);
    }

    @Override
    public int numLeavesInTree(){
        //TODO: Implement this function
        Leaf<T> tempRoot = this.root;
        return numLeavesInTreeHelper(tempRoot);
    }

    @Override
    public boolean perfectlyBalanced(){
        //TODO: Implement this function
        Leaf<T> tempRoot = this.root;
        return (BalancedHelper(tempRoot) % 2 == 0);
    }

    @Override
    public Leaf<T> findParent(T data){
        //TODO: Implement this function
        Leaf<T> tempRoot = this.root;
        if(tempRoot != null && tempRoot.data.compareTo(data) == 0){
            System.out.println(tempRoot.toString());
            return null;
        }
        return findParentHelper(tempRoot, data);
    }

    @Override
    public BinaryTree<T> convertTree(){
        //TODO: Implement this function
        Leaf<T> tempRoot = this.root;
        Leaf<T> cloneTreeNode = clone(tempRoot);
        BinaryTree<T> returnTree = new MirroredBinaryTree<T>();
        returnTree.root = convertTreeHelper(cloneTreeNode);
        return returnTree;
    }

    // HELPER FUNCTIONS

    public Leaf<T> clone(Leaf<T> tempRoot){
		if(isEmpty() == true  || tempRoot == null){
            return null;
        }
        Leaf<T> cloneHead = new Leaf<T>(tempRoot.data);
		cloneHead.right = clone(tempRoot.right);
		cloneHead.left = clone(tempRoot.left);
		return cloneHead;
	}

    public Leaf<T> convertTreeHelper(Leaf<T> tempRoot){
        if(isEmpty() == true  || tempRoot == null){
            return null;
        }
        Leaf<T> leftSubtre = convertTreeHelper(tempRoot.left);
        Leaf<T> rightSubtre = convertTreeHelper(tempRoot.right);
        tempRoot.left = rightSubtre;
        tempRoot.right = leftSubtre;
        return tempRoot;
    }


    public boolean isEmpty(){
        return (this.root == null);
    }
    // * Correct
    public boolean ContainsHelper(Leaf<T> tempRoot, T elem){
        if(isEmpty() == true || tempRoot == null){
            return false;
        }
        System.out.println(tempRoot.toString());
        if(elem.compareTo(tempRoot.data) == 0){
            return true;
        }
        if(elem.compareTo(tempRoot.data) < 0){
            return ContainsHelper(tempRoot.left, elem);
        }
        else{
            return ContainsHelper(tempRoot.right, elem);
        }
    }
    // * Correct
    public Leaf<T> FindHelper(Leaf<T> tempRoot, T elem){
        if(isEmpty() == true || tempRoot == null){
            return null;
        }
        System.out.println(tempRoot.toString());
        if(elem.compareTo(tempRoot.data) == 0){
            return tempRoot;
        }
        if(elem.compareTo(tempRoot.data) < 0){
            return FindHelper(tempRoot.left, elem);
        }
        else{
            return FindHelper(tempRoot.right, elem);
        }
    }

    public int HeightHelper(Leaf<T> tempRoot){
        if(isEmpty() == true || tempRoot == null){
            return 0;
        }
        else{
            int tempLeft = HeightHelper(tempRoot.left);
            int tempRight = HeightHelper(tempRoot.right);
            return (Math.max(tempLeft, tempRight)) + 1;
        }
    }
    // * Correct
    public int BalancedHelper(Leaf<T> tempRoot){
        if(isEmpty() == true || tempRoot == null){
            return 0;
        }
        else{
            int tempLeft = BalancedHelper(tempRoot.left);
            int tempRight = BalancedHelper(tempRoot.right);
            if(tempLeft == tempRight){
                return 2;
            }
            return 3;
        }
    }
    // * Correct
    public void DFST_Inoder(Leaf<T> tempRoot){
		if(isEmpty() == true || tempRoot != null){
			DFST_Inoder(tempRoot.left);
            System.out.println(tempRoot.toString());
			DFST_Inoder(tempRoot.right);
		}
	}

    // * Correct
    public int numLeavesInTreeHelper(Leaf<T> tempRoot){
        if(isEmpty() == true || tempRoot == null){
            return 0;
        }
        return 1 + numLeavesInTreeHelper(tempRoot.left) + numLeavesInTreeHelper(tempRoot.right);
    }

    // * Correct
    public Leaf<T> findParentHelper(Leaf<T> tempRoot, T data){
        //TODO: Implement this function
        if(isEmpty() == true  || tempRoot == null || tempRoot.data.compareTo(data) == 0){
            return null;
        }
        System.out.println(tempRoot.toString());
        if(data.compareTo(tempRoot.data) < 0){
            if(tempRoot.left != null && tempRoot.left.data.compareTo(data) == 0){
                if(tempRoot.left.data.compareTo(data) == 0){
                    return tempRoot;
                }
            }
            else{
                return findParentHelper(tempRoot.left, data);
            }
        }
        else{
            if(tempRoot.right != null && tempRoot.right.data.compareTo(data) == 0){
                if(tempRoot.right.data.compareTo(data) == 0){
                    return tempRoot;
                }
            }
            else{
                return findParentHelper(tempRoot.right, data);
            }
        }
        return null;
    }
}