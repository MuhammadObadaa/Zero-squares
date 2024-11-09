package factories;

import models.*;
import constants.Color;

public class CellFactory {

    private static final char NODECHAR = '.';
    private static final char WALLCHAR = '#';
    private static final char BORDERCHAR = ' ';

    public static Cell getCell(int x, int y, char inputChar) throws Exception {
        switch(inputChar){
            case CellFactory.WALLCHAR:
                return new Wall(x,y);
            case CellFactory.BORDERCHAR:
                return new Border(x,y);
            case CellFactory.NODECHAR:
                return new Node(x,y);
            default:
                if(Color.getColor(inputChar) != null)
                    return new Node(x,y);
        }

        throw new Exception("Unknown char in the file");
    }
}
