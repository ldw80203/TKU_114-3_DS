public class TemperatureLevel {
    public static void main(String[] args) {
        double temperature = 26.5;

        if (temperature < 15) {
            System.out.println("Cold");
        } else if (temperature < 28) {
            System.out.println("Comfortable");
        } else {
            System.out.println("Hot");
        }
    }
}
