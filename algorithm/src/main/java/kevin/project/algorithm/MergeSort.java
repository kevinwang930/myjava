package kevin.project.algorithm;

import java.util.Arrays;

public class MergeSort {

    /* precondition: a[lo...mid] sorted     a[mid+1..hi] sorted*/
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }
        int i = lo, j = mid+1;
        for (int k = lo; k <=hi ; k++) {
            if (i > mid)  a[k] = aux[j++];
            else if (j > hi) a[k] =  aux[i++];
            else if (aux[i].compareTo(aux[j]) <=0) a[k]=aux[i++];
            else a[k] = aux[j++];
        }
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) /2;
        sort(a,aux,lo,mid);
        sort(a,aux,mid+1,hi);
        if (a[mid].compareTo(a[mid+1]) <=0) return;
        merge(a, aux, lo,mid,hi);
    }

    public static void sort(Comparable[] a) {
        Comparable[] aux = Arrays.copyOf(a,a.length);
        sort(a,aux,0,a.length-1);
    }


    public static void main(String[] args) {
        Integer[] a = new Integer[] {1,5,2,4,3,10};
        Integer[] b = Arrays.copyOf(a,a.length);
        sort(a,b,0,a.length-1);
        System.out.println(Arrays.toString(a));
    }
}
