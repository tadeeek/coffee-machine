import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Machine coffeeMachine = new Machine();
        Scanner scanner = new Scanner(System.in);
        loop: while (true) {
            if (coffeeMachine.getCurrentState() == MachineState.CHOOSEaction) {
                System.out.println();
                System.out.println("=== Write action (buy, fill, take, remaining, exit) ===");
                System.out.print("> ");
                coffeeMachine.action(scanner.nextLine());
            } else if (coffeeMachine.getCurrentState() == MachineState.CHOOSEcoffee) {
                System.out.println();
                System.out.println(
                        "=== What do you want to buy? (1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu) ===");
                System.out.print("> ");
                coffeeMachine.action(scanner.nextLine());
            } else if (coffeeMachine.getCurrentState() == MachineState.CHOOSEexit) {
                break loop;
            }
        }
    }
}