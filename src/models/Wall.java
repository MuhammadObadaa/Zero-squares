package models;

import behaviors.Blocker;
import behaviors.Movable;
import constants.Color;
import constants.MoveDirection;

public class Wall extends Cell {

    public Wall(int x, int y) {
        super(x, y, Color.BLACK);
    }

    @Override
    public boolean blocks(Movable movable, MoveDirection moveDirection) {
        return this.x == moveDirection.getNewX(movable.getX()) &&
                this.y == moveDirection.getNewY(movable.getY());
    }

}