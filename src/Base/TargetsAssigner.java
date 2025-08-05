package Base;

import Roles.Civilian;
import Roles.Survivor;

import java.util.Random;

public class TargetsAssigner {
    private final Random random = new Random();

    public void assignTargets(Player[] players) {
        int playersNumber = players.length;
        if (!this.rolesAssigned(players)) {
            System.out.println("Targets cannot be generated because roles are not assigned");
            return;
        }

        int targetIndex;
        Player[] delayedTargetPlayers = new Player[playersNumber];
        for (int i = 0; i < playersNumber; i++) {
            if (!players[i].getRole().isInitTargetLast())
            {
                targetIndex = generateTargetIndex(i, playersNumber);
                players[i].setTarget(players[targetIndex]);

                // If unable to assign a target for the current role
                if (!players[i].getRole().initTarget(players, i)) {
                    // Change role to a default (civilian)
                    changeRoleToDefault(i, players);
                }
            }
            else {
                delayedTargetPlayers[i] = players[i];
            }
        }

        // Initializing targets for roles with initTargetLast = true
        for (int i = 0; i < delayedTargetPlayers.length; i++) {
            if (delayedTargetPlayers[i] == null) {
                continue;
            }

            targetIndex = generateTargetIndex(i, playersNumber);
            delayedTargetPlayers[i].setTarget(players[targetIndex]);

            // If unable to assign a target for the current role
            if (!delayedTargetPlayers[i].getRole().initTarget(players, i)) {
                // Change role to a default (civilian)
                changeRoleToDefault(i, players);
            }
        }
    }

    private boolean rolesAssigned(Player[] players) {
        for (Player p : players) {
            if (p.getRole() == null) return false;
        }
        return true;
    }

    private int generateTargetIndex(int playerIndex, int playersNumber) {
        int targetIndex = this.random.nextInt(playersNumber);
        while (targetIndex == playerIndex && playersNumber>1) {
            targetIndex = random.nextInt(playersNumber);
        }
        return targetIndex;
    }

    private void changeRoleToDefault(int playerIndex, Player[] players) {
        players[playerIndex].setRole(Civilian.init(players.length));
        players[playerIndex].getRole().initTarget(players, playerIndex);
    }
}
