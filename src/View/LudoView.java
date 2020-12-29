package View;

import Controller.LudoController;
import Model.LudoBoard;
import Model.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LudoView {
    LudoBoard board;
    JFrame mainFrame;
    JPanel container;
    JPanel labelContainer;
    JPanel throwContainer;
    JPanel diceContainer;
    JPanel buttonContainer;
    JPanel turnPanel;
    JLabel nameLabel;
    JLabel nameLabel2;
    JLabel dice;
    JButton throwButton;
    BoardView boardView;
    LudoController controller;
    Color bgColor;
    Random random;
    List<Tile> viableTiles;

    public LudoView(LudoBoard board) {
        this.board = board;
        random = new Random();
        viableTiles = new ArrayList<>();
        bgColor = new Color(250, 240, 200);
        nameLabel = new JLabel("Current player:");
        nameLabel2 = new JLabel();
        boardView = new BoardView(board);
        turnPanel = new JPanel();
        throwContainer = new JPanel();
        labelContainer = new JPanel();
        dice = new JLabel();
        mainFrame = new JFrame("Ludo");
        container = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        buttonContainer = new JPanel();
        diceContainer = new JPanel();
        throwButton = new JButton("Roll the dice");

        boardView.setBackground(bgColor);
        boardView.setPreferredSize(new Dimension(880, 880));
        boardView.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.mouseClicked(e.getPoint());
            }
        });

        turnPanel.setBackground(bgColor);
        turnPanel.setPreferredSize(new Dimension(880, 150));
        turnPanel.setLayout(new BoxLayout(turnPanel, BoxLayout.X_AXIS));

        dice.setIcon(new ImageIcon(getClass().getResource("/Img/dice1.jpg")));
        dice.setHorizontalAlignment(SwingConstants.CENTER);

        diceContainer.setLayout(new BorderLayout());
        diceContainer.setBackground(bgColor);
        diceContainer.setPreferredSize(new Dimension(220, 150));
        diceContainer.setMaximumSize(new Dimension(220, 150));
        diceContainer.add(dice, BorderLayout.CENTER);

        throwButton.setFont(new Font("Ubuntu", Font.BOLD, 16));
        throwButton.addActionListener(e -> {
            throwButton.setVisible(false);
            rollTheDice();
        });

        buttonContainer.setLayout(new GridBagLayout());
        buttonContainer.setBackground(bgColor);
        buttonContainer.setPreferredSize(new Dimension(220, 150));
        buttonContainer.setMaximumSize(new Dimension(220, 150));
        buttonContainer.add(throwButton);

        throwContainer.setBackground(bgColor);
        throwContainer.setLayout(new BoxLayout(throwContainer, BoxLayout.X_AXIS));
        throwContainer.add(diceContainer);
        throwContainer.add(buttonContainer);

        nameLabel.setFont(new Font("Ubuntu", Font.BOLD, 20));
        nameLabel2.setFont(new Font("Ubuntu", Font.BOLD, 20));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 0, 0);

        labelContainer.setBackground(bgColor);
        labelContainer.setLayout(new GridBagLayout());
        labelContainer.add(nameLabel2, gbc);
        labelContainer.add(nameLabel);
        labelContainer.setMaximumSize(new Dimension(440, 150));
        labelContainer.setPreferredSize(new Dimension(440, 150));

        turnPanel.add(labelContainer);
        turnPanel.add(throwContainer);

        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(boardView);
        container.add(turnPanel);

        mainFrame.add(container);
        mainFrame.setVisible(true);
        mainFrame.setSize(880, 1080);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void updateVisualisation() {
        nameLabel2.setText(board.getPlayer(0).getName());
        boardView.repaint();
    }

    public void bindController(LudoController controller) {
        this.controller = controller;
    }

    private void rollTheDice() {
        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                int res = 1;
                for (int i = 0; i < 10; i++) {
                    res = random.nextInt(6) + 1;
                    this.publish(res);
                    Thread.sleep(100);
                }
                controller.diceRolled(res);
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
                int i = chunks.get(chunks.size() - 1);
                updateDice(i);
            }
        };
        worker.execute();
    }

    private void updateDice(int a) {
        dice.setIcon(new ImageIcon(getClass().getResource("/Img/dice" + a + ".jpg")));
    }

    public void publishButton() {
        throwButton.setVisible(true);
    }

    public void executeMove(int i, int res, boolean lastMove) {
        int j = 0;
        for (; j < 3; j++) {
            if (board.getPlayer(0).getPawns().get(j) + res == i || (i == 1 && res == 6 && board.getPlayer(0).getPawns().get(j) == 0)) {
                break;
            }
        }
        int workerJ = j;
        int workerRes = res;
        SwingWorker<Void, Integer> executor = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                if (i == 1 && res == 6) {
                    Thread.sleep(200);
                    board.getPlayer(0).getPawns().set(workerJ, board.getPlayer(0).getPawns().get(workerJ) + 1);
                    updateVisualisation();
                } else {
                    int counter = workerRes;
                    while (counter > 0) {
                        counter--;
                        Thread.sleep(200);
                        board.getPlayer(0).getPawns().set(workerJ, board.getPlayer(0).getPawns().get(workerJ) + 1);
                        updateVisualisation();
                    }
                }
                Thread.sleep(1000);
                if (lastMove) {
                    board.nextPlayer();
                }
                updateVisualisation();
                publishButton();
                return null;
            }
        };
        executor.execute();

    }
}
