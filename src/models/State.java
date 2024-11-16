package models;

import behaviors.Movable;
import constants.Color;
import constants.MoveDirection;

import java.util.ArrayList;
import java.util.Iterator;

public class State implements Cloneable {

    private static Cell[][] grid;

    private State parent;

    private ArrayList<Square> squares;
    private ArrayList<Goal> goals;

    private boolean status;

    public State(Cell[][] grid,ArrayList<Square> squares, ArrayList<Goal> goals) {
        State.grid = grid;
        this.squares = squares;
        this.goals = goals;
        this.status = true;
    }

    private State(){}

    public void print(){
        for (int i = 0; i <State.grid.length; i++) {
            for (int j = 0; j <State.grid[0].length; j++) {
                System.out.print(this.at(i,j));
            }
            System.out.println();
        }
    }

    private String at(int x,int y){

        Color fg = null;
        Color bg;
        String symbol = "   ";

        Goal goal = this.goalAt(x, y);
        Square square = this.squareAt(x, y);

        //TODO: assert cell is node when has a goal or square;

        if(goal != null){
            bg = goal.getColor();
        }else{
            bg = State.grid[x][y].getColor();
        }

        if(square != null){
            fg = square.getColor();

            symbol = square.getSymbol() ;
        }else{
            symbol = State.grid[x][y].getSymbol();
        }

        return Color.formColor(fg,bg) + symbol + Color.resetColorCode();
    }

    public boolean empty(){
        // Because there could be Goals more than squares, it's fine to check the squares list only;
        return this.squares.isEmpty();
    }

    public void move(MoveDirection direction){

        ArrayList<Square> squaresToMove = new ArrayList<>(this.squares.reversed());

        Iterator<Square> squareIterator = squaresToMove.iterator();

        int nx,ny;

        while (squareIterator.hasNext()) {
            Square square = squareIterator.next();
            nx = direction.getNewX(square.getX());
            ny = direction.getNewY(square.getY());

            if(this.blocked(nx,ny,square,direction)){
                squareIterator.remove();
            }
        }

        squareIterator = this.squares.iterator();
        while (squareIterator.hasNext()) {
            Square square = squareIterator.next();

            if(!squaresToMove.contains(square)) {
                continue;
            }

            nx = direction.getNewX(square.getX());
            ny = direction.getNewY(square.getY());


            while(!this.blocked(nx,ny,square,direction)){

                square.move(direction);

                if(this.squareInGoal(nx,ny)){
                    squareIterator.remove();
                    this.goals.remove(this.goalAt(nx,ny));
                    break;
                }

                if(this.squareInTrap(nx,ny)){
                    this.status = false;
                    return;
                }

                nx = direction.getNewX(nx);
                ny = direction.getNewY(ny);
            }
        }
    }

    private boolean blocked(int x, int y, Movable movable, MoveDirection direction){
        return State.grid[x][y].blocks(movable,direction) || (this.squareAt(x,y) != null && this.squareAt(x,y).blocks(movable,direction));
    }

    private boolean squareInTrap(int x,int y){
        return this.squareAt(x,y) != null && State.grid[x][y] instanceof Trap;
    }

    private boolean squareInGoal(int x,int y){
        if(this.goalAt(x, y) == null || this.squareAt(x,y) == null)
            return false;

        if(this.goalAt(x, y).getColor() == Color.NOCOLOR) {
            this.goalAt(x, y).setColor(this.squareAt(x, y).getColor());
            return false;
        }

        return this.goalAt(x, y).getColor() == this.squareAt(x, y).getColor();
    }

    public boolean getStatus(){
        return this.status;
    }

    public Square squareAt(int x, int y){
        for (Square square : this.squares) {
            if(square.getX() == x && square.getY() == y)
                return square;
        }

        return null;
    }

    public Goal goalAt(int x, int y){
        for (Goal goal : this.goals) {
            if(goal.getX() == x && goal.getY() == y)
                return goal;
        }

        return null;
    }

    public void setParent(State parent){
        this.parent = parent;
    }

    public State getParent(){
        return this.parent;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equals;

        if(((State)obj).squares.size() != this.squares.size() || ((State)obj).goals.size() != this.goals.size())
            return false;

        for (Square square : this.squares) {
            equals = false;
            for (Square subSquare : ((State) obj).squares) {
                if(square.equals(subSquare)){
                    equals = true;
                    break;
                }
            }

            if(!equals)
                return false;
        }

        for (Goal goal : this.goals) {
            equals = false;
            for (Goal subGoal : ((State) obj).goals) {
                if(goal.equals(subGoal)){
                    equals = true;
                    break;
                }
            }

            if(!equals)
                return false;
        }

        return true;
    }

    @Override
    public State clone() throws CloneNotSupportedException {
        State clone = new State();
        clone.squares = new ArrayList<>();
        clone.goals = new ArrayList<>();
        clone.status = this.status;

        for (Square square : this.squares)
            clone.squares.add((Square)square.clone());

        for (Goal goal : this.goals)
            clone.goals.add((Goal)goal.clone());

        return clone;
    }

    public ArrayList<State> nextStates(){
        ArrayList<State> nextStates = new ArrayList<>();

        for (MoveDirection moveDirection : MoveDirection.values()) {
            State s;
            try {
                s = this.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }

            s.move(moveDirection);

            if (!this.equals(s) && s.getStatus())
                nextStates.add(s);
        }
        
        return nextStates;
    }
}
