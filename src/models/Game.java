package models;

import constants.Color;
import factories.CellFactory;
import factories.NodeFactory;

import java.io.BufferedReader;
import java.io.FileReader;

public class Game {
    private State state;
    // make the file accepts the initial grid have a square in another goal

    public Game(String filePath){
        int height = 0;
        int width = 0;

        try{
            // validate the file TODO: make it a function
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            String line = br.readLine();
            width = line.length();
            height = 1;

            while((line = br.readLine()) != null) {
                height++;

                if(width != line.length()) {
                    throw new Exception("file Lines are not the same length");
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
        CompositeCell[][] cCells = new CompositeCell[height][width];

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            int i = 0;
            while ((line = br.readLine()) != null) {
                for (int j = 0; j < line.length(); j++) {

                    char symbol = line.charAt(j);

                    cCells[i][j] = new CompositeCell(CellFactory.getCell(i,j,symbol));

                    if(Color.getColor(symbol) != null)
                        cCells[i][j].setNode(NodeFactory.getNode(i,j,symbol));
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

}
