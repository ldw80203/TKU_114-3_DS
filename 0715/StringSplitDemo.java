public class StringSplitDemo {
    public static void main(String[] args) {
        String record = " Keyboard , 890 , 12 ";
        String[] parts = record.split(",", -1);

        if (parts.length != 3) {
            System.out.println("資料格式錯誤：應該有名稱、價格、庫存三個欄位");
            return;
        }

        String name = parts[0].trim();
        try {
            int price = Integer.parseInt(parts[1].trim());
            int stock = Integer.parseInt(parts[2].trim());

            System.out.println("名稱：" + name);
            System.out.println("價格：" + price);
            System.out.println("庫存：" + stock);
        } catch (NumberFormatException e) {
            System.out.println("資料格式錯誤：價格與庫存必須是整數");
        }
    }
}
