
public class QuickSort1 {
  
  public void sortInPlace (int[] data)
  {
    quickSortRecursive (data, 0, data.length-1);
  }
  
  void swap (int[] data, int i, int j)
  {
    int temp = data[i];
    data[i] = data[j];
    data[j] = temp;
  }

  int leftPartition (int[] data, int left, int right)
  {
    // INSERT YOUR CODE HERE. Use the leftmost element as
    // the partitioning element.

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
  
  void quickSortRecursive (int[] data, int left, int right)
  {
    // For sub-ranges larger than 1, split and recurse:
    if (left < right) {
      // 1. Partition:
      int partitionPosition = leftPartition (data, left, right);

      // 2. Sort left side:
      quickSortRecursive (data, left, partitionPosition-1);

      // 3. Sort right side:
      quickSortRecursive (data, partitionPosition+1, right);

      // Partition element is already in the correct place.
    }
  }

  public static void test1 ()
  {
    QuickSort1 qSort = new QuickSort1 ();
    int totalTests = 1;
    int dataSize = 10;
    int[] data = new int [dataSize];
    for (int ntests=0; ntests < totalTests; ntests++) {
      // Create the data:
      for (int i=0; i < dataSize; i++)
        data[i] = (int) UniformRandom.uniform ( (int) 100, (int) 1000);
      // Sort:
      qSort.sortInPlace (data);
      // Check:
      boolean ok = true;
      for (int i=0; i < dataSize-1; i++)
        if (data[i] > data[i+1])
          ok = false;
      if (! ok) {
        System.out.println ("Failure detected for test#" + ntests);
        System.exit (0);
      }
    }
    System.out.println ("Passed");
  }

  public static void test2 ()
  {
    QuickSort1 qSort = new QuickSort1 ();
    int dataSize = 100;
    int[] data = new int [dataSize];
    for (int i=0; i < dataSize; i++)
      data[i] = i;
    qSort.sortInPlace (data);

    // Check:
    boolean ok = true;
    for (int i=0; i < dataSize-1; i++)
      if (data[i] > data[i+1])
        ok = false;
    if (! ok) {
      System.out.println ("Failure detected in test2");
      System.exit (0);
    }
    System.out.println ("Passed");
  }

  public static void main (String[] argv)
  {
    test1();
  }
  
}


public class UniformRandom {

  // Basic Lehmer generator - constants
  static final long m = 2147483647L;
  static final long a = 48271L;
  static final long q = 44488L;
  static final long r = 3399L;

  static long r_seed = 12345678L; 

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