package models;

import behaviors.Blocker;
import constants.Color;

import java.util.Objects;

public abstract class Cell extends Positioned implements Blocker,Cloneable {
    protected Color color;
    protected static String symbol = "   ";//'█';

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

    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hashCode(color);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && this.color == ((Cell) obj).color;
    }
}
