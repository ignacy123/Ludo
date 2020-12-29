package View;

import Model.LudoBoard;
import Model.Player;
import Model.Tile;

import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BoardView extends Panel {
    LudoBoard board;
    int offset = 5;

    public BoardView(LudoBoard board) {
        this.board = board;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawPath(g2d);
        drawPlayerTiles(g2d);
        drawViableTiles(g2d);
        drawPawns(g2d);
    }


    public void drawTile(Graphics2D g2d, Tile t, int border, Color c) {
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.BLACK);
        g2d.drawRect(t.getLeft(), t.getTop(), 80, 80);
        g2d.setColor(c);
        g2d.fillRect(t.getLeft() + border, t.getTop() + border, 80 - border, 80 - border);
        g2d.setColor(Color.BLACK);
    }

    public void drawPawn(Graphics2D g2d, Tile tile, Color c) {
        g2d.setStroke(new BasicStroke(4));
        g2d.drawOval(tile.getX() * 80 + offset, tile.getY() * 80 + offset, 80 - 2 * offset, 80 - 2 * offset);
        g2d.setColor(c);
        g2d.fillOval(tile.getX() * 80 + offset, tile.getY() * 80 + offset, 80 - 2 * offset, 80 - 2 * offset);
        g2d.setColor(Color.BLACK);
    }

    public void drawSmallerTile(Graphics2D g2d, int x, int y, int border, Color c) {
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x * 80, y * 80, 80, 80);
        g2d.setColor(c);
        g2d.fillRect((x * 80) + border, (y * 80) + border, 80 - 2 * border, 80 - 2 * border);
        g2d.setColor(Color.BLACK);
    }


    public void drawPath(Graphics2D g2d) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 11; j++) {
                drawTile(g2d, new Tile(4 + i, j), 1, Color.WHITE);
                drawTile(g2d, new Tile(j, 4 + i), 1, Color.WHITE);
            }
        }
    }


    private void drawPlayerTiles(Graphics2D g2d) {
        //we know there can't be less than 2 players
        drawPlayer(g2d, 10, 10, board.getPlayer(0).getColor(), -1, -1, false);
        if (!board.getPlayer(1).isDummy()) {
            drawPlayer(g2d, 10, 0, board.getPlayer(1).getColor(), -1, 1, true);
        } else {
            drawPlayer(g2d, 10, 0, Color.LIGHT_GRAY, -1, 1, true);
        }
        if (!board.getPlayer(2).isDummy()) {
            drawPlayer(g2d, 0, 0, board.getPlayer(2).getColor(), 1, 1, false);
        } else {
            drawPlayer(g2d, 0, 0, Color.LIGHT_GRAY, 1, 1, false);
        }
        if (!board.getPlayer(3).isDummy()) {
            drawPlayer(g2d, 0, 10, board.getPlayer(3).getColor(), 1, -1, true);
        } else {
            drawPlayer(g2d, 0, 10, Color.LIGHT_GRAY, 1, -1, true);
        }
    }

    private void drawPlayer(Graphics2D g2d, int x, int y, Color c, int ort1, int ort2, boolean swap) {
        drawTile(g2d, new Tile(x, y), 1, c);
        drawTile(g2d, new Tile(x + ort1, y), 1, c);
        drawTile(g2d, new Tile(x, y + ort2), 1, c);
        drawTile(g2d, new Tile(x + ort1, y + ort2), 1, c);
        if (!swap) {
            drawSmallerTile(g2d, x, y + (ort2) * 4, 4, c);
            for (int i = 1; i < 5; i++) {
                drawTile(g2d, new Tile(x + (ort1) * i, y + 5 * ort2), 1, c);
            }
        } else {
            drawSmallerTile(g2d, x + (ort1) * 4, y, 4, c);
            for (int i = 1; i < 5; i++) {
                drawTile(g2d, new Tile(x + (ort1) * 5, y + (ort2) * i), 1, c);
            }

        }
    }

    private void drawPawns(Graphics2D g2d) {
        drawBase(g2d, 10, 10, board.getPlayer(0));
        drawOut(g2d, new Tile(10, 6), board.getPlayer(0));
        drawHome(g2d, new Tile(9, 5), -1, board.getPlayer(0));
        if (!board.getPlayer(1).isDummy()) {
            drawBase(g2d, 10, 1, board.getPlayer(1));
            drawOut(g2d, new Tile(6, 0), board.getPlayer(1));
            drawHome(g2d, new Tile(5, 1), 1, board.getPlayer(1));
        }
        if (!board.getPlayer(2).isDummy()) {
            drawBase(g2d, 1, 1, board.getPlayer(2));
            drawOut(g2d, new Tile(0, 4), board.getPlayer(2));
            drawHome(g2d, new Tile(1, 5), 1, board.getPlayer(2));
        }
        if (!board.getPlayer(3).isDummy()) {
            drawBase(g2d, 1, 10, board.getPlayer(3));
            drawOut(g2d, new Tile(4, 10), board.getPlayer(3));
            drawHome(g2d, new Tile(5, 9), -1, board.getPlayer(3));
        }
    }


    private void drawBase(Graphics2D g2d, int x, int y, Player player) {
        int count = Collections.frequency(player.getPawns(), 0);
        if (count > 0) {
            drawPawn(g2d, new Tile(x, y), player.getColor());
        }
        if (count > 1) {
            drawPawn(g2d, new Tile(x - 1, y), player.getColor());
        }
        if (count > 2) {
            drawPawn(g2d, new Tile(x - 1, y - 1), player.getColor());
        }
        if (count > 3) {
            drawPawn(g2d, new Tile(x, y - 1), player.getColor());
        }
    }

    private void drawOut(Graphics2D g2d, Tile Tile, Player player) {
        List<Integer> nonZero = player.getPawns().stream().filter(x -> x != 0 && x < 40).sorted().collect(Collectors.toList());
        for (Integer i : nonZero) {
            Tile c = new Tile(Tile.getX(), Tile.getY());
            c.addDist(i-1);
            drawPawn(g2d, c, player.getColor());
        }
    }

    private void drawHome(Graphics2D g2d, Tile Tile, int orientation, Player player) {
        List<Integer> nonZero = player.getPawns().stream().filter(x -> x >= 40 && x < 44).sorted().collect(Collectors.toList());
        for (Integer i : nonZero) {
            i = i - 40;
            Tile c;
            if (Tile.getX() == 5) {
                c = new Tile(Tile.getX(), Tile.getY() + orientation * i);
            } else {
                c = new Tile(Tile.getX() + orientation * i, Tile.getY());
            }
            drawPawn(g2d, c, player.getColor());
        }
    }

    private void drawViableTiles(Graphics2D g2d) {
        for (Tile t : board.getViableTiles()) {
            drawTile(g2d, t, 1, Color.cyan);
        }
    }

}
