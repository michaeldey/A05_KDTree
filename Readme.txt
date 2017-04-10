/******************************************************************************
 *  Name: Alfredo Rodriguez    
 *
 *  Partner Name:   Ning Zhang, Michael Dey      
 *
 *  Hours to complete assignment (optional): 20 hours
 *
 ******************************************************************************/

/******************************************************************************
 *  Describe the Node data type you used to implement the
 *  2d-tree data structure.
 *****************************************************************************/

Uses a Point2D point, with a generic Value val and a rectangle with left and right children
to create a tree

/******************************************************************************
 *  Describe your method for range search in a kd-tree.
 *****************************************************************************/

Recursively checks the left and right node to see if the any node points intersect the 
Rectangle, checks if they’re within, and recursively sends the method to the left
And right children.

/******************************************************************************
 *  Describe your method for nearest neighbor search in a kd-tree.
 *****************************************************************************/

Calculates the Euclidean distance between two points and recursively checks each
Node for smaller and smaller distances.

Stop checking the tree if the distance stops getting smaller

/******************************************************************************
 *  Using the 64-bit memory cost model from the textbook and lecture,
 *  give the total memory usage in bytes of your 2d-tree data structure
 *  as a function of the number of points N. Use tilde notation to
 *  simplify your answer (i.e., keep the leading coefficient and discard
 *  lower-order terms).
 *
 *  Include the memory for all referenced objects (including
 *  Node, Point2D, and RectHV objects) except for Value objects
 *  (because the type is unknown). Also, include the memory for
 *  all referenced objects.
 *
 *  Justify your answer below.
 *
 *****************************************************************************/

bytes per Point2D:  

bytes per RectHV: 

bytes per KdTree of N points:   ~

/******************************************************************************
 *  How many nearest neighbor calculations can your brute-force
 *  implementation perform per second for input100K.txt (100,000 points)
 *  and input1M.txt (1 million points), where the query points are
 *  random points in the unit square? Show the math how you used to determine
 *  the operations per second. (Do not count the time to read in the points
 *  or to build the 2d-tree.)
 *
 *  Repeat the question but with the 2d-tree implementation.
 *****************************************************************************/

                       calls to nearest() per second
                     brute force               2d-tree
                     ---------------------------------
input100K.txt

input1M.txt


/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/

Yes, nearestNode has issues with causing the program to crash if it gets to 
Certain points it dislikes

/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 *****************************************************************************/

None other than the inter such as stackOverflow.

/******************************************************************************
 *  Describe any serious problems you encountered.                    
 *****************************************************************************/

Not enough time to finish the assignment

/******************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 *****************************************************************************/

We did this by peer programing by which we all created every class, test
And methods together

/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on  how helpful the class meeting was and on how much you learned 
 * from doing the assignment, and whether you enjoyed doing it.
 *****************************************************************************/

This assignment overall was very daunting and confusing. The explanations given
By the Princeton system was very vague causing us to be confused for hours on end
Until we came with solutions and ended up finishing the assignment.

Overall this assignment could have been polished or explained better.
