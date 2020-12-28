package View;

import Model.LudoBoard;

import javax.swing.*;
import java.awt.*;

public class BoardView extends JPanel {
    LudoBoard board;

    public BoardView(LudoBoard board) {
        this.board = board;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawPath(g2d);
        drawPlayerTiles(g2d);
    }

    public void drawTile(Graphics2D g2d, int x, int y, int border, Color c) {
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1));
        g2d.drawRect(x * 80, y * 80, 80, 80);
        g2d.setColor(c);
        g2d.fillRect((x * 80) + border, (y * 80) + border, 80 - border, 80 - border);
        g2d.setColor(Color.BLACK);
    }

    public void drawSmallerTile(Graphics2D g2d, int x, int y, int border, Color c) {
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1));
        g2d.drawRect(x * 80, y * 80, 80, 80);
        g2d.setColor(c);
        g2d.fillRect((x * 80) + border, (y * 80) + border, 80 - 2 * border, 80 - 2 * border);
        g2d.setColor(Color.BLACK);
    }


    public void drawPath(Graphics2D g2d) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 11; j++) {
                drawTile(g2d, 4 + i, j, 1, Color.WHITE);
                drawTile(g2d, j, 4 + i, 1, Color.WHITE);
            }
        }
    }


    private void drawPlayerTiles(Graphics2D g2d) {
        //we know there can't be less than 2 players
        drawPlayer(g2d, 10, 10, board.getPlayer(0).getColor(), -1, -1, false);
        drawPlayer(g2d, 10, 0, board.getPlayer(1).getColor(), -1, 1, true);
        if(board.getPlayerCount()>2){
            drawPlayer(g2d, 0, 0, board.getPlayer(2).getColor(), 1, 1, false);
        }else{
            drawPlayer(g2d, 0, 0, Color.LIGHT_GRAY, 1, 1, false);
        }
        if(board.getPlayerCount()>3){
            drawPlayer(g2d, 0, 10, board.getPlayer(3).getColor(), 1, -1, true);
        }else{
            drawPlayer(g2d, 0, 10, Color.LIGHT_GRAY, 1, -1, true);
        }
    }

    private void drawPlayer(Graphics2D g2d, int x, int y, Color c, int ort1, int ort2, boolean swap) {
        drawTile(g2d, x, y, 1, c);
        drawTile(g2d, x + ort1, y, 1, c);
        drawTile(g2d, x, y + ort2, 1, c);
        drawTile(g2d, x + ort1, y + ort2, 1, c);
        if (!swap) {
            drawSmallerTile(g2d, x, y + (ort2) * 4, 4, c);
            for (int i = 1; i < 5; i++) {
                drawTile(g2d, x + (ort1) * i, y + 5 * ort2, 1, c);
            }
        } else {
            drawSmallerTile(g2d, x + (ort1) * 4, y , 4, c);
            for (int i = 1; i < 5; i++) {
                drawTile(g2d, x + (ort1) * 5, y + (ort2) * i, 1, c);
            }

        }
    }


}
