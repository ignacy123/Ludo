package View;

import Model.LudoBoard;

import javax.swing.*;
import java.awt.*;

public class LudoView{
    LudoBoard board;
    JFrame mainFrame;
    BoardView boardView;

    public LudoView(LudoBoard board) {
        this.board = board;
        boardView = new BoardView(board);
        mainFrame = new JFrame("Ludo");
        mainFrame.setSize(880, 910);
        mainFrame.add(boardView);
        mainFrame.setVisible(true);
        mainFrame.setBackground(new Color(250, 240, 200));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void updateVisualisation() {
        boardView.repaint();
    }
}
