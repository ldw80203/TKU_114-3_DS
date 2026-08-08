public class Q05_FinalScore {
    private static final double EXAM_WEIGHT = 0.40;
    private static final double ASSIGNMENT_WEIGHT = 0.60;
    private static final int MAX_SCORE = 100;
    private static final int MAX_BONUS = 10;

    public static void main(String[] args) {
        System.out.println(calculateFinalScore(80, 90, 5));
        System.out.println(calculateFinalScore(100, 100, 10));
        System.out.println(calculateFinalScore(-1, 80, 5));
        System.out.println(calculateFinalScore(70, 60, 11));
    }

    public static double calculateFinalScore(
            int examScore,
            int assignmentScore,
            int bonus
    ) {
        if (examScore < 0 || examScore > MAX_SCORE
                || assignmentScore < 0 || assignmentScore > MAX_SCORE
                || bonus < 0 || bonus > MAX_BONUS) {
            return -1.0;
        }

        double score = examScore * EXAM_WEIGHT
                + assignmentScore * ASSIGNMENT_WEIGHT
                + bonus;
        if (score > MAX_SCORE) {
            return MAX_SCORE;
        }
        return score;
    }
}
