public class ContestRankingSystem {
    public static void main(String[] args) {
        Contestant[] contestants = {
            new Contestant("C001", "Amy", 95, 320),
            new Contestant("C002", "Ben", 88, 280),
            new Contestant("C003", "Cara", 95, 310),
            new Contestant("C004", "David", 72, 400),
            new Contestant("C005", "Eva", 88, 260)
        };

        insertionSort(contestants);
        printRanking(contestants);
    }

    public static void insertionSort(Contestant[] contestants) {
        for (int index = 1; index < contestants.length; index++) {
            Contestant key = contestants[index];
            int position = index - 1;

            while (position >= 0 && shouldComeAfter(contestants[position], key)) {
                contestants[position + 1] = contestants[position];
                position--;
            }
            contestants[position + 1] = key;
        }
    }

    private static boolean shouldComeAfter(Contestant current, Contestant key) {
        if (current.getScore() != key.getScore()) {
            return current.getScore() < key.getScore();
        }
        return current.getFinishSeconds() > key.getFinishSeconds();
    }

    private static void printRanking(Contestant[] contestants) {
        System.out.println("Rank Contestant");
        for (int index = 0; index < contestants.length; index++) {
            System.out.printf("%4d %s%n", index + 1, contestants[index]);
        }
    }
}
