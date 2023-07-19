public class MergeSort <T extends Comparable<T>> extends Sort<T> {
    @Override
    @SuppressWarnings("unchecked")
    public Comparable<T>[] sort(Comparable<T>[] arr) {
        //Hint This function can be implemented as a one liner consisting of a return and a function call
        return mergeSort(arr);
    }

    @SuppressWarnings("unchecked")
    private Comparable<T>[] mergeSort(Comparable<T>[] arr){
        //Do not change the position of this function.
        printArr(arr);
        //Add any code below
        return (Comparable<T>[]) merge(returnArrayCopy(arr, 0, arr.length - 1), 0, arr.length - 1);
    }

    @SuppressWarnings("unchecked")
    private Comparable<T>[] merge(Comparable<T>[] arr, int lh, int rh) {
        // printArr(arr);
        int mid = getMidPoint(lh, rh);
        if(!(lh == 0 && rh == arr.length - 1)) {
            printArr(returnArrayCopy(arr, lh, rh));
        }
        if (lh < rh) {
            // printArr(arr, lh, rh);
            merge(arr, lh, mid);
            merge(arr, mid + 1, rh);
        }
        // printArr(arr, lh, rh);
        // merge solved pieces to get solution to original problem
        int a = 0, f = lh, l = mid + 1;
        T[] tmp = (T[]) new Comparable[rh - lh + 1];
        while (f <= mid && l <= rh) {
            Comparable<T> temp = arr[f];
            boolean found = (temp.compareTo((T) arr[l]) < 0);
            tmp[a++] = found ? (T) arr[f++] : (T) arr[l++];
        }
        while (f <= mid) {
            tmp[a++] = (T) arr[f++];
        }
        while (l <= rh) {
            tmp[a++] = (T) arr[l++];
        }
        a = 0;
        while (lh <= rh) {
            arr[lh++] = tmp[a++];
        }
        return arr;
    }

    @SuppressWarnings("unchecked")
    public T[] returnArrayCopy(Comparable<T>[] arr, int lh, int rh){
        T[] copy = (T[]) new Comparable[(rh - lh) + 1];
        for (int t = 0, z = lh; z <= rh; t++, z++) {
            copy[t] = (T) arr[z];
        }
        return copy;
    }

    private int getMidPoint(int lh, int rh){
        return (int)Math.floor((lh + rh) / 2);
    }
}