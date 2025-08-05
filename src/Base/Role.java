package Base;

import java.util.ArrayList;

public abstract class Role {
    private String roleName;
    protected String task;

    // Roles that cannot be targeted
    protected final ArrayList<Class<? extends Role>> targetProhibitedRoles = new ArrayList<>();
    // Roles that can be targeted despite their own target rules
    protected final ArrayList<Class<? extends Role>> targetExceptionsRoles = new ArrayList<>();

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public String getRoleName() {
        return this.roleName;
    }
    public String getTask() {
        return this.task;
    }

    protected boolean isProhibitedRole(Role role) {
        return this.targetProhibitedRoles.contains(role.getClass());
    }
    protected boolean isExceptionsRole(Role role) {
        return this.targetExceptionsRoles.contains(role.getClass());
    }

    // If this role should kill
    protected boolean isKilling = true;
    public boolean isKilling() {
        return this.isKilling;
    }


    // If the target should be assigned after all others targets
    protected boolean initTargetLast = false;
    public boolean isInitTargetLast() {
        return this.initTargetLast;
    }
    public abstract boolean initTarget(Player[] players, int playerIndex);

    // For roles with additional action
    protected boolean didAdditionalAction = false;
    public void doAdditionalAction(Player player) {
    }
}
