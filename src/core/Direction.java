package core;

public enum Direction {
    NORTH(-1, 0), SOUTH(1, 0), EAST(0, 1), WEST(0, -1);

    public final int dRow;
    public final int dCol;

    Direction(int r, int c) {
        this.dRow = r;
        this.dCol = c;
    }
}