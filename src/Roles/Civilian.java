package Roles;

import Base.Player;
import Base.Role;

public class Civilian extends Role {
    public static int number = 0;
    protected Civilian() {
    }
    public static Civilian init(int playersNumber) {
        // Prevent creating this role if the limit has been reached
        if (Civilian.number == Civilian.getLimit(playersNumber)) {
            return null;
        }

        Civilian newCivilian = new Civilian();

        newCivilian.isKilling = false;

        Civilian.number += 1;
        newCivilian.setRoleName("💩 Civilian");
        return newCivilian;
    }

    @Override
    public boolean initTarget(Player[] players, int playerIndex) {
        this.task = "Your mission: just survive.\nSo many roles, and you got Civilian LOL.";
        return true;
    }

    public static int getLimit(int playersNum) {
        return playersNum;
    }

    public static void resetCounter() {
        Civilian.number = 0;
    }
}
