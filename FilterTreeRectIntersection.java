
import edu.gwu.algtest.*;
import edu.gwu.util.*;
import edu.gwu.geometry.*;
import java.util.*;

public class FilterTreeRectIntersection implements RectangleSetIntersectionAlgorithm {

	public FilterTreeNode root;
	public LinkedList<IntPair> rectangleset;
	public IntPair[] intersect;

	//INPUT: two rectangle sets.
	//OUTPUT: an array of pairs, with each pair containing two ID's, with one coming from each rectangle set.
	public IntPair[] findIntersections(IntRectangle [] rectSet1, IntRectangle [] rectSet2) {

		ArrayList <IntPair> temp = new ArrayList <IntPair>();
		IntRectangle first;
		IntRectangle second;
		int counter = 0;

		root = new FilterTreeNode(0, 100, 0, 100, 0);

		create(rectSet1);

		for(int i=0; i<rectSet2.length; i++) {
			rectangleset = searchTree(rectSet2[i]);
		}

		if(rectangleset != null) {
			for(int j=0; j<rectangleset.size(); j++) {
				temp.add(rectangleset.get(j));
			}
		}

		intersect = new IntPair[temp.size()];

		if(intersect.length != 0) {
			for(int k=0; k<temp.size(); k++) {
				intersect[k] = temp.get(k);
			}
		}

		else {

			return null;
		}

		return intersect;
	}

	public IntPair getIntersections(IntRectangle first, IntRectangle second) {
		
		if(!(second.topLeft.x > first.bottomRight.x || first.topLeft.x > second.bottomRight.x) && !(first.topLeft.y < second.bottomRight.y || second.topLeft.y < first.bottomRight.y)) {
			IntPair prectangle = new IntPair(first.ID, second.ID);
			return prectangle;
		}

		return null;
	}

	public void create(IntRectangle [] rectSet1) {
		for(int i=0; i<rectSet1.length; i++) {
			if(rectSet1[i] != null) {
				insert(root, rectSet1[i]);
			}
		}
	}

