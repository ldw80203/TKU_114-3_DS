import java.util.Locale;
import java.util.Scanner;

public class TextAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = readNonBlankLine(sc, "請輸入一行文字：");
        String trimmed = text.trim();
        String[] words = splitWords(trimmed);

        System.out.println("原始字元數：" + text.length());
        System.out.println("有效字元數：" + trimmed.length());
        System.out.println("單字數量：" + words.length);
        System.out.println("母音總數：" + countVowels(trimmed));
        System.out.println("最長單字：" + findLongestWord(words));

        System.out.print("請輸入要搜尋的關鍵字：");
        String keyword = sc.nextLine();
        System.out.println("關鍵字出現次數：" + countKeyword(trimmed, keyword));

        sc.close();
    }

    public static String readNonBlankLine(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!sc.hasNextLine()) {
                throw new IllegalStateException("沒有讀到文字輸入");
            }

            String line = sc.nextLine();
            if (!line.trim().isEmpty()) {
                return line;
            }
            System.out.println("內容不能是空白，請重新輸入。");
        }
    }

    public static String[] splitWords(String text) {
        if (text == null || text.trim().isEmpty()) {
            return new String[0];
        }
        return text.trim().split("\\s+");
    }

    public static int countVowels(String text) {
        int count = 0;
        String normalized = text.toLowerCase(Locale.ROOT);
        for (int i = 0; i < normalized.length(); i++) {
            if ("aeiou".indexOf(normalized.charAt(i)) >= 0) {
                count++;
            }
        }
        return count;
    }

    public static String findLongestWord(String[] words) {
        if (words.length == 0) {
            return "（沒有單字）";
        }

        String longest = words[0];
        for (String word : words) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        return longest;
    }

    public static int countKeyword(String text, String keyword) {
        String normalizedText = text.toLowerCase(Locale.ROOT);
        String normalizedKeyword = keyword.trim().toLowerCase(Locale.ROOT);
        if (normalizedKeyword.isEmpty()) {
            return 0;
        }

        int count = 0;
        int start = 0;
        while (true) {
            int index = normalizedText.indexOf(normalizedKeyword, start);
            if (index == -1) {
                return count;
            }
            count++;
            start = index + normalizedKeyword.length();
        }
    }
}
