package Model;

import java.awt.*;
import java.util.Objects;

public class Tile {
    int left;
    int right;
    int top;
    int bottom;
    int x;
    int y;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        top = y * 80;
        bottom = (y + 1) * 80;
        left = x * 80;
        right = (x + 1) * 80;
    }

    public boolean contains(Point p) {
        return p.getX() >= left && p.getX() <= right && p.getY() <= bottom && p.getY() >= top;
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getTop() {
        return top;
    }

    public int getBottom() {
        return bottom;
    }

    public void addDist(int dist) {
        while (dist > 0) {
            if (x == 6) {
                if (y != 4 && y != 10) {
                    y++;
                    dist--;
                    continue;
                } else if (y == 4) {
                    x++;
                    dist--;
                    continue;
                } else {
                    x--;
                    dist--;
                    continue;
                }
            }
            if (x == 4) {
                if (y != 6 && y != 0) {
                    y--;
                    dist--;
                    continue;
                } else if (y == 6) {
                    x--;
                    dist--;
                    continue;
                } else {
                    x++;
                    dist--;
                    continue;
                }
            }
            if (y == 4) {
                if (x != 10) {
                    x++;
                } else {
                    y++;
                }
                dist--;
                continue;
            }
            if (y == 6) {
                if (x != 0) {
                    x--;
                } else {
                    y--;
                }
                dist--;
                continue;
            }
            if (y == 0) {
                x++;
                dist--;
                continue;
            }
            if (y == 10) {
                x--;
                dist--;
                continue;
            }
            if (x == 0) {
                y--;
                dist--;
                continue;
            }
            if (x == 10) {
                y++;
                dist--;
                continue;
            }
        }
        top = y * 80;
        bottom = (y + 1) * 80;
        left = x * 80;
        right = (x + 1) * 80;

    }

    @Override
    public String toString() {
        return "Tile{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return left == tile.left &&
                right == tile.right &&
                top == tile.top &&
                bottom == tile.bottom &&
                x == tile.x &&
                y == tile.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, top, bottom, x, y);
    }
}
