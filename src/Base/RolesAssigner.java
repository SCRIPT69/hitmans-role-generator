package Base;

import Roles.Civilian;

import java.util.Random;

public class RolesAssigner {
    private final Random random = new Random();
    private final RolesFactory rolesFactory = new RolesFactory();

    public void assignRoles(Player players[]) {
        int playersNumber = players.length;


        for (int i = 0; i < playersNumber; i++) {
            Role assignedRole = null;
            int attempts = 0;
            int maxAttempts = 10;

            while (assignedRole == null && attempts < maxAttempts) {
                int randomRoleIndex = this.random.nextInt(rolesFactory.getNumberOfRoles());
                assignedRole = rolesFactory.createRole(randomRoleIndex, playersNumber);
                attempts++;
            }

            if (assignedRole == null) {
                assignedRole = Civilian.init(playersNumber);
            }

            players[i].setRole(assignedRole);
        }
    }
}
