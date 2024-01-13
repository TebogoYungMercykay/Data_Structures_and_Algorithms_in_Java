public class NaturalOrderList<T extends Comparable<T>> extends SelfOrderingList<T> {
    @Override
    public SelfOrderingList<T> getBlankList() {
        //TODO: Implement the function
        return new NaturalOrderList<T>();
    }

    @Override
    public void access(T data) {
        
    }

    @Override
    public void insert(T data) {
        //TODO: Implement the function
        Node<T> newNode = new Node<T>(data);
        if(this.head == null) {
            this.head = newNode;
        }
        else{
            Node<T> nodePtr = this.head;
            while(nodePtr.next != null && nodePtr.next.data.compareTo(data) >= 0) {
                nodePtr = nodePtr.next;
            }
            if(nodePtr == this.head){
                if(this.head.data.compareTo(data) >= 0){
                    newNode.next = this.head.next;
                    newNode.prev = this.head;
                    if(this.head.next != null){
                        (this.head.next).prev = newNode;
                    }
                    this.head.next = newNode;
                }
                else{
                    this.head = newNode;
                    newNode.next = nodePtr;
                    nodePtr.prev = newNode;
                }
            }
            else{
                newNode.next = nodePtr.next;
                newNode.prev = nodePtr;
                if(nodePtr.next != null){
                    (nodePtr.next).prev = newNode;
                }
                nodePtr.next = newNode;
            }
        }
    }
}
