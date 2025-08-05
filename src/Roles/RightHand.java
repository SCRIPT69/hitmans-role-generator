package Roles;
import Base.Player;
import Base.Role;

import java.util.ArrayList;
import java.util.Random;

public class RightHand extends Role {
    public static int number = 0;
    protected RightHand() {
    }
    public static RightHand init(int playersNumber) {
        // Prevent creating another Hitman if the role limit has been reached
        if (RightHand.number == RightHand.getLimit(playersNumber)) {
            return null;
        }

        RightHand newRightHand = new RightHand();
        RightHand.number += 1;

        // This role does not kill its target
        newRightHand.isKilling = false;

        // This role should get its target after all others
        newRightHand.initTargetLast = true;

        // List with prohibited roles
        newRightHand.targetProhibitedRoles.add(Juggernaut.class);
        newRightHand.targetProhibitedRoles.add(ProKiller.class);
        newRightHand.targetProhibitedRoles.add(Zombie.class);

        newRightHand.setRoleName("🤝 Right Hand");
        return newRightHand;
    }

    @Override
    public boolean initTarget(Player[] players, int playerIndex) {
        Player target = players[playerIndex].getTarget();
        // If the player's target is not a killer, or the target targets the Right Hand, or the target's role is prohibited,
        // choose a new target
        if (!target.getRole().isKilling() || target.getTarget() == players[playerIndex] || this.isProhibitedRole(target.getRole())) {
            Player newTarget = this.chooseNewTarget(players, playerIndex);

            if (newTarget == null) {
                return false;
            }
            players[playerIndex].setTarget(newTarget);
            target = newTarget;
        }

        this.task = "Your mission: assist " + target.getName() + " in killing their target.\nThe boss is counting on you.";

        return true;
    }

    private Player chooseNewTarget(Player[] players, int playerIndex) {
        ArrayList<Player> targets = new ArrayList<>();
        for (int i = 0; i < players.length; i++) {
            if (i == playerIndex) {
                continue;
            }

            if (!this.isProhibitedRole(players[i].getRole())) {
                if (players[i].getTarget() != players[playerIndex] && players[i].getRole().isKilling())
                {
                    targets.add(players[i]);
                }
            }
        }
        if (targets.size() == 0) {
            return null;
        }
        Random random = new Random();
        return targets.get(random.nextInt(targets.size()));
    }

    public static int getLimit(int playersNum) {
        return 3;
    }

    public static void resetCounter() {
        RightHand.number = 0;
    }
}
