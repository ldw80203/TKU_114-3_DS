public class StoreProduct {
    private String id;
    private String name;
    private int price;
    private int stock;

    public StoreProduct(String id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public StoreProduct(StoreProduct other) {
        this(other.id, other.name, other.price, other.stock);
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return id + " " + name + " price=" + price + " stock=" + stock;
    }
}
