// ++ AVLTree ++

/* Using the in-order traversal to print tree in an Aesthetic manner and Testing
 */


public class AvlTree {
    private Node root; 	//declaring the root to null
    //inner class represent a node in AVLTree.
    private class Node {
        int num; // number stored in the node
        Node parentNode; // Parent of the node
        Node root; //root of the tree
        Node right; //Right Child of the node
        Node left; // Left Child of the node.
        int height; // Height of the node in the tree.


        /**
         * Constructor
         * Creats a new node
         * @param parentNode The parent of the node.
         * @param val The value stored in it.
         */
        public Node(int val, Node parentNode)
        {
            this.parentNode = parentNode;
            this.num = val;
        }


        /**
         * the left Child of the node
         * @param Child new node
         */
        void Lchild(Node Child)
        {
            this.left = Child;
        }

        /**
         * for the Right Child of the node.
         * @param Child new right child node
         */
        void Rchild(Node Child)
        {
            if (Child != null)
            {  Child.parentNode = this;
            }
            this.right = Child;
        }
        /**
         * to Print each node in the tree with its value, height and if its tje root or parent
         * @return each node in the tree
         */
        public String toString()
        {
            return "n - '"+  num + "' h " + height + ":  p " + (parentNode == null ?
                    "-(Root)" : parentNode.num) + "     ||     ";
        }

    }


    /**
     * height of the node
     * @param node whose height we want
     * @return height of the tree
     */
    private int height(Node node) {
        return node == null ? -1:node.height;
    }

