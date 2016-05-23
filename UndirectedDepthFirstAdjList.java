
import edu.gwu.algtest.*;
import edu.gwu.util.*;
import edu.gwu.geometry.*;
import edu.gwu.debug.*;
import java.util.*;

	public class UndirectedDepthFirstAdjList implements UndirectedGraphSearchAlgorithm {

		public LinkedList<LinkedList> points = new LinkedList<LinkedList>(); 
		public ArrayList<Integer> connected = new ArrayList<Integer>(); 
		public ArrayList<Integer> checkconnected = new ArrayList<Integer>(); 
		public ArrayList<Boolean> checked = new ArrayList<Boolean>(); 
		public ArrayList<Boolean> completedcheck = new ArrayList<Boolean>(); 
		public ArrayList<Integer> completed = new ArrayList<Integer>(); 
		public ArrayList<Integer> visited = new ArrayList<Integer>(); 
		public ArrayList<Integer> componentlist = new ArrayList<Integer>(); 
		public int num_components = 0; 
		public int completioncount = 0;
		public int visitcount = 0;  
		public UniformRandom rv = new UniformRandom(); 

		//default constructor.
		public UndirectedDepthFirstAdjList() {

		}

		//return a list of articulation edges.
		public GraphEdge [] articulationEdges() {
			//leave empty.
			return null;
		}

		//return a list of articulation vertices.
		public int [] articulationVertices() {
			//leave empty.
			return null;
		}

		//return an array of vertices of exactly the same length as the number of vertices.
		public int [] breadthFirstVisitOrder() {
			//leave empty.
			return false;
		}

		//return the component label for each vertex for an undirected graph.
		public int[] componentLabels() {
			
			//add a component label as long as the list is not empty.
			if(componentlist != null) {
				
				//create a temporary array to be returned by function.
				int [] temparray = new int[componentlist.size()];
				
				//convert arraylist to array.
				for(int i=0; i<componentlist.size(); i++) {
					temparray[i] = componentlist.get(i);
				}

				return temparray;
			}
			
			return null;
		}

		//helper method - recursively traverse up once the vertex with no neighbors has been found.
		public void completionRecursive(int startVertex, int counter) {
			
			completedcheck.set(startVertex, true);

			//continue to traverse down until final vertex with no neighbors has been found.
			for(int i=0; i<points.get(startVertex).size(); i++) {

				GraphEdge temp = (GraphEdge) points.get(startVertex).get(i);

				if(completedcheck.get(temp.endVertex) != true) {
					completionRecursive(temp.endVertex, completioncount);
				}
			}
		
			counter = completioncount;
			completed.set(startVertex, completioncount);
			completioncount = counter+1;
		}

		//return an array where the i-th entry is the completion time (order) of vertex i in the depth first search. 
		public int[] depthFirstCompletionOrder() {

			if(completed != null) {
				completed = new ArrayList<Integer>();

				//fill the default completed arraylist with 0 values.
				for(int i=0; i<points.size(); i++) {
					completed.add(0);
				}

				//convert the arraylist to an array to be returned by method later.
				int[] temparray = new int[completed.size()];

				for(int j=0; j<completed.size(); j++) {
					
					//call recursive method to find first point with no neighbors, which will appear in list first.
					if(completedcheck.get(j) != true) {
						completionRecursive(j, completioncount);
					}
				}

				//convert arraylist to array values.
				for(int k=0; k<temp.length; k++) {
					temparray[k] = completed.get(k);
				}

				return temparray;

			}
		
			return null;
		}	

		//helper method - recursively find the point where there are no neighbors to be visited.
		public void visitRecursive(int startVertex, int visitcounter) {

			componentlist.set(startVertex, num_components);
			checked.set(startVertex, true);
			visited.set(startVertex, visitcount);
			visitcount = visitcounter+1;
			
			//continue to go through list until you find a point that has no unvisited neighbors.
			for(int i=0; i<points.get(startVertex).size(); i++) {

				GraphEdge temp = (GraphEdge) points.get(startVertex).get(i);

				if(checked.get(temp.endVertex)!=true) {
					visitRecursive(temp.endVertex, visitcount);
				}

			}
		}	

		//return an array of vertices, of exactly the same length as the number of vertices.
		public int[] depthFirstVisitOrder() {
			
			if(visited != null) {
				visited = new ArrayList<Integer>();

				//fill visited arraylist with zeroes to begin.
				for(int i=0; i<points.size(); i++) {
					visited.add(0);
				}

				//convert arraylist to array to be returned by method later.
				int[] temparray = new int[visited.size()];

				for(int j=0; j<visited.size(); j++) {
					
					if(checked.get(j)!=true) {
						visitRecursive(j,visitcount);
						num_components=num_components+1;
					}
				}

				//convert arraylist to array to be returned by method later.
				for(int k=0; k<temp.length; k++) {
					temparray[k]=visited.get(k);
				}

				return temparray;
			}
		
			return null;
		}

		//return true if the graph has a cycle, false otherwise.
		public boolean existsCycle() {
			//leave empty.
			return false;
		}

		//return true if the graph has an odd cycle (not a bipartite), false otherwise.
		public boolean existsOddCycle() {
			//leave empty.
			return false;
		}

		//given the number of vertices (assumed to be numbered) and whether the graph edges will carry weights.
		public void initialize(int numVertices, boolean isWeighted) {
 			
 			points = new LinkedList<LinkedList>();
			connected = new ArrayList<Integer>();
	 		checkconnected = new ArrayList<Integer>();
	 		componentlist = new ArrayList<Integer>();
	 		checked = new ArrayList<Boolean>();
			completedcheck = new ArrayList<Boolean>();
			visited = new ArrayList<Integer>();
			completed = new ArrayList<Integer>();
			num_components = 0;
			visitcount = 0;
			completioncount = 0;

			//initialize values for each vertex.
			for(int i=0; i<numVertices; i++) {

				//add to list of connected components.
				connected.add(i);

				//initalizes the vertices in a linked list.
				LinkedList<GraphEdge> temp = new LinkedList<GraphEdge>();

				//initialize all other arraylists.
				points.add(temp);
				checked.add(false);
				completedcheck.add(false);
				checkconnected.add(0);
				visited.add(0);
				completed.add(0);
				componentlist.add(i);

			}
		}

		//called with the endpoints of the edge.
		public void insertUndirectedEdge(int startVertex, int endVertex, double weight) {
			
			if(points != null) {

				if(points.get(startVertex) != null && points.get(endVertex)!=null) {

					//add the edge to the linked list
					GraphEdge temp = new GraphEdge(startVertex,endVertex,weight);
					points.get(startVertex).add(temp);

					GraphEdge temp2 = new GraphEdge(endVertex,startVertex,weight);
					points.get(endVertex).add(temp2);

			
					//updates the list of connected components.
					if(connected.get(startVertex)<=connected.get(endVertex)) {
						connected.set(endVertex,connected.get(startVertex));
					}

					else if(connected.get(endVertex)<connected.get(startVertex)) {
						connected.set(startVertex, connected.get(endVertex));
					}

				}
			}
		
		}

		//return the number of connected components for an undirected graph.
		public int numConnectedComponents() {
			
			int counter = 0;
			
			//reset the counter.
			for(int c=0; c<checkconnected.size(); c++) {
				checkconnected.set(c, 0);
			}
		
			//search for connected components
			for(int i=0; i<connected.size(); i++) {
				//add to number of checked components.
				checkconnected.set(connected.get(i), checkconnected.get(connected.get(i))+1);
			}

			//find out how many connected components are there
			for(int j=0; j<checkconnected.size(); j++) {

				if(checkconnected.get(j)!=0) {	
					counter++;
				}
			}

			return counter;
		}

		public static boolean randomCoinFlip (double p) {
    		if (UniformRandom.uniform() < p) {
      			return true;
      		}

    		else {
      			return false;
      		}
  		}

  		public static void main(String [] args) {
  			
			UndirectedDepthFirstAdjList udfal = new UndirectedDepthFirstAdjList();
			udfal.initialize(10, false);

			for(int i=0; i<udfal.points.size(); i++) {
				for(int j=0; j<udfal.points.size(); j++) {
					if(i != j) {
						if(randomCoinFlip(0.2) == true) {
							udfal.insertUndirectedEdge(i, j, 0);
						}
					}
				}
			}

			for(int i=0; i<udfal.points.size(); i++) {
				for(int j=0; j<udfal.points.size(); j++) {
					if(i != j) {
						if(randomCoinFlip(0.1) == true) {
							udfal.insertUndirectedEdge(i, j, 0);
						}
					}
				}
			}

			udfal.initialize(20, false);

			for(int i=0; i<udfal.points.size(); i++) {
				for(int j=0; j<udfal.points.size(); j++) {
					if(i != j) {
						if(randomCoinFlip(0.2) == true) {
							udfal.insertUndirectedEdge(i, j, 0);
						}
					}
				}
			}

			for(int i=0; i<udfal.points.size(); i++) {
				for(int j=0; j<udfal.points.size(); j++) {
					if(i != j) {
						if(randomCoinFlip(0.1) == true){
							udfal.insertUndirectedEdge(i, j, 0);
						}
					}
				}
			}
	
			udfal.numConnectedComponents();
		}

		public java.lang.String getName() {
			return "srau you know something whatever";
		} 

		public void setPropertyExtractor(int algID, edu.gwu.util.PropertyExtractor prop) {
			//leave empty.
		}
}

