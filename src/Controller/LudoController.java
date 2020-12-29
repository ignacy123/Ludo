package Controller;

import Model.LudoBoard;
import Model.Player;
import Model.Tile;
import View.LudoView;

import java.awt.*;
import java.util.*;


public class LudoController {
    LudoBoard board;
    LudoView view;
    Map<Tile, Integer> viables;
    int res;

    public LudoController(LudoBoard board, LudoView view) {
        this.board = board;
        this.view = view;
        viables = new HashMap<>();
    }

    public void start() {
        view.updateVisualisation();
    }

    public void diceRolled(int res) {
        System.out.println("dice rolled: " + res);
        this.res = res;
        board.clearViableTiles();
        viables.clear();
        Player curr = board.getPlayer(0);
        for (Integer i : curr.getPawns()) {
            if (i == 0) {
                if (res == 6) {
                    board.addViableTile(new Tile(10, 6));
                    viables.put(new Tile(10, 6), 1);
                }
                continue;
            }
            if (i + res <= 40) {
                Tile t = new Tile(10, 6);
                t.addDist(i + res - 1);
                board.addViableTile(t);
                viables.put(t, i + res);
            } else if (i + res < 44) {
                Tile t = new Tile(8 - (i + res - 41), 5);
                board.addViableTile(t);
                viables.put(t, i + res);
            }
            if (i + res == 44) {
                board.addViableTile(new Tile(5, 5));
                viables.put(new Tile(5, 5), 44);
            }
        }
        if (viables.size() == 0) {
            board.nextPlayer();
            view.publishButton();
        }
        view.updateVisualisation();
    }

    public void mouseClicked(Point p) {
        Tile t = getViable(p);
        if (t != null) {
            System.out.println("clicked on viable");
            board.clearViableTiles();
            int i = viables.get(t);
            view.executeMove(i, res, true);
        } else {
            System.out.println("mouse clicked: " + p);
            return;
        }
    }

    private Tile getViable(Point p) {
        for (Tile t : viables.keySet()) {
            if (t.contains(p)) {
                return t;
            }
        }
        return null;
    }
}
