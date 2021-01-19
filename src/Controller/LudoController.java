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
    Tile t;
    Player curr;

    public LudoController(LudoBoard board, LudoView view) {
        this.board = board;
        this.view = view;
        viables = new HashMap<>();
    }

    public void start() {
        view.updateVisualisation();
    }

    public void diceRolled(int res) {
        //System.out.println("dice rolled: " + res);
        this.res = res;
        board.clearViableTiles();
        viables.clear();
        curr = board.getPlayer(0);
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
            } else if (i + res < 45) {
                Tile t = new Tile(9 - (i + res - 41), 5);
                board.addViableTile(t);
                viables.put(t, i + res);
            } else if (i + res == 45) {
                board.addViableTile(new Tile(5, 5));
                viables.put(new Tile(5, 5), i + res);
            }
        }
        if (viables.size() == 0) {
            if (res != 6) {
                board.nextPlayer();
            }
            view.publishButton();
        }
        view.updateVisualisation();
    }

    public void mouseClicked(Point p) {
        t = getViable(p);
        if (t != null) {
            //System.out.println("clicked on viable");
            board.clearViableTiles();
            int i = viables.get(t);
            viables.clear();
            view.executeMove(i, res);
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

    public void moveExecuted(Map<Tile, HashMap<Color, Integer>> pawned) {
        boolean hasKnock = true;
        if (isSafe(t)) {
            hasKnock = false;
        }
        if (hasKnock) {
            if (pawned.get(t).size() != 2) {
                hasKnock = false;
            }
        }
        if (hasKnock) {
            Color other = (Color) pawned.get(t).keySet().stream().filter(x -> !x.equals(curr.getColor())).toArray()[0];
            if (pawned.get(t).get(other) > 1) {
                hasKnock = false;
            }
            if (hasKnock) {
                System.out.println("knock out: " + curr.getColor() + ", " + other);
                board.knockOut(t, other);
            }
        }
        view.updateVisualisation();
        if (!hasKnock && res != 6 && !t.equals(new Tile(5, 5))) {
            board.nextPlayer();
        }
        board.checkIfDone();
        if (board.finished()) {
            view.finishGame(board.getFinished());
        }
        view.publishButton();
        view.updateVisualisation();
    }

    private boolean isSafe(Tile t) {
        if (t.getX() == 6 && t.getY() == 0) {
            return true;
        }
        if (t.getX() == 10 && t.getY() == 6) {
            return true;
        }
        if (t.getX() == 4 && t.getY() == 10) {
            return true;
        }
        if (t.getX() == 0 && t.getY() == 4) {
            return true;
        }
        if (t.getY() == 5 && t.getX() != 0 && t.getX() != 10) {
            return true;
        }
        if (t.getX() == 5 && t.getY() != 0 && t.getY() != 10) {
            return true;
        }
        return false;
    }
}
