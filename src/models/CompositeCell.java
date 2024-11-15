package models;

import behaviors.Blocker;
import behaviors.Movable;
import constants.Color;
import constants.MoveDirection;

public class CompositeCell extends Positioned implements Blocker, Cloneable {
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

    public Node getSquare(){
        return this.square;
    }

    public Node getGoal(){
        return this.goal;
    }

    public void dropSquare(){
        this.square = null;
    }

    private void dropGoal(){
        this.goal = null;
    }

    public boolean squareInGoal(){
        if(this.goal == null || this.square == null)
            return false;

        if(this.goal.getColor() == Color.NOCOLOR){
            ((Goal)this.goal).setColor(this.square.getColor());
            return false;
        }

        if(this.goal.getColor() == this.square.getColor()){
            dropGoal();
            dropSquare();

            return true;
        }
        return false;
    }

    public boolean squareInTrap(){
        return this.square != null && this.cell instanceof Trap;
    }

    @Override
    public String toString() {

        Color fg = null;
        Color bg;
        String symbol = "   ";

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

    @Override
    public CompositeCell clone() throws CloneNotSupportedException {
        CompositeCell clone = new CompositeCell(this.cell);

        if(this.square != null)
            clone.square = (Square) this.square.clone();
        if(this.goal != null)
            clone.goal = (Goal) this.goal.clone();

        return clone;
    }
}