class UniformRandom {

  // Basic Lehmer generator - constants
  static final long m = 2147483647L;
  static final long a = 48271L;
  static final long q = 44488L;
  static final long r = 3399L;

  static long r_seed = 12345678L; 
  public UniformRandom(){

  }
  public static void set_seed (long seed)
  {
    r_seed = seed;
  }

  // Basic Lehmer generator - uniform[0,1]
  // For more information see Knuth, Vol. II.
  public static double uniform ()
  {
    long hi = r_seed / q;
    long lo = r_seed - q * hi;
    long t = a * lo - r * hi;
    if (t > 0)
      r_seed = t;
    else
      r_seed = t + m;
    return ( (double) r_seed / (double) m );
  }

  // U[a,b] generator 
  public static double uniform (double a, double b)
  {
    if (b > a)
      return ( a + (b-a) * uniform() );
    else { 
      System.out.println ("ERR in uniform(double,double):a="+a+"b="+b); 
      return 0;
    }
  }

  // Discrete Uniform random generator - returns an
  // integer between a and b
  public static long uniform (long a, long b)
  {
    if (b > a) {
      double x = uniform ();
      long c = ( a + (long) Math.floor((b-a+1)*x) );
      return c;
    }
    else if (a == b) 
      return a;
    else { 
      System.out.println ("ERR: in uniform(long,long):a="+a+"b="+b); 
      return 0;
    }
  }

} // End of class "UniformRandom"