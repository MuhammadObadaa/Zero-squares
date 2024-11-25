package models;

import behaviors.Movable;
import constants.Color;
import constants.MoveDirection;

public class Goal extends Node{

    public Goal(int x, int y) {
        // use reset code \u001B[0m
        this(x, y, Color.WHITE);
    }

    public Goal(int x, int y, Color color) {
        super(x, y, color);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean blocks(Movable movable, MoveDirection moveDirection) {
        return false;
    }

    @Override
    public int hashCode() {
        return this.color.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Goal && super.equals(obj);
    }
}
