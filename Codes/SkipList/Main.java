public class Main {
    public static void main(String[] args) {


        SkipList<Integer> myList = new SkipList<>(3);
        System.out.println("initial boundary tests: ");
        if(myList.isEmpty()){                               //checking if empty
            System.out.println("list is empty");
        }else System.out.println("list is not empty");
        System.out.println();

        System.out.println("Printing empty: ");             //printing empty list
        System.out.println(myList);

        System.out.println();
        System.out.println("Deleting 54");                  //deleting from empty list

        if(myList.delete(54)) System.out.println("54 deleted");
        else System.out.println("54 not found");

        System.out.println();
        System.out.println("printing path for empty list"); //printing node in empty list
        myList.printSearchPath(57);


        System.out.println("-------------");

        /*Expected outputs
        initial boundary tests:
            list is empty

            Printing empty:
            [Lvl 2]
            [Lvl 1]
            [Lvl 0]

            Deleting 54
            54 not found

            printing path for empty list
            -------------

         */



        System.out.println("Inserts: ");
//        SkipList<Integer> myList = new SkipList<>(3);
        for (int i = 0; i < 10; i++) {
            System.out.println("inserting: " + i + ": ");       //removable
            myList.insert(i);
            System.out.println(myList);                         //removable
            System.out.println("");                             //removable
        }
            System.out.println("----------------");
        //inserts expected output v
        /*
        inserting: 0:
        [Lvl 2]
        [Lvl 1]
        [Lvl 0]->[0]

        inserting: 1:
        [Lvl 2]------>[1]
        [Lvl 1]------>[1]
        [Lvl 0]->[0]->[1]

        inserting: 2:
        [Lvl 2]------>[1]
        [Lvl 1]------>[1]
        [Lvl 0]->[0]->[1]->[2]

        inserting: 3:
        [Lvl 2]------>[1]------>[3]
        [Lvl 1]------>[1]------>[3]
        [Lvl 0]->[0]->[1]->[2]->[3]

        inserting: 4:
        [Lvl 2]------>[1]------>[3]->[4]
        [Lvl 1]------>[1]------>[3]->[4]
        [Lvl 0]->[0]->[1]->[2]->[3]->[4]

        inserting: 5:
        [Lvl 2]------>[1]------>[3]->[4]
        [Lvl 1]------>[1]------>[3]->[4]
        [Lvl 0]->[0]->[1]->[2]->[3]->[4]->[5]

        inserting: 6:
        [Lvl 2]------>[1]------>[3]->[4]
        [Lvl 1]------>[1]------>[3]->[4]
        [Lvl 0]->[0]->[1]->[2]->[3]->[4]->[5]->[6]

        inserting: 7:
        [Lvl 2]------>[1]------>[3]->[4]
        [Lvl 1]------>[1]------>[3]->[4]
        [Lvl 0]->[0]->[1]->[2]->[3]->[4]->[5]->[6]->[7]

        inserting: 8:
        [Lvl 2]------>[1]------>[3]->[4]
        [Lvl 1]------>[1]------>[3]->[4]---------------->[8]
        [Lvl 0]->[0]->[1]->[2]->[3]->[4]->[5]->[6]->[7]->[8]

        inserting: 9:
        [Lvl 2]------>[1]------>[3]->[4]
        [Lvl 1]------>[1]------>[3]->[4]---------------->[8]
        [Lvl 0]->[0]->[1]->[2]->[3]->[4]->[5]->[6]->[7]->[8]->[9]

        -----------------

         */
        System.out.println(myList);
        System.out.println();
        System.out.print("searching for 0\t");            //first node search
        myList.printSearchPath(0);
        System.out.print("Searching for 8\t");              //searching middle
        myList.printSearchPath(8);
        System.out.print("Searching for 2\t");              //searching middle
        myList.printSearchPath(2);
        System.out.print("searching for 9\t");            //last node search
        myList.printSearchPath(9);

        /*
        [Lvl 2]------>[1]------>[3]->[4]
        [Lvl 1]------>[1]------>[3]->[4]---------------->[8]
        [Lvl 0]->[0]->[1]->[2]->[3]->[4]->[5]->[6]->[7]->[8]->[9]

        Searching for 0 [1][1][0]
        Searching for 8	[1][3][4][8]
        Searching for 2	[1][3][3][2]
        Searching for 9 [1][3][4][8][9]
         */

        System.out.println("--------------");
        System.out.println("Deleting");

        System.out.println("Deleting 0");
        myList.delete(0);                   //first node delete
        System.out.println(myList);
        System.out.println();
        System.out.println("Deleting 3");
        myList.delete(3);                   //middle delete
        System.out.println(myList);
        System.out.println();
        System.out.println("Deleting 9");
        myList.delete(9);                   //last node delete
        System.out.println(myList);
        System.out.println();
        System.out.println("Deleting 689420");    //Deleting non-existent node
        if(myList.delete(69720)){
            System.out.println("69420 deleted");
        }else{
            System.out.println("69420 not found");
        }

        System.out.println();
        /*Expected output

        Deleting
        Deleting 0
        [Lvl 2]->[1]------>[3]->[4]
        [Lvl 1]->[1]------>[3]->[4]---------------->[8]
        [Lvl 0]->[1]->[2]->[3]->[4]->[5]->[6]->[7]->[8]->[9]

        Deleting 3
        [Lvl 2]->[1]------>[4]
        [Lvl 1]->[1]------>[4]---------------->[8]
        [Lvl 0]->[1]->[2]->[4]->[5]->[6]->[7]->[8]->[9]

        Deleting 9
        [Lvl 2]->[1]------>[4]
        [Lvl 1]->[1]------>[4]---------------->[8]
        [Lvl 0]->[1]->[2]->[4]->[5]->[6]->[7]->[8]

        Deleting 69420
        69420 not found
         */

        System.out.println("---------------------");
        System.out.println("Non-integer testing");
        SkipList<String> List3 = new SkipList<>(6);


        String[] arr = new String[]{"tre", "eret", "itor", "etie", "oer", "tff", "grete", "egregg", "cdjvb"};
        for(int i = 0; i < 9; i++){
            System.out.println("inserting: " + arr[i] + ": ");
            List3.insert(arr[i]);
            System.out.println(List3);
            System.out.println("");
        }

        System.out.println("Deleting oer");
        List3.delete("oer");
        System.out.println(List3);
        System.out.println("");

        System.out.print("searching for tff\t");
        List3.printSearchPath("tff");
        System.out.println("");
        System.out.println("-----------------------------");

        /*Expected output
            ---------------------
        Non-integer testing
        inserting: tre:
        [Lvl 5]
        [Lvl 4]
        [Lvl 3]
        [Lvl 2]
        [Lvl 1]
        [Lvl 0]->[tre]

        inserting: eret:
        [Lvl 5]
        [Lvl 4]
        [Lvl 3]
        [Lvl 2]
        [Lvl 1]
        [Lvl 0]->[eret]->[tre]

        inserting: itor:
        [Lvl 5]
        [Lvl 4]
        [Lvl 3]
        [Lvl 2]
        [Lvl 1]
        [Lvl 0]->[eret]->[itor]->[tre]

        inserting: etie:
        [Lvl 5]
        [Lvl 4]
        [Lvl 3]
        [Lvl 2]
        [Lvl 1]--------->[etie]
        [Lvl 0]->[eret]->[etie]->[itor]->[tre]

        inserting: oer:
        [Lvl 5]
        [Lvl 4]
        [Lvl 3]
        [Lvl 2]------------------------->[oer]
        [Lvl 1]--------->[etie]--------->[oer]
        [Lvl 0]->[eret]->[etie]->[itor]->[oer]->[tre]

        inserting: tff:
        [Lvl 5]
        [Lvl 4]
        [Lvl 3]
        [Lvl 2]------------------------->[oer]
        [Lvl 1]--------->[etie]--------->[oer]->[tff]
        [Lvl 0]->[eret]->[etie]->[itor]->[oer]->[tff]->[tre]

        inserting: grete:
        [Lvl 5]
        [Lvl 4]
        [Lvl 3]
        [Lvl 2]---------------------------------->[oer]
        [Lvl 1]--------->[etie]->[grete]--------->[oer]->[tff]
        [Lvl 0]->[eret]->[etie]->[grete]->[itor]->[oer]->[tff]->[tre]

        inserting: egregg:
        [Lvl 5]
        [Lvl 4]
        [Lvl 3]
        [Lvl 2]-------------------------------------------->[oer]
        [Lvl 1]->[egregg]--------->[etie]->[grete]--------->[oer]->[tff]
        [Lvl 0]->[egregg]->[eret]->[etie]->[grete]->[itor]->[oer]->[tff]->[tre]

        inserting: cdjvb:
        [Lvl 5]
        [Lvl 4]->[cdjvb]
        [Lvl 3]->[cdjvb]
        [Lvl 2]->[cdjvb]-------------------------------------------->[oer]
        [Lvl 1]->[cdjvb]->[egregg]--------->[etie]->[grete]--------->[oer]->[tff]
        [Lvl 0]->[cdjvb]->[egregg]->[eret]->[etie]->[grete]->[itor]->[oer]->[tff]->[tre]

        Deleting oer
        [Lvl 5]
        [Lvl 4]->[cdjvb]
        [Lvl 3]->[cdjvb]
        [Lvl 2]->[cdjvb]
        [Lvl 1]->[cdjvb]->[egregg]--------->[etie]->[grete]--------->[tff]
        [Lvl 0]->[cdjvb]->[egregg]->[eret]->[etie]->[grete]->[itor]->[tff]->[tre]

        Searching for tff [cdjvb][egregg][etie][grete][tff]

         */

        System.out.println("Char testing");

        SkipList<Character> l1 = new SkipList<>(6);

        Character[] arr1 = new Character[]{'a', 'b', 'c', 'v', 'd', 'g', 'j', 'y', 'i', 'p', 'e', 'g', 's', '6', '7'};
        for(int i = 0; i < 15; i++){
            System.out.println("inserting: " + arr1[i] + ": ");
            l1.insert(arr1[i]);
            System.out.println(l1);
            System.out.println("");
        }

        System.out.println("Deleting v");
        l1.delete('v');
        System.out.println(l1);
        System.out.println("");

        System.out.print("searching for s\t");
        l1.printSearchPath('s');
        System.out.println("");

        /*Expected output
        Char testing
            inserting: a:
            [Lvl 5]
            [Lvl 4]
            [Lvl 3]
            [Lvl 2]
            [Lvl 1]
            [Lvl 0]->[a]

            inserting: b:
            [Lvl 5]
            [Lvl 4]
            [Lvl 3]
            [Lvl 2]
            [Lvl 1]
            [Lvl 0]->[a]->[b]

            inserting: c:
            [Lvl 5]
            [Lvl 4]
            [Lvl 3]
            [Lvl 2]
            [Lvl 1]
            [Lvl 0]->[a]->[b]->[c]

            inserting: v:
            [Lvl 5]
            [Lvl 4]
            [Lvl 3]
            [Lvl 2]
            [Lvl 1]---------------->[v]
            [Lvl 0]->[a]->[b]->[c]->[v]

            inserting: d:
            [Lvl 5]
            [Lvl 4]
            [Lvl 3]
            [Lvl 2]---------------->[d]
            [Lvl 1]---------------->[d]->[v]
            [Lvl 0]->[a]->[b]->[c]->[d]->[v]

            inserting: g:
            [Lvl 5]
            [Lvl 4]
            [Lvl 3]
            [Lvl 2]---------------->[d]
            [Lvl 1]---------------->[d]->[g]->[v]
            [Lvl 0]->[a]->[b]->[c]->[d]->[g]->[v]

            inserting: j:
            [Lvl 5]
            [Lvl 4]
            [Lvl 3]
            [Lvl 2]---------------->[d]
            [Lvl 1]---------------->[d]->[g]->[j]->[v]
            [Lvl 0]->[a]->[b]->[c]->[d]->[g]->[j]->[v]

            inserting: y:
            [Lvl 5]
            [Lvl 4]
            [Lvl 3]
            [Lvl 2]---------------->[d]
            [Lvl 1]---------------->[d]->[g]->[j]->[v]->[y]
            [Lvl 0]->[a]->[b]->[c]->[d]->[g]->[j]->[v]->[y]

            inserting: i:
            [Lvl 5]
            [Lvl 4]-------------------------->[i]
            [Lvl 3]-------------------------->[i]
            [Lvl 2]---------------->[d]------>[i]
            [Lvl 1]---------------->[d]->[g]->[i]->[j]->[v]->[y]
            [Lvl 0]->[a]->[b]->[c]->[d]->[g]->[i]->[j]->[v]->[y]

            inserting: p:
            [Lvl 5]
            [Lvl 4]-------------------------->[i]
            [Lvl 3]-------------------------->[i]
            [Lvl 2]---------------->[d]------>[i]
            [Lvl 1]---------------->[d]->[g]->[i]->[j]------>[v]->[y]
            [Lvl 0]->[a]->[b]->[c]->[d]->[g]->[i]->[j]->[p]->[v]->[y]

            inserting: e:
            [Lvl 5]
            [Lvl 4]------------------------------->[i]
            [Lvl 3]------------------------------->[i]
            [Lvl 2]---------------->[d]----------->[i]
            [Lvl 1]---------------->[d]->[e]->[g]->[i]->[j]------>[v]->[y]
            [Lvl 0]->[a]->[b]->[c]->[d]->[e]->[g]->[i]->[j]->[p]->[v]->[y]

            inserting: g:
            [Lvl 5]
            [Lvl 4]------------------------------------>[i]
            [Lvl 3]------------------------------------>[i]
            [Lvl 2]---------------->[d]---------------->[i]
            [Lvl 1]---------------->[d]->[e]->[g]------>[i]->[j]------>[v]->[y]
            [Lvl 0]->[a]->[b]->[c]->[d]->[e]->[g]->[g]->[i]->[j]->[p]->[v]->[y]

            inserting: s:
            [Lvl 5]
            [Lvl 4]------------------------------------>[i]
            [Lvl 3]------------------------------------>[i]
            [Lvl 2]---------------->[d]---------------->[i]
            [Lvl 1]---------------->[d]->[e]->[g]------>[i]->[j]----------->[v]->[y]
            [Lvl 0]->[a]->[b]->[c]->[d]->[e]->[g]->[g]->[i]->[j]->[p]->[s]->[v]->[y]

            inserting: 6:
            [Lvl 5]
            [Lvl 4]----------------------------------------->[i]
            [Lvl 3]----------------------------------------->[i]
            [Lvl 2]--------------------->[d]---------------->[i]
            [Lvl 1]--------------------->[d]->[e]->[g]------>[i]->[j]----------->[v]->[y]
            [Lvl 0]->[6]->[a]->[b]->[c]->[d]->[e]->[g]->[g]->[i]->[j]->[p]->[s]->[v]->[y]

            inserting: 7:
            [Lvl 5]
            [Lvl 4]---------------------------------------------->[i]
            [Lvl 3]---------------------------------------------->[i]
            [Lvl 2]-------------------------->[d]---------------->[i]
            [Lvl 1]------>[7]---------------->[d]->[e]->[g]------>[i]->[j]----------->[v]->[y]
            [Lvl 0]->[6]->[7]->[a]->[b]->[c]->[d]->[e]->[g]->[g]->[i]->[j]->[p]->[s]->[v]->[y]

            Deleting v
            [Lvl 5]
            [Lvl 4]---------------------------------------------->[i]
            [Lvl 3]---------------------------------------------->[i]
            [Lvl 2]-------------------------->[d]---------------->[i]
            [Lvl 1]------>[7]---------------->[d]->[e]->[g]------>[i]->[j]----------->[y]
            [Lvl 0]->[6]->[7]->[a]->[b]->[c]->[d]->[e]->[g]->[g]->[i]->[j]->[p]->[s]->[y]

            Searching for s [i][j][y][p][s]

         */
    }
}
