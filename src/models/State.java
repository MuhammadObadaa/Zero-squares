package models;

import java.util.ArrayList;

public class State {
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

    /* public State[] nextState(){

    } */
}
