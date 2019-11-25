
// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree. Note that all "matching" is
 * based on the compareTo method.
 *
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
    /**
     * Construct the tree.
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     *
     * @param x the item to insert.
     */
    public void insert(AnyType x) {
        root = insert(x, root);
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     *
     * @param x the item to remove.
     */
    public void remove(AnyType x) {
        root = remove(x, root);
    }

    /**
     * Find the smallest item in the tree.
     *
     * @return smallest item or null if empty.
     */
    public AnyType findMin() {
        // if( isEmpty( ) )
        //     throw new UnderflowException();
        return findMin(root).element;
    }

    /**
     * Find the largest item in the tree.
     *
     * @return the largest item of null if empty.
     */
    public AnyType findMax() {
        // if( isEmpty( ) )
        //     throw new UnderflowException( );
        return findMax(root).element;
    }

    /**
     * Find an item in the tree.
     *
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty() {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree() {
        if (isEmpty())
            System.out.println("Empty tree");
        else
            printTree(root);
    }

    /**
     * Internal method to insert into a subtree.
     *
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return new BinaryNode<>(x, null, null);

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = insert(x, t.left);
        else if (compareResult > 0)
            t.right = insert(x, t.right);
        else
            ; // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     *
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return t; // Item not found; do nothing

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = remove(x, t.left);
        else if (compareResult > 0)
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null) // Two children
        {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else
            t = (t.left != null) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     *
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
        if (t == null)
            return null;
        else if (t.left == null)
            return t;
        return findMin(t.left);
    }

    /**
     * Internal method to find the largest item in a subtree.
     *
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
        if (t != null)
            while (t.right != null)
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     *
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return false;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            return contains(x, t.left);
        else if (compareResult > 0)
            return contains(x, t.right);
        else
            return true; // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     *
     * @param t the node that roots the subtree.
     */
    private void printTree(BinaryNode<AnyType> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }

    /**
     * Internal method to compute height of a subtree.
     *
     * @param t the node that roots the subtree.
     */
    private int height() {
        return height(root);
    }

    private int height(BinaryNode<AnyType> t) {
        if (t == null)
            return -1;
        else
            return 1 + Math.max(height(t.left), height(t.right));
    }

    // Node Count
    private int count() {
        return count(root);
    }

    private int count(BinaryNode<AnyType> x) {
        if (x != null) {
            count(x.left);
            nodeCount++;
            count(x.right);
        }
        return nodeCount;
    }

    // isFull
    private boolean isFull() {
        return isFull(root);
    }

    private boolean isFull(BinaryNode<AnyType> x) {
        if (x == null)
            return true;

        if (x.left == null && x.right == null)
            return true;

        if (x.left != null && x.right != null)
            return (isFull(x.left) && isFull(x.right));

        return false;
    }

    // Compare Structure
    private boolean compareStructure(BinarySearchTree x) {
        return compareStructure(root, x.root);
    }

    private boolean compareStructure(BinaryNode<AnyType> x1, BinaryNode<AnyType> x2) {
        if (x1 == null && x2 == null)
            return true;

        if (x1 != null && x2 != null)
            return (compareStructure(x1.left, x2.left) && compareStructure(x1.right, x2.right));

        return false;
    }

    // isEqual
    private boolean isEqual(BinarySearchTree x) {
        return isEqual(root, x.root);
    }

    private boolean isEqual(BinaryNode<AnyType> x1, BinaryNode<AnyType> x2) {
        if (x1 == null && x2 == null)
            return true;

        if (x1 != null && x2 != null)
            return (x1.element == x2.element && isEqual(x1.left, x2.left) && isEqual(x1.right, x2.right));

        return false;
    }

    // copy
    private BinaryNode<AnyType> copy() {
        BinaryNode<AnyType> newNode = new BinaryNode<AnyType>(root.element);
        copy(root, newNode);
        return newNode;
    }

    private void copy(BinaryNode<AnyType> x1, BinaryNode<AnyType> x2) {
        if (x1 != null) {
            x2.element = x1.element;
            if (x1.left != null) {
                x2.left = new BinaryNode<AnyType>(x1.left.element);
                copy(x1.left, x2.left);
            }
            if (x1.right != null) {
                x2.right = new BinaryNode<AnyType>(x1.right.element);
                copy(x1.right, x2.right);
            }
        }
    }

    // mirror
    private BinaryNode<AnyType> mirror() {
        BinaryNode<AnyType> newNode = new BinaryNode<AnyType>(root.element);
        mirror(root, newNode);
        return newNode;
    }

    private void mirror(BinaryNode<AnyType> x1, BinaryNode<AnyType> x2) {
        if (x1 != null) {
            x2.element = x1.element;
            if (x1.left != null) {
                x2.right = new BinaryNode<AnyType>(x1.left.element);
                mirror(x1.left, x2.right);
            }
            if (x1.right != null) {
                x2.left = new BinaryNode<AnyType>(x1.right.element);
                mirror(x1.right, x2.left);
            }
        }
    }

    // isMirror
    private boolean isMirror(BinarySearchTree<AnyType> x) {
        BinaryNode<AnyType> xNew = new BinaryNode<AnyType>(x.root.element);
        xNew = mirror();
        return isEqual(xNew, x.root);
    }

    // rightShift
    private void rightShift(AnyType n) {
        BinaryNode<AnyType> temp = root, prev = null;
        while (temp != null) {
            if (temp.element.compareTo(n) == 0)
                break;
            else {
                if (temp.element.compareTo(n) < 0) {
                    prev = temp;
                    temp = temp.right;
                } else {
                    prev = temp;
                    temp = temp.left;
                }
            }
        }
        if (temp == root) {
            if (temp.left != null) {
                prev = temp;
                root = temp.left;
                prev.left = root.right;
                root.right = prev;
            } else
                System.out.println("Left Shift is not possible");
        } else if (temp != null) {
            if (prev.left.element.compareTo(temp.element) == 0) {
                if (temp.left != null) {
                    prev.left = temp.left;
                    temp.left = temp.left.right;
                    prev.left.right = temp;

                } else
                    System.out.println("Left Shift is not possible");
            } else {
                if (temp.left != null) {
                    prev.right = temp.left;
                    temp.left = temp.left.right;
                    prev.right.right = temp;
                } else
                    System.out.println("Left shift is not possible");
            }
        } else
            System.out.println(n + " not found");
    }

    // leftShift
    private void leftShift(AnyType n) {
        BinaryNode<AnyType> temp = root, prev = null;
        while (temp != null) {
            if (temp.element.compareTo(n) == 0) {
                break;
            } else {
                if (temp.element.compareTo(n) < 0) {
                    prev = temp;
                    temp = temp.right;
                } else {
                    prev = temp;
                    temp = temp.left;
                }
            }
        }
        if (temp == root) {

            if (temp.left != null) {
                prev = temp;
                root = temp.right;
                prev.right = root.left;
                root.left = prev;
            } else {
                System.out.println("Left Shift is not possible");
            }
        } else if (temp != null) {
            if (prev.left.element.compareTo(temp.element) == 0) {
                if (temp.left != null) {
                    prev.left = temp.right;
                    temp.right = temp.right.left;
                    prev.left.left = temp;

                } else {
                    System.out.println("Left Shift is not possible");
                }
            } else {
                if (temp.left != null) {
                    prev.right = temp.right;
                    temp.right = temp.right.left;
                    prev.right.left = temp;
                } else {
                    System.out.println("Left shift is not possible");
                }
            }
        } else
            System.out.println(n + " not found");
    }

    // printLevel
    void printLevel() {
        int h = height();
        int i;
        for (i = 1; i <= h + 1; i++) {
            printLevel(root, i);
            System.out.println();
        }

    }

    void printLevel(BinaryNode<AnyType> root, int level) {
        if (root == null)
            return;

        if (level == 1)
            System.out.print(root.element + " ");

        else if (level > 1) {
            printLevel(root.left, level - 1);
            printLevel(root.right, level - 1);
        }
    }

    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType> {
        // Constructors
        BinaryNode(AnyType theElement) {
            this(theElement, null, null);
        }

        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }

        AnyType element; // The data in the node
        BinaryNode<AnyType> left; // Left child
        BinaryNode<AnyType> right; // Right child
    }

    /** The tree root. */
    private BinaryNode<AnyType> root;
    int nodeCount = 0;

    // Test program
    public static void main(String[] args) {
        // BinarySearchTree<Integer> t = new BinarySearchTree<>( );
        // final int NUMS = 4000;
        // final int GAP = 37;

        // System.out.println( "Checking... (no more output means success)" );

        // for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
        // t.insert( i );

        // for( int i = 1; i < NUMS; i+= 2 )
        // t.remove( i );

        // System.out.println( tnew.height() );

        // if( NUMS < 40 )
        // t.printTree( );
        // if( t.findMin( ) != 2 || t.findMax( ) != NUMS - 2 )
        // System.out.println( "FindMin or FindMax error!" );

        // for( int i = 2; i < NUMS; i+=2 )
        // if( !t.contains( i ) )
        // System.out.println( "Find error1!" );

        // for( int i = 1; i < NUMS; i+=2 )
        // {
        // if( t.contains( i ) )
        // System.out.println( "Find error2!" );
        // }

        BinarySearchTree<Integer> tnew1 = new BinarySearchTree<>();
        BinarySearchTree<Integer> tnew2 = new BinarySearchTree<>();
        BinarySearchTree<Integer> tnew1Copy = new BinarySearchTree<>();

        int a[] = { 4, 2, 7, 1, 3, 6, 9 };
        int b[] = { 4, 2, 7, 1, 3, 6, 8 };

        for (int i = 0; i < a.length; i++)
            tnew1.insert(a[i]);
        for (int i = 0; i < b.length; i++)
            tnew2.insert(b[i]);

        System.out.println("***** Tree 1 *****");
        tnew1.printLevel();

        System.out.println("\n***** Tree 2 *****");
        tnew2.printLevel();
        // a
        System.out.println("\na) nodeCount : Tree 1 = " + tnew1.count());

        // b
        if (tnew1.isFull())
            System.out.println("\nb) isFull : Tree 1 is Full");
        else
            System.out.println("\nb) isFull : Tree 1 is Not Full");

        // c
        if (tnew1.compareStructure(tnew2))
            System.out.println("\nc) compareStructure : Tree 1 & 2 has Same Structure");
        else
            System.out.println("\nc) compareStructure : Tree 1 & 2 has Not Same Structure");

        // d
        if (tnew1.isEqual(tnew2))
            System.out.println("\nd) isEqual : Tree 1 & 2 are Equal");
        else
            System.out.println("\nd) isEqual : Tree 1 & 2 are Not Equal");

        // e
        System.out.println("\ne) copy :");
        if (tnew1.root == null)
            System.out.println("Null Tree");
        else {
            tnew1Copy.root = tnew1.copy();
            System.out.println("***** Original Tree *****");
            tnew1.printLevel();
            System.out.println("***** Copied Tree *****");
            tnew1Copy.printLevel();
        }

        // f
        System.out.println("\nf) mirror :");
        if (tnew1.root == null)
            System.out.println("Null Tree");
        else {
            tnew1Copy.root = tnew1.mirror();
            System.out.println("***** Original Tree *****");
            tnew1.printLevel();
            System.out.println("***** Mirror Tree *****");
            tnew1Copy.printLevel();
        }

        // g
        System.out.println("\ng) isMirror");
        System.out.println("***** Tree 1 *****");
        tnew1.printLevel();
        System.out.println("***** Tree 2 *****");
        tnew1Copy.printLevel();
        if (tnew1.isMirror(tnew1Copy))
            System.out.println("Tree 1 & 2 are Mirror");
        else
            System.out.println("Tree 1 & 2 are Not Mirror");

        // h
        System.out.println("\nh) rotateRight");
        tnew1.rightShift(4);
        tnew1.printLevel();

        // i
        System.out.println("\ni) rotateLeft");
        tnew1.leftShift(2);
        tnew1.printLevel();

        // j
        System.out.println("\nj) printLevel");
        tnew1.printLevel();

        // k
        System.out.println("\nk) demo");
        System.out.println("Done");
    }
}
