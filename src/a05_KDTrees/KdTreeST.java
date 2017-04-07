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
        if      (cmp < 0) return get(x.lb, key); 	//key is to the left, so send get() to left child
        else if (cmp > 0) return get(x.rt, key); 	//key is to the right, so send get() to right child
        else              return x.value;			//key == x so return x.value
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
    		x.value = val;					//this is where we want to put, so make x.value val
    		return x;
    	}
    	else if (cmp<0)
    	{
    		x.lb=put(key, val, x.lb);		//we want to put to the left, so call put() on the left node
    		return x;
    	}
    	else 
    	{
    		x.rt=put(key, val, x.rt);		//we want to put to the right, so call put() on the right node
    		return x;
    	}
    	
    }
    
    
}//end of KdTreeST class
