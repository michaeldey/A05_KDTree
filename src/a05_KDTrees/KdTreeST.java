package a05_KDTrees;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Point2D;

/*
* code is based off of Sedgwick's BST.java implementation
*/
public class KdTreeST <Key extends Comparable<Key>>//NearestNeighbor calls KdTreeST<Integer>
{
	private Node root; //start of the tree
	private static int size; //counts the number of nodes
	
	private class Node{
		   private Key key;			// the key (probably an integer)
		   private Point2D value;   // the symbol table maps the point to this value
		   private RectHV rect;    	// the axis-aligned rectangle corresponding to this node
		   private Node lb;        	// the left/bottom subtree
		   private Node rt;        	// the right/top subtree
		   
		   private Node(Key key, Point2D value)
		   {
//			   this.p = (Point2D) key;					//sets the point to the given Point2D
//			   
//			   this.value = value;				//value is a String
			   size++; 							//every time a Node is created, size is incremented
		   }
		}
	
	public boolean contains(Key key)
	{
		return get(key) != null;
	}
	
	public boolean isEmpty()
	{
		return (size()==0);
	}
	
	public int size()
	{
		return size;
	}
	
	public Point2D nearest(Point2D query)
	{
		//returns nearest Point2D
		return null;		
	}
	
    public Point2D get(Key key) 
    {
        return get(root, key);
    }
   
    //return value associated with the given key rooted in tree at x
    private Point2D get(Node x, Key key) 
    {
        if (key == null) throw new IllegalArgumentException("called get() with a null key");
        if (x == null) return null;
        int cmp = key.compareTo((Key) x.key);
        if      (cmp < 0) return get(x.lb, key); 	//key is to the left, so send get() to left child
        else if (cmp > 0) return get(x.rt, key); 	//key is to the right, so send get() to right child
        else              return x.value;			//key == x so return x.value
    }
    
    //NearestNeighbor calls: "kdtree.put(p, i);" where p is a Point2D and i is an int
    public void put(Point2D val, Key key) 
    {
        put(val, key, root);
    }
    
    private Node put(Point2D val, Key key,  Node x)
    {
    	if (key == null) throw new IllegalArgumentException("called get() with a null key");
    	if (x == null) return new Node(key, val);
    	int cmp = key.compareTo((Key)x.key);
    	if (cmp==0)
    	{
    		x.value = val;					//this is where we want to put, so make x.value val
    		return x;
    	}
    	else if (cmp<0)
    	{
    		x.lb=put(val, key, x.lb);		//we want to put to the left, so call put() on the left node
    		return x;
    	}
    	else 
    	{
    		x.rt=put(val, key, x.rt);		//we want to put to the right, so call put() on the right node
    		return x;
    	}
    	
    }
    
    
}//end of KdTreeST class
