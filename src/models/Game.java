package models;

import constants.Color;
import constants.MoveDirection;
import factories.CellFactory;
import factories.NodeFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Game {
    private State initState,currentState;
    private Cell[][] grid;
    // make the file accepts the initial grid have a square in another goal

    public Game(String filePath){

        int height = 0,width = 0,lineLength;

        try{
            // validate the file TODO: make it a function
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            String line = br.readLine();
            lineLength = line.length();
            width = lineLength/2;
            height = 1;

            while((line = br.readLine()) != null) {
                height++;

                if(lineLength != line.length()) {
                    throw new Exception("file Lines are not the same length, line:" + height);
                }
            }

            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        //Find better place to locate this function
        this.setGame(height,width,filePath);
    }

    private void setGame(int height, int width, String filePath) {
        String line;
        int lineLength;
        this.grid = new Cell[height][width];

        Node sampleNode;

        ArrayList<Square> squares = new ArrayList<>();
        ArrayList<Goal> goals = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            int i = 0;
            while ((line = br.readLine()) != null) {
                lineLength = line.length();

                for (int ch = 0,j = 0; ch < lineLength; ch+=2,++j) {

                    char symbol = line.charAt(ch);

                    grid[i][j] = CellFactory.getCell(i,j,symbol);

                    if(Color.getColor(symbol) != null) {
                        sampleNode = NodeFactory.getNode(i, j, symbol);

                        if(sampleNode instanceof Goal) {
                            goals.add((Goal) sampleNode);
                        }else{
                            squares.add((Square) sampleNode);
                        }

                        if(symbol == line.charAt(ch+1))
                            continue;

                        symbol = line.charAt(ch+1);

                        sampleNode = NodeFactory.getNode(i, j, symbol);

                        if(sampleNode instanceof Goal) {
                            goals.add((Goal) sampleNode);
                        }else{
                            squares.add((Square) sampleNode);
                        }
                    }
                }
                ++i;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.initState = new State(this.grid,squares,goals);
        try {
            this.currentState = this.initState.clone();
        }catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void show(){
        this.currentState.print();
    }

    public void move(MoveDirection direction){
        this.currentState.move(direction);

        if(!currentState.getStatus()){
            this.reset();
        }
    }

    public void reset(){
        try {
            this.currentState = this.initState.clone();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public State getState() {
        try {
            return this.currentState.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean finished(){
        return this.currentState.empty();
    }
}
