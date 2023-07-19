public class App {
    /*  EXTENSIVE TESTING MAIN   */
    public static void main(String[] args) {
        test0();
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
    }

    public static void test0() {
        System.out.println("-------------- TEST 0 - NORMAL STUFF -------------");
        BinaryTree<Integer> t = new StandardBinaryTree<>();
        t.insert(2);
        t.insert(1);
        t.insert(3);
        System.out.println("Tree Height");
        System.out.println(t.height() + "\n");
        System.out.println("Tree Number of Leaves");
        System.out.println(t.numLeavesInTree() + "\n");
        System.out.println("Depth First Traversal");
        t.depthFirstTraversal();
        System.out.println("\nFind parent of [3]");
        t.findParent(3);
        System.out.println("\nFind [1]");
        t.find(1);
        System.out.println("\nContains [5]");
        t.contains(5);
        System.out.println("-------------------- THE END ---------------------");
    }

    public static void test1() {
        System.out.println("-------- TEST 1 - MIRRORED: NORMAL STUFF ---------");
        BinaryTree<Integer> t = new StandardBinaryTree<>();
        t.insert(2);
        t.insert(1);
        t.insert(3);
        t = t.convertTree();
        System.out.println("Tree Height");
        System.out.println(t.height() + "\n");
        System.out.println("Tree Number of Leaves");
        System.out.println(t.numLeavesInTree() + "\n");
        System.out.println("Depth First Traversal");
        t.depthFirstTraversal();
        System.out.println("\nFind parent of [3]");
        t.findParent(3);
        System.out.println("\nFind [1]");
        t.find(1);
        System.out.println("\nContains [5]");
        t.contains(5);
        System.out.println("-------------------- THE END ---------------------");
    }

    public static void test2(){
        System.out.println();
        System.out.println("----------- TEST 2 - STANDARD TREE ---------------");
        // Standard Tree:
        //      5
        //     / \
        //    3   7
        //   / \ / \
        //  2  4 6  8
        BinaryTree<Integer> t = new StandardBinaryTree<>();
        t.insert(5);
        t.insert(3);
        t.insert(7);
        t.insert(2);
        t.insert(4);
        t.insert(6);
        t.insert(8);
        System.out.println("Tree Height");
        System.out.println(t.height() + "\n");
        System.out.println("Tree Number of Leaves");
        System.out.println(t.numLeavesInTree() + "\n");
        System.out.println("Depth First Traversal");
        t.depthFirstTraversal();
        System.out.println("\nFind parent of [5]");
        t.findParent(5);
        System.out.println("\nFind parent of [3]");
        t.findParent(3);
        System.out.println("\nFind parent of [4]");
        t.findParent(4);
        System.out.println("\nFind parent of [8]");
        t.findParent(8);
        System.out.println("\nFind [5]");
        t.find(1);
        System.out.println("\nFind [2]");
        t.find(1);
        System.out.println("\nFind [7]");
        t.find(1);
        System.out.println("\nContains [5]");
        t.contains(5);
        System.out.println("\nContains [9]");
        t.contains(9);
        System.out.println("\nContains [6]");
        t.contains(6);
        System.out.println("\nContains [4]");
        t.contains(4);
        System.out.println("\nContains [2]");
        t.contains(2);
        System.out.println("\nContains [3]");
        t.contains(3);
        System.out.println("\nContains [7]");
        t.contains(7);
        System.out.println("\nContains [1]");
        t.contains(1);
        System.out.println("\nPerfectly Balanced");
        t.perfectlyBalanced();
        System.out.println();
        System.out.println("-------------------- THE END ---------------------");
    }

    public static void test3(){
        System.out.println();
        System.out.println("----------- TEST 3 - MIRRORED TREE ---------------");
        // Before mirroring:
        //      5
        //     / \
        //    3   7
        //   / \ / \
        //  2  4 6  8

        // After mirroring:
        //      5
        //     / \
        //    7   3
        //   / \ / \
        //  8  6 4  2
        BinaryTree<Integer> t = new StandardBinaryTree<>();
        t.insert(5);
        t.insert(3);
        t.insert(7);
        t.insert(2);
        t.insert(4);
        t.insert(6);
        t.insert(8);
        t = t.convertTree();
        System.out.println("Tree Height");
        System.out.println(t.height() + "\n");
        System.out.println("Tree Number of Leaves");
        System.out.println(t.numLeavesInTree() + "\n");
        System.out.println("Depth First Traversal");
        t.depthFirstTraversal();
        System.out.println("\nFind parent of [5]");
        t.findParent(5);
        System.out.println("\nFind parent of [3]");
        t.findParent(3);
        System.out.println("\nFind parent of [4]");
        t.findParent(4);
        System.out.println("\nFind parent of [8]");
        t.findParent(8);
        System.out.println("\nFind [5]");
        t.find(1);
        System.out.println("\nFind [2]");
        t.find(1);
        System.out.println("\nFind [7]");
        t.find(1);
        System.out.println("\nContains [5]");
        t.contains(5);
        System.out.println("\nContains [9]");
        t.contains(9);
        System.out.println("\nContains [6]");
        t.contains(6);
        System.out.println("\nContains [4]");
        t.contains(4);
        System.out.println("\nContains [2]");
        t.contains(2);
        System.out.println("\nContains [3]");
        t.contains(3);
        System.out.println("\nContains [7]");
        t.contains(7);
        System.out.println("\nContains [1]");
        t.contains(1);
        System.out.println("\nPerfectly Balanced");
        t.perfectlyBalanced();
        System.out.println();
        System.out.println("-------------------- THE END ---------------------");
    }

    public static void test4() {
        System.out.println("-------------- TEST 4 - NORMAL STUFF -------------");
        // Before mirroring:
        //      5
        //     / \
        //    3   7
        //   / \ / \
        //  2  4 6  8

        // After mirroring:
        //      5
        //     / \
        //    7   3
        //   / \ / \
        //  8  6 4  2
        BinaryTree<Integer> t = new StandardBinaryTree<>();
        t.insert(5);
        t.insert(3);
        t.insert(7);
        t.insert(2);
        t.insert(4);
        t.insert(6);
        t.insert(8);
        // t = t.convertTree();
        System.out.println("Tree Height");
        System.out.println(t.height() + "\n");
        System.out.println("Tree Number of Leaves");
        System.out.println(t.numLeavesInTree() + "\n");
        System.out.println("Depth First Traversal");
        t.depthFirstTraversal();
        System.out.println("\nFind parent of [3]");
        t.findParent(3);
        System.out.println("\nFind parent of [4]");
        t.findParent(4);
        System.out.println("\nFind parent of [8]");
        t.findParent(8);
        System.out.println("-------------------- THE END ---------------------");
    }

    public static void test5() {
        System.out.println("-------------- TEST 5 - NORMAL STUFF -------------");
        // Before mirroring:
        //      5
        //     / \
        //    3   7
        //   / \ / \
        //  2  4 6  8

        // After mirroring:
        //      5
        //     / \
        //    7   3
        //   / \ / \
        //  8  6 4  2
        BinaryTree<Integer> t = new StandardBinaryTree<>();
        t.insert(5);
        t.insert(3);
        t.insert(7);
        t.insert(2);
        t.insert(4);
        t.insert(6);
        t.insert(8);
        t = t.convertTree();
        System.out.println("Tree Height");
        System.out.println(t.height() + "\n");
        System.out.println("Tree Number of Leaves");
        System.out.println(t.numLeavesInTree() + "\n");
        System.out.println("Depth First Traversal");
        t.depthFirstTraversal();
        System.out.println("\nFind parent of [3]");
        t.findParent(3);
        System.out.println("\nFind parent of [4]");
        t.findParent(4);
        System.out.println("\nFind parent of [8]");
        t.findParent(8);
        System.out.println("-------------------- THE END ---------------------");
    }
    public static void test6() {
        System.out.println("-------- TEST 6 - MIRRORED: NORMAL STUFF ---------");
        BinaryTree<Integer> p = new StandardBinaryTree<>();
        p.insert(2);
        p.insert(1);
        p.insert(3);

        BinaryTree<Integer> t = p.convertTree();
        System.out.println("Tree Height");
        System.out.println(t.height() + "\n");
        System.out.println("Tree Number of Leaves");
        System.out.println(t.numLeavesInTree() + "\n");
        System.out.println("Depth First Traversal");
        t.depthFirstTraversal();
        System.out.println("Depth First Traversal");
        p.depthFirstTraversal();
        System.out.println("\nFind parent of [3]");
        t.findParent(3);
        System.out.println("\nFind [1]");
        t.find(1);
        System.out.println("\nContains [5]");
        t.contains(5);
        System.out.println("-------------------- THE END ---------------------");
    }

    public static void test7() {
        System.out.println("-------- TEST 7 - MIRRORED: NORMAL STUFF ---------");
        BinaryTree<Integer> p = new MirroredBinaryTree<>();
        p.insert(2);
        p.insert(1);
        p.insert(3);

        BinaryTree<Integer> t = p.convertTree();
        System.out.println("Tree Height");
        System.out.println(t.height() + "\n");
        System.out.println("Tree Number of Leaves");
        System.out.println(t.numLeavesInTree() + "\n");
        System.out.println("Depth First Traversal");
        t.depthFirstTraversal();
        System.out.println("Depth First Traversal");
        p.depthFirstTraversal();
        System.out.println("\nFind parent of [3]");
        t.findParent(3);
        System.out.println("\nFind [1]");
        t.find(1);
        System.out.println("\nContains [5]");
        t.contains(5);
        System.out.println("-------------------- THE END ---------------------");
    }
}

/*
Tree Height
1

Tree Number of Leaves
3

Depth First Traversal
L[null]<-[1]->R[null]
L[1]<-[2]->R[3]
L[null]<-[3]->R[null]

Find parent of [3]
L[1]<-[2]->R[3]

Find [1]
L[1]<-[2]->R[3]
L[null]<-[1]->R[null]

Contains [5]
L[1]<-[2]->R[3]
L[null]<-[3]->R[null]
 */