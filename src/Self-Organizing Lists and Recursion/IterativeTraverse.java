public class IterativeTraverse<T extends Comparable<T>> extends Traverser<T>{
    public IterativeTraverse(){
        //TODO: Implement the function
        this.list = null;
    };
    
    public IterativeTraverse(SelfOrderingList<T> list){
        //TODO: Implement the function
        Node<T> currentNode = list.head;
        SelfOrderingList<T> copyList = list.getBlankList();
        while (currentNode != null) {
            copyList.insert(currentNode.data);
            currentNode = currentNode.next;
        }
        this.list = copyList;
    }

    @Override
    public SelfOrderingList<T> reverseList() {
        //TODO: Implement the function
        if(this.list == null){
            return null;
        }
        if(this.list.head == null){
            return null;
        }
        else{
            Node<T> tempListHead = this.list.head;
            while(tempListHead.next != null){
                tempListHead = tempListHead.next;
            }
            SelfOrderingList<T> newList = this.list.getBlankList();
            Node<T> copyLastNode = tempListHead;
            while(tempListHead != null){
                newList.insert(tempListHead.data);
                tempListHead = tempListHead.prev;
            }
            return newList;
        }
    }

    @Override
    public boolean contains(T data) {
        //TODO: Implement the function
        if(this.list.head != null){
            Node<T> tempListHead = this.list.head;
            while(tempListHead != null){
                if(tempListHead.data.compareTo(data) == 0){
                    return true;
                }
                tempListHead = tempListHead.next;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        //TODO: Implement the function
        String result = "";
        if(this.list.head != null) {
            result = "->";
            for(Node<T> node = this.list.head; node != null; node = node.next) {
                result = result + node.toString();
                if(node.next != null) {
                    result = result + "->";
                }
            }
        }
        return result;
    }

    @Override
    public Node<T> get(int pos) {
        //TODO: Implement the function
        if(pos >= this.size() || pos < 0) {
            return null;
        }
        else{
            int counter = 0;
            Node<T> tempListHead = this.list.head;
            while(tempListHead != null){
                if(counter == pos) {
                    return tempListHead;
                }
                tempListHead = tempListHead.next;
                counter += 1;
            }
        }
        return null;
    }

    @Override
    public Node<T> find(T data) {
        //TODO: Implement the function
        if(this.contains(data) != false) {
            Node<T> tempListHead = this.list.head;
            while(tempListHead != null){
                if(tempListHead.data.compareTo(data) == 0) {
                    return tempListHead;
                }
                tempListHead = tempListHead.next;
            }
        }
        return null;
    }

    @Override
    public int size() {
        //TODO: Implement the function
        int size = 0;
        if(this.list.head != null) {
            for(Node<T> nodePointer = this.list.head; nodePointer != null; nodePointer = nodePointer.next) {
                size += 1;
            }
        }
        return size;
    }

    @Override
    public SelfOrderingList<T> clone(SelfOrderingList<T> otherList) {
        //TODO: Implement the function
        if(otherList == null) {
            return null;
        }
        if(otherList.head == null) {
            return otherList.getBlankList();
        }
        else{
            SelfOrderingList<T> clonedList = otherList.getBlankList();
            Node<T> tempListHead = otherList.head;
            while(tempListHead != null){
                clonedList.insert(tempListHead.data);
                tempListHead = tempListHead.next;
            }
            return clonedList;
        }
    }
}
