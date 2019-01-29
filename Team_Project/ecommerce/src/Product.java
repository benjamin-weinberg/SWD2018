public class Product {
    /**
     * Product ID number. This variable keeps track of the product ID number which is the variable passed back to the server after a client purchases an item.
     * The string is final because the product is bound to the ID number and should not be modified.
     */
    private final String id;
    /**
     * Product Name. This string stores the name of the product. The string is declared final because the name of the product should not be modified.
     */
    private final String name;
    /**
     * Price. This string keeps track of the price of the product to show the user on the product display window. The string is declared final because the
     * price is fixed and should not be modified.
     */
    private final String price;
    /**
     * Description of Product. This String stores the description of the product to be used in the product display window. The string is declared final because
     * the description of the product should not be modified after the
     */
    private final String description;
    /**
     * Seller Name. This String stores the seller name to be used in the product display window.
     */
    private final String seller;
    private final String quantity;

    public Product(String id, String name, String price, String description, String seller, String quantity)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.seller = seller;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getSeller() {
        return seller;
    }

    public String getQuantity() {
        return quantity;
    }

    public String convertToAppend() //formats the product fro printing purposes
    {
        return  "ID: "+getId()+"\n"+
                "Product Name: "+getName()+"\n"+
                "Price: $"+getPrice()+"\n"+
                "Description: "+getDescription()+"\n"+
                "Seller: "+getSeller()+"\n" +
                "Quantity: " + getQuantity() + "\n";
    }
    public String convertToMessage(){ //formats the product as a csv to transfer
        return "product:" + id + "," + name + "," + price + "," + description + "," + seller+ "," + quantity +"\n";
    }
}
