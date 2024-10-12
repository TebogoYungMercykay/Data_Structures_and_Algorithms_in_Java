/**
 * Name: Selepe Sello
 * Student Number: 20748052
 */



public class OrganisingList {

    public ListNode root = null;

    public OrganisingList() {

    }
    
    /**
     * Calculate the sum of keys recursively, starting with the given node until the end of the list
     * @return The sum of keys from the current node to the last node in the list
     * NOTE: 'int' and not 'Integer' here because it cannot return 'null'
     */
    public static int sumRec(ListNode node) {
        // Your code here...
        if(node.next != null){
            return node.key + sumRec(node.next);
        }
        return node.key;
    }

    /**
     * Calculate and set the data for the given node.
     * @return The calculated data for the given node
     * NOTE: 'int' and not 'Integer' here because it cannot return 'null'
     */
    public static int dataRec(ListNode node) {
        // Your code here...
        if(node.next != null){
            return node.data = ((node.key * sumRec(node)) - dataRec(node.next));
        }
        return node.key;
    }

    /**
     * Calculate the data field of all nodes in the list using the recursive functions.
     * DO NOT MODIFY!
     */
    public void calculateData() {
        if (root != null) {
            dataRec(root);
        }
    }

    /**
     * Retrieve the data for the node with the specified key and swap the
     * accessed node with its predecessor, then recalculate data fields.
     * @return The data of the node before it has been moved,
     * otherwise 'null' if the key does not exist.
     */
    public Integer getData(Integer key) {
        // Your code here...
        if(contains(key)==true && root != null){
            ListNode tempNode = root;
            ListNode prevNode = null;
            ListNode extraNode = null;
            while(tempNode != null) {
                if (tempNode.key == key) {
                    if (extraNode != null) {
                        extraNode.next = tempNode;
                        prevNode.next = tempNode.next;
                        tempNode.next = prevNode;
                    }
                    else if (prevNode != null) {
                        prevNode.next = tempNode.next;
                        tempNode.next = prevNode;
                        root = tempNode;
                    }
                    int pNode = tempNode.data;
                    calculateData();
                    return pNode;
                }
                extraNode = prevNode;
                prevNode = tempNode;
                tempNode = tempNode.next;
            }
            return null;
        }
        calculateData();
        return null;
    }

    /**
     * Delete the node with the given key.
     * calculateData() should be called after deletion.
     * If the key does not exist, do nothing.
     */
    public void delete(Integer key) {
        // Your code here...
        if(contains(key)==true){
            ListNode tempNode = root;
            while((tempNode.next != null)){
                if(tempNode.next.key == key){
                    tempNode.next = tempNode.next.next;

                    calculateData();
                    break;}
                tempNode = tempNode.next;
                // System.out.println(temp.key);
            }
        }
        return;
    }

    /**
     * Insert a new key into the linked list.
     * 
     * New nodes should be inserted to the back
     * Duplicate keys should not be added.
     */
    public void insert(Integer key) {
        // Your code here...
        // System.out.println("Hello World2");
        if(!(root != null)){
            ListNode tempList = new ListNode(key);
            tempList.next = null;
            root = tempList;
            calculateData();
        }
        else if(!(contains(key)==true)){
            ListNode tempList = new ListNode(key);
            tempList.data = key;
            tempList.next = null;
            calculateData();
            ListNode curr = root;
            while(curr.next != null){curr = curr.next;}
            curr.next = tempList;
            calculateData();
        }
        // else{
        //     else if(!(contains(key)==true)){
        //         ListNode tempList = new ListNode(key);
        //         tempList.data = key;
        //         tempList.next = null;
        //         ListNode curr = root;
        //         while(curr.next != null){curr = curr.next;}
        //         curr.next = tempList;
        //         calculateData();
        //     }
        // }
        return;
    }

    /**
     * Check if a key is contained in the list
     * @return true if the key is in the list, otherwise false
     */
    public Boolean contains(Integer key) {
        // Your code here...
        if(root != null){
            ListNode tempNode = root;
            while(tempNode != null){
                if(tempNode.key == key){return true;}
                tempNode = tempNode.next;
            }
        }
        return false;
    }

    /**
     * Return a string representation of the Linked List.
     * DO NOT MODIFY!
     */
    public String toString() {
        if (root == null) {
            return "List is empty";
        }

        String result = "";
        for (ListNode node = root; node != null; node = node.next) {
            result = result.concat("[K: " + node.key + ", D: " + node.data + "]");

            if (node.next != null) {
                result = result.concat(" ");
            }
        }

        return result;
    }

    /**
     * Return a string representation of the linked list, showing only the keys of nodes.
     * DO NOT MODIFY!
     */
    public String toStringKeysOnly() {

        if (root == null) {
            return "List is empty";
        }

        String result = "";
        for (ListNode node = root; node != null; node = node.next) {
            result = result + node.key;

            if (node.next != null) {
                result = result.concat(", ");
            }
        }

        return result;
    }
}