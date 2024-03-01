/* Submitted by :- Niteesh Kulhari
 * Course :-  CS-570
 * Treap implementation
 * */

import java.util.Random;
import java.util.Stack;


public class Treap<E extends Comparable<E>> {

    // Private static inner class.
     
    private static class Node<E> {
        public E data;
        public int priority;
        public Node<E> left = null;
        public Node<E> right = null;

        public Node(E data, int priority) {
            if (data == null) {
                throw new NullPointerException("Error: Data parameter cannot be null");
            }

            this.data = data;
            this.priority = priority;
        }

        //Performs a right rotation according to Figure 2
        public void rotateRight() {

            Node<E> temp_node = new Node<E>(this.data, this.priority);

            if (this.left != null) {
                if (this.right != null) {
                    temp_node.right = this.right;
                }
                if (this.left.right != null) {
                    temp_node.left = this.left.right;
                }
                this.data = this.left.data;
                this.priority = this.left.priority;
                this.right = temp_node;
                if (this.left.left != null) {
                    this.left = this.left.left;
                } else {
                    this.left = null;
                }
            }
        }

        
         //Performs a left rotation according to Figure 2
         
        public void rotateLeft() {
            Node<E> temp_node = new Node<E>(this.data, this.priority);
            if (this.right != null) {
                if (this.left != null) {
                    temp_node.left = this.left;
                }
                if (this.right.left != null) {
                    temp_node.right = this.right.left;
                }
                this.data = this.right.data;
                this.priority = this.right.priority;
                this.left = temp_node;
                if (this.right.right != null) {
                    this.right = this.right.right;
                } else {
                    this.right = null;
                }
            }
        }

        // method that prints Keys and it's priority.
         
        @Override
        public String toString() {
            return data.toString();
        }
    }

    private Random priorityGenerator;
    private Node<E> root;

    
    //creates an empty treap
    public Treap() {
        this.priorityGenerator = new Random();
        this.root = null;
    }

    //Treap(long seed) creates an empty treap and initializes priorityGenerator
    
    public Treap(long seed) {
        if (seed <= 0) {
            throw new NullPointerException("Error: Seed parameter cannot less or equal to 0");
        }
        this.priorityGenerator = new Random(seed);
        this.root = null;
    }

    //To insert the given element into the tree, create a new node containing key
     
    boolean add(E key) {
        // Random random = new Random;
        // int n = random.nextInt(100);

        return add(key, this.priorityGenerator.nextInt());
    }

    // helper method to restore the heap invariant
     
    void reheap(Stack<Node<E>> stack, Node<E> child) {
        Node<E> pre = stack.pop();
        while (pre != null && pre.priority < child.priority) {
            if (((Comparable<E>) child.data).compareTo((E) pre.data) < 0) {
                pre.rotateRight();
                if (stack.isEmpty()) {
                    return;
                }
            } else {
                pre.rotateLeft();
                if (stack.isEmpty()) {
                    return;
                }
            }
            pre = stack.pop();
        }
    }

    //To insert the given element into the tree, create a new node containing key
     
    boolean add(E key, int priority) {
        if (key == null || priority < 0) {
            throw new NullPointerException(
                    "Error: Key parameter cannot be null and priority number must be greater than 0");
        }

        if (this.root == null) {
            this.root = new Node<E>(key, priority);

            return true;
        }

        Node<E> root = this.root;
        Stack<Node<E>> stack = new Stack<Node<E>>();

        while (root != null) {
            stack.push(root);
            if (((Comparable<E>) root.data).compareTo(key) == 0) {
                return false;
            } else if (((Comparable<E>) root.data).compareTo(key) > 0) {
                root = root.left;
            } else {
                root = root.right;
            }
        }

        root = stack.peek();

        if (((Comparable<E>) root.data).compareTo(key) > 0) {
            Node<E> node = new Node<E>(key, priority);
            stack.peek().left = node;
            stack.push(node);
        } else {
            Node<E> node = new Node<E>(key, priority);
            stack.peek().right = node;
            stack.push(node);
        }

        Node<E> node = stack.pop();
        reheap(stack, node);

        return true;
    }

    boolean del;

    // Deletes the node with the given key from the treap.
     
    boolean delete(E key) {
        if (key == null) {
            throw new NullPointerException("Error: Key parameter cannot be null");
        }
        if (find(key) == false) {
            System.out.println("Node with that key was not found in the Treap");
        }

        this.root = deleter(this.root, key);

        return del;
    }

    // helper method with the delete
    private Node<E> deleter(Node<E> node, E key) {
        if (key == null) {
            throw new NullPointerException("Error: Key parameter cannot be null");
        }
        if (node == null) {
            del = false;
            return null;
        } else if (key.compareTo(node.data) < 0) {
            node.left = deleter(node.left, key);
        } else if (key.compareTo(root.data) > 0) {
            node.right = deleter(node.right, key);
        } else {
            del = true;
            if (node.left == null && node.right == null) {
                return null;
            } else if (node.right == null) {
                node.rotateRight();
                node.right = deleter(node.right, key);
            } else if (node.left == null) {
                node.rotateLeft();
                node.left = deleter(node.left, key);
            }
        }

        return node;
    }

    // Finds a node with the given key.
    private boolean find(Node<E> root, E key) {
        if (key == null) {
            throw new NullPointerException("Error: Key parameter cannot be null");
        }
        if (this.root == null) {
            return false;
        }

        if (key.compareTo(root.data) < 0) {
            return find(root.left, key);
        } else if (key.compareTo(root.data) == 0) {
            return true;
        } else {
            return find(root.right, key);
        }
    }

    // Finds a node with the given key in the treap
     
    public boolean find(E key) {
        if (key == null) {
            throw new NullPointerException("Error: Key Parameter cannot be Null");
        }

        return find(this.root, key);
    }

    // A method to transverse the tree.
    
    private void preorderTraverse(Node<E> node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append(" ");
        }
        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append("(key=");
            sb.append(node.toString());
            sb.append(", priority=");
            sb.append(node.priority);
            sb.append(")");
            sb.append("\n");
            preorderTraverse(node.left, depth + 1, sb);
            preorderTraverse(node.right, depth + 1, sb);
        }
    }

    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preorderTraverse(root, 1, sb);
        return sb.toString();
    }

    
    public static void main(String[] args) {
        // Initialize a Tree
        Treap<Integer> testTree = new Treap<Integer>();

        // Adding elements
        testTree.add(4, 19);
        testTree.add(2, 31);
        testTree.add(6, 70);
        testTree.add(1, 84);
        testTree.add(3, 12);
        testTree.add(5, 83);
        testTree.add(7, 26);

        // Deleting Elements
        testTree.delete(1);

        // Finding Elements
        System.out.println(testTree.toString());
        System.out.println(testTree.find(3));
    }
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	