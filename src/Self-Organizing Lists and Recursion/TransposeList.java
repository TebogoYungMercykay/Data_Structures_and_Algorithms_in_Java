public class TransposeList<T extends Comparable<T>> extends SelfOrderingList<T> {
    @Override
    public SelfOrderingList<T> getBlankList() {
        //TODO: Implement the function
        return new TransposeList<T>();
    }

    @Override
    public void access(T data) {
        //TODO: Implement the function
        if(this.head == null){
            return;
        }
        else if(this.head.data.compareTo(data) == 0){
            return;
        }
        else if(this.head.next != null && this.head.next.data.compareTo(data) == 0){
            Node<T> currentNode = null, prevNode = null;
            prevNode = this.head;
            currentNode = this.head.next;
            // Fixing the links
            (currentNode.next).prev = this.head;
            this.head.next = currentNode.next;
            this.head.prev = currentNode;
            currentNode.next = prevNode;
            currentNode.prev = null;
            this.head = currentNode;
            return;
        }
        else{
            Boolean contained = false;
            for(Node<T> pointer = this.head; pointer != null; pointer = pointer.next){
                if(pointer.data.compareTo(data) == 0){
                    contained = true;
                    break;
                }
            }
            if(contained == true){
                Node<T> pred_PredecesorNode = null, predecesorNode = null, accessedValue = null;
                pred_PredecesorNode = predecesorNode = accessedValue = this.head;

                while(accessedValue != null && accessedValue.data.compareTo(data) != 0){
                    pred_PredecesorNode = predecesorNode;
                    predecesorNode = accessedValue;
                    accessedValue = accessedValue.next;
                }

                if(accessedValue.next != null){
                    (accessedValue.next).prev = predecesorNode;
                }
                predecesorNode.next = accessedValue.next;
                predecesorNode.prev = accessedValue;
                accessedValue.next = predecesorNode;
                accessedValue.prev = pred_PredecesorNode;
                pred_PredecesorNode.next = accessedValue;
                return;
            }
            else{
                return;
            }
        }
    }
}
