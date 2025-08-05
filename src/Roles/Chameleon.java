package Roles;

import Base.GameUI;
import Base.Player;
import Base.Role;

import java.lang.reflect.Method;
import java.util.*;

public class Chameleon extends Role {
    public static int number = 0;
    private Role role1;
    private Role role2;
    protected Chameleon() {
    }
    public static Chameleon init(int playersNumber) {
        // Prevent creating another Hitman if the role limit has been reached
        if (Chameleon.number == Chameleon.getLimit(playersNumber)) {
            return null;
        }

        Chameleon newChameleon = new Chameleon();

        newChameleon.isKilling = false;
        newChameleon.initTargetLast = true;

        // Roles available for Chameleon to choose from
        newChameleon.targetExceptionsRoles.add(Juggernaut.class);
        newChameleon.targetExceptionsRoles.add(ProKiller.class);
        newChameleon.targetExceptionsRoles.add(Hitman.class);
        newChameleon.targetExceptionsRoles.add(Seer.class);
        newChameleon.targetExceptionsRoles.add(Survivor.class);

        Chameleon.number += 1;
        newChameleon.setRoleName("🦎 Chameleon");
        return newChameleon;
    }

    @Override
    public boolean initTarget(Player[] players, int playerIndex) {
        Player player = players[playerIndex];

        // Just in case: prevent a bodyguard from becoming the target of their own target
        if (player.getTarget().getRole().getClass() == Bodyguard.class) {
            this.targetExceptionsRoles.remove(Hitman.class);
        }

        // TODO: Add proper role limit checks
        // If the role limit for Seer has been reached, remove it from the options
        if (Seer.number == Seer.getLimit(players.length)) {
            this.targetExceptionsRoles.remove(Seer.class);
        }

        this.task = "Your mission: choose a new role.\nMake your decision wisely.\n\n";
        String newRoles = this.chooseRoles(players, playerIndex);
        this.task += newRoles;
        return true;
    }

    private String chooseRoles(Player[] players, int playerIndex) {
        List<Class<? extends Role>> availableRoles = new ArrayList<>(this.targetExceptionsRoles);
        Collections.shuffle(availableRoles);

        if (availableRoles.isEmpty()) {
            this.task += "⚠️ No available roles to choose from.";
            return "Error: no roles available.";
        }

        Class<? extends Role> roleClass1 = availableRoles.get(0);
        Class<? extends Role> roleClass2 = availableRoles.size() > 1 ? availableRoles.get(1) : roleClass1;

        this.role1 = this.initRoleFromClass(roleClass1, players.length);
        this.role2 = this.initRoleFromClass(roleClass2, players.length);

        if (this.role1 == null || this.role2 == null) {
            this.task += "⚠️ An error occurred while generating roles. Please restart the game.";
            return "Role selection error.";
        }

        // Pre-initialize targets just in case
        this.role1.initTarget(players, playerIndex);
        this.role2.initTarget(players, playerIndex);

        return String.format("1 — %s\n2 — %s", role1.getRoleName(), role2.getRoleName());
    }
    private Role initRoleFromClass(Class<? extends Role> roleClass, int playersNumber) {
        try {
            Method initMethod = roleClass.getMethod("init", int.class);
            Object result = initMethod.invoke(null, playersNumber);
            if (result instanceof Role) {
                return (Role) result;
            }
        } catch (Exception e) {
            e.printStackTrace(); // Можно заменить на логирование
        }
        return null;
    }

    public void doAdditionalAction(Player player) {
        if (this.didAdditionalAction) {
            return;
        }
        else {
            this.didAdditionalAction = true;
        }

        System.out.println("Choose your role (1 or 2):");
        Scanner scanner = new Scanner(System.in);
        String choice = "";
        while (true) {
            if (choice.equals("1")) {
                player.setRole(role1);
                break;
            }
            else if(choice.equals("2")) {
                player.setRole(role2);
                break;
            }
            choice = scanner.nextLine();
        }
        GameUI UI = new GameUI();
        UI.showPlayerRole(player);
    }

    public static int getLimit(int playersNum) {
        return 1;
    }

    public static void resetCounter() {
        Chameleon.number = 0;
    }
}
