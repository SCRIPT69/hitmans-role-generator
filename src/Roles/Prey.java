package Roles;

import Base.GameUI;
import Base.Player;
import Base.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Prey extends Role {
    public static int number = 0;
    protected Prey() {
    }
    public static Prey init(int playersNumber) {
        // Prevent creating this role if the limit has been reached
        if (Prey.number == Prey.getLimit(playersNumber)) {
            return null;
        }

        Prey newPrey = new Prey();

        newPrey.isKilling = false;
        newPrey.initTargetLast = true;

        Prey.number += 1;
        newPrey.setRoleName("🐗 Prey");

        return newPrey;
    }

    @Override
    public boolean initTarget(Player[] players, int playerIndex) {
        Player player = players[playerIndex];
        if (this.task == null) {
            System.out.printf("Player %s is the Prey! Anyone who kills them gets a reward.\n", player.getName());
            GameUI.scanner.nextLine();
        }
        this.task = "Your mission: survive.\nYou're the prey, and the hunt is on!";
        String protectors = this.findProtectors(players, playerIndex);
        if (!protectors.equals("")) {
            this.task += "\nThese players must protect you: " + protectors;
        }
        return true;
    }

    private String findProtectors(Player[] players, int playerIndex) {
        List<String> protectors = new ArrayList<>();
        for (Player player : players) {
            if (player.getRole().getClass() == Bodyguard.class && player.getTarget() == players[playerIndex]) {
                protectors.add(player.getName());
            }
        }
        return String.join(", ", protectors);
    }

    public static int getLimit(int playersNum) {
        return 1;
    }

    public static void resetCounter() {
        Prey.number = 0;
    }
}
