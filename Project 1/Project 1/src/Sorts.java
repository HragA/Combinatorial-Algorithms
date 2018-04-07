/**
 * Hrag Ayvazian
 * 9/26/17
 * Project #1
 */
public class Sorts {

    //Returns true only if a is sorted from smallest to largest values

    public static boolean isSorted (int [] A) {
        for(int i = 0; i < A.length-1; i++) {
            if(A[i] > A[i+1]) {
                return false;
            }
        }
        return true;
    }

    /* --------------------MergeSort ----------------------------------------*/

    //Merges sorted slices a[i.. j] and a[j + 1 ... k] for 0<= i <=j < k < a.length
    public static void merge (int A[], int i, int m, int j) {
        int left = m - i + 1;

        int right = j - m;

        int leftarr[] = new int[left];

        int rightarr[] = new int[right];

        for(int b = 0; b < left; b++) {
            leftarr[b] = A[i + b];
        }

        for(int z = 0; z < right; z++) {
            rightarr[z] = A[m + 1 + z];
        }

        int x = 0;
        int y = 0;
        int c = i;
        while(x < left && y < right) {
            if(leftarr[x] <= rightarr[y]) {
                A[c] = leftarr[x];
                x++;
            }
            else {
                A[c] = rightarr[y];
                y++;
            }
            c++;
        }

        while(x < left) {
            A[c] = leftarr[x];
            x++;
            c++;
        }

        while(y < right) {
            A[c] = rightarr[y];
            y++;
            c++;
        }
    }

    //Sorts a[ i .. k] for 0<=i <= k < a.length
    public static void mergeSort (int A[], int i, int j) {
        if(i<j) {
            int m = (i+j)/2;
            mergeSort(A, i, m);
            mergeSort(A,m+1, j);
            merge(A, i, m, j);
        }
    }

    //Sorts the array a using mergesort
    public static void mergeSort (int A[]) {
        int i=0;
        int j = A.length-1;
        mergeSort(A,0,j);
    }

    /* ---------- QuickSort ---------------------------------------------- */

    //Implements in-place partition from text. Partitions subarray s[a..b] into s[a..l-1] and s[l+1..b]
    // so that all elements of s[a..l-1] are less than each element in s[l+1..b]
    public static int partition (int A[], int i, int j) {
        int par = A[j];
        int l = i;
        int r = j-1;
        int temp;
        while(l <= r) {

            while(l <= r && A[l] <= par) {
                l++;
            }

            while(r >= l && A[r] >= par) {
                r--;
            }

            if(l < r) {
                temp = A[l];
                A[l] = A[r];
                A[r] = temp;

            }
        }
        temp = A[l];
        A[l] = A[j];
        A[j] = temp;
        return l;
    }

    //Quick sorts subarray a[i..j]
    public static void quickSort (int A[], int i, int j) {
        if(i < j) {
            int s = partition(A, i, j);
            quickSort(A, i, s-1);
            quickSort(A, s+1, j);
        }
    }

    //Quick sorts array a
    public static void quickSort( int[] A) {
        int i=0;
        int j = A.length-1;
        quickSort(A,0,j);
    }
}