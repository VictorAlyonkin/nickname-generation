import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Main {
    public static AtomicInteger threeLetters = new AtomicInteger(0);
    public static AtomicInteger fourLetters = new AtomicInteger(0);
    public static AtomicInteger fiveLetters = new AtomicInteger(0);

    public static void main(String[] args) {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
            countBeautifulText(texts[i]);
        }
        System.out.println("Красивых слов с длиной 3: " + threeLetters);
        System.out.println("Красивых слов с длиной 4: " + fourLetters);
        System.out.println("Красивых слов с длиной 5: " + fiveLetters);
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static void countBeautifulText(String text) {
        if (isPalindrome(text) || isSameLetters(text) || isIncreasing(text)) {
            switch (text.length()) {
                case 3:
                    threeLetters.getAndIncrement();
                    break;
                case 4:
                    fourLetters.getAndIncrement();
                    break;
                case 5:
                    fiveLetters.getAndIncrement();
                    break;
            }
        }
    }

    public static boolean isPalindrome(String text) {
        return IntStream.range(0, text.length() / 2)
                .noneMatch(i -> text.charAt(i) != text.charAt(text.length() - i - 1));
    }

    public static boolean isSameLetters(String text) {
        return IntStream.range(1, text.length())
                .noneMatch(i -> text.charAt(0) != text.charAt(i));
    }

    public static boolean isIncreasing(String text) {
        return IntStream.range(0, text.length() - 1)
                .noneMatch(i -> text.charAt(i) > text.charAt(i + 1));
    }
}