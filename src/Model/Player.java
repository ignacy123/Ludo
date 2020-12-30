package Model;


import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Player {
    Color c;
    List<Integer> pawns;
    String name;
    boolean dummy;

    public Player(Color c, String name, boolean dummy) {
        this.c = c;
        this.dummy = dummy;
        pawns = Arrays.asList(0, 0, 0, 0);
        this.name = name;
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

    public void done() {
        dummy = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return dummy == player.dummy &&
                Objects.equals(c, player.c) &&
                Objects.equals(pawns, player.pawns) &&
                Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(c, pawns, name, dummy);
    }
}
