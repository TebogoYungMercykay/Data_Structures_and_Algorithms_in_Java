public class Main {
    public static void main(String[] args) {
        Test1();
        Test2();
    }
    public static void Test2() {
        minDHeap<Integer> heap = new minDHeap<>(5);
        System.out.println("Testing the insert Method.");
        for (int i = 70; i >= 0; i--) {
            heap.insert(i);
        }
        System.out.println(heap);

        // * HELPER FUNCTIONS START HERE

        System.out.println("Testing the Helper Methods.");
        System.out.println("Testing Find.");
        System.out.println("index: " + heap.find(0));
        System.out.println("index: " + heap.find(5));
        System.out.println("index: " + heap.find(2));
        System.out.println("index: " + heap.find(1));
        System.out.println("index: " + heap.find(8));
        System.out.println("index: " + heap.find(9));
        System.out.println("index: " + heap.find(10));

        System.out.println("Testing Parent().");
        System.out.println("index: " + heap.parent(heap.find(0)));
        System.out.println("index: " + heap.parent(heap.find(5)));
        System.out.println("index: " + heap.parent(heap.find(2)));
        System.out.println("index: " + heap.parent(heap.find(1)));
        System.out.println("index: " + heap.parent(heap.find(8)));
        System.out.println("index: " + heap.parent(heap.find(9)));
        System.out.println("index: " + heap.parent(heap.find(10)));

        System.out.println("\nTesting spouses().");
        System.out.print("spouses for " + 0 + ": ");
        printArray(heap.spouses(heap.find(0)));
        System.out.print("spouses for " + 5 + ": ");
        printArray(heap.spouses(heap.find(5)));
        System.out.print("spouses for " + 2 + ": ");
        printArray(heap.spouses(heap.find(2)));
        System.out.print("spouses for " + 1 + ": ");
        printArray(heap.spouses(heap.find(1)));
        System.out.print("spouses for " + 8 + ": ");
        printArray(heap.spouses(heap.find(8)));
        System.out.print("spouses for " + 9 + ": ");
        printArray(heap.spouses(heap.find(9)));
        System.out.print("spouses for " + 10 + ": ");
        printArray(heap.spouses(heap.find(10)));

        System.out.println("\nTesting resize_array().");
        heap.resize_array(13);
        System.out.println(heap);
        heap.resize_array(11);
        System.out.println(heap);
        heap.resize_array(5);
        System.out.println(heap);
        heap.resize_array(13);
        System.out.println(heap);
        heap.resize_array(0);
        System.out.print("Empty: " + heap);
        System.out.println("\nTesting the insert Method.");
        for (int i = 70; i >= 0; i--) {
            heap.insert(i);
        }
        System.out.println(heap);

        // * HELPER FUNCTIONS END HERE

        System.out.println("\nTesting min() method");
        System.out.println(heap.min(1));
        System.out.println(heap.min(heap.find(5)));
        System.out.println(heap.min(heap.find(2)));
        System.out.println(heap.min(heap.find(1)));
        System.out.println(heap.min(heap.find(7)));
        System.out.println(heap.min(23));

        System.out.println("\nTesting max() method");
        System.out.println(heap.max(0));
        System.out.println(heap.max(heap.find(5)));
        System.out.println(heap.max(heap.find(2)));
        System.out.println(heap.max(heap.find(1)));
        System.out.println(heap.max(heap.find(10)));
        System.out.println(heap.max(23));

        System.out.println("\nTesting the pathToRoot() method");
        System.out.println(heap.pathToRoot(0));
        System.out.println(heap.pathToRoot(5));
        System.out.println(heap.pathToRoot(2));
        System.out.println(heap.pathToRoot(1));
        System.out.println(heap.pathToRoot(6));
        System.out.println(heap.pathToRoot(4));
        System.out.println(heap.pathToRoot(8));
        System.out.println(heap.pathToRoot(18));

        System.out.println("\nTesting the Change D Method");
        heap.changeD(2);
        System.out.println(heap);
        System.out.println("\nTesting the pathToRoot method");
        System.out.println(heap.pathToRoot(5));
        System.out.println("DONE");
        System.out.println(heap);
        System.out.println("\nFinally testing the remove method");
        for (int i = 0; i <= 20; i++) {
            heap.remove(i);
        }
        System.out.println(heap);
    }
    // TEST 1
    public static void Test1() {
        minDHeap<Integer> heap = new minDHeap<>(3);
        System.out.println("Testing the insert Method.");
        for (int i = 10; i >= 0; i--) {
            heap.insert(i);
        }
        System.out.println(heap);

        // * HELPER FUNCTIONS START HERE

        System.out.println("Testing the Helper Methods.");
        System.out.println("Testing Find.");
        System.out.println("index: " + heap.find(0));
        System.out.println("index: " + heap.find(5));
        System.out.println("index: " + heap.find(2));
        System.out.println("index: " + heap.find(1));
        System.out.println("index: " + heap.find(8));
        System.out.println("index: " + heap.find(9));
        System.out.println("index: " + heap.find(10));

        System.out.println("Testing Parent().");
        System.out.println("index: " + heap.parent(heap.find(0)));
        System.out.println("index: " + heap.parent(heap.find(5)));
        System.out.println("index: " + heap.parent(heap.find(2)));
        System.out.println("index: " + heap.parent(heap.find(1)));
        System.out.println("index: " + heap.parent(heap.find(8)));
        System.out.println("index: " + heap.parent(heap.find(9)));
        System.out.println("index: " + heap.parent(heap.find(10)));

        System.out.println("\nTesting spouses().");
        System.out.print("spouses for " + 0 + ": ");
        printArray(heap.spouses(heap.find(0)));
        System.out.print("spouses for " + 5 + ": ");
        printArray(heap.spouses(heap.find(5)));
        System.out.print("spouses for " + 2 + ": ");
        printArray(heap.spouses(heap.find(2)));
        System.out.print("spouses for " + 1 + ": ");
        printArray(heap.spouses(heap.find(1)));
        System.out.print("spouses for " + 8 + ": ");
        printArray(heap.spouses(heap.find(8)));
        System.out.print("spouses for " + 9 + ": ");
        printArray(heap.spouses(heap.find(9)));
        System.out.print("spouses for " + 10 + ": ");
        printArray(heap.spouses(heap.find(10)));

        System.out.println("\nTesting resize_array().");
        heap.resize_array(13);
        System.out.println(heap);
        heap.resize_array(11);
        System.out.println(heap);
        heap.resize_array(5);
        System.out.println(heap);
        heap.resize_array(13);
        System.out.println(heap);
        heap.resize_array(0);
        System.out.print("Empty: " + heap);
        System.out.println("\nTesting the insert Method.");
        for (int i = 10; i >= 0; i--) {
            heap.insert(i);
        }
        System.out.println(heap);

        // * HELPER FUNCTIONS END HERE

        System.out.println("\nTesting min() method");
        System.out.println(heap.min(1));
        System.out.println(heap.min(heap.find(5)));
        System.out.println(heap.min(heap.find(2)));
        System.out.println(heap.min(heap.find(1)));
        System.out.println(heap.min(heap.find(7)));
        System.out.println(heap.min(23));

        System.out.println("\nTesting max() method");
        System.out.println(heap.max(0));
        System.out.println(heap.max(heap.find(5)));
        System.out.println(heap.max(heap.find(2)));
        System.out.println(heap.max(heap.find(1)));
        System.out.println(heap.max(heap.find(10)));
        System.out.println(heap.max(23));

        System.out.println("\nTesting the pathToRoot() method");
        System.out.println(heap.pathToRoot(0));
        System.out.println(heap.pathToRoot(5));
        System.out.println(heap.pathToRoot(2));
        System.out.println(heap.pathToRoot(1));
        System.out.println(heap.pathToRoot(6));
        System.out.println(heap.pathToRoot(4));
        System.out.println(heap.pathToRoot(8));
        System.out.println("Empty: " + heap.pathToRoot(18));

        System.out.println("\nTesting the Change D Method");
        heap.changeD(2);
        System.out.println(heap);
        System.out.println("\nTesting the pathToRoot method");
        System.out.println(heap.pathToRoot(5));
        System.out.println("DONE");
        System.out.println(heap);
        System.out.println("\nFinally testing the remove method");
        for (int i = 0; i <= 5; i++) {
            heap.remove(i);
        }
        System.out.println(heap);
    }
    public static void printArray(int[] array){
        for(int i = 0; i < array.length; i++){
            if(array[i] != -1){
                System.out.print(array[i] + " ");
            }
        }
        System.out.println();
    }
}

/*
public void remove(T val) {
    // find the index of the value to be removed
    int index = -1;
    for (int i = 0; i < size; i++) {
        if (nodes[i].equals(val)) {
            index = i;
            break;
        }
    }
    if (index == -1) {
        return;  // value not found
    }
    // swap the value to be removed with the last element in the heap
    nodes[index] = nodes[size-1];
    size--;
    // swap down until the node is in the correct position
    while (true) {
        // find the smallest child of the node
        int smallestChild = index*d+1;
        if (smallestChild >= size) {
            break;  // no spouses
        }
        for (int j = 2; j <= d; j++) {
            if (index*d+j >= size) {
                break;
            }
            if (nodes[index*d+j].compareTo(nodes[smallestChild]) < 0) {
                smallestChild = index*d+j;
            }
        }
        // swap with the smallest child if necessary
        if (nodes[smallestChild].compareTo(nodes[index]) < 0) {
            swapData(index, smallestChild);
            index = smallestChild;  // continue swapping down
        } else {
            break;  // node is in the correct position
        }
    }
}
*/