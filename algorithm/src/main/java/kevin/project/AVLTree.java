package kevin.project;


class Node {
    int key, height;
    Node left, right;

    Node(int key) {
        this.key = key;
        height = 1;
    }
}

class AVLTree {
    private Node root;

    // Define a Node class that holds the data and pointers to left and right children


    // A utility function to get the height of a node
    int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // A utility function to perform right rotation on a node
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // A utility function to perform left rotation on a node
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // A utility function to get the balance factor of a node
    int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    // A recursive function to insert a key in the AVL tree
    Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }

        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }

        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }

        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // A wrapper function to insert a key in the AVL tree
    void insert(int key) {
        root = insert(root, key);
    }

    // A utility function to traverse the AVL tree in in-order
    void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }

    // A wrapper function to traverse the AVL tree in in-order
    void inOrder() {
        inOrder(root);
    }

    void showRoot() {
        System.out.println(root.key);
    }

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        avlTree.insert(3);
        avlTree.insert(5);
        avlTree.insert(7);
        avlTree.showRoot();

    }
}


