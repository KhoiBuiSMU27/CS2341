public class Product implements Comparable<Product>{

    private final String ID;
    private final String name;
    private final String category;
    private final String price;

    public Product(String ID, String name, String category, String price) {
        this.ID = ID;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getID() { return ID; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getPrice() { return price; }

    public int compareTo(Product product) {
        return ID.compareTo(product.getID());
    }

    @Override
    public String toString() {
        return  "-----------------------------------------------\n" +
                "ID: " + ID + "\n" +
                "Name: " + name + "\n" +
                "Category: " + category + "\n" +
                "Price: " + price + "\n";
    }
}
