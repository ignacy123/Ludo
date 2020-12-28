package Controller;

import Model.LudoBoard;
import View.LudoView;

public class LudoController {
    LudoBoard board;
    LudoView view;

    public LudoController(LudoBoard board, LudoView view) {
        this.board = board;
        this.view = view;
    }

    public void start() {
        view.updateVisualisation();
    }
}
