public class MergeSort {

    /**
     * lo      mid        hi
     * [a,c,e,e,e,g,m,r,r,t]
     */
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);

        // Copy entire array into auxillary array
        for(int k = lo; k <= hi; k++)
            aux[k] = a[k];

        // k = index for original array
        // i = index for aux 1st half
        // j = index for aux 2nd half
        int i = lo, j = mid+1;
        for(int k = lo; k<=hi; k++) {

            // halves might be of different size
            // if i > mid we are done merging first half
            if      (i > mid)              a[k] = aux[j++];
            // if j > hi we are done merging second half
            else if (j > hi)               a[k] = aux[i++];

            // Step 3: Compare items in i and j
            // smaller item is placed in position k
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }

        assert isSorted(a, lo, hi);
    }

    /**
     * Recursively call sort
     * - break down input to pairs
     * - sorted on "pop" phase of recursive call
     * - make sure aux array variable initialised only once
     */
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        // pointing at only one item in array
        if(hi <= lo) return;
        // find mid point in array
        int mid = lo + (hi - lo) / 2;
        // sort both halves
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        // stop if already sorted
        if (!less(a[mid+1], a[mid])) return;
        // merge the sorted halves
        merge(a, aux, lo, mid, hi);
    }

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    public static void main(String[] args) {
        Integer[] ints = {5,2,4,1,6,9,7,3,8,10};
        sort(ints);
        System.out.println();
        System.out.println("sorted:");
        outputArray(ints);
    }

    private static void outputArray(Comparable[] stuff) {
        System.out.print("[ ");
        for(Comparable thing : stuff)
            System.out.print(thing+" ");
        System.out.println("]");
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) == -1;
    }

    private static boolean isSorted(Comparable[] toCheck, int start, int end) {
        Comparable prev = toCheck[start];
        for(int i=start+1; i<=end; i++) {
            if(prev.compareTo(toCheck[i]) > 0)
                return false;
        }
        return true;
    }

}
