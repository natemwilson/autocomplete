import java.util.*;
public class BinarySearchDeluxe {

    // Returns the index of the first key in a[] that equals the search key, or -1 if no such key.
    public static <T> int firstIndexOf(T[] a, T key, Comparator<T> comparator) {
    	int index = Arrays.binarySearch(a, key, comparator);
    	if (index < 0) return -1;

    	if (index == 0) return index;

    	while (comparator.compare(a[index-1], a[index]) == 0) {
    		index--;
    		if (index == 0) return index;
    	}
    	return index;
    }

    // Returns the index of the last key in a[] that equals the search key, or -1 if no such key.
    public static <T> int lastIndexOf(T[] a, T key, Comparator<T> comparator) {
	int index = Arrays.binarySearch(a, key, comparator);

    	if (index == (a.length-1)) return index;
    	if (index < 0) return -1;

    	while (comparator.compare(a[index], a[index+1]) == 0) {
    		index++;
    		if (index == (a.length-1)) return index;
    	}
    	return index;    	
    }

    // unit testing (required)
    public static void main(String[] args) {

    	
    }
}