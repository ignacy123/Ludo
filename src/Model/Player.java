package Model;


import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Player {
    Color c;
    List<Integer> pawns;
    String name;
    boolean dummy;

    public Player(Color c, boolean dummy) {
        this.c = c;
        this.dummy = dummy;
        pawns = Arrays.asList(38, 41, 42, 43);
        this.name = c.toString();
    }

    public Color getColor() {
        return c;
    }

    public List<Integer> getPawns() {
        return pawns;
    }

    public String getName() {
        return name;
    }

    public boolean isDummy() {
        return dummy;
    }

}
