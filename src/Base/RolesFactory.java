package Base;

import java.util.ArrayList;

import Roles.*;

public class RolesFactory {
    private final ArrayList<RoleFactory> factories = new ArrayList<>();

    public RolesFactory() {
        // Adding references to the static init methods of specific roles
        factories.add(Hitman::init);
        factories.add(Survivor::init);
        factories.add(Bodyguard::init);
    }

    public void resetRolesCounter() {
        Hitman.resetCounter();
        Survivor.resetCounter();
        Bodyguard.resetCounter();
    }

    public Role createRole(int factoryIndex, int playersNumber) {
        return factories.get(factoryIndex).create(playersNumber);
    }

    public int getNumberOfRoles() {
        return factories.size();
    }
}