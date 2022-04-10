import java.util.Scanner;

class SimpleChatBot {

    private String botName = "Lucy";
    private String birthYear = "2015";

    protected void introduceMyself() {
        System.out.printf("""
                Hello! My name is %s.
                I was created in %s.
                """, botName, birthYear);
    }

    private Scanner in = new Scanner(System.in);

    protected void askName() {
        System.out.println("Please, remind me your name.");
        System.out.printf("What a great name you have, %s!\n", in.nextLine());
    }

    protected void guessAge() {

        System.out.print("""
                Let me guess your age.
                Enter remainders of dividing your age by 3, 5 and 7.
                """);

        int remainder3 = in.nextInt();
        int remainder5 = in.nextInt();
        int remainder7 = in.nextInt();

        int age = (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105;
        System.out.printf("Your age is %d; that's a good time to start programming!\n", age);
    }

    protected void countNumber() {
        System.out.println("Now I will prove to you that I can count to any number you want.");
        int number = in.nextInt();

        for (int i = 0; i <= number; ++i) {
            System.out.println(i + "!");
        }
    }

    protected void testKnowledge() {

        System.out.print("""
                Let's test your programming knowledge.
                Why do we use methods?
                1. To repeat a statement multiple times.
                2. To decompose a program into several small subroutines.
                3. To determine the execution time of a program.
                4. To interrupt the execution of a program.
                """);

        int answer = in.nextInt();
        while (answer != 2) {
            System.out.println("Please, try again.");
            answer = in.nextInt();
        }

        System.out.println("Congratulations, have a nice day!");
    }
}
