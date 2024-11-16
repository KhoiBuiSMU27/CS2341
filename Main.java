import java.util.ArrayList;

public class Main {

    public static ArrayList<String> parseCSV(String line)
    {
        ArrayList<String> result = new ArrayList<>();
        StringBuilder currentField = new StringBuilder();
        boolean inQuotes = false;

        for (char c : line.toCharArray()) {
            if (c == '\"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                result.add(currentField.toString());
                currentField.setLength(0);
            } else {
                currentField.append(c);
            }
        }
        result.add(currentField.toString());

        return result;
    }

    public static void main (String[] args)
    {
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<String> productIDs = new ArrayList<>();

        StdIn.readLine();
        while (!StdIn.isEmpty())
        {
            String line = StdIn.readLine();
            ArrayList<String> data = parseCSV(line);

            // Get IDs for the Key
            String ID = data.get(0);
            productIDs.add(ID);

            // Other data for the Value
            String name = data.get(1);
            if (name.charAt(name.length() - 1) == '\"') { name = name.substring(0, name.length() - 1); }
            if (name.charAt(0) == '\"') { name = name.substring(1, name.length() - 1); }

            String category = data.get(2);
            String price = data.get(3);

            // Create a Product object and insert it into an ArrayList
            Product product = new Product(ID, name, category, price);
            products.add(product);
        }

        // Insert the products into the Red-Black tree
        RedBlackBST<String, Product> bst = new RedBlackBST<>();
        for (int i = 0; i < products.size(); i++)
            bst.put(productIDs.get(i), products.get(i));

        // Search a product using its ID and print out its information
        StdOut.print(bst.get("4c69b61db1fc16e7013b43fc926e502d"));
        StdOut.print(bst.get("18018b6bc416dab347b1b7db79994afa"));
        StdOut.print(bst.get("f8c32a45e507a177992973cf0d46d20c"));

        // Test insertion
        Product product1 = new Product("abcdefghijklmnopqrstuvwxyz123456", "random product", "test", "$100");
        bst.put(product1.getID(), product1); // insert a new product
        StdOut.print(bst.get(product1.getID()));

        StdOut.println("-----------------------------------------------");
        bst.put(productIDs.get(1), products.get(1)); // already exists
    }

}