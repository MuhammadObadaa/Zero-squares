package models;

import behaviors.Blocker;
import behaviors.Movable;
import constants.Color;
import constants.MoveDirection;

public class Square extends Node implements Movable {
    protected static char symbol = '■';

    public Square(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public char getSymbol() {
        return Square.symbol;
    }

    @Override
    public void move(MoveDirection moveDirection) {
        this.x = moveDirection.getNewX(this.x);
        this.y = moveDirection.getNewY(this.y);
    }

    @Override
    public boolean blocks(Movable movable, MoveDirection moveDirection) {
        return this.x == moveDirection.getNewX(movable.getX()) &&
                this.y == moveDirection.getNewY(movable.getY());
    }
}