	public void insert(FilterTreeNode r, IntRectangle first) {

		FilterTreeNode nequadrant = new FilterTreeNode(r.leftX, r.midX, r.midY, r.topY, r.level+1);
		FilterTreeNode nwquadrant = new FilterTreeNode(r.midX, r.rightX, r.midY, r.topY, r.level+1);
		FilterTreeNode swquadrant = new FilterTreeNode(r.leftX, r.midX, r.botY, r.midY, r.level+1);
		FilterTreeNode sequadrant = new FilterTreeNode(r.midX, r.rightX, r.botY, r.midY, r.level+1);

		if(onGridLines(first, nequadrant) == TRUE) {
			if((nequadrant.leftX < first.topLeft.x) && (nequadrant.topY > first.topLeft.y) && (nequadrant.rightX > first.bottomRight.x) && (nequadrant.botY < first.bottomRight.y)) {
				if(r.quadrants[0] == null) {
					r.quadrants[0] = nequadrant;
					insert(r.quadrants[0], first);
				}

				else {
					insert(r.quadrants[0], first);
				}
			}

			else {
				r.rectList.add(first);
			}
		}

		else if(onGridLines(first, nwquadrant) == TRUE) {
			if((nwquadrant.leftX < first.topLeft.x) && (nwquadrant.topY > first.topLeft.y) && (nwquadrant.rightX > first.bottomRight.x) && (nwquadrant.botY < first.bottomRight.y)) {
				if(r.quadrants[1] == null) {
					r.quadrants[1] = nwquadrant;
					insert(r.quadrants[1], first);
				}

				else {
					insert(r.quadrants[1], first);
				}
			}

			else {
				r.rectList.add(first);
			}
		}

		else if(onGridLines(first, swquadrant) == TRUE) {
			if((swquadrant.leftX < first.topLeft.x) && (swquadrant.topY > first.topLeft.y) && (swquadrant.rightX > first.bottomRight.x) && (swquadrant.botY < first.bottomRight.y)) {
				if(r.quadrants[2] == null) {
					r.quadrants[2] = swquadrant;
					insert(r.quadrants[2], first);
				}

				else {
					insert(r.quadrants[2], first);
				}
			}

			else {
				r.rectList.add(first);
			}
		}

		else if(onGridLines(first, sequadrant) == TRUE) {
			if((sequadrant.leftX < first.topLeft.x) && (sequadrant.topY > first.topLeft.y) && (sequadrant.rightX > first.bottomRight.x) && (sequadrant.botY < first.bottomRight.y)) {
				if(r.quadrants[3] == null) {
					r.quadrants[3] = sequadrant;
					insert(r.quadrants[3], first);
				}

				else {
					insert(r.quadrants[3], first);
				}
			}

			else {
				r.rectList.add(first);
			}
		}

		return;

		/*

		//ArrayList <FilterTreeNode> list = new ArrayList <FilterTreeNode>();

		//level = 1;

		//FilterTreeNode tree = new FilterTreeNode();

		//the rectangle does not fall into a single quadrant.
		if((rectangle.topLeft.y > root.midY && rectangle.bottomRight.y < root.midY) || (rectangle.topLeft.x > root.midX && rectangle.bottomRight.x < root.midX)) {
			//insert at first level of linked list.
			//rectList[level].insert(rectangle, root);
		}

		//the rectangle falls into the first (NE) quadrant.
		else if((rectangle.topLeft.y > root.midY) && (rectangle.topLeft.x > root.midX)) {
			level++;
			quadrants[0];
			//point to next linked list.
			insert(rectangle, root);  //half the length of the previous root.
		}

		//the rectangle falls into the second (NW) quadrant.
		else if((rectangle.topLeft.y > root.midY) && (rectangle.topLeft.x < root.midX)) {
			level++;
			quadrants[1];
			//point to next linked list.
			insert(rectangle, root); //half the length of the previous root.
		}

		//the rectangle falls into the third (SW) quadrant.
		else if((rectangle.topLeft.y < root.midY) && (rectangle.topLeft.x < root.midX)) {
			level++;
			quadrants[2];
			//point to the next linked list.
			insert(rectangle, root); //half the length of the previous root. 
		}

		else if((rectangle.topLeft.y < root.midY) && (rectangle.topLeft.x > root.midX)) {
			level++;
			quadrants[3];
			//point to the next linked list.
			insert(rectangle, root); //half the length of the previous root.
		}

		*/
	}

	public LinkedList<IntPair> searchTree(IntRectangle second) {
		LinkedList<IntPair> intersection = new LinkedList<IntPair>();
		return search(second, root, intersection);
	}

