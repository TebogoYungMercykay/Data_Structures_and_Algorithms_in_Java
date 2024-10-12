// Author: Selepe Sello
// This Is A Testing Main For The Code
// import javax.xml.soap.Node;

public class App {
    public static void main(String[] args) throws Exception {
        //TODO: Implement an extensive testing main!!!
        //Note: the print function tests both next and previous links
        //comment part of the code you will not be testing
        mainTestSelfOrdering();
        mainTestNaturalOrdering();
        mainTestMoveToFront();
        mainTestTranspose();
        mainTestCountList();
        mainTestIterativeTraverse();
        mainTestRecursiveTraverse();
    }
    public static void print(SelfOrderingList<Integer> list)
    {
        Node<Integer> node = list.head;
        Node<Integer> nodeReverse = null;
        System.out.print("Printing List Foward: ");
        while(node!= null)
        {
            System.out.print(node.toString());
            if(node.next != null){
                System.out.print("<=>");
            }
            else{
                nodeReverse = node;
            }
            node = node.next;
        }
        System.out.println();
        System.out.print("Printing List Backward: ");
        while(nodeReverse!= null)
        {
            System.out.print(nodeReverse.toString());
            if(nodeReverse.prev != null){
                System.out.print("<=>");
            }
            nodeReverse = nodeReverse.prev;
        }
        System.out.println();
        System.out.println();
    }
    public static void mainTestSelfOrdering(){
        System.out.println(".......... Testing The Node Class ..............");
        Node<Integer> node1 = new Node<>(1);
        if(node1.prev == null){
            System.out.print("null<-");
        }
        System.out.print("(" + node1.data + "[" + node1.accessCount + "])");
        if(node1.next == null){
            System.out.print("->null");
        }
        System.out.println();
        System.out.println("toString(): " + node1.toString());
        System.out.println("............ Done ...........");
        System.out.println("....... Testing The SelfOrderingList Class .......");
        CountList <Integer> countList = new CountList<>();
        System.out.println("... Testing Insert Function ( Doubly Linked List Compliance ) ...");
        for(int i = 0; i <= 6; i++)
        {
            countList.insert(i);
        }
        for(int i = 0; i <= 4; i++)
        {
            countList.insert(i);
        }
        countList.insert(7);
        countList.insert(3);
        System.out.println("Done Inserting The Values Now Printing.");
        System.out.println("List Structure: 0<=>1<=>2<=>3<=>4<=>5<=>6<=>0<=>1<=>2<=>3<=>4<=>7<=>3");
        print(countList);
        System.out.println("... Testing Remove Function ( Doubly Linked List Compliance ) ...");
        System.out.println("Removing 0...");
        countList.remove(0);
        System.out.println("Done Removing, Now Printing The List.");
        System.out.println("List Structure: 1<=>2<=>3<=>4<=>5<=>6<=>0<=>1<=>2<=>3<=>4<=>7<=>3");
        print(countList);
        System.out.println("Removing 7 and 5...");
        countList.remove(7);
        countList.remove(5);
        System.out.println("Done Removing, Now Printing The List.");
        System.out.println("List Structure: 1<=>2<=>3<=>4<=>6<=>0<=>1<=>2<=>3<=>4<=>3");
        print(countList);
        System.out.println("Removing 0, 9 and 4...");
        countList.remove(0);
        countList.remove(9);
        countList.remove(4);
        System.out.println("Done Removing, Now Printing The List.");
        System.out.println("List Structure: 1<=>2<=>3<=>6<=>1<=>2<=>3<=>4<=>3");
        print(countList);
        System.out.println("............... DONE TESTING ..............");
    }
    public static void mainTestNaturalOrdering(){
        System.out.println("............ Testing Natural Order Class .............");
        System.out.println("Final List Structure: 7<=>6<=>5<=>5<=>4<=>4<=>3<=>3<=>3<=>3<=>2<=>2<=>1<=>1<=>0<=>0");
        System.out.println();
        NaturalOrderList<Integer> list = new NaturalOrderList<>();
        for(int i = 0; i <= 6; i++){
            list.insert(i);
        }
        print(list);
        for(int i = 0; i <= 4; i++){
            list.insert(i);
        }
        print(list);
        list.insert(7);
        list.insert(3);
        list.insert(3);
        list.insert(5);
        print(list);
        System.out.println("............... DONE TESTING ..............");
    }
    public static void mainTestMoveToFront(){
        System.out.println("............ Testing Move To Front  Class .............");
        MoveToFrontList<Integer> list = new MoveToFrontList<>();
        for(int i = 0; i <= 6; i++)
        {
            list.insert(i);
        }
        for(int i = 0; i <= 4; i++)
        {
            list.insert(i);
        }
        list.insert(7);
        list.insert(3);
        System.out.println("List Structure: 0<=>1<=>2<=>3<=>4<=>5<=>6<=>0<=>1<=>2<=>3<=>4<=>7<=>3");
        System.out.println("Final List Structure: 6<=>5<=>3<=>1<=>7<=>0<=>4<=>2<=>0<=>1<=>2<=>3<=>4<=>3");
        System.out.println();
        print(list);
        System.out.println("......... Testing Access Method ...................");
        System.out.println(".... Acessing 0 ...");
        list.access(0);
        print(list);
        System.out.println(".... Acessing 7 ...");
        list.access(7);
        print(list);
        System.out.println(".... Acessing 5 ...");
        list.access(5);
        print(list);
        System.out.println(".... Acessing 11 ...");
        list.access(11);
        print(list);
        System.out.println(".... Acessing 4 ...");
        list.access(4);
        print(list);
        System.out.println(".... Acessing 0 ...");
        list.access(0);
        print(list);
        System.out.println(".... Acessing 7 ...");
        list.access(7);
        print(list);
        System.out.println(".... Acessing 1 ...");
        list.access(1);
        print(list);
        System.out.println(".... Acessing 3 ...");
        list.access(3);
        print(list);
        System.out.println(".... Acessing 5 ...");
        list.access(5);
        print(list);
        System.out.println(".... Acessing 6 ...");
        list.access(6);
        print(list);
        System.out.println("Final List Structure: 6<=>5<=>3<=>1<=>7<=>0<=>4<=>2<=>0<=>1<=>2<=>3<=>4<=>3");
        System.out.println("............... DONE TESTING ..............");
    }
    public static void mainTestTranspose(){
        System.out.println("............ Testing Transpose Class ..............");
        TransposeList<Integer> list = new TransposeList<>();
        for(int i = 0; i <= 6; i++)
        {
            list.insert(i);
        }
        for(int i = 0; i <= 4; i++)
        {
            list.insert(i);
        }
        list.insert(7);
        list.insert(3);
        System.out.println("List Structure: 0<=>1<=>2<=>3<=>4<=>5<=>6<=>0<=>1<=>2<=>3<=>4<=>7<=>3");
        System.out.println("Final List Structure: 1<=>0<=>3<=>2<=>7<=>5<=>6<=>4<=>0<=>1<=>2<=>3<=>4<=>3");
        System.out.println();
        print(list);
        System.out.println("......... Testing Access Method ...................");
        System.out.println(".... Acessing 0 ...");
        list.access(0);
        print(list);
        System.out.println(".... Acessing 7 ...");
        list.access(7);
        print(list);
        System.out.println(".... Acessing 7 ...");
        list.access(7);
        print(list);
        System.out.println(".... Acessing 7 ...");
        list.access(7);
        print(list);
        System.out.println(".... Acessing 7 ...");
        list.access(7);
        print(list);
        System.out.println(".... Acessing 7 ...");
        list.access(7);
        print(list);
        System.out.println(".... Acessing 7 ...");
        list.access(7);
        print(list);
        System.out.println(".... Acessing 7 ...");
        list.access(7);
        print(list);
        System.out.println(".... Acessing 7 ...");
        list.access(7);
        print(list);
        System.out.println(".... Acessing 5 ...");
        list.access(5);
        print(list);
        System.out.println(".... Acessing 11 ...");
        list.access(11);
        print(list);
        System.out.println(".... Acessing 4 ...");
        list.access(4);
        print(list);
        System.out.println(".... Acessing 0 ...");
        list.access(0);
        print(list);
        System.out.println(".... Acessing 7 ...");
        list.access(7);
        print(list);
        System.out.println(".... Acessing 1 ...");
        list.access(1);
        print(list);
        System.out.println(".... Acessing 3 ...");
        list.access(3);
        print(list);
        System.out.println(".... Acessing 5 ...");
        list.access(5);
        print(list);
        System.out.println(".... Acessing 6 ...");
        list.access(6);
        print(list);
        System.out.println(".... Acessing 3 ...");
        list.access(3);
        print(list);
        System.out.println("Final List Structure: 1<=>0<=>3<=>2<=>7<=>5<=>6<=>4<=>0<=>1<=>2<=>3<=>4<=>3");
        System.out.println("............... DONE TESTING ..............");
    }
    public static void mainTestCountList(){
        System.out.println("Testing countlist fucntions.................................");
        CountList <Integer> list = new CountList<>();
        for(int i = 0; i <= 6; i++)
        {
            list.insert(i);
        }
        for(int i = 0; i <= 4; i++)
        {
            list.insert(i);
        }
        list.insert(7);
        list.insert(3);
        System.out.println("List Structure: 0[0]<=>1[0]<=>2[0]<=>3[0]<=>4[0]<=>5[0]<=>6[0]<=>0[0]<=>1[0]<=>2[0]<=>3[0]<=>4[0]<=>7[0]<=>3[0]");
        System.out.println("Final List Structure: 7[8]<=>0[4]<=>5[4]<=>4[2]<=>1[2]<=>3[2]<=>6[2]<=>2[1]<=>0[0]<=>1[0]<=>2[0]<=>3[0]<=>4[0]<=>3[0]");
        print(list);
        System.out.println("......... Testing Access Method ...................");
        System.out.println(".... Acessing 0 ...");
        list.access(0);
        print(list);
        System.out.println(".... Acessing 7 ...");
        list.access(7);
        print(list);
        System.out.println(".... Acessing 7 ...");
        list.access(7);
        print(list);
        System.out.println(".... Acessing 7 ...");
        list.access(7);
        print(list);
        System.out.println(".... Acessing 5 ...");
        list.access(5);
        print(list);
        System.out.println(".... Acessing 11 ...");
        list.access(11);
        print(list);
        System.out.println(".... Acessing 4 ...");
        list.access(4);
        print(list);
        System.out.println(".... Acessing 0 ...");
        list.access(0);
        print(list);
        System.out.println(".... Acessing 7 ...");
        list.access(7);
        print(list);
        System.out.println(".... Acessing 1 ...");
        list.access(1);
        print(list);
        System.out.println(".... Acessing 3 ...");
        list.access(3);
        print(list);
        System.out.println(".... Acessing 5 ...");
        list.access(5);
        print(list);
        System.out.println(".... Acessing 6 ...");
        list.access(6);
        print(list);
        System.out.println(".... Acessing 0 ...");
        list.access(0);
        print(list);
        System.out.println(".... Acessing 7 ...");
        list.access(7);
        print(list);
        System.out.println(".... Acessing 7 ...");
        list.access(7);
        print(list);
        System.out.println(".... Acessing 7 ...");
        list.access(7);
        print(list);
        System.out.println(".... Acessing 5 ...");
        list.access(5);
        print(list);
        System.out.println(".... Acessing 11 ...");
        list.access(11);
        print(list);
        System.out.println(".... Acessing 4 ...");
        list.access(4);
        print(list);
        System.out.println(".... Acessing 0 ...");
        list.access(0);
        print(list);
        System.out.println(".... Acessing 7 ...");
        list.access(7);
        print(list);
        System.out.println(".... Acessing 1 ...");
        list.access(1);
        print(list);
        System.out.println(".... Acessing 3 ...");
        list.access(3);
        print(list);
        System.out.println(".... Acessing 5 ...");
        list.access(5);
        print(list);
        System.out.println(".... Acessing 6 ...");
        list.access(6);
        print(list);
        System.out.println(".... Acessing 2 ...");
        list.access(2);
        print(list);
        System.out.println("Final List Structure: 7[8]<=>0[4]<=>5[4]<=>4[2]<=>1[2]<=>3[2]<=>6[2]<=>2[1]<=>0[0]<=>1[0]<=>2[0]<=>3[0]<=>4[0]<=>3[0]");
        System.out.println("............... DONE TESTING ..............");
    }
    public static void mainTestIterativeTraverse(){
        System.out.println("Testing Iterative traverse functions...........................");
        IterativeTraverse<Integer> iterative = new IterativeTraverse<>();
        CountList<Integer> iterativList = new CountList<>();
        for(int i = 0; i <= 6; i++)
        {
            iterativList.insert(i);
        }
        for(int i = 0; i <= 4; i++)
        {
            iterativList.insert(i);
        }
        iterativList.insert(7);
        iterativList.insert(3);
        System.out.println("Printing The Doubly List: ");
        print(iterativList);
        System.out.println("...... Testing the setList() method ......");
        iterative.setList(iterativList);
        System.out.println("........... Done Setting ............");
        System.out.println("List Structure: 0[0]<=>1[0]<=>2[0]<=>3[0]<=>4[0]<=>5[0]<=>6[0]<=>0[0]<=>1[0]<=>2[0]<=>3[0]<=>4[0]<=>7[0]<=>3[0]");
        System.out.println("........... Testing toString .............");
        System.out.println(iterative);
        System.out.println();
        System.out.println("...... Testing the Par-zed Constructor ......");
        IterativeTraverse<Integer> iterative2 = new IterativeTraverse<>(iterativList);
        System.out.println("............... toString .................");
        System.out.println(iterative2.toString());
        System.out.println();
        System.out.println("...... Testing Contains Method ......");
        if(iterative2.contains(1) == true){
            System.out.println("Contained!");
        }
        else{
            System.out.println("not found!");
        }
        if(iterative2.contains(0) == true){
            System.out.println("Contained!");
        }
        else{
            System.out.println("not found!");
        }
        if(iterative2.contains(7) == true){
            System.out.println("Contained!");
        }
        else{
            System.out.println("not found!");
        }
        if(iterative2.contains(3) == true){
            System.out.println("Contained!");
        }
        else{
            System.out.println("not found!");
        }
        if(iterative2.contains(34) == true){
            System.out.println("Contained!");
        }
        else{
            System.out.println("not found!");
        }
        System.out.println("...... Testing Size Method ......");
        System.out.println("Size: " + iterative2.size());
        System.out.println("...... Testing Find Method ......");
        System.out.println("Find (4[0]): " + iterative2.find(4).data);
        System.out.println("Find (7[0]): " + iterative2.find(7).data);
        Node<Integer> findNull = iterative2.find(23);
        if(findNull == null){
            System.out.println("Not found");
        }
        else{
            System.out.println("Find (23[x]): " + findNull.data);
        }
        System.out.println("...... Testing Get Method ......");
        System.out.println("Get index 13 - 3[0]: " + iterative2.get(13).toString());
        System.out.println("Get index 0 - 0[0]: " + iterative2.get(0).toString());
        System.out.println("Get index 5 - 5[0]: " + iterative2.get(5).toString());
        System.out.println("Get index -1 N/A: " + iterative2.get(-1));
        System.out.println("Get index 100 N/A: " + iterative2.get(100));
        System.out.println("...... Testing Reverse Method ......");
        System.out.println("Original List: " + iterative2.toString());
        SelfOrderingList<Integer> reverseList = iterative2.reverseList();
        System.out.println("Reversed List: ");
        print(reverseList);
        SelfOrderingList<Integer> cloneList = iterative2.clone(reverseList);
        System.out.println("...... Testing Clone Method ......");
        System.out.println("Cloning from Reverses List...");
        System.out.println("Cloned List: ");
        print(cloneList);
        System.out.println("............... DONE TESTING ..............");
    }
    public static void mainTestRecursiveTraverse(){
        RecursiveTraverse<Integer> recursive = new RecursiveTraverse<>();
        CountList<Integer> recursivList = new CountList<>();
        for(int i = 0; i <= 6; i++)
        {
            recursivList.insert(i);
        }
        for(int i = 0; i <= 4; i++)
        {
            recursivList.insert(i);
        }
        recursivList.insert(7);
        recursivList.insert(3);
        System.out.println("Printing The Doubly List: ");
        print(recursivList);
        System.out.println("...... Testing the setList() method ......");
        recursive.setList(recursivList);
        System.out.println("........... Done Setting ............");
        System.out.println("List Structure: 0[0]<=>1[0]<=>2[0]<=>3[0]<=>4[0]<=>5[0]<=>6[0]<=>0[0]<=>1[0]<=>2[0]<=>3[0]<=>4[0]<=>7[0]<=>3[0]");
        System.out.println("........... Testing toString .............");
        System.out.println(recursive.toString());
        System.out.println();
        System.out.println("...... Testing the Par-zed Constructor ......");
        RecursiveTraverse<Integer> recursive2 = new RecursiveTraverse<>(recursivList);
        System.out.println("............... toString .................");
        System.out.println(recursive2.toString());
        System.out.println();
        System.out.println("...... Testing Contains Method ......");
        if(recursive2.contains(1) == true){
            System.out.println("Contained!");
        }
        else{
            System.out.println("not found!");
        }
        if(recursive2.contains(0) == true){
            System.out.println("Contained!");
        }
        else{
            System.out.println("not found!");
        }
        if(recursive2.contains(7) == true){
            System.out.println("Contained!");
        }
        else{
            System.out.println("not found!");
        }
        if(recursive2.contains(3) == true){
            System.out.println("Contained!");
        }
        else{
            System.out.println("not found!");
        }
        if(recursive2.contains(34) == true){
            System.out.println("Contained!");
        }
        else{
            System.out.println("not found!");
        }
        System.out.println("...... Testing Size Method ......");
        System.out.println("Size: " + recursive2.size());
        System.out.println("...... Testing Find Method ......");
        System.out.println("Find (4[0]): " + recursive2.find(4).data);
        System.out.println("Find (7[0]): " + recursive2.find(7).data);
        Node<Integer> findNull = recursive2.find(23);
        if(findNull == null){
            System.out.println("Not found");
        }
        else{
            System.out.println("Find (23[x]): " + findNull.data);
        }
        System.out.println("...... Testing Get Method ......");
        System.out.println("Get index 13 - 3[0]: " + recursive2.get(13).toString());
        System.out.println("Get index 0 - 0[0]: " + recursive2.get(0).toString());
        System.out.println("Get index 5 - 5[0]: " + recursive2.get(5).toString());
        System.out.println("Get index -1 N/A: " + recursive2.get(-1));
        System.out.println("Get index 100 N/A: " + recursive2.get(100));
        System.out.println("...... Testing Reverse Method ......");
        System.out.println("Original List: " + recursive2.toString());
        SelfOrderingList<Integer> reverseList = recursive2.reverseList();
        System.out.println("Reversed List: ");
        print(reverseList);
        SelfOrderingList<Integer> cloneList = recursive2.clone(reverseList);
        System.out.println("...... Testing Clone Method ......");
        System.out.println("Cloning from Reverses List...");
        System.out.println("Cloned List: ");
        print(cloneList);
        System.out.println("............... DONE TESTING ..............");
    }
}