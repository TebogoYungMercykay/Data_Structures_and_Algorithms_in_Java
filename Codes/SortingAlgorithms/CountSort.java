public class CountSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    @SuppressWarnings("unchecked")
    public Comparable<T>[] sort(Comparable<T>[] arr) {
        //Do not alter given code nor add code above
        int[] count = formCountArr(arr);
        printArr(arr, count);
        int[] sumCount = sumCount(count);
        printArr(arr, sumCount);
        Comparable<T>[] res = new Comparable[arr.length]; //This array needs to be populated with the final result
        //Add code below this
        for (int i = arr.length - 1; i >= 0; i--) {
            int index = arr[i].hashCode();
            int sortedIndex = sumCount[index] - 1;
            res[sortedIndex] = arr[i];
            sumCount[index]--; // Decrement the count for the index
        }
        //Do not change the statement below
        printArr(res, sumCount);
        // Ensure only return statement is below this
        return res;
    }

    private int[] formCountArr(Comparable<T>[] arr){
        int countArraySize = countArraySize(arr);
        int[] countArr = new int[countArraySize + 1];
        for (int i = 0; i < arr.length; i++){
            int hashCode = arr[i].hashCode();
            countArr[hashCode] += 1;
        }
        return countArr;
    }

    private int[] sumCount(int[] countArr){
        int[] result = new int[countArr.length];
        for (int i = 1; i < countArr.length; i++){
            result[i] = result[i-1] + countArr[i];
        }
        return result;
    }

    private int countArraySize(Comparable<T>[] arr){
        int maxHashCode, i;
        for (i = 0, maxHashCode = Integer.MIN_VALUE; i < arr.length; i++){
            int hashCode = arr[i].hashCode();
            if (hashCode >= maxHashCode) {
                maxHashCode = hashCode;
            }
        }
        return maxHashCode;
    }

    private void printArr(Comparable<T>[] arr, int[] count){
        if(arr == null || count == null)
            System.out.println("NULL ARRAYS");

        String res = "[";
        for(Comparable<T> t: arr){
            res += t + "{" + count[t.hashCode()] + "},";
        }
        if(res.length() > 0){
            res = res.substring(0, res.length()-1);
        }
        res += "]";
        System.out.println(res);
    }
}