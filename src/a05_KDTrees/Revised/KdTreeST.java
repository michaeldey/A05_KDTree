package main;

/********************************************************
 *
 *  Project :  <A05 Kd Tree>
 *  File    :  <KdTreeST.java>
 *  Name    :  <Ning Zhang, Michael Dey && Alfredo Rodriguez>
 *  Date    : <4/8/17>
 * 	Class	: CSIS 2420
 * 	Teacher	: Gene Riggs
 *	Description:	constructs a KD Tree algorithm
 *
 ********************************************************/

/********************************************************
 *
 *  Project :  <A05 Kd Tree>
 *  File    :  <KdTreeST.java>
 *  Name    :  <Ning Zhang, Michael Dey && Alfredo Rodriguez>
 *  Date    : <4/8/17>
 * 	Class	: CSIS 2420
 * 	Teacher	: Gene Riggs
 *	Description:	constructs a KD Tree algorithm
 *
 ********************************************************/

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KdTreeST<Value> {
	
	//Fields
	private int size;
	private Node root;
	
	/**Constructor method
	 *
	 * @return root null && set size 0
	 */
	public KdTreeST() {
		root = null;
		size = 0;
	}
	
	/**
	 * Is empty method
	 *
	 * @return 0 if tree is empty
	 */
	public boolean isEmpty() {
		return size() == 0;
		
	}
	
	/**
	 * size method
	 *
	 * @return size of tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * put method
	 *
	 * @param Point2D p
	 * @param Generic Value val
	 *
	 * @void Checks where p or val is null
	 * if not places a value as a Node within the tree
	 */
	public void put(Point2D p, Value val) {
		
		if (p == null || val == null)
			throw new NullPointerException("can't be null");
		root = put(root, p, val, true);
	}
	
	/**
	 * Node private helper method recursively places new point based on cases.
	 * If none of the cases are found or same x coordinate, places right
	 *
	 * @param node
	 * @param p = Point 2D
	 * @param val = Key Value
	 * @param horz = true if it's horizontal; false if it's vertical
	 * @return new Node of RectHV
	 */
	private Node put(Node node, Point2D p, Value val, boolean horz) {
		
		// If there is no root Node
		// Create Root Node
		if (node == null) {
			
			size++;
			
			return new Node(p, val, new RectHV(-Double.MAX_VALUE, -Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE));
		}
		
		//Gets distance between two nodes
		//X distance if horz; Y if it's vertical
		double compare = compareCoord(node, p, horz);
		
		//@return < 0 go to left; > 0 go to the right; == 0 return value
		if (compare < 0)
			node.left = put(node.left, p, val, !horz);
		else if (compare > 0)
			node.right = put(node.right, p, val, !horz);
		else if (node.p.equals(p))
			node.val = val;
		else
			node.right = put(node.right, p, val, !horz);
		
		return node;
	}
	
	/**
	 * compares the prior node point and the new point
	 *
	 * @param node
	 * @param p
	 * @param horz
	 * @return If it's Horizontal compare x; If it's vertical compare y
	 */
	private double compareCoord(KdTreeST<Value>.Node node, Point2D p, boolean horz) {
		if (horz) {
			
			return p.x() - node.p.x();
			
		} else {
			
			return p.y() - node.p.y();
		}
		
	}
	
	/**
	 * get method that traverse Tree and looks for Point2D
	 *
	 * @param p = Point2D
	 * @return value of Point2D you're looking for
	 */
	public Value get(Point2D p) {
		if (p == null)
			throw new NullPointerException();
		return get(root, p, true);
	}
	
	/**
	 * get helper method, recursively finds point
	 *
	 * @param node
	 * @param p
	 * @param horz
	 * @return Recursively traverse Tree to find Point2D
	 */
	private Value get(Node node, Point2D p, boolean horz) {
		
		if (node == null)
			return null;
		
		double compare = compareCoord(node, p, horz);
		
		if (compare < 0)
			return get(node.left, p, !horz);
		else if (compare > 0)
			return get(node.right, p, !horz);
		else if (node.p.equals(p))
			return node.val;
		else
			return get(node.right, p, !horz);
		
	}
	
	/**
	 * contains method
	 *
	 * @param p = Point2D
	 * @return True if the Tree contains p
	 */
	public boolean contains(Point2D p) {
		return get(p) != null;
	}
	
	/**
	 * returns range of RectHV in a Que
	 *
	 * @param rect = RectHV
	 * @return Point2D
	 */
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null)
			throw new java.lang.NullPointerException();
		
		//Creates a Queue that holds all the points
		//That are within the rectangle
		Queue<Point2D> rangequeue = new Queue<Point2D>();
		
		range(rect, rangequeue, root);
		return rangequeue;
		
	}
	
	/**
	 * recursively enqueues points if their are with in the range
	 *
	 * @param rect = RectHv
	 * @param rangequeue = Queue<Point2D>
	 * @param node = KdTreeST<Value>.Node
	 */
	private void range(RectHV rect, Queue<Point2D> rangequeue, KdTreeST<Value>.Node node) {
		
		//Base Case
		if (node == null)
			return;
		
		//Check if the node is not intersecting the Rectangle
		if (!rect.intersects(node.rect))
			return;
		
		//Checks if node is within Rect
		if (rect.contains(node.p))
			//Add node to RangeQueue
			rangequeue.enqueue(node.p);
		
		//Recursively its children left and right
		range(rect, rangequeue, node.left);
		range(rect, rangequeue, node.right);
		
	}
	
	/**
	 * iterable that returns all points using a queue
	 *
	 * @return points in level order
	 */
	public Iterable<Point2D> points() {
		
		//Queue of Point2D objects
		Queue<Point2D> pointsQueue = new Queue<Point2D>();
		
		//Queue of Nodes
		Queue<Node> nodeQueue = new Queue<Node>();
		
		//Sets Root as the first Node
		nodeQueue.enqueue(root);
		
		//While the NodeQueue is not empty
		while (!nodeQueue.isEmpty()) {
			
			//Takes the first node out of the Queue
			//And store it in X
			Node x = nodeQueue.dequeue();
			
			//Checks if the Node object is Null
			if (x == null)
				continue;
			
			//Places x into the points queue
			pointsQueue.enqueue(x.p);
			
			//Place x children into the node queue
			nodeQueue.enqueue(x.left);
			nodeQueue.enqueue(x.right);
		}
		return pointsQueue;
	}
	
	/**
	 * @param p = Point2D
	 * @return the closest Node based on the Euclidean distance
	 */
	public Point2D nearest(Point2D p)
	{
		if (p == null)
			throw new NullPointerException("Point value cannot be null");

		// recursively search for nearest Node
		// return nearest Point2D object
		return nearest(root, p, root.p.distanceSquaredTo(p), root.p, true);

	}

	/**
	 *
	 * @param node
	 * @param p
	 * @param minDis
	 * @param champion
	 * @param horizontal
	 * @return Nearest item to perfection
	 */
	private Point2D nearest(Node node, Point2D p, double minDis, Point2D champion, boolean horizontal) {

		if (node == null) {

			return champion;
		}

		double cmp = node.p.distanceSquaredTo(p);

		if (cmp < minDis) {

			minDis = cmp;
			champion = node.p;
		}

		if (horizontal) {

			if (node.p.x() < p.x()) {

				champion = nearest(node.right, p, minDis, champion, !horizontal);

				if (node.left != null && node.left.p.distanceSquaredTo(p) < minDis) {

					champion = nearest(node.left, p, minDis, champion, !horizontal);
				}
			} else {

				champion = nearest(node.left, p, minDis, champion, !horizontal);

				if (node.right != null && node.right.p.distanceSquaredTo(p) < minDis) {

					champion = nearest(node.right, p, minDis, champion, !horizontal);
				}
			}
		} else {

			if (node.p.y() < p.y()) {

				champion = nearest(node.right, p, minDis, champion, !horizontal);

				if (node.left != null && node.left.p.distanceSquaredTo(p) < minDis) {

					champion = nearest(node.left, p, minDis, champion, !horizontal);
				}
			} else {

				champion = nearest(node.left, p, minDis, champion, !horizontal);

				if (node.right != null && node.right.p.distanceSquaredTo(p) < minDis) {

					champion = nearest(node.right, p, minDis, champion, !horizontal);
				}
			}
		}
		return champion;

	}


	private class Node {
		
		//Fields
		private Point2D p; // the point
		private Value val; // the symbol table maps the point to this value
		private RectHV rect; // the axis-aligned rectangle corresponding to th//
		// node
		private Node left; // the left/bottom subtree
		private Node right;
		
		//Constructor
		public Node(Point2D point, Value value, RectHV rectangle) {
			p = point;
			val = value;
			rect = rectangle;
			
		}
		
		public List<Node> getChildren() {
			return new ArrayList<Node>(Arrays.asList(left, right));
		}
		
		//Return Left
		public Node left() {
			return left;
		}
		
		//Return right
		public Node right() {
			return right;
		}
	}
	
	
	public static void main(String[] args)
	{
		KdTreeST<Point2D> kd  = new KdTreeST<>();
		System.out.println(kd.isEmpty());
		System.out.println(kd.size());
		System.out.println("****************************");
		Point2D p = new Point2D(0.2, 0.3);
		Point2D p2 = new Point2D(0.3, 0.3);
		Point2D p3 = new Point2D(0.4, 0.8);
		Point2D p4 = new Point2D(0.1, 0.3);
		Point2D p5 = new Point2D(0.2, 0.8);
		
		
	}
}
