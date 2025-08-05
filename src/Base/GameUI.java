package Base;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameUI {
    public static final Scanner scanner = new Scanner(System.in);

    public int getPlayersNumber() {
        System.out.print("Enter the number of players: ");
        int playersNumber = 0;
        while (playersNumber <= 0) {
            try {
                playersNumber = scanner.nextInt();
                if (playersNumber <= 0) {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid integer.");
                scanner.nextLine(); // очистка буфера ввода
            }
        }
        scanner.nextLine();
        return playersNumber;
    }

    public String[] getPlayersNames(int playersNumber) {
        System.out.println("Please enter the names one by one:");
        String[] names = new String[playersNumber];
        for (int i = 0; i < playersNumber; i++) {
            System.out.print("Player "+(i+1)+": ");
            names[i] = scanner.nextLine().trim();
            if (names[i].isEmpty()) {
                System.out.println("Name cannot be empty. Please try again.");
                i--;
            }
        }
        return names;
    }

    public void printPlayersOneByOne(Player[] players) {
        for (Player player : players) {
            this.printSpaces();
            System.out.println("Player " + player.getName()+". Press Enter to see your role");
            scanner.nextLine();
            this.printSpaces();

            this.showPlayerRole(player);
            scanner.nextLine();
        }
        this.printSpaces();
        System.out.println("Press Enter to finish...");
        scanner.nextLine();
    }

    public void showPlayerRole(Player player) {
        Role role = player.getRole();
        System.out.printf("Player %s - %s\n", player.getName(), role.getRoleName());
        System.out.println(role.getTask() + "\n");
        role.doAdditionalAction(player);
    }

    private void printSpaces() {
        System.out.println("\n".repeat(30));
    }
}
