public class MoveToFrontList<T extends Comparable<T>> extends SelfOrderingList<T> {
    @Override
    public SelfOrderingList<T> getBlankList() {
        //TODO: Implement the function
        return new MoveToFrontList<T>();
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
        else{
            Boolean contained = false;
            for(Node<T> pointerVariable = this.head; pointerVariable != null; pointerVariable = pointerVariable.next){
                if(pointerVariable.data.compareTo(data) == 0){
                    contained = true;
                    break;
                }
            }
            if(contained == true){
                Node<T> predecesorNode = null, accessedValue = null;
                accessedValue = this.head;
                while(accessedValue != null && accessedValue.data.compareTo(data) != 0){
                    predecesorNode = accessedValue;
                    accessedValue = accessedValue.next;
                }
                predecesorNode.next = accessedValue.next;
                if(accessedValue.next != null){
                    (accessedValue.next).prev = predecesorNode;
                }
                accessedValue.prev = null;
                accessedValue.next = this.head;
                this.head.prev = accessedValue;
                this.head = accessedValue;
            }
            else{
                return;
            }
        }
    }
}
