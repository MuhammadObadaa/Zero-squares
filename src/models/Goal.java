package models;

public class Goal extends Node{
    private Color color;

    public Goal(int x, int y) {
        super(x, y);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Goal(int x, int y, Color color) {
        this(x, y);
        this.color = color;
    }
}
