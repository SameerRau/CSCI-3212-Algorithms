
import edu.gwu.algtest.*;
import edu.gwu.debug.*;
import edu.gwu.util.*;

public class insertionsort implements SortingAlgorithm {

	public insertionsort() {

	}

	public static void printarray(int[] data) {
   		System.out.print("Values: ");
   		
   		for (int i=0; i<data.length; i++) {
			System.out.print(" " + data[i]);
   		}

    	System.out.println("");
  	}

	public String getName() {
		return "srau insertion sort implementation";
	}

	public void setPropertyExtractor(int algID, edu.gwu.util.PropertyExtractor prop) {
		//leave empty.
	}

	public void sortInPlace(int [] data) {
		
		int temp;
		int next_element;

		for(int i=1; i<data.length; i++) {
		 	temp = data[i];

		 	next_element = i;

		 	while(next_element > 0 && data[next_element-1] > temp) {
		 		data[next_element] = data[next_element-1];
		 		next_element--;
		 	}

		 	data[next_element] = temp;
 		}
	}

	public void sortInPlace(java.lang.Comparable[] data) {
		//leave empty.
	}

	public int[] createSortIndex(int [] data) {
		return data;
	}

	public int[] createSortIndex(java.lang.Comparable[] data) {
		//leave empty.
		return null;
	}

	public static void main(String [] argv) {

		insertionsort is = new insertionsort();
  		int elements = Integer.parseInt(argv[0]);
      	int[] data = {5,3,2,11,7};

      	System.out.println ("Original array: ");  
      	is.printarray (data);

      	is.sortInPlace(data);

      	System.out.println ("Sorted array: ");
      	is.printarray (data);
  	}
}
