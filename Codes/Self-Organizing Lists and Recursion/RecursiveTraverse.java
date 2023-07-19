public class RecursiveTraverse<T extends Comparable<T>> extends Traverser<T>{
    public RecursiveTraverse(){
        //TODO: Implement the function
        this.list = null;
    }

    public RecursiveTraverse(SelfOrderingList<T> list){
        //TODO: Implement the function
        SelfOrderingList<T> copyList = list.getBlankList();
        Node<T> tempListHead = list.head;
        RecursiveTraverseHelper(tempListHead, copyList);
        this.list = copyList;
    }
    // Helper function
    private void RecursiveTraverseHelper(Node<T> tempListHead, SelfOrderingList<T> copyList) {
        if (tempListHead == null) {
            return;
        }
        copyList.insert(tempListHead.data);
        RecursiveTraverseHelper(tempListHead.next, copyList);
    }

    @Override
    public SelfOrderingList<T> reverseList() {
        //TODO: Implement the function
        SelfOrderingList<T> copyList = list.getBlankList();
        Node<T> tempListHead = this.list.head;
        return reverseListHelper(tempListHead, copyList);
    }
    // Helper function
    private SelfOrderingList<T> reverseListHelper(Node<T> tempListHead, SelfOrderingList<T> copyList) {
        if (tempListHead == null) {
            return copyList;
        }
        reverseListHelper(tempListHead.next, copyList);
        copyList.insert(tempListHead.data);
        return copyList;
    }

    @Override
    public boolean contains(T data) {
        //TODO: Implement the function
        Node<T> tempListHead = this.list.head;
        boolean boolContained = containsHelper(tempListHead, data);
        return boolContained;
    }

    // Helper function
    private boolean containsHelper(Node<T> tempListHead, T data) {
        if (tempListHead == null) {
            return false;
        }
        if (tempListHead.data.compareTo(data) == 0) {
            return true;
        }
        return containsHelper(tempListHead.next, data);
    }

    @Override
    public String toString() {
        //TODO: Implement the function
        Node<T> tempListHead = this.list.head;
        String returnStr = toStringHelper(tempListHead, "->");
        return returnStr;
    }

    // Helper function
    private String toStringHelper(Node<T> tempListHead, String returnStr) {
        if (tempListHead == null) {
            return "";
        }
        else{
            returnStr = "->" + tempListHead.toString() + toStringHelper(tempListHead.next, returnStr);
            return returnStr;
        }
    }

    @Override
    public Node<T> get(int pos) {
        //TODO: Implement the function
        if(pos >= this.size() || pos < 0) {
            return null;
        }
        Node<T> tempListHead = this.list.head;
        Node<T> returnNode = getHelper(tempListHead, 0, pos);
        return returnNode;
    }

    // Helper function
    private Node<T> getHelper(Node<T> tempListHead, int start, int pos) {
        if (tempListHead == null) {
            return null;
        }
        if (start == pos) {
            return tempListHead;
        }
        return getHelper(tempListHead.next, start+1, pos);
    }

    @Override
    public Node<T> find(T data) {
        //TODO: Implement the function
        Node<T> tempListHead = this.list.head;
        Node<T> returnNode = findHelper(tempListHead, data);
        return returnNode;
    }

    // Helper function
    private Node<T> findHelper(Node<T> tempListHead, T data) {
        if (tempListHead == null) {
            return null;
        }
        if (tempListHead.data.compareTo(data) == 0) {
            return tempListHead;
        }
        return findHelper(tempListHead.next, data);
    }

    @Override
    public int size() {
        //TODO: Implement the function
        if(this.list != null) {
            Node<T> tempListHead = this.list.head;
            int returnSize = sizeHelper(tempListHead);
            return returnSize;
        }
        return 0;
    }

    // Helper function
    private int sizeHelper(Node<T> tempListHead) {
        if (tempListHead == null) {
            return 0;
        }
        else{
            return 1 + sizeHelper(tempListHead.next);
        }
    }

    @Override
    public SelfOrderingList<T> clone(SelfOrderingList<T> otherList) {
        //TODO: Implement the function
        if(otherList == null) {
            return null;
        }
        if(otherList.head == null) {
            return this.list.getBlankList();
        }
        else{
            SelfOrderingList<T> copyList = otherList.getBlankList();
            Node<T> tempListHead = otherList.head;
            RecursiveTraverseHelper(tempListHead, copyList);
            return copyList;
        }
    }

    // Helper function
    private Node<T> cloneHelper(SelfOrderingList<T> clonedList, Node<T> tempListHead) {
        if (tempListHead == null) {
            return null;
        }
        Node<T> tempInsertNode = new Node<T>(tempListHead.data);
        tempInsertNode.next = cloneHelper(clonedList, tempListHead.next);
        // clonedList.head = tempInsertNode;
        // return clonedList.head;
        return tempInsertNode;
    }
}
