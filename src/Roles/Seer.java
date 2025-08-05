package Roles;

import Base.GameUI;
import Base.Player;
import Base.Role;

import java.util.ArrayList;
import java.util.List;

public class Seer extends Role {
    public static int number = 0;
    private String predictedPlayer;
    protected Seer() {
    }
    public static Seer init(int playersNumber) {
        // Prevent creating another Hitman if the role limit has been reached
        if (Seer.number == Seer.getLimit(playersNumber)) {
            return null;
        }

        Seer newSeer = new Seer();

        newSeer.isKilling = false;
        newSeer.initTargetLast = true;

        // Заполняем список с ролями исключениями для цели
        newSeer.targetExceptionsRoles.add(Juggernaut.class);
        newSeer.targetExceptionsRoles.add(ProKiller.class);
        newSeer.targetExceptionsRoles.add(Zombie.class);

        Seer.number += 1;
        newSeer.setRoleName("🔮 Seer");
        return newSeer;
    }

    @Override
    public boolean initTarget(Player[] players, int playerIndex) {
        Player player = players[playerIndex];
        this.task = "Your goal is to survive — knowing the fates of others.\nYou can see the future, but changing it won’t be easy.\n\n";
        String othersTargets = this.findOthersTargets(players, playerIndex);
        this.task += othersTargets;
        return true;
    }

    private String findOthersTargets(Player[] players, int playerIndex) {
        List<String> othersTargets = new ArrayList<>();
        for (int i = 0; i < players.length; i++) {
            if (i == playerIndex) {
                continue;
            }

            if (this.isExceptionsRole(players[i].getRole())) {
                if (players[i].getRole().getClass() == Juggernaut.class || players[i].getRole().getClass() == Zombie.class) {
                    othersTargets.add("Player " + players[i].getName() + " target: kill everyone");
                }
                else {
                    othersTargets.add("Player " + players[i].getName() + " target: unknown");
                }
            }
            else if (players[i].getRole().isKilling()){
                othersTargets.add("Player " + players[i].getName() + " target: " + players[i].getTarget().getName());
            }
        }
        return String.join("\n", othersTargets);
    }

    public void doAdditionalAction(Player player) {
        if (this.didAdditionalAction) {
            System.out.println("Seer's predicted survivor: " + this.predictedPlayer + "\n");
            return;
        }
        else {
            this.didAdditionalAction = true;
        }

        System.out.println("Prediction: type the name of the player you believe will survive ");
        this.predictedPlayer = GameUI.scanner.nextLine();
    }

    public static int getLimit(int playersNum) {
        return 1;
    }

    public static void resetCounter() {
        Seer.number = 0;
    }
}
