package Roles;
import Base.Player;
import Base.Role;

public class Hitman extends Role {
    public static int number = 0;
    protected Hitman() {
    }
    public static Hitman init(int playersNumber) {
        // Prevent creating another Hitman if the role limit has been reached
        if (Hitman.number == Hitman.getLimit(playersNumber)) {
            return null;
        }

        Hitman newHitman = new Hitman();
        Hitman.number += 1;
        newHitman.setRoleName("🎯 Hitman");
        return newHitman;
    }

    @Override
    public boolean initTarget(Player[] players, int playerIndex) {
        Player player = players[playerIndex];
        Player target = player.getTarget();

        this.task = "Your mission: eliminate player " + target.getName() + ".\nDon’t ask unnecessary questions.";
        return true;
    }

    public static int getLimit(int playersNum) {
        return playersNum;
    }

    public static void resetCounter() {
        Hitman.number = 0;
    }
}
