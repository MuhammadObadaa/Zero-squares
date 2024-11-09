package models;

import behaviors.Blocker;
import behaviors.Movable;
import constants.Color;
import constants.MoveDirection;

public class CompositeCell extends Positioned implements Blocker {
    // Custom composite design pattern

    protected Cell cell;
    protected Node square;
    protected Node goal;

    public CompositeCell(Cell cell) {
        super(cell.getX(), cell.getY());
        this.cell = cell;
    }

//    public void setBase(Cell cell){
//        this.cell = cell;
//    }

    public void setNode(Node node){
        if(node instanceof Square)
            this.square = node;
        else if(node instanceof Goal)
            this.goal = node;
    }

    public Node getNode(){
        if(this.square != null)
            return this.square;
        else if(this.goal != null)
            return this.goal;
        return null;
    }

    @Override
    public String toString() {

        Color fg = null;
        Color bg;
        char symbol = ' ';

        //TODO: assert cell is node when has a goal or square;

        if(this.goal != null){
            bg = this.goal.getColor();
        }else{
            bg = cell.getColor();
        }

        if(this.square != null){
            fg = this.square.getColor();

            symbol = this.square.getSymbol() ;
        }

        return Color.formColor(fg,bg) + symbol + Color.getNoColorCode();
    }

    @Override
    public boolean blocks(Movable movable, MoveDirection moveDirection) {
        if(this.square != null && this.square.blocks(movable, moveDirection)){
            return true;
        }
        return this.cell.blocks(movable, moveDirection);
    }
}
