public class Q05_RecursiveArrayReport {
    public static void main(String[] args) {
        int[] data = {12, -3, 25, 8, 25, 40, 5};

        System.out.println("10～30 筆數：" +
            countInRange(data, 0, 10, 30));
        System.out.println("正數總和：" +
            sumPositive(data, 0));
        System.out.println("25 最後索引：" +
            findLast(data, 0, 25));
        System.out.println("99 最後索引：" +
            findLast(data, 0, 99));
    }

    public static int countInRange(
        int[] data,
        int index,
        int minimum,
        int maximum
    ) {
        if(index == data.length){
            return 0;
        }
        if(data[index] <= maximum && data[index] >= minimum){
            return countInRange(data, index + 1, minimum, maximum)+1;
        }
        else{
            return countInRange(data, index + 1, minimum, maximum);
        }
        
    }

    public static int sumPositive(int[] data, int index) {
        if(index == data.length){
             return 0;
        }
        if(data[index] > 0){
            return sumPositive(data, index + 1) + data[index];
        }
        else{
        return sumPositive(data, index + 1);}
    }

    public static int findLast(
        int[] data,
        int index,
        int target
    ) {
        
        if(index == data.length){
            return -1;
        }
        int later = findLast(data, index + 1, target);

        if(later != -1){
            return later; 
        }
        if(data[index] == target){
            return index;
        }
        return -1;
    }
}
 