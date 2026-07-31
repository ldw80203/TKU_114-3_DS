public class AllOccurrenceSearch {
    public static void main(String[] args) {
        int[] values = {105, 203, 118, 450, 326, 118, 203, 118};

        searchAndPrint(values, 118);
        searchAndPrint(values, 105);
        searchAndPrint(values, 999);
    }

    public static void searchAndPrint(int[] values, int target) {
        int count = 0;
        int checks = 0;
        StringBuilder indexes = new StringBuilder();

        for (int index = 0; index < values.length; index++) {
            checks++;
            if (values[index] == target) {
                if (count > 0) {
                    indexes.append(", ");
                }
                indexes.append(index);
                count++;
            }
        }

        if (count == 0) {
            System.out.println(target + "：找不到");
        } else {
            System.out.println(target + " 的索引：" + indexes);
            System.out.println("出現次數：" + count);
        }
        System.out.println("比較次數：" + checks);
    }
}
