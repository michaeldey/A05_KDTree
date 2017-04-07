package a05_KDTrees;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
* code is based off of Sedgwick's BST.java implementation
*/
public class KdTreeST <Key extends Comparable<Key>,Value>
{
	private Node root; //start of the tree
	private static int size; //counts the number of nodes
	
	private class Node {
		   private Point2D key;    // the point
		   private Value value;    // the symbol table maps the point to this value
		   private RectHV rect;    // the axis-aligned rectangle corresponding to this node
		   private Node lb;        // the left/bottom subtree
		   private Node rt;        // the right/top subtree
		   
		   private Node(Key key, Value value)
		   {
			   this.key = (Point2D)key;					//sets the point to the given Point2D
			   this.value = value;						//value is a String
			   size++; 									//every time a Node is created, size is incremented
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
	
    public Value get(Key key) 
    {
        return get(root, key);
    }
   
    //return value associated with the given key rooted in tree at x
    private Value get(Node x, Key key) 
    {
        if (key == null) throw new IllegalArgumentException("called get() with a null key");
        if (x == null) return null;
        int cmp = key.compareTo((Key) x.key);
        if      (cmp < 0) return get(x.lb, key);
        else if (cmp > 0) return get(x.rt, key);
        else              return x.value;
    }
    
    
    public void put(Key key, Value val) 
    {
        put(key, val, root);
    }
    
    private Node put(Key key, Value val, Node x)
    {
    	if (key == null) throw new IllegalArgumentException("called get() with a null key");
    	if (x == null) return new Node(key, val);
    	int cmp = key.compareTo((Key)x.key);
    	if (cmp==0)
    	{
    		x.value = val;
    		return x;
    	}
    	else if (cmp<0)
    	{
    		x.lb=put(key, val, x.lb);
    		return x;
    	}
    	else 
    	{
    		x.rt=put(key, val, x.rt);
    		return x;
    	}
    	
    }
    
    
}//end of KdTreeST class
