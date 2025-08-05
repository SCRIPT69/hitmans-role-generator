package Base;
import java.util.Random;
import Roles.Survivor;


public class Game {
    private final GameUI UI = new GameUI();
    private final RolesFactory rolesFactory = new RolesFactory();
    private final RolesAssigner rolesAssigner = new RolesAssigner();
    private final TargetsAssigner targetsAssigner = new TargetsAssigner();
    private final Random random = new Random();
    private int playersNumber;
    private Player[] players;

    // Initializes and starts the game by setting up players, roles, and targets
    public void startGame() {
        // After starting a new game we should reset all counters for roles
        rolesFactory.resetRolesCounter();

        // Getting players number and their names
        this.playersNumber = UI.getPlayersNumber();
        String[] names = UI.getPlayersNames(this.playersNumber);

        this.players = new Player[this.playersNumber];
        for (int i = 0; i < this.playersNumber; i++) {
            this.players[i] = new Player(names[i]);
        }


        // 1 - Generating random roles for players
        this.rolesAssigner.assignRoles(this.players);

        // 2 - Generating random targets for players
        this.targetsAssigner.assignTargets(this.players);

        this.UI.printPlayersOneByOne(this.players);

        for (Player player : this.players) {
            this.UI.showPlayerRole(player);
        }
    }
}
