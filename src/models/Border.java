package models;

import behaviors.Movable;
import constants.Color;
import constants.MoveDirection;

public class Border extends Cell{

    public Border(int x, int y) {
        super(x, y, Color.BLACK);
    }

    @Override
    public boolean blocks(Movable movable, MoveDirection moveDirection) {
        return true;
    }
}
