import edu.gwu.algtest.*;
import edu.gwu.util.*;
import edu.gwu.geometry.*;
import java.util.*;

public class NaiveRectIntersection implements RectangleSetIntersectionAlgorithm {

	//INPUT: two rectangle sets.
	//OUTPUT: an array of pairs, with each pair containing two ID's, with one coming from each rectangle set.
	public IntPair[] findIntersections(IntRectangle [] rectSet1, IntRectangle [] rectSet2) {

		ArrayList <IntPair> temp = new ArrayList <IntPair>();
		IntRectangle first;
		IntRectangle second;
		int counter = 0;

		for(int i=0; i<rectSet1.length; i++) {

			first = rectSet1[i];

			for(int j=0; j<rectSet2.length; j++) {

				second = rectSet2[j];

				//an intersection exists between the two individual rectangles.
				if(!(second.topLeft.x > first.bottomRight.x || first.topLeft.x > second.bottomRight.x) && !(first.topLeft.y < second.bottomRight.y || second.topLeft.y < first.bottomRight.y)) {
					
					//get the ID of the two rectangles since they have been found to intersect.
					IntPair pair = new IntPair(first.ID, second.ID);

					//add the pair to the list of overall pairs.
					temp.add(pair);

					//keep track of the number of intersections.
					counter++;
				}
			}
		}

		//when no intersections between two individual rectangles exist between either rectangle set, return that no pairs were found.
		if(counter==0) {
			return null;
		}

		IntPair [] intersect = new IntPair[temp.size()];

		for(int k=0; k<intersect.length; k++) {
			intersect[k] = temp.get(k);
		}

		return intersect;
	}





				/*

				//topleft (x coordinate) - topleft (y coordinate) intersection.
				if(rectSet1[i].topLeft.x.compareTo(rectSet2[j].topLeft.y) == 0) {
					return IntPair(rectSet1[i].topLeft.ID, rectSet2[j].topLeft.ID);
				}

				//topleft (x coordinate) - bottomright (y coordinate) intersection.
				else if(rectSet1[i].topLeft.x.compareTo(rectSet2[j].bottomRight.y) == 0) {
					return IntPair(rectSet1[i].topLeft.ID, rectSet2[j].bottomRight.ID);
				}

				//topleft (y coordinate) - topleft (x coordinate) intersection.
				else if(rectSet1[i].topLeft.y.compareTo(rectSet2[j].topLeft.x) == 0) {
					return IntPair(rectSet1[i].topLeft.ID, rectSet2[j].topLeft.ID.x);
				}

				//topleft (y coordinate) - bottomright (x coordinate) intersection.
				else if(rectSet1[i].topLeft.y.compareTo(rectSet2[j].bottomRight.x) == 0) {
					return IntPair(rectSet1[i].topLeft.ID, rectSet2[j].bottomRight.ID);
				}

				//bottomright (x coordinate) - topleft (y coordinate) intersection.
				else if(rectSet1[i].bottomRight.x.compareTo(rectSet2[j].topLeft.y) == 0) {
					return IntPair(rectSet1[i].bottomRight.ID, rectSet2[j].topLeft.ID);
				}

				//bottomright (x coordinate) - bottomright (y coordinate) intersection.
				else if(rectSet1[i].bottomRight.x.compareTo(rectSet2[j].bottomRight.y) == 0) {
					return IntPair(rectSet1[i].bottomRight.ID, rectSet2[j].bottomRight.ID);
				}

				//bottomright (y coordinate) - topleft (x coordinate) intersection.
				else if(rectSet1[i].bottomRight.y.compareTo(rectSet2[j].topLeft.x) == 0) {
					return IntPair(rectSet1[i].bottomRight.ID, rectSet2[j].topLeft.ID);
				}

				//bottomright (y coordinate) - bottomright (x coordinate) intersection.
				else if(rectSet1[i].bottomRight.y.compareTo(rectSet2[j].bottomRight.x) == 0) {
					return IntPair(rectSet1[i].bottomRight.ID.y, rectSet2[j].bottomRight.ID.x);
				}

				*/


			//}


		//}




		//for(int i=0; i<recSet1.length; i++) {
	//		for(int j=0; j<rectSet2.length; j++) {
//				if(rectSet1[i].compareTo(rectSet2[j]) == 0) {
					//return pair(i, j);
					//return intpair(i.getID(), j.getID());
					//return intpair(i.hashcode(), j.hashcode());
					//not sure how to return the ordered pair coordinates, must get the right ID,
	//			}

	//			else {
					//reiterate the loop. the ordered pair is not a match between the two rectangle sets.
	//			}
//			}
//		}

		//return a null pair of (0, 0) if no ordered pair can be found.

		//if(rectSet1. .compareTo() rectSet2. || rectSet1. . compareTo() rectSet2. || )
	//}
	
	//overrides the corresponding method in Object to test for equality among two instances.
	//public boolean equals(java.lang.Object obj) {
		//if coordinate x == coordinate y
		//return true; 
		//else	
		//return false;
	//}

	//ID is used as the hashcode.
	//public int hashCode() {
		//return ID;
		//return getID();
	//}

	//Overrides Object's toString.
	//public java.lang.String toString() {
		//return "toString method";
	//}

	//return the rectangle's unique ID.
	//public int getID() {
		//return ID;
	//}

	//public static void resetIDs() {
		//leave empty???
	//}

	public java.lang.String getName() {
		return "srau NaiveRectIntersection implementation";
	}

	public void setPropertyExtractor(int algID, edu.gwu.util.PropertyExtractor prop) {
		//leave empty.
	}

}