    /**
     * Updating the height of the nodes in the tree by the
     * MAx height of its right and left sub +1
     * @param  node is the node in rotation
     */
    private void updateHeight(Node node) {
        //taking Maximum height of the tree
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * the difference between the heights of the right
     * and left subtrees of the node
     * @param node particular nodes left and
     *      right subtrees height difference
     * @return returns the result
     */
    private int heightDiff(Node node) {
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        int result = rightHeight - leftHeight;
        return result;
    }


    /**
     * Inserting node in the Avl tree
     * @param value to be inserted
     */
    public void insert(int value) {
        insert(root, value);
    }

    /**
     * Insert a particular node at a particular index
     * @param  node - is gonna be inserted at the particular index
     */
    private void insert(Node node, int num) {

        //tree is empty-> insert at root
        if (root == null) {
            root = new Node(num, null);
        }

        else {
            // if node num is greater than the root of the tree -> insert node at the Right Subtree
            if (num > node.num) {
                if (node.right != null)
                {
                    insert(node.right, num);
                }
                else {
                    node.right = new Node(num, node);
                }


                // when the right subtree is more, perfrom -> ROTATION
                if (height(node.right) - height(node.left) == 2) //(imbalance 2)
                {
                    if (num > node.right.num)
                        //left ROTATE (if the num > right)
                        LeftRotate(node);
                    else {
                        //Right LEft rotation is done
                        RLRotate(node);
                    }
                }
            }


            // if node is less than the root ->  insert node at the LEft Subtree
            else if (num < node.num) {
                if (node.left != null)
                {insert(node.left, num);
                }
                else {
                    node.left = new Node(num, node); }

                //when the left subtree is more
                if (height(node.left) - height(node.right) == 2)  //(imbalance 2)
                {
                    if (num < node.left.num) {
                        //Right rotation
                        rightRotate(node);
                    } else {
                        //left right rotation
                        LRRotate(node);
                    }
                }
            }
            //updating the height of each node inserted
            updateHeight(node);
        }
    }



    //delete algorithm
    /**
     * Deleting the item
     * @param index is the index at which the item is to be deleted
     * @return true if the item exists otherwise false;
     */
    public boolean delete(int index) {
        //using search method to find it
        Node selNode = search(index);
        //if not found
        if (selNode == null){
            return false;}
        else
        {selNode = deleteNode(selNode);
            reBalanceT(selNode.parentNode);
            return true;
        }
    }


    /**
     * Deleting the pariticular node from the Avl tree
     * @param selNode where the node is to be deleted
     */
    private Node deleteNode(Node selNode) {
        //no children to the node
        if (noChildren(selNode))
        {		//if its the left child
            if (ifLeftNode (selNode)) {
                selNode.parentNode.left = null; }
            else { //else right
                selNode.parentNode.right = null; }
        }

        //if its only 1 Child
        else if (selNode.left == null ^ selNode.right == null)
        {
            Node notEmpty = selNode.left == null ? selNode.right : selNode.left;
            if (ifLeftNode (selNode)) { //if its the left child
                selNode.parentNode.Lchild(notEmpty);
            }
            else { //else right
                selNode.parentNode.Rchild(notEmpty);
            }
        }

        //IF there are 2 children
        else {
            // before - followed by
            Node before  = before (selNode);
            selNode.num = before .num;
            selNode = deleteNode(before );
        }

        updateHeight(selNode.parentNode);
        return selNode;
    }





    /**
     * fixing imbalance by LEft Rotation
     * @param  Critical is the critical node
     */
    private void LeftRotate(Node Critical) {
        Node rightChild = Critical.right;
        Node parentNode = Critical.parentNode;

        Node LfchildofRightc = rightChild.left;
        Critical.Rchild(LfchildofRightc);
        rightChild.Lchild(Critical);
        //if the parent is null
        if (parentNode == null) {
            this.root = rightChild;
            rightChild.parentNode = null;
            return; //doesnt throw
        }
        //if the parent node is the critical node
        if (parentNode.left == Critical) {
            parentNode.Lchild(rightChild);}
        else
        { parentNode.Rchild(rightChild); }
        //updating the height
        updateHeight(Critical);
        updateHeight(rightChild);
    }




    /**
     * fixing imbalance by Right Rotation
     * @param  Critical is the critical node
     */

    private void rightRotate(Node Critical) {
        Node leftChild = Critical.left;
        Node parentNode = Critical.parentNode;

        Node RchildOfLc = leftChild.right;
        Critical.Lchild(RchildOfLc);
        leftChild.Rchild(Critical);
        //if the parent is null
        if (parentNode == null) {
            this.root = leftChild;
            leftChild.parentNode = null;
        }
        //if the parent node is the critical node
        if (parentNode.left == Critical)
        {
            parentNode.Lchild(leftChild);
        }
        else
        {parentNode.Rchild(leftChild);}

        //update the height of the nodes changed
        updateHeight(Critical);
        updateHeight(leftChild);
    }




    /**
     * fixing imbalance by Right Rotation
     * @param  node is the node in rotation
     */
    private void LRRotate(Node node) {
        //left first
        LeftRotate(node.left);
        //then right
        rightRotate(node);
    }

    /**
     * fixing imbalance by Right Rotation
     * @param  node is the node in rotation
     */
    private void RLRotate(Node node) {
        //right rotation first
        rightRotate(node.right);
        //then left rotation
        LeftRotate(node);
    }




    /**
     * to check if its at left Child
     * @param Child is the node we checking @
     */
    private boolean ifLeftNode (Node Child) {
        return (Child.parentNode != null && Child.parentNode.left == Child );
    }

    /**
     *  to check if thetr no children
     * @param node of the node
     */
    private boolean noChildren(Node node) {
        return (node.right == null && node.left == null);
    }


    /**
     *to find the predessor/ before of the node
     * @param node of the one we find the before of
     */
    private Node before (Node node) {
        Node current = node.left;
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }

    /**
     * rebalances the tree// fixing the immbalance
     * by suitable rotation
     * @param node the nodes immabalance
     */
    private void reBalanceT(Node node) {
        Node parentNode = node.parentNode;
        int difference = heightDiff(node); //the diff btw right and left subtree

        if (difference == 2)
        { //the difference is 2 then
            if (height(node.right.right) >= height(node.right.left)) {
                //left rotate
                LeftRotate(node);
            } else {
                //right and then left rotate
                RLRotate(node);
            }
        }

        else if (difference == -2)
        { //if the diff is -2
            if (height(node.left.left) >= height(node.left.right)) {
                //rotate RIght
                rightRotate(node);
            } else {
                //left and then right rotation
                LRRotate(node);
            }

        }

        //updating the height of the tree
        //after changing the positing
        updateHeight(node);
    }



    /**
     *BinarySearch Method to search for the node
     * @param node the node
     * @param index of the node
     */
    private Node BinarySearch(Node node, int index) {
        //if the node is not found
        if (node == null) return null;
        ///
        if (index == node.num) { return node;}

        if (index > node.num && node.right != null)
        {//right of it
            return BinarySearch(node.right, index);
        }

        if (index < node.num && node.left != null)
        {//left of it
            return BinarySearch(node.left, index);
        }

        return null;
    }


    /**
     * searche for the index
     * @param index of the node
     */
    public Node search(int index) {
        //using the binary Search
        return BinarySearch(root, index);
    }




    /**
     * Using the in-order traversal to print
     * tree in an aesthetic manner
     * @param node in the AVl tree
     */
    private void inorderTraversal(Node node) {
        //if the node is non empty
        if (node != null) {
            inorderTraversal(node.left);
            //print using the toString Method!
            System.out.print(node.toString());
            inorderTraversal(node.right);
        }
    }


    /**
     * Using the in-order traversal to print
     * tree in an aesthetic manner as well as
     * for Well testing the Avl tree Code
     */
    public void printTree() {
        inorderTraversal(root);
        //print!!
        System.out.println();
    }




    public static void main(String[] args) {
        AvlTree T = new AvlTree();

        System.out.println(">> In the following AVLTree:- n = node 'value' , p = the parent of the node , and h = height of it");

        // Testing!!

        //testing if the tree is empty
        T.printTree();
        System.out.println("the tree is empty!");

        System.out.println("  ");
        //testing the insert Method
        T.insert(10);
        T.insert(2);
        T.insert(20);
        T.printTree();

        //testing if the tree is still balanced after adding more nodes (Rebalancing)
        //as root is still 10
        System.out.println(" e ");
        System.out.println("> When 6 and 12 is inserted, the tree is still balanced!");
        T.insert(6);
        T.insert(12);
        T.insert(22);
        T.insert(23);
        T.printTree();


        //deleting node with 2 children
        System.out.println("  ");
        System.out.println("> Delete 20 (has 2 children) ");
        T.delete(20);
        T.printTree();


        //deleting node with 1 Child
        System.out.println("  ");
        System.out.println("> Delete 2 (has 1 child) ");
        T.delete(2);
        T.printTree();


        //testing the balancing method after deleting the root
        System.out.println("  ");
        System.out.println("> Delete the root 10 (Root) ");
        T.delete(10);
        T.printTree();

        //testing if the tree is still balanced if we delete the root
        System.out.println("  ");
        System.out.println("> Inserting more nodes to check rotation");
        T.insert(26);
        T.insert(9);
        T.insert(5);
        T.printTree();


    }}
