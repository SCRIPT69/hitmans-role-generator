package Base;

public class Player {
    private String name;
    private Player target;
    private Role role;

    public String getName() {
        return this.name;
    }
    public Player getTarget() {
        return target;
    }
    public void setTarget(Player target) {
        this.target = target;
    }
    public Role getRole() {
        return this.role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    public Player(String name) {
        this.name = name;
    }
}
