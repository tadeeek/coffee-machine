import java.util.InputMismatchException;
import java.util.Scanner;

public class Machine {
    // Initial coffee machiine state
    private int water = 200;
    private int milk = 200;
    private int coffeeBeans = 10;
    private int disposableCups = 5;
    private int money = 100;
    private MachineState currentState = MachineState.CHOOSEaction;

    public MachineState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(MachineState currentState) {
        this.currentState = currentState;
    }

    public void action(String input) {
        if (currentState == MachineState.CHOOSEaction) {
            switch (input) {
                case "buy":
                    currentState = MachineState.CHOOSEcoffee;
                    break;
                case "fill":
                    fill();
                    break;
                case "take":
                    take();
                    break;
                case "remaining":
                    status();
                    break;
                case "exit":
                    currentState = MachineState.CHOOSEexit;
                    break;
                default:
                    System.out.println("Invalid action, try again");
                    break;
            }
        } else if (currentState == MachineState.CHOOSEcoffee) {
            buy(input);
            currentState = MachineState.CHOOSEaction;
        }
    }

    public void buy(String input) {
        // Amount of products needed for espresso
        int espressoWater = 250;
        int espressoCoffeeBeans = 16;
        int espressoMoney = 4;
        // Amount of products needed for latte
        int latteWater = 350;
        int latteMilk = 75;
        int latteCoffeeBeans = 20;
        int latteMoney = 7;
        // Amount of products needed for cappuccino
        int cappuccinoWater = 200;
        int cappuccinoMilk = 100;
        int cappuccinoCoffeeBeans = 12;
        int cappuccinoMoney = 6;

        switch (input) {
            case "1":
                if (water >= espressoWater && coffeeBeans >= espressoCoffeeBeans && disposableCups >= 1) {
                    water -= espressoWater;
                    coffeeBeans -= espressoCoffeeBeans;
                    money += espressoMoney;
                    disposableCups -= 1;
                    System.out.println("I have enough resources, making you a coffee!");
                } else if (water < espressoWater)
                    System.out.println("Sorry, not enough water!");
                else if (coffeeBeans < espressoCoffeeBeans)
                    System.out.println("Sorry, not enough coffee beans!");
                else if (disposableCups == 0)
                    System.out.println("Sorry, not enough disposable cups");
                break;

            case "2":
                if (water >= latteWater && milk >= latteMilk && coffeeBeans >= latteCoffeeBeans
                        && disposableCups >= 1) {
                    water -= latteWater;
                    milk -= latteMilk;
                    coffeeBeans -= latteCoffeeBeans;
                    money += latteMoney;
                    disposableCups -= 1;
                    System.out.println("I have enough resources, making you a coffee!");
                } else if (water < latteWater)
                    System.out.println("Sorry, not enough water!");
                else if (milk < latteMilk)
                    System.out.println("Sorry, not enough milk!");
                else if (coffeeBeans < latteCoffeeBeans)
                    System.out.println("Sorry, not enough coffee beans!");
                else if (disposableCups == 0)
                    System.out.println("Sorry, not enough disposable cups");
                break;
            case "3":
                if (water >= cappuccinoWater && milk >= cappuccinoMilk && coffeeBeans >= cappuccinoCoffeeBeans
                        && disposableCups >= 1) {
                    water -= cappuccinoWater;
                    milk -= cappuccinoMilk;
                    coffeeBeans -= cappuccinoCoffeeBeans;
                    money += cappuccinoMoney;
                    disposableCups -= 1;
                    System.out.println("I have enough resources, making you a coffee!");
                } else if (water < cappuccinoWater)
                    System.out.println("Sorry, not enough water!");
                else if (milk < cappuccinoMilk)
                    System.out.println("Sorry, not enough milk!");
                else if (coffeeBeans < cappuccinoCoffeeBeans)
                    System.out.println("Sorry, not enough coffee beans!");
                else if (disposableCups == 0)
                    System.out.println("Sorry, not enough disposable cups");
                break;
            case "back":
                currentState = MachineState.CHOOSEaction;
                break;
            default:
                System.out.println("Invalid action, switching to main menu");
                break;

        }
    }

    public void fill() {
        System.out.print("\n");
        int input = 0;
        input = fillInputValidation("Write how many ml of water do you want to add:");
        water += input;
        input = fillInputValidation("Write how many ml of milk do you want to add:");
        milk += input;
        input = fillInputValidation("Write how many grams of coffee beans do you want to add: ");
        coffeeBeans += input;
        input = fillInputValidation("Write how many disposable cups of coffee beans do you want to add: ");
        disposableCups += input;
    }

    public int fillInputValidation(String message) {
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        boolean correctInput = true;

        theWhile: while (correctInput) {
            System.out.print(message);
            System.out.print("\n> ");
            try {
                input = scanner.nextInt();
                correctInput = !correctInput;
            } catch (InputMismatchException e) {
                System.out.println("Input should be a number. Please try again \n");
                input = 0;
                scanner.nextLine(); // Workaround to consume \n produced by hitting enter if user typed string
                continue theWhile;
            }
        }
        return input;
    }

    public void take() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    public void status() {
        System.out.println("\n= The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(coffeeBeans + " of coffee beans");
        System.out.println(disposableCups + " of disposable cups");
        System.out.println("$" + money + " of money");

    }

}
