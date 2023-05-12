import java.util.ArrayList;
import java.util.List;
public class BST <K extends Comparable<K>, V > {
    private Node root;
    private class Node {
        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }
    class KeyValuePair {
        private K key;
        private V value;

        public KeyValuePair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
    public void put(K key, V val) {
        root = putNode(root, key, val);
    }

    private Node putNode(Node node, K key, V val) {
        if (node == null) {
            return new Node(key, val);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = putNode(node.left, key, val);
        } else if (cmp > 0) {
            node.right = putNode(node.right, key, val);
        } else {
            node.value = val;
        }

        return node;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            return node.value;
        }
        return null;
    }

    private Node getNode(Node node, K key) {
        if (node == null || key.equals(node.key)) {
            return node;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return getNode(node.left, key);
        } else {
            return getNode(node.right, key);
        }
    }

    public void delete(K key) {
        root = deleteNode(root, key);
    }

    private Node deleteNode(Node node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = deleteNode(node.left, key);
        } else if (cmp > 0) {
            node.right = deleteNode(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            Node successor = findMin(node.right);
            node.key = successor.key;
            node.value = successor.value;
            node.right = deleteMin(node.right);
        }

        return node;
    }
    private Node findMin(Node node) {
        if (node.left == null) {
            return node;
        }
        return findMin(node.left);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }
    public Iterable<KeyValuePair> iterator() {
        List<KeyValuePair> keyValues = new ArrayList<>();
        inOrderTraversal(root, keyValues);
        return keyValues;
    }

    private void inOrderTraversal(Node node, List<KeyValuePair> keyValues) {
        if (node != null) {
            inOrderTraversal(node.left, keyValues);
            keyValues.add(new KeyValuePair(node.key, node.value));
            inOrderTraversal(node.right, keyValues);
        }
    }
}
