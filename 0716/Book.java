public class Book {
    private String title;
    private int price;

    public Book(String title, int price) {
        this.title = title == null || title.trim().isEmpty()
                ? "未命名書籍" : title.trim();
        this.price = Math.max(price, 0);
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return title + "，價格：" + price;
    }
}