	public LinkedList<IntPair> search(IntRectangle second, FilterTreeNode r, LinkedList<IntPair> intersection) {

		IntPair p;

		if(r.rectList != null) {
			for(int i=0; i<r.rectList.size(); i++) {
				IntRectangle rectangle = (IntRectangle) r.rectList.get(i);
				p = getIntersections(rectangle, second);

				if(p != null) {
					intersection.add(p);
				}
			}
		} 

		if(r.quadrants[0] != null) {
			if(onGridLines(second, r.quadrants[0]) == TRUE) {
				search(second, r.quadrants[0], intersection);
			} 
		}

		else if(r.quadrants[1] != null) {
			if(onGridLines(second, r.quadrants[1]) == TRUE) {
				search(second, r.quadrants[1], intersection);
			} 
		}

		else if(r.quadrants[2] != null) {
			if(onGridLines(second, r.quadrants[2]) == TRUE) {
				search(second, r.quadrants[2], intersection);
			} 
		}

		else if(r.quadrants[3] != null) {
			if(onGridLines(second, r.quadrants[3]) == TRUE) {
				search(second, r.quadrants[3], intersection);
			} 
		}

		return intersection;

		/*

		for(int i=0; i<rectList.size(); i++) {
			findIntersections(rectangle, rectList(i));
		}

		//the rectangle does not fall into a single quadrant.
		if((rectangle.topLeft.y > root.midY && rectangle.bottomRight.y < root.midY) || (rectangle.topLeft.x > root.midX && rectangle.bottomRight.x < root.midX)) {
			//search at level of linked list.
			//rectlist[level].search(rectangle, root);
		}

		//the rectangle falls into the first (NE) quadrant.
		else if((rectangle.topLeft.y > root.midY) && (rectangle.topLeft.x > root.midX)) {
			level++;
			quadrants[0];
			//point to next linked list.
			search(rectangle, root); //half the length of the previous root.
		}

		//the rectangle falls into the second (NW) quadrant.
		else if((rectangle.topLeft.y > root.midY) && (rectangle.topLeft.x < root.midX)) {
			level++;
			quadrants[1];
			//point to next linked list.
			search(rectangle, root); //half the length of the previous root.
		}

		//the rectangle falls into the third (SW) quadrant.
		else if((rectangle.topLeft.y < root.midY) && (rectangle.topLeft.x < root.midX)) {
			level++;
			quadrants[2];
			//point to the next linked list.
			search(rectangle, root); //half the length of the previous root.
		}

		else if((rectangle.topLeft.y < root.midY) && (rectangle.topLeft.x > root.midX)) {
			level++;
			quadrants[3];
			//point to the next linked list.
			search(rectangle, root); //half the length of the previous root.
		}
		*/
	}

	

	public boolean onGridLines(IntRectangle first, FilterTreeNode r) {
		if(r==null) {
			return FALSE;
		}

		if(!((r.leftX > first.bottomRight.x) || (first.topLeft.x > r.rightX)) && !((first.topLeft.y < r.botY) || (r.topY < first.bottomRight.y))) {
			return TRUE;
		}

		return FALSE;
	}

	public static void main(String[] args) {

		FilterTreeRectIntersection ftri = new FilterTreeRectIntersection();
		IntRectangle[] qrectangle_example = new IntRectangle[5];
		IntRectangle[] qrectangle = new IntRectangle[5];

		IntRectangle R1 = new IntRectangle(89,57,91,37);
		IntRectangle R2 = new IntRectangle(12,80,45,66);
		IntRectangle R3 = new IntRectangle(28,40,39,29);
		IntRectangle R4 = new IntRectangle(18,70,32,60);
		IntRectangle R5 = new IntRectangle(79,94,82,90);
		IntRectangle R6 = new IntRectangle(13,52,88,27);
		IntRectangle R7 = new IntRectangle(12,92,49,67);
		IntRectangle R8 = new IntRectangle(22,44,42,35);
		IntRectangle R9 = new IntRectangle(19,100,30,50);
		IntRectangle R10 = new IntRectangle(74,93,86,91);

		qrectangle_example[0]=R1;
		qrectangle_example[1]=R2;
		qrectangle_example[2]=R3;
		qrectangle_example[3]=R4;
		qrectangle_example[4]=R5;
		qrectangle[0]=R6;
		qrectangle[1]=R7;
		qrectangle[2]=R8;
		qrectangle[3]=R9;
		qrectangle[4]=R10;

		ftri.create(qrectangle_example);
		//ftri.print(ftri.root);

		ftri.create(qrectangle);
		//ftri.print(ftri.root);
	}

	//public void print(FilterTreeNode r) {
	//	if(r==null) {
	//		System.out.print("There is no tree to print out.");
	//	}

	//	else {

	//	}
	//}

	public java.lang.String getName() {
		return "srau NaiveRectIntersection implementation";
	}

	public void setPropertyExtractor(int algID, edu.gwu.util.PropertyExtractor prop) {
		//leave empty.
	}
}