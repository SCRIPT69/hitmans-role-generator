package Roles;
import Base.Player;
import Base.Role;

import java.util.ArrayList;
import java.util.Random;

public class Bodyguard extends Role {
    public static int number = 0;
    protected Bodyguard() {
    }
    public static Bodyguard init(int playersNumber) {
        // Prevent creating another Hitman if the role limit has been reached
        if (Bodyguard.number == Bodyguard.getLimit(playersNumber)) {
            return null;
        }

        Bodyguard newBodyguard = new Bodyguard();
        Bodyguard.number += 1;

        // This role does not eliminate its target
        newBodyguard.isKilling = false;

        // This role should receive its target after all others
        newBodyguard.initTargetLast = true;

        // Add roles that cannot be assigned as targets
        //newBodyguard.targetProhibitedRoles.add();

        newBodyguard.setRoleName("🛡️ Bodyguard");
        return newBodyguard;
    }

    @Override
    public boolean initTarget(Player[] players, int playerIndex) {
        Player target = players[playerIndex].getTarget();
        // Check if the current target is dangerous or not allowed
        boolean targetIsDangerous =
                (target.getTarget() == players[playerIndex] && target.getRole().isKilling()) ||
                        this.isProhibitedRole(target.getRole());
        if (targetIsDangerous) {
            Player newTarget = this.chooseNewTarget(players, playerIndex);

            if (newTarget == null) {
                return false;
            }
            players[playerIndex].setTarget(newTarget);
            target = newTarget;
        }

        this.task = "Your mission: protect player " + target.getName() + ".\nIf they fall, you lose everything.";

        return true;
    }

    private Player chooseNewTarget(Player[] players, int playerIndex) {
        ArrayList<Player> targets = new ArrayList<>();
        for (int i = 0; i < players.length; i++) {
            if (i == playerIndex) {
                continue;
            }

            if (this.isValidTarget(players, playerIndex, players[i]))
            {
                targets.add(players[i]);
            }
        }
        if (targets.size() == 0) {
            return null;
        }
        Random random = new Random();
        return targets.get(random.nextInt(0, targets.size()));
    }

    private boolean isValidTarget(Player[] players, int selfIndex, Player candidate) {
        if (candidate == players[selfIndex]) return false;
        if (isProhibitedRole(candidate.getRole())) return false;
        if (candidate.getTarget() == players[selfIndex] && candidate.getRole().isKilling()) return false;
        return true;
    }

    public static int getLimit(int playersNum) {
        return 3;
    }

    public static void resetCounter() {
        Bodyguard.number = 0;
    }
}
