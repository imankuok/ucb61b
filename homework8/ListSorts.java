package homework8;

import java.util.Random;

/* ListSorts.java */


public class ListSorts {

  private final static int SORTSIZE = 1000;

  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
 * @throws QueueEmptyException 
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q) throws QueueEmptyException {
    // Replace the following line with your solution.
	  
	  
	  LinkedQueue queues = new LinkedQueue();
	  try {
	  for (int i=0; i< q.size(); i++) {
		  Object o= q.dequeue();
		  LinkedQueue temp = new LinkedQueue();
		  temp.enqueue(o);
		  queues.enqueue(temp);
	  }
      } catch (QueueEmptyException uf) {
	        System.err.println("Error: attempt to dequeue from empty queue.");
	    }
	  
    return queues;
  }

  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
 * @throws QueueEmptyException 
   **/
  public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) throws QueueEmptyException {
    // Replace the following line with your solution.

	  
	  LinkedQueue a = new LinkedQueue();
	  try{
	  while (!q1.isEmpty() && !q2.isEmpty()) {
		  Comparable item1 = (Comparable)q1.front();
          Comparable item2 = (Comparable)q2.front();
           if (item1.compareTo(item2)<0) {
        	   a.enqueue(q1.dequeue());
           }
           else {
        	   a.enqueue(q2.dequeue());
           }
	  }
	  if (q1.isEmpty()) {
		  a.append(q2);
	  }
	  else {
		  a.append(q1);
	  }
	 }  catch (QueueEmptyException uf) {
	        System.err.println("Error: attempt to dequeue from empty queue.");
	    }
	  
	  
    return a;
  }

  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
 * @throws QueueEmptyException 
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
                               LinkedQueue qSmall, LinkedQueue qEquals, 
                               LinkedQueue qLarge) throws QueueEmptyException {
    // Your solution here.
	  while (!qIn.isEmpty()) {
		  Comparable c = (Comparable) qIn.dequeue();
		  if (c.compareTo(pivot)<0) {
			  qSmall.enqueue(c);
		  }
		  else if (c.compareTo(pivot)==0) {
			  qEquals.enqueue(c);
	      }
		  else if (c.compareTo(pivot)>0) {
			  qLarge.enqueue(c);
	      }
	  }
  }

  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
 * @throws QueueEmptyException 
   **/
  public static void mergeSort(LinkedQueue q) throws QueueEmptyException {
    // Your solution here.
	  if (q.size() <= 1) {
	        return;
	    }
	  try {
	   LinkedQueue split = makeQueueOfQueues(q);
	   
	   while (split.size() >1) {
		   LinkedQueue q1 = (LinkedQueue) split.dequeue();
		   LinkedQueue q2 = (LinkedQueue) split.dequeue();
		   LinkedQueue a = mergeSortedQueues(q1,q2);
		   split.enqueue(a);

	   }
	   
	   split = (LinkedQueue) split.dequeue();
        q.append(split);
	  } catch (QueueEmptyException uf) {
	        System.err.println("Error: attempt to dequeue from empty queue.");
	    }
	  
  }

  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
 * @throws QueueEmptyException 
   **/
  public static void quickSort(LinkedQueue q) throws QueueEmptyException {
    // Your solution here.
	  if(q.size() <= 1){
	        return;
	   }
	  else {
	  LinkedQueue qSmall = new LinkedQueue();
	    LinkedQueue qEquals = new LinkedQueue();
	    LinkedQueue qLarge = new LinkedQueue();
	    
	  
	  Random rand = new Random();
	  int p = rand.nextInt(q.size());
	  
	  Comparable pv = (Comparable) q.nth(p);
      partition(q,pv,qSmall,qEquals,qLarge);
      quickSort(qSmall);
      quickSort(qLarge);
      q.append(qSmall);
      q.append(qEquals);
      q.append(qLarge);
	  
     }
  }

  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(new Integer((int) (size * Math.random())));
    }
    return q;
  }

  /**
   *  main() performs some tests on mergesort and quicksort.  Feel free to add
   *  more tests of your own to make sure your algorithms works on boundary
   *  cases.  Your test code will not be graded.
 * @throws QueueEmptyException 
   **/
  public static void main(String [] args) throws QueueEmptyException {

    LinkedQueue q = makeRandom(10);
    System.out.println(q.toString());
    mergeSort(q);
    System.out.println(q.toString());

    q = makeRandom(10);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString());

    /* Remove these comments for Part III.*/
    Timer stopWatch = new Timer();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    mergeSort(q);
    stopWatch.stop();
    System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    stopWatch.reset();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    quickSort(q);
    stopWatch.stop();
    System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");
   
  }

}
