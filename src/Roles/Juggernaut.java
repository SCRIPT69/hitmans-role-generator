package Roles;

import Base.Player;
import Base.Role;

public class Juggernaut extends Role {
    public static int number = 0;
    protected Juggernaut() {
    }
    public static Juggernaut init(int playersNumber) {
        // Prevent creating this role if the limit has been reached
        if (Juggernaut.number == Juggernaut.getLimit(playersNumber)) {
            return null;
        }

        Juggernaut newJuggernaut = new Juggernaut();
        Juggernaut.number += 1;
        newJuggernaut.setRoleName("💀 Juggernaut");
        newJuggernaut.task = "Your mission: KILL THEM ALL.\nNo allies. No mercy. Only DEATH.";
        return newJuggernaut;
    }

    @Override
    public boolean initTarget(Player[] players, int playerIndex) {
        return true;
    }

    public static int getLimit(int playersNum) {
        return playersNum;
    }

    public static void resetCounter() {
        Juggernaut.number = 0;
    }
}
