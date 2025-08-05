package Roles;

import Base.GameUI;
import Base.Player;
import Base.Role;

import java.util.Scanner;

public class Zombie extends Role {
    public static int number = 0;
    protected Zombie() {
    }
    public static Zombie init(int playersNumber) {
        // Prevent creating another Hitman if the role limit has been reached
        if (Zombie.number == Zombie.getLimit(playersNumber)) {
            return null;
        }

        Zombie newZombie = new Zombie();
        Zombie.number += 1;
        newZombie.setRoleName("🧟 Zombie");
        newZombie.task = "Your mission: kill and infect everyone.\nGrow your army of the undead.";
        return newZombie;
    }

    // Зомби не получает цель, он должен убить и заразить всех
    @Override
    public boolean initTarget(Player[] players, int playerIndex) {
        System.out.printf("Player %s is a Zombie!\n", players[playerIndex].getName());
        GameUI.scanner.nextLine();
        return true;
    }

    public static int getLimit(int playersNum) {
        return 1;
    }

    public static void resetCounter() {
        Zombie.number = 0;
    }
}
