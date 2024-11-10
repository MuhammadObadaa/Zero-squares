package models;

import behaviors.Blocker;
import constants.Color;

public abstract class Cell extends Positioned implements Blocker,Cloneable {
    protected Color color;
    protected static String symbol = " b ";//'█';

    public Cell(int x, int y) {
        super(x, y);
    }

    public Cell(int x, int y, Color color) {
        this(x, y);
        this.color = color;
    }

    public String getSymbol() {
        return Cell.symbol;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public Cell clone() throws CloneNotSupportedException {
        Cell clone = (Cell) super.clone();

        clone.color = this.color;
        clone.x = this.x;
        clone.y = this.y;

        return clone;
    }
}
