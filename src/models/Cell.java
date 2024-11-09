package models;

import behaviors.Blocker;
import constants.Color;

public abstract class Cell extends Positioned implements Blocker {
    protected Color color;
    protected static char symbol = ' ';//'█';
    protected static boolean background = true;

    public Cell(int x, int y) {
        super(x, y);
    }

    public Cell(int x, int y, Color color) {
        this(x, y);
        this.color = color;
    }

    public char getSymbol() {
        return Cell.symbol;
    }

    public Color getColor() {
        return color;
    }
}
