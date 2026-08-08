public class Q01_ParkingFeeFix {
    public static void main(String[] args) {
        int[] testMinutes = {-20, 30, 31, 60, 61, 120, 121, 420};

        for (int minutes : testMinutes) {
            int fee = calculateFee(minutes);
            System.out.println("停車分鐘：" + minutes + " 停車費：" + fee);
        }
    }

    public static int calculateFee(int minutes) {
        if (minutes < 0) {
            return -1;
        }

        if (minutes <= 30) {
            return 0;
        }

        if (minutes <= 120) {
            return ((minutes - 1) / 30) * 20;
        }

        int fee = 60 + ((minutes - 120 + 59) / 60) * 30;
        if (fee > 180) {
            return 180;
        }
        return fee;
    }
}
