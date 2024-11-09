package models;

import behaviors.Movable;
import constants.Color;
import constants.MoveDirection;

public class Node extends Cell{

    public Node(int x, int y) {
        super(x, y, Color.WHITE);
    }

    public Node(int x,int y,Color color) {
        super(x, y, color);
    }

    @Override
    public boolean blocks(Movable movable, MoveDirection moveDirection) {
        return false;
    }
}
