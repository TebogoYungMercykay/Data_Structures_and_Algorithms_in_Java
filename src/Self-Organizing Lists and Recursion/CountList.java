public class CountList<T extends Comparable<T>> extends SelfOrderingList<T>{
    @Override
    public SelfOrderingList<T> getBlankList() {
        //TODO: Implement the function
        return new CountList<T>();
    }

    @Override
    public void access(T data) {
        //TODO: Implement the function
        if(this.head == null){
            return;
        }
        if(this.head.data.compareTo(data) == 0){
            this.head.accessCount = this.head.accessCount + 1;
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
                Node<T> tempNode = this.head;
                while(tempNode != null){
                    if(tempNode.data.compareTo(data) == 0){
                        tempNode.accessCount += 1;
                        this.remove(tempNode.data);
                        break;
                    }
                    tempNode = tempNode.next;
                }
                // System.out.println(tempNode.toString());
                if(this.head != null && this.head.accessCount < tempNode.accessCount){
                    this.head.prev = tempNode;
                    tempNode.next = this.head;
                    tempNode.prev = null;
                    this.head = tempNode;
                }
                else if(this.head.next != null && this.head.next.accessCount < tempNode.accessCount){
                    (this.head.next).prev = tempNode;
                    tempNode.next = this.head.next;
                    tempNode.prev = this.head;
                    this.head.next = tempNode;
                }
                else{
                    Node<T> accessedNode = this.head;
                    Node<T> predecessor = null;
                    // 5[3]
                    // null<-0[4]<=>1[4]<=>2[3]<=>3[2]<=>4[1]<=>5[0]<=>6[0]->null
                    // predecessorr = 2[3]
                    // accessedNode = 3[2]
                    while(accessedNode != null && accessedNode.accessCount >= tempNode.accessCount){
                        predecessor = accessedNode;
                        accessedNode = accessedNode.next;
                    }
                    if(accessedNode == null){
                        predecessor.next = tempNode;
                        tempNode.prev = predecessor;
                        tempNode.next = accessedNode;
                    }
                    else{
                        predecessor.next = tempNode;
                        tempNode.prev = predecessor;
                        accessedNode.prev = tempNode;
                        tempNode.next = accessedNode;
                    }
                }
            }
            else{
                return;
            }
        }
    }
}
