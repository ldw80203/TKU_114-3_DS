import java.util.ArrayList;

class Q12_Product {
    private String id;
    private String name;
    private int price;
    private int stock;

    public Q12_Product(String id, String name, int price, int stock) {
        this.id = id == null ? "" : id.trim();
        this.name = name == null ? "" : name.trim();
        this.price = Math.max(price, 0);
        this.stock = Math.max(stock, 0);
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getStock() { return stock; }
    @Override public String toString() { return id + " " + name + " price=" + price + " stock=" + stock; }
}

public class Q12_InventoryCatalog {
    private ArrayList<Q12_Product> products = new ArrayList<>();

    public boolean addProduct(Q12_Product product) {
        if (product == null || product.getId().isEmpty()) return false;
        for (Q12_Product current : products) {
            if (current.getId().equalsIgnoreCase(product.getId())) return false;
        }
        products.add(product);
        return true;
    }

    public Q12_Product[] createSortedCopyById() {
        Q12_Product[] copy = new Q12_Product[products.size()];
        for (int index = 0; index < products.size(); index++) copy[index] = products.get(index);
        if (copy.length > 1) mergeSort(copy, new Q12_Product[copy.length], 0, copy.length - 1);
        return copy;
    }

    private void mergeSort(Q12_Product[] data, Q12_Product[] temp, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(data, temp, left, mid);
        mergeSort(data, temp, mid + 1, right);
        merge(data, temp, left, mid, right);
    }

    private void merge(Q12_Product[] data, Q12_Product[] temp, int left, int mid, int right) {
        int leftIndex = left, rightIndex = mid + 1, outputIndex = left;
        while (leftIndex <= mid && rightIndex <= right) {
            if (data[leftIndex].getId().compareToIgnoreCase(data[rightIndex].getId()) <= 0) {
                temp[outputIndex++] = data[leftIndex++];
            } else {
                temp[outputIndex++] = data[rightIndex++];
            }
        }
        while (leftIndex <= mid) temp[outputIndex++] = data[leftIndex++];
        while (rightIndex <= right) temp[outputIndex++] = data[rightIndex++];
        for (int index = left; index <= right; index++) data[index] = temp[index];
    }

    public Q12_Product binarySearchById(Q12_Product[] sortedProducts, String id) {
        if (sortedProducts == null || id == null || id.trim().isEmpty()) return null;
        String target = id.trim();
        int left = 0, right = sortedProducts.length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            int comparison = sortedProducts[middle].getId().compareToIgnoreCase(target);
            if (comparison == 0) return sortedProducts[middle];
            if (comparison < 0) left = middle + 1;
            else right = middle - 1;
        }
        return null;
    }

    public ArrayList<Q12_Product> findByNameKeyword(String keyword) {
        ArrayList<Q12_Product> matches = new ArrayList<>();
        if (keyword == null || keyword.trim().isEmpty()) return matches;
        String target = keyword.trim().toLowerCase();
        for (Q12_Product product : products) {
            if (product.getName().toLowerCase().contains(target)) matches.add(product);
        }
        return matches;
    }

    public ArrayList<Q12_Product> findLowStock(int maximumStock) {
        ArrayList<Q12_Product> matches = new ArrayList<>();
        for (Q12_Product product : products) {
            if (product.getStock() <= maximumStock) matches.add(product);
        }
        return matches;
    }

    public int totalInventoryValue() {
        int total = 0;
        for (Q12_Product product : products) total += product.getPrice() * product.getStock();
        return total;
    }
}

class Q12_InventoryDemo {
    public static void main(String[] args) {
        Q12_InventoryCatalog catalog = new Q12_InventoryCatalog();
        catalog.addProduct(new Q12_Product("P205", "Wireless Mouse", 650, 4));
        catalog.addProduct(new Q12_Product("P101", "Keyboard", 1200, 8));
        catalog.addProduct(new Q12_Product("P330", "Gaming Mouse", 1800, 2));
        catalog.addProduct(new Q12_Product("P150", "Monitor", 5200, 5));
        Q12_Product[] sorted = catalog.createSortedCopyById();
        System.out.println("依編號排序：");
        for (Q12_Product product : sorted) System.out.println(product);
        System.out.println("查詢 P150：" + catalog.binarySearchById(sorted, "p150"));
        System.out.println("名稱包含 mouse：" + catalog.findByNameKeyword("mouse"));
        System.out.println("低庫存：" + catalog.findLowStock(4));
        System.out.println("庫存總值：" + catalog.totalInventoryValue());
    }
}
