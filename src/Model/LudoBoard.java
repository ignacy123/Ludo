package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LudoBoard {
    int playerCount;
    List<Player> players;
    List<Tile> viableTiles;

    public LudoBoard(int players) {
        this.playerCount = players;
        this.players = new ArrayList<>();
        this.viableTiles = new ArrayList<>();
        fillPlayers();
    }

    private void fillPlayers() {
        //TODO randomize
        players.add(new Player(Color.RED, false));
        players.add(new Player(Color.BLUE, false));
        if (playerCount > 2) {
            players.add(new Player(Color.YELLOW, false));
        } else {
            players.add(new Player(Color.YELLOW, true));
        }
        if (playerCount > 3) {
            players.add(new Player(Color.GREEN, false));
        } else {
            players.add(new Player(Color.GREEN, true));
        }

    }

    public int getPlayerCount() {
        return playerCount;
    }

    public Player getPlayer(int a) {
        return players.get(a);
    }

    public List<Tile> getViableTiles() {
        return viableTiles;
    }

    public void clearViableTiles() {
        viableTiles.clear();
    }

    public void addViableTile(Tile t) {
        viableTiles.add(t);
    }

    public void nextPlayer() {
        Collections.rotate(players, 3);
        while (players.get(0).isDummy()) {
            Collections.rotate(players, 3);
        }
    }
}
