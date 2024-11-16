package models;

import constants.Color;
import constants.MoveDirection;

import java.util.ArrayList;
import java.util.Iterator;

public class State implements Cloneable {
    protected int height;
    protected int width;

    protected CompositeCell[][] grid;

    private ArrayList<Square> squares;
    private ArrayList<Goal> goals;

    private boolean status = true;

    public State(CompositeCell[][] grid) {
        this.grid = grid;
        this.height = grid.length;
        this.width = grid[0].length;

        this.squares = new ArrayList<>();
        this.goals = new ArrayList<>();

        Node sampleNode;

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if((sampleNode = grid[i][j].getSquare()) != null)
                    this.squares.add((Square)sampleNode);
                if ((sampleNode = grid[i][j].getGoal()) != null)
                    this.goals.add((Goal)sampleNode);
            }
        }

        //assert this.squares.size() == this.goals.size();
    }

    public void print(){
        for (int i = 0; i <this.height; i++) {
            for (int j = 0; j <this.width; j++) {
                System.out.print(this.grid[i][j]);
            }
            System.out.println();
        }
    }

    public boolean empty(){
        // Because there could be Goals more than squares, it's fine to check the squares list only;
        return this.squares.isEmpty();
    }

    public void move(MoveDirection direction){
        this.squares.sort(direction.getComparator());
        boolean uncoloredGoalVisited;

        ArrayList<Square> squaresToMove = new ArrayList<>(this.squares.reversed());

        Iterator<Square> squareIterator = squaresToMove.iterator();

        int nx,ny;

        while (squareIterator.hasNext()) {
            Square square = squareIterator.next();
            nx = direction.getNewX(square.getX());
            ny = direction.getNewY(square.getY());

            if(this.grid[nx][ny].blocks(square,direction)){
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

            uncoloredGoalVisited = false;

            while(! this.grid[nx][ny].blocks(square,direction)){

                grid[square.getX()][square.getY()].dropSquare();
                square.move(direction);

                grid[nx][ny].setNode(square);

                if(grid[nx][ny].getSquare() != null && grid[nx][ny].getSquare().getColor() == Color.NOCOLOR)
                    uncoloredGoalVisited = true;

                if(grid[nx][ny].squareInGoal()){
                    squareIterator.remove();
                    this.goals.remove(this.grid[nx][ny].getGoal());
                    break;
                }else if(uncoloredGoalVisited){
                    for (Square sq : this.squares) {
                        this.status = false;
                        for (Goal goal : this.goals) {
                            if(goal.getColor() == sq.getColor()){
                                this.status = true;
                                break;
                            }
                        }
                        if(!this.status)
                            return;
                    }
                }

                if(grid[nx][ny].squareInTrap()){
                    this.status = false;
                    return;
                }

                nx = direction.getNewX(nx);
                ny = direction.getNewY(ny);
            }
        }
    }

    public boolean getStatus(){
        return this.status;
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
        State clone = (State)super.clone();
        clone.squares = new ArrayList<>();
        clone.goals = new ArrayList<>();

        Node sampleNode;

        clone.grid = new CompositeCell[this.height][this.width];

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                clone.grid[i][j] = this.grid[i][j].clone();
                if((sampleNode = clone.grid[i][j].getGoal()) != null)
                    clone.goals.add((Goal)sampleNode);
                if((sampleNode = clone.grid[i][j].getSquare()) != null)
                    clone.squares.add((Square)sampleNode);
            }
        }

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
