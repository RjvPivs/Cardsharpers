package Cardsharpers.FunctionalClasses;

import Cardsharpers.Players.Cardsharper;
import Cardsharpers.Players.HonestPlayer;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Functional class.
 */
public class Utils {
    /**
     * Fills the arrays.
     *
     * @param honestPlayers Array of honest players.
     * @param cardsharpers  Array of sharpers.
     */
    public static void createArrays(HonestPlayer[] honestPlayers, Cardsharper[] cardsharpers) {
        for (int i = 0; i < honestPlayers.length; i++) {
            honestPlayers[i] = new HonestPlayer(i);
            honestPlayers[i].start();
        }
        for (int i = 0; i < cardsharpers.length; i++) {
            cardsharpers[i] = new Cardsharper(honestPlayers, i);
            cardsharpers[i].start();
        }
    }

    /**
     * Interrupts all the threads.
     *
     * @param honestPlayers Array of honest players.
     * @param cardsharpers  Array of sharpers.
     * @throws InterruptedException In case of interruption.
     */
    public static void stop(HonestPlayer[] honestPlayers, Cardsharper[] cardsharpers) throws InterruptedException {
        for (int i = 0; i < honestPlayers.length; i++) {
            honestPlayers[i].interrupt();
            honestPlayers[i].join();
        }
        for (int i = 0; i < cardsharpers.length; i++) {
            cardsharpers[i].interrupt();
            cardsharpers[i].join();
        }
    }

    /**
     * Finds the maximum score.
     *
     * @param honestPlayers Array of honest players.
     * @param cardsharpers  Array of sharpers.
     * @param output        List of winning players.
     * @return The maximum.
     */
    public static int max(HonestPlayer[] honestPlayers, Cardsharper[] cardsharpers, ArrayList<String> output) {
        int max = 0;
        System.out.println("Here are the scores of our players:");
        for (int i = 0; i < honestPlayers.length; i++) {
            System.out.printf("%s with he score %d\n", honestPlayers[i].getTheName(), honestPlayers[i].getScore());
            if (honestPlayers[i].getScore() > max) {
                max = honestPlayers[i].getScore();
                output.clear();
                output.add(honestPlayers[i].getTheName());
            } else if (honestPlayers[i].getScore() == max) {
                output.add(honestPlayers[i].getTheName());
            }
        }
        for (int i = 0; i < cardsharpers.length; i++) {
            System.out.printf("%s with he score %d\n", cardsharpers[i].getTheName(), cardsharpers[i].getScore());
            if (cardsharpers[i].getScore() > max) {
                output.clear();
                max = cardsharpers[i].getScore();
                output.add(cardsharpers[i].getTheName());
            } else if (cardsharpers[i].getScore() == max) {
                output.add(cardsharpers[i].getTheName());
            }
        }
        return max;
    }

    /**
     * Displays info about winners.
     *
     * @param honestPlayers Array of honest players.
     * @param cardsharpers  Array of sharpers.
     */
    public static void maximum(HonestPlayer[] honestPlayers, Cardsharper[] cardsharpers) {
        ArrayList<String> output = new ArrayList<String>();
        int max = max(honestPlayers, cardsharpers, output);
        if (output.size() == 1) {
            System.out.println("Here`s our winner:");
        } else {
            System.out.println("Here`re our winners:");
        }
        for (String str : output) {
            System.out.printf("%s with the score %d\n", str, max);
        }
    }

    /**
     * Reads the number of players.
     *
     * @param sizes Array of sizes.
     * @throws Exception In case of incorrect input.
     */
    public static void read(Integer[] sizes) throws Exception {
        System.out.println("Welcome to our new game! Please enter two numbers: honest player`s number and sharpers.");
        System.out.println("Honest number is [1, 100], sharper is [0, 100]. Now, please, enter:");
        boolean checker = false;
        while (!checker) {
            try {
                Scanner scanner = new Scanner(System.in);
                sizes[0] = scanner.nextInt();
                sizes[1] = scanner.nextInt();
                if (sizes[0] < 1 || sizes[1] < 0 || sizes[0] > 100 || sizes[1] > 100) {
                    throw new Exception("");
                }
                checker = true;
            } catch (InputMismatchException e) {
                System.out.println("The input data was incorrect. Try again.");
            } catch (Exception e) {
                System.out.println("The input data was less than 1 or higher than 100. Try again.");
            }
        }
        System.out.println("Now we are ready to start! Now, please, wait for 10 seconds!");
    }

    /**
     * Checks if player wants to continue.
     *
     * @return Wants or no.
     */
    public static boolean goOn() {
        boolean check = false;
        System.out.println("If you want to continue, enter yes, in other case-no.");
        while (!check) {
            Scanner scanner = new Scanner(System.in);
            String string = scanner.nextLine();
            switch (string) {
                case "yes":
                    return false;
                case "no":
                    return true;
                default:
                    System.out.println("Incorrect input, please, try again.");
                    break;
            }
        }
        return false;
    }
}
