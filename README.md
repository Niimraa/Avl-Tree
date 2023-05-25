# Avl-Tree
: Implementation of an AVL tree: Efficient Insertion and Deletion of elements

## Requirements:
- AVL tree ADT must be able to contain elements of **any comparable object type**, but all
  elements in a single tree are the same type.
- ADT must support insertion of new elements, one at a time. Additionally, 
  ### the *Insertion algorithm implementation* properties:
  1. It must be O(log n) in the worst case. This means you have to store subtree heights in the
  nodes  to avoid incurring the linear-time cost of a recursive height
  method. 
  2. The tree must restore the AVL property after insertions using rotations.
  3. You are free to choose whether or not to allow duplicate items in the AVL tree.

 - ADT must have an operation for determining whether a given element is in the tree.
 - ADT must have an operation that deletes an element. The mechanism for specifying the
  item to delete is up to you, but again your design decisions here will be assessed for their appropriateness.
  ### the *Deletion algorithm implementation properties:*
  1. It must be O(log n) in the worst case. At worst O(n log n).
  2. It must restore the AVL property after deletions using rotations.
  
  - You must include a **test program**  that *demonstrates the correctness of implementation.* 
  Your program’s output should be designed to convince the marker that your insertion, rotation, and lookup, and search methods work correctly. *you can print out the tree, describe
  what operation is about to be performed, and then print the resulting tree. The onus is on you to use your test program to demonstrate that your implementation works*.. 
        Thus you should demonstrate cases that invoke all of the different kinds of rotations     and special cases that might arise. Your examples should be non-trivial, but simple enough     that they can be easily verified by inspection
