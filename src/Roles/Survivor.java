package Roles;
import Base.Player;
import Base.Role;

import java.util.ArrayList;
import java.util.List;

public class Survivor extends Role {
    public static int number = 0;
    protected Survivor() {
    }
    public static Survivor init(int playersNumber) {
        // Prevent creating another Hitman if the role limit has been reached
        if (Survivor.number == Survivor.getLimit(playersNumber)) {
            return null;
        }

        Survivor newSurvivor = new Survivor();
        Survivor.number += 1;

        // This role does not kill targets
        newSurvivor.isKilling = false;

        // This role must receive its target after everyone else
        newSurvivor.initTargetLast = true;

        // Roles that must always be considered as threats
        //newSurvivor.targetExceptionsRoles.add();

        newSurvivor.setRoleName("🏃🏻‍️ Survivor");
        newSurvivor.task = "Your mission: survive at any cost\n";
        return newSurvivor;
    }

    @Override
    public boolean initTarget(Player[] players, int playerIndex) {
        String targets = findTargets(players, playerIndex);
        this.task += targets.isEmpty() ? "Nobody is hunting you" : "You are a target of " + targets;
        return true;
    }

    private String findTargets(Player[] players, int playerIndex) {
        List<String> hunters = new ArrayList<>();

        for (Player p : players) {
            if ((p.getTarget() == players[playerIndex] && p.getRole().isKilling()) || this.isExceptionsRole(p.getRole())) {
                hunters.add(p.getName());
            }
        }

        return String.join(", ", hunters);
    }

    public static int getLimit(int playersNum) {
        return playersNum;
    }

    public static void resetCounter() {
        Survivor.number = 0;
    }
}
