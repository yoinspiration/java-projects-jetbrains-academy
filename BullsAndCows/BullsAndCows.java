import java.util.Random;
import java.util.Scanner;

class BullsAndCows {

    private char[] secretCode = null;
    private int length;
    private final int SECRET_CODE_LENGTH_LIMIT = 36;

    private Scanner in = new Scanner(System.in);

    protected void generateCode() {
        int numberOfSymbols = 0;

        System.out.println("Input the length of the secret code:");
        String lengthStr = in.nextLine();
        try {
            length = Integer.parseInt(lengthStr);
        } catch (NumberFormatException e) {
            System.out.printf("Error: \"%s\" isn't a valid number.\n", lengthStr);
            System.exit(1);
        }

        System.out.println("Input the number of possible symbols in the code:");
        String numberOfSymbolsStr = in.nextLine();
        try {
            numberOfSymbols = Integer.parseInt(numberOfSymbolsStr);
        } catch (NumberFormatException e) {
            System.out.printf("Error: \"%s\" isn't a valid number.\n", numberOfSymbolsStr);
            System.exit(2);
        }

        if (length > SECRET_CODE_LENGTH_LIMIT) {
            System.out.printf("Error: can't generate a secret number with a length of %d " +
                    "because there aren't enough unique digits.\n", length);
            System.exit(3);
        } else if (length > numberOfSymbols) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.",
                    length, numberOfSymbols);
            System.exit(4);
        } else if (numberOfSymbols > SECRET_CODE_LENGTH_LIMIT) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(5);
        } else {
            final char[] SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();

            System.out.print("The secret is prepared: ");
            for (int i = 0; i < length; i++) {
                System.out.print("*");
            }
            if (numberOfSymbols <= 10) {
                System.out.printf(" (0-%d).\n", numberOfSymbols);
            } else if (numberOfSymbols == 11) {
                System.out.println(" (0-9, a).\n");
            } else {
                System.out.printf(" (0-9, a-%c).\n", SYMBOLS[numberOfSymbols-1]);
            }

            Random random = new Random();
            char[] code = new char[length];
            code[0] = SYMBOLS[random.nextInt(numberOfSymbols)];

            char randomSymbol;
            for (int i = 1; i < length; i++) {
                randomSymbol = SYMBOLS[random.nextInt(numberOfSymbols)];
                for (int j = 0; j < i; j++) {
                    if (randomSymbol == code[j]) {
                        i--;
                        break;
                    }
                    code[i] = randomSymbol;
                }
            }

            secretCode = code;
        }
    }

    protected void startGame() {
        if (secretCode != null) {
            System.out.println("Okay, let's start a game!");

            char[] guess;
            int turn = 0;
            do {
                turn++;
                System.out.printf("Turn %d:\n", turn);
                guess = in.next().toCharArray();
            } while (evaluateGuess(guess) != length);
            System.out.println("Congratulations! You guessed the secret code.");
        }
    }

    // 返回bullCount的值
    protected int evaluateGuess(char[] guess) {
        int bullCount = 0;
        int cowCount = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (secretCode[i] == guess[j]) {
                    if (i == j) {
                        bullCount++;
                    } else {
                        cowCount++;
                    }
                }
            }
        }

        if (bullCount != 0 && cowCount != 0) {
            System.out.printf("Grade: %d %s and %d %s.\n", bullCount, singularOrPlural(bullCount, "bull"),
                    cowCount, singularOrPlural(cowCount, "cow"));
        } else if (bullCount != 0) {
            System.out.printf("Grade: %d %s.\n", bullCount, singularOrPlural(bullCount, "bull"));
        } else if (cowCount != 0) {
            System.out.printf("Grade: %d %s.\n", cowCount, singularOrPlural(cowCount, "cow"));
        } else {
            System.out.println("Grade: None.");
        }

        return bullCount;
    }

    private String singularOrPlural(int count, String word) {
        if (count == 1) {
            return word;
        } else {
            return word + "s";
        }
    }
}
