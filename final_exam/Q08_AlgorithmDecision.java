
import com.sun.source.tree.Tree;

public class Q08_AlgorithmDecision {
    public static void main(String[] args) {
        int[] data = new int[64];
        for (int index = 0; index < data.length; index++) {
            data[index] = (index + 1) * 3;
        }

        System.out.println("已排序：" +
            isSortedAscending(data));
        System.out.println("循序比較次數：" +
            sequentialChecks(data, 192));
        System.out.println("二分比較次數：" +
            binaryChecks(data, 192));
        System.out.println("建議：" +
            chooseSearch(true, data.length, 5));
    }

    public static boolean isSortedAscending(int[] data) {
        if(data.length == 0){
        return true;}
        
    }

    public static int sequentialChecks(int[] data, int target) {
        return 0;
    }

    public static int binaryChecks(int[] data, int target) {
        return 0;
    }

    public static String chooseSearch(
        boolean sorted,
        int dataSize,
        int expectedSearches
    ) {
        return "";
    }
}
