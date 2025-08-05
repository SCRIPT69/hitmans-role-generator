package Roles;

import Base.Player;
import Base.Role;

public class ProKiller extends Role {
    public static int number = 0;
    protected ProKiller() {
    }
    public static ProKiller init(int playersNumber) {
        // Prevent creating this role if the limit has been reached
        if (ProKiller.number == ProKiller.getLimit(playersNumber)) {
            return null;
        }

        ProKiller newProKiller = new ProKiller();
        ProKiller.number += 1;
        newProKiller.setRoleName("🗡️ Pro Killer");
        newProKiller.task = "Your mission: kill any player you choose.\nThe decision of who dies is all yours.";
        return newProKiller;
    }

    @Override
    public boolean initTarget(Player[] players, int playerIndex) {
        return true;
    }

    public static int getLimit(int playersNum) {
        return playersNum;
    }

    public static void resetCounter() {
        ProKiller.number = 0;
    }
}
