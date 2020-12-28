package Main;

import Model.LudoBoard;
import Controller.LudoController;
import View.LudoView;

import javax.swing.*;
import java.awt.*;

public class Start {
    static JFrame optionsFrame;
    public static void startGame(int count) {
        LudoBoard board = new LudoBoard(count);
        LudoView view = new LudoView(board);
        LudoController controller = new LudoController(board, view);
        optionsFrame.setVisible(false);
        controller.start();
    }

    public static void main(String[] args) {
        //pick options
        optionsFrame = new JFrame();
        optionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        optionsFrame.setSize(300, 600);
        JButton button = new JButton("Start");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);
        JComboBox playerCount = new JComboBox(new Integer[]{2, 3, 4});
        optionsFrame.add(playerCount, BorderLayout.CENTER);
        optionsFrame.add(buttonPanel, BorderLayout.PAGE_END);
        optionsFrame.setVisible(true);
        button.addActionListener(x -> startGame((int) playerCount.getSelectedItem()));
    }
}
