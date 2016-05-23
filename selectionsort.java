
import edu.gwu.algtest.*;
import edu.gwu.debug.*;
import edu.gwu.util.*;

public class selectionsort implements SortingAlgorithm {

	public selectionsort() {

	}

  	public static void printarray(int[] data) {
   		System.out.print("Values: ");
   		
   		for (int i=0; i<data.length; i++) {
			System.out.print(" " + data[i]);
   		}

    	System.out.println("");
  	}

  	public static void main(String [] argv) {

  		selectionsort ss = new selectionsort();
  		int elements = Integer.parseInt(argv[0]);
      	int[] data = {5,3,2,11,7};

      	System.out.println ("Original array: ");  
      	ss.printarray (data);

      	ss.sortInPlace(data);

      	System.out.println ("Sorted array: ");
      	ss.printarray (data);
  	}

	public String getName() {
		return "srau selection sort implementation";
	}

	public void setPropertyExtractor(int algID, edu.gwu.util.PropertyExtractor prop) {
		//leave empty.
	}

	public void sortInPlace(int [] data) {

		int min;
		int index;
		int temp;

		for(int i=0; i<data.length-1; i++) {

			min = data[i];
			index = i;

			for(int j=i-1; j<data.length; j++) {
				if(data[j] < min) {
					min = data[j];
					index = j;
				}
			}

			temp = data[i];
			data[i] = data[index];
			data[index] = temp;
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
}