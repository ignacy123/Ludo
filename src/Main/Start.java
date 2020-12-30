package Main;

import Model.LudoBoard;
import Controller.LudoController;
import View.LudoView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;

public class Start {
    static JFrame optionsFrame;
    static JPanel container;
    static JLabel[] labels;
    static JTextField[] fields;
    static Font font;
    static Color bgColor;

    public static void startGame(int count) {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String toAdd = fields[i].getText();
            if (toAdd.equals("")) {
                toAdd = "anonymous" + (i + 1);
            }
            names.add(toAdd);
        }
        LudoBoard board = new LudoBoard(count, names);
        LudoView view = new LudoView(board);
        LudoController controller = new LudoController(board, view);
        view.bindController(controller);
        optionsFrame.setVisible(false);
        controller.start();
    }

    public static void main(String[] args) {
        font = new Font("Ubuntu", Font.BOLD, 50);
        bgColor = new Color(250, 240, 200);
        //pick options
        labels = new JLabel[]{
                new JLabel("First player:"),
                new JLabel("Second player:"),
                new JLabel("Third player:"),
                new JLabel("Fourth player:"),
        };
        fields = new JTextField[]{
                new JTextField(10),
                new JTextField(10),
                new JTextField(10),
                new JTextField(10)
        };
        for (int i = 0; i < 4; i++) {
            labels[i].setFont(font);
            fields[i].setFont(font);
        }
        optionsFrame = new JFrame();
        optionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container = new JPanel();
        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 0, 0);
        JComboBox playerCount = new JComboBox(new Integer[]{2, 3, 4});
        container.add(playerCount, gbc);
        for (int i = 0; i < 4; i++) {
            gbc.gridx = 0;
            gbc.gridy = 1 + 2 * i;
            container.add(labels[i], gbc);
            gbc.gridy++;
            container.add(fields[i], gbc);
        }
        JButton button = new JButton("Start");
        button.setFont(font);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);
        gbc.gridx = 0;
        gbc.gridy = 9;
        container.add(buttonPanel, gbc);
        container.setBackground(bgColor);
        optionsFrame.setBackground(bgColor);
        optionsFrame.add(container);
        optionsFrame.pack();
        updateFields(2);
        optionsFrame.setVisible(true);
        playerCount.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Integer i = (Integer) e.getItem();
                updateFields(i);
            }
        });
        button.addActionListener(x -> startGame((int) playerCount.getSelectedItem()));
    }

    private static void updateFields(Integer i) {
        for (int j = 0; j < i; j++) {
            labels[j].setVisible(true);
            fields[j].setVisible(true);
        }
        for (; i < 4; i++) {
            labels[i].setVisible(false);
            fields[i].setVisible(false);
        }
    }
}
