import java.util.Scanner;

class CoffeeMachine {

    protected int water = 400;
    protected int milk = 540;
    protected int beans = 120;
    protected int cups = 9;
    protected int money = 550;

    protected void showState() {
        System.out.printf("""
                The coffee machine has:
                %d ml of water
                %d ml of milk
                %d g of coffee beans
                %d disposable cups
                $%d of money
                """, water, milk, beans, cups, money);
    }

    private final Scanner in = new Scanner(System.in);

    protected void writeAction() {
        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            String action = in.next();

            switch (action) {
                case "buy" -> selectCoffee();
                case "fill" -> fill();
                case "take" -> take();
                case "remaining" -> showState();
                case "exit" -> {
                    return;
                }
            }
        }
    }

    protected void selectCoffee() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String choice = in.next();
        switch (choice) {
            case "1" -> buy(Coffee.ESPRESSO);
            case "2" -> buy(Coffee.LATTE);
            case "3" -> buy(Coffee.CAPPUCCINO);
        }
    }

    protected void buy(Coffee coffee) {
        if (water < coffee.water) {
            System.out.println("Sorry, not enough water!");
        } else if (milk < coffee.milk) {
            System.out.println("Sorry, not enough milk!");
        } else if (beans < coffee.beans) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if (cups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            water -= coffee.water;
            milk -= coffee.milk;
            beans -= coffee.beans;
            cups--;
            money += coffee.money;
        }
    }

    protected void fill() {
        System.out.println("Write how many ml of water you want to add:");
        int water = in.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        int milk = in.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        int beans = in.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add:");
        int cups = in.nextInt();

        this.water += water;
        this.milk += milk;
        this.beans += beans;
        this.cups += cups;
    }

    protected void take() {
        System.out.println("I gave you $" + money);
        this.money = 0;
    }
}
