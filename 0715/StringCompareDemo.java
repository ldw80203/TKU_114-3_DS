public class StringCompareDemo {
    public static void main(String[] args) {
        String first = new String("Java");
        String second = "Java";

        System.out.println("== 比較參考結果：" + (first == second));
        System.out.println("equals() 內容比較：" + first.equals(second));
        System.out.println("忽略大小寫比較：" + first.equalsIgnoreCase("java"));
    }
}
