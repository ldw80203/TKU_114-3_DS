public class Q06_BinarySearchRange {
    public static void main(String[] args) {
        int[] data = {5, 10, 10, 10, 18, 25, 25, 40};

        System.out.println("10 第一次：" + findFirst(data, 10));
        System.out.println("10 最後一次：" + findLast(data, 10));
        System.out.println("10 出現次數：" +
            countOccurrences(data, 10));
        System.out.println("99 第一次：" + findFirst(data, 99));
    }

    public static int findFirst(int[] data, int target) {
        int r = data.length -1;
        int l = 0;
        int ans = -1;
        while (r >= l){
            int m = (r + l) / 2;
            if(data[m] == target){
                ans = m;
                r = m - 1;
            }
            else if(data[m] > target){
                r = m - 1;
            }
            else if(data[m] < target){
                l = m + 1;
            }
        }
        return ans;
    }

    public static int findLast(int[] data, int target) {
        int r = data.length -1;
        int l = 0;
        int ans = -1;
        while (r >= l){
            int m = (r + l) / 2;
            if(data[m] == target){
                ans = m;
                l = m + 1;
            }
            else if(data[m] > target){
                r = m - 1;
            }
            else if(data[m] < target){
                l = m + 1;
            }
        }
        return ans;
    }

    public static int countOccurrences(int[] data, int target) {
        int first = findFirst(data, target);
        if(first == -1){
            return 0;
        }
        int last = findLast(data, target);
        return last - first +1;
        
    }

}
