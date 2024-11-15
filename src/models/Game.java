package models;

import constants.Color;
import constants.MoveDirection;
import factories.CellFactory;
import factories.NodeFactory;

import java.io.BufferedReader;
import java.io.FileReader;

public class Game {
    private State state;
    private String filePath;
    // make the file accepts the initial grid have a square in another goal

    public Game(String filePath){
        this.filePath = filePath;

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
        CompositeCell[][] cCells = new CompositeCell[height][width];

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            int i = 0;
            while ((line = br.readLine()) != null) {
                lineLength = line.length();

                for (int ch = 0,j = 0; ch < lineLength; ch+=2,++j) {

                    char symbol = line.charAt(ch);

                    cCells[i][j] = new CompositeCell(CellFactory.getCell(i,j,symbol));

                    if(Color.getColor(symbol) != null) {
                        cCells[i][j].setNode(NodeFactory.getNode(i, j, symbol));

                        if(symbol == line.charAt(ch+1))
                            continue;

                        symbol = line.charAt(ch+1);
                        cCells[i][j].setNode(NodeFactory.getNode(i, j, symbol));
                    }
                }
                ++i;
            }

            //TODO: assert that the square colors are the same of the goals colors

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.state = new State(cCells);
    }

    public void show(){
        this.state.print();
    }

    public void move(MoveDirection direction){
        this.state.move(direction);

        if(!state.getStatus()){
            this.reset();
        }
    }

    public void reset(){
        this.setGame(this.state.height,this.state.width,this.filePath);
    }

    public State getState() {
        return this.state;
    }

    public boolean finished(){
        return this.state.empty();
    }
}
