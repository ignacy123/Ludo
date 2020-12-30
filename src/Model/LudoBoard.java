package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class LudoBoard {
    int playerCount;
    List<Player> players;
    List<String> finished;
    List<Tile> viableTiles;

    public LudoBoard(int players, List<String> names) {
        this.playerCount = players;
        this.players = new ArrayList<>();
        this.viableTiles = new ArrayList<>();
        this.finished = new ArrayList<>();
        fillPlayers(names);
    }

    private void fillPlayers(List<String> names) {
        Random random = new Random();
        Collections.rotate(names, random.nextInt(4));
        players.add(new Player(Color.RED, names.get(0), false));
        players.add(new Player(Color.BLUE, names.get(1), false));
        if (playerCount > 2) {
            players.add(new Player(Color.YELLOW, names.get(2), false));
        } else {
            players.add(new Player(Color.YELLOW, "", true));
        }
        if (playerCount > 3) {
            players.add(new Player(Color.GREEN, names.get(3), false));
        } else {
            players.add(new Player(Color.GREEN, "", true));
        }

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

    public void knockOut(Tile t, Color other) {
        int ind = 0;
        while (true) {
            if (players.get(ind).getColor().equals(other)) {
                break;
            }
            ind++;
        }
        Tile beg = new Tile(10, 6);
        if (ind == 1) {
            beg = new Tile(6, 0);
        }
        if (ind == 2) {
            beg = new Tile(0, 4);
        }
        if (ind == 3) {
            beg = new Tile(4, 10);
        }
        for (int i = 0; i < 4; i++) {
            Tile beg2 = new Tile(beg.getX(), beg.getY());
            beg2.addDist(players.get(ind).pawns.get(i) - 1);
            if (beg2.equals(t)) {
                players.get(ind).pawns.set(i, 0);
                break;
            }
        }
    }

    public void checkIfDone() {
        int notFinished = players.get(0).getPawns().stream().filter(x -> x != 45).collect(Collectors.toList()).size();
        if (notFinished > 0) {
            return;
        }
        players.get(0).done();
        finished.add(players.get(0).getName());
        if (!finished()) {
            nextPlayer();
        }
    }

    public boolean finished() {
        return players.stream().filter(x -> !x.isDummy()).collect(Collectors.toList()).size() == 0;
    }

    public List<String> getFinished() {
        return finished;
    }
}
