package models;

import constants.MoveDirection;

import java.util.ArrayList;
import java.util.Iterator;

public class State implements Cloneable {
    protected int height;
    protected int width;

    protected CompositeCell[][] grid;

    private ArrayList<Square> squares;
    private ArrayList<Goal> goals;

    public State(CompositeCell[][] grid) {
        this.grid = grid;
        this.height = grid.length;
        this.width = grid[0].length;

        this.squares = new ArrayList<>();
        this.goals = new ArrayList<>();

        Node sampleNode;

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if((sampleNode = grid[i][j].getNode()) instanceof Square)
                    this.squares.add((Square)sampleNode);
                else if ((sampleNode = grid[i][j].getNode()) instanceof Goal)
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

    public void move(MoveDirection direction){
        this.squares.sort(direction.getComparator());

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

            while(! this.grid[nx][ny].blocks(square,direction)){

                grid[square.getX()][square.getY()].dropSquare();
                square.move(direction);

                grid[nx][ny].setNode(square);

                if(grid[nx][ny].squareInGoal()){
                    squareIterator.remove();
                    break;
                }

                nx = direction.getNewX(nx);
                ny = direction.getNewY(ny);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        boolean equals;

        for (Square square : this.squares) {
            equals = false;
            for (Square subSquare : ((State) obj).squares) {
                if(subSquare.equals(square)){
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
                if(subGoal.equals(goal)){
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

        for (Square square : this.squares) {
            clone.squares.add((Square)square.clone());
        }

        for (Goal goal : this.goals) {
            clone.goals.add((Goal)goal.clone());
        }

        clone.grid = new CompositeCell[this.height][this.width];

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                clone.grid[i][j] = this.grid[i][j].clone();
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

            if (!this.equals(s))
                nextStates.add(s);
        }
        
        return nextStates;
    }
}
