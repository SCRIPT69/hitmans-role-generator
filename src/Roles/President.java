package Roles;

import Base.GameUI;
import Base.Player;
import Base.Role;

import java.util.Scanner;

public class President extends Survivor {
    public static int number = 0;
    protected President() {
    }
    public static President init(int playersNumber) {
        // Prevent creating this role if the limit has been reached
        if (President.number == President.getLimit(playersNumber)) {
            return null;
        }

        President newPresident = new President();

        newPresident.isKilling = false;
        newPresident.initTargetLast = true;

        // Roles that the President should automatically be wary of
        newPresident.targetExceptionsRoles.add(Juggernaut.class);
        newPresident.targetExceptionsRoles.add(ProKiller.class);
        newPresident.targetExceptionsRoles.add(Zombie.class);

        President.number += 1;
        newPresident.setRoleName("🎩 President");
        return newPresident;
    }

    @Override
    public boolean initTarget(Player[] players, int playerIndex) {
        Player player = players[playerIndex];
        this.task = "Your mission: survive at all costs. If you die, chaos will follow.\n";
        System.out.printf("Player %s is the President! If he dies, all players who did not have him as a target will be penalized.\n", player.getName());
        GameUI.scanner.nextLine();
        return super.initTarget(players, playerIndex);
    }

    public static int getLimit(int playersNum) {
        return 1;
    }

    public static void resetCounter() {
        President.number = 0;
    }
}
