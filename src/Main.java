public class Main {
    public static <KeyValuePair> void main(String[] args) {
        BST<String, Integer> bst = new BST<>();
// Assume the binary search tree is populated with key-value pairs
        bst.put("A", 20);
        bst.put("B", 35);
        bst.put("C", 15);
        for (BST<String, Integer>.KeyValuePair kvp : bst.iterator()) {
            String key = kvp.getKey();
            Integer value = kvp.getValue();
            // Perform operations using the key-value pair
            System.out.println("Key: " + key + ", Value: " + value);
        }

    }
}