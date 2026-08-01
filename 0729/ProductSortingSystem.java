public class ProductSortingSystem {
    public static void main(String[] args) {
        StoreProduct[] original = {
            new StoreProduct("P001", "Keyboard", 1290, 12),
            new StoreProduct("P002", "Mouse", 650, 30),
            new StoreProduct("P003", "Monitor", 5200, 7),
            new StoreProduct("P004", "Webcam", 1290, 5),
            new StoreProduct("P005", "USB Cable", 180, 80),
            new StoreProduct("P006", "SSD", 2500, 18),
            new StoreProduct("P007", "Notebook Stand", 880, 20),
            new StoreProduct("P008", "Speaker", 1680, 9),
            new StoreProduct("P009", "Microphone", 2200, 6),
            new StoreProduct("P010", "Mouse Pad", 250, 45)
        };

        runMode(original, "price", "ascending", 1);
        runMode(original, "price", "descending", 2);
        runMode(original, "stock", "descending", 3);
    }

    private static void runMode(
        StoreProduct[] original,
        String field,
        String direction,
        int mode
    ) {
        StoreProduct[] data = copyProducts(original);
        insertionSort(data, mode);
        System.out.println();
        System.out.println("Sort field: " + field + ", direction: " + direction);
        printProducts(data);
    }

    private static StoreProduct[] copyProducts(StoreProduct[] original) {
        StoreProduct[] copy = new StoreProduct[original.length];
        for (int index = 0; index < original.length; index++) {
            copy[index] = new StoreProduct(original[index]);
        }
        return copy;
    }

    public static void insertionSort(StoreProduct[] products, int mode) {
        for (int index = 1; index < products.length; index++) {
            StoreProduct key = products[index];
            int position = index - 1;
            while (position >= 0 && shouldComeAfter(products[position], key, mode)) {
                products[position + 1] = products[position];
                position--;
            }
            products[position + 1] = key;
        }
    }

    private static boolean shouldComeAfter(
        StoreProduct current,
        StoreProduct key,
        int mode
    ) {
        if (mode == 1) {
            return current.getPrice() > key.getPrice();
        }
        if (mode == 2) {
            return current.getPrice() < key.getPrice();
        }
        return current.getStock() < key.getStock();
    }

    private static void printProducts(StoreProduct[] products) {
        for (StoreProduct product : products) {
            System.out.println(product);
        }
    }
}
