package a05_KDTrees;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RedBlackBST;

public class PointST<Value> {
		private PointST<Value> st;
		private int size;
		private RedBlackBST rB;
		private Queue<Point2D> myQueue = new Queue<Point2D>();//holds a Queue of Point2D objects	
		
		public PointST()                                // construct an empty symbol table of points 
		{
		   rB = new RedBlackBST();
		}
	   
		public boolean isEmpty()
		{
		   // is the symbol table empty?
		   return (size()==0);
		}
	   
	   
		public int size() // number of points
		{
		   return rB.size();
		}
	   
		public void put(Point2D p, Value val)      // associate the value val with point p
		{		 
		   myQueue.enqueue(p); //enqueue p into the queue
		   
		   //key is p.toString()
		   //value is object p
		   rB.put(p.toString() , p);		   
		}
	   
	   
		public Value get(Point2D p)                 // value associated with point p 
		{
		   //we are using toString to create the key
		   //we have to cast the return value as Value so java will accept it
		   return (Value) rB.get(p.toString());
		}
	   
		public boolean contains(Point2D p)            // does the symbol table contain point p? 
		{
		   return (rB.contains(p.toString()));//we use toString as the key
		}
	   
		public Iterable<Point2D> points() // all points in the symbol table 
		{
			return new Iterable<Point2D>()
			{
				@Override
				public Iterator<Point2D> iterator() {
					// TODO Auto-generated method stub
					return new pointIterator();
				}			
			};
		}
		
		private class pointIterator<Point2D> implements Iterator<Point2D>
		{		 	   
		   
			@Override
			public boolean hasNext() 
			{
				return (!myQueue.isEmpty());
			}
	
			@Override
			public Point2D next() 
			{
				return (Point2D) myQueue.dequeue(); //returns the next object in the queue
			}		   
	   }
	   
	   public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle 
	   {
		   rangeIterator range = new rangeIterator();		   
		   return range;
	   }
	   
	   private class rangeIterator <Point2D> implements Iterable<Point2D>
	   {
		   private rNode firstNode;
		   private class rNode
		   {
			   Point2D item;
			   rNode next;
		   }
		   
		   public Iterator<Point2D> iterator()
		   {
			   return new ListIterator();
		   }
		   
		   private class ListIterator implements Iterator<Point2D>
		   {
	    		private rNode current = firstNode;
	            public boolean hasNext()  { return current != null;                     }
	            public void remove()      { throw new UnsupportedOperationException();  }

	            public Point2D next() 
	            {
	                if (!hasNext()) throw new NoSuchElementException();
	                Point2D item = current.item;
	                current = current.next; 
	                return item;   
	            }
		   }
	   }//end of rangeIterator
	   
	   
	   
//	   public           Point2D nearest(Point2D p)             // a nearest neighbor to point p; null if the symbol table is empty 
//	   {
//		   Ning
//	   }
	   
	   
	   
	   
	   public static void main(String[] args)                  // unit testing of the methods (not graded)
	   {
		   PointST test = new PointST();
		   System.out.println("Test isEmpty(): " + test.isEmpty());
		   
		   Point2D myPoint = new Point2D(0.5, 0.75);
		   Point2D myPoint2 = new Point2D(0.1, 0.2);
		   
//		   System.out.println(myPoint);
		   test.put(myPoint, myPoint.toString());
		   test.put(myPoint2, myPoint2.toString());
		   
		   System.out.println(test.get(myPoint));
		   System.out.println(test.get(myPoint2));
		   System.out.println();
		   
		   System.out.println(test.myQueue);
		   
		   System.out.println(test.points().iterator().hasNext());

		   for (Object m : test.points())
		   {
			   System.out.println(m); 
		   }
//		   
	   }//end of main

}//end of PointST
