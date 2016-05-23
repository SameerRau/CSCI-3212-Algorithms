
import edu.gwu.algtest.*;
import edu.gwu.debug.*;
import edu.gwu.util.*;

public class MyPartition implements PartitionAlgorithm {

	public MyPartition() {

	}

	public static void printarray(int[] data) {
   		System.out.print("Values: ");
   		
   		for (int i=0; i<data.length; i++) {
			System.out.print(" " + data[i]);
   		}

    	System.out.println("");
  	}

  	public static void main(String [] argv) {
  		MyPartition mp = new MyPartition();
  		int elements = Integer.parseInt(argv[0]);
      	int[] data = {5,3,2,11,7};
      	int left = data[0];
      	int right = data.length-1;

      	System.out.println ("Original array: ");  
      	mp.printarray(data);

      	mp.leftIncreasingPartition(data, left, right);

      	System.out.println ("Sorted array: ");
      	mp.printarray(data);
  	}

	public int leftIncreasingPartition(int [] data, int left, int right) {
		int first_element = data[left];
		int left_cursor = left;
		int right_cursor = right;

		while(left_cursor < right_cursor) {
			if(data[left_cursor] < first_element) {
				left_cursor++;
			}

			if(data[right_cursor] > first_element) {
				right_cursor--;
			}

			if(left_cursor < right_cursor) {
				if(data[left_cursor]==first_element & data[right_cursor]==first_element) {
                	left_cursor++;
                	right_cursor--;
            	}

            	int temp = data[left_cursor];
				data[left_cursor] = data[right_cursor];
				data[right_cursor] = temp;
			}
		}
	
		return right_cursor;
	}

	public int rightIncreasingPartition(int [] data, int left, int right) {
		//leave empty
		return -1;
	}

	public String getName() {
		return "srau mypartition implementation";
	}

	public void setPropertyExtractor(int algID, edu.gwu.util.PropertyExtractor prop) {
		//leave empty.
	}

	public int[] createSortIndex(java.lang.Comparable[] data) {
		//leave empty.
	}

	public int[] createSortIndex(int [] data) {
		//leave empty.
	}

	public void sortInPlace(java.lang.Comparable[] data) {
		//leave empty.
	}

	public void sortInPlace(int [] data) {
		//leave empty.
	}
}