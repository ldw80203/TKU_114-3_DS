public class CartItem {
    private String code;
    private String name;
    private double unitPrice;
    private int quantity;

    public CartItem(String code, String name, double unitPrice, int quantity) {
        this.code = code;
        this.name = name;
        this.unitPrice = Math.max(unitPrice, 0);
        this.quantity = Math.max(quantity, 1);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int amount) {
        if (amount > 0) {
            quantity += amount;
        }
    }

    public boolean setQuantity(int quantity) {
        if (quantity <= 0) {
            return false;
        }
        this.quantity = quantity;
        return true;
    }

    public double getSubtotal() {
        return unitPrice * quantity;
    }

    @Override
    public String toString() {
        return code + " | " + name + " | price=" + unitPrice
            + " | quantity=" + quantity + " | subtotal=" + getSubtotal();
    }
}
