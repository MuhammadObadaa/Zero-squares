package models;

import behaviors.Movable;
import constants.MoveDirection;

public class Trap extends Cell{
    protected static String symbol = " * ";

    public Trap(int x,int y){
        super(x,y);
    }

    public String getSymbol(){
        return symbol;
    }

    @Override
    public boolean blocks(Movable movable, MoveDirection moveDirection) {
        return false;
    }
}
