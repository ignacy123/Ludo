package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LudoBoard {
    int playerCount;
    List<Player> players;

    public LudoBoard(int players) {
        this.playerCount = players;
        this.players = new ArrayList<Player>();
        fillPlayers();
    }

    private void fillPlayers() {
        //TODO randomize
        players.add(new Player(Color.RED));
        players.add(new Player(Color.BLUE));
        if (playerCount > 2) {
            players.add(new Player(Color.YELLOW));
        }
        if (playerCount > 3) {
            players.add(new Player(Color.GREEN));
        }

    }

    public int getPlayerCount() {
        return playerCount;
    }

    public Player getPlayer(int a) {
        return players.get(a);
    }
}
