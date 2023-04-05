abstract class BinaryTree<T extends Comparable<T>> {
    protected Leaf<T> root = null;

    public abstract void insert(T data);

    protected void insert(T data, boolean standardTree){
        if(root == null){
            root = new Leaf<T>(data);
            return;
        }
        recInserter(root, standardTree, data);
    }

    public abstract void depthFirstTraversal();

    public abstract int height();

    public abstract boolean contains(T data);

    public abstract int numLeavesInTree();

    public abstract Leaf<T> find(T data);

    public abstract Leaf<T> findParent(T data);

    public abstract boolean perfectlyBalanced();

    private void recInserter(Leaf<T> curr, boolean standardTree, T data){
        if(curr == null || curr.data.compareTo(data) == 0)
            return;

        if(!standardTree){
            if(curr.data.compareTo(data) > 0){
                if(curr.right == null){
                    curr.right = new Leaf<T>(data);
                } else {
                    recInserter(curr.right, standardTree, data);
                }
            } else {
                if(curr.left == null){
                    curr.left = new Leaf<T>(data);
                } else {
                    recInserter(curr.left, standardTree, data);
                }
            }
        } else {
            if(curr.data.compareTo(data) < 0){
                if(curr.right == null){
                    curr.right = new Leaf<T>(data);
                } else {
                    recInserter(curr.right, standardTree, data);
                }
            } else {
                if(curr.left == null){
                    curr.left = new Leaf<T>(data);
                } else {
                    recInserter(curr.left, standardTree, data);
                }
            }
        }
    }

    public abstract BinaryTree<T> convertTree();
}
