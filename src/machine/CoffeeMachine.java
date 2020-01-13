package machine;
import java.util.Arrays;
import java.util.Scanner;

public class CoffeeMachine {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Command command = null;
        while (command != Command.EXIT) {
            command = receiveUserCommand();
            executeCommand(command);
        }
    }

    private static void executeCommand(Command command) {
        switch (command) {
            case BUY:
                buyCoffee();
                break;
            case FILL:
                fillMachine();
                break;
            case TAKE:
                takeMoney();
                break;
            case REMAINING:
                displayStatus();
                break;
            default:
                break;
        }
    }

    private static void displayStatus() {
        Machine.getInstance().displayStatus();
    }

    private static void takeMoney() {
        int moneyGiven = Machine.getInstance().takeMoney();
        System.out.println("I gave you " + moneyGiven);
    }

    private static void fillMachine() {
        System.out.println("Write how many ml of water do you want to add:");
        int waterToFill = Integer.parseInt(scanner.nextLine());
        System.out.println("Write how many ml of milk do you want to add:");
        int milkToFill = Integer.parseInt(scanner.nextLine());
        System.out.println("Write how many grams of coffee beans do you want to add:");
        int coffeeToPut = Integer.parseInt(scanner.nextLine());
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        int cupsToAdd = Integer.parseInt(scanner.nextLine());

        Machine.getInstance().refillMachine(waterToFill, milkToFill, coffeeToPut, cupsToAdd);
    }

    private static void buyCoffee() {
        System.out.printf("What do you want to buy? 1 - %s, 2 - %s, 3 - %s\n",
                CoffeeType.ESPRESSO.toString().toLowerCase(),
                CoffeeType.LATTE.toString().toLowerCase(),
                CoffeeType.CAPPUCCINO.toString().toLowerCase());

        String input = scanner.nextLine();
        if ("back".equals(input)) {
            return;
        }
        int order = Integer.parseInt(input);
        CoffeeType coffeeType = CoffeeType.values()[order - 1];
        Machine.getInstance().pourCoffee(coffeeType);
    }

    private static Command receiveUserCommand() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String command = scanner.nextLine().toUpperCase();
        return Command.valueOf(command);
    }
}