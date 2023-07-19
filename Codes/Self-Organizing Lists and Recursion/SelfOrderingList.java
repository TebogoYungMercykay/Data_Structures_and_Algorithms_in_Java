abstract class SelfOrderingList<T extends Comparable<T>> {
    public Node<T> head = null;

    public void insert(T data){
        //TODO: Implement the function
        Node<T> insertNode = new Node<T>(data);
        if(this.head == null){
            this.head = insertNode;
            return;
        }
        else{
            Node<T> nodePtr = this.head;
            while(nodePtr.next != null){
                nodePtr = nodePtr.next;
            }
            nodePtr.next = insertNode;
            insertNode.prev = nodePtr;
        }
    }

    public void remove(T data){
       //TODO: Implement the function
       Node<T> previousNode = null;
       Node<T> nodePtr = this.head;

       if(this.head == null){
           return;
       }
       while(nodePtr != null && nodePtr.data.compareTo(data) != 0){
           previousNode = nodePtr;
           nodePtr = nodePtr.next;
       }
       if(previousNode == null && this.head.data.compareTo(data) == 0){
           this.head = this.head.next;
           if(this.head != null){
               this.head.prev = null;
           }
           return;
       }
       if(nodePtr != null && previousNode != null  && nodePtr.data.compareTo(data) == 0){
           previousNode.next = nodePtr.next;
           if(previousNode.next != null){
               (previousNode.next).prev = previousNode;
           }
       }
    }

    public abstract void access(T data);

    public abstract SelfOrderingList<T> getBlankList();
}
