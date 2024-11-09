package factories;

import constants.Color;
import models.Goal;
import models.Node;
import models.Square;

public class NodeFactory {

    public static Node getNode(int x, int y, char inputChar){
        if(NodeFactory.isSquare(inputChar)) {
            return new Square(x, y, Color.getColor(inputChar));
        }

        return new Goal(x, y, Color.getColor(inputChar));

    }

    public static boolean isSquare(char inputChar){
        return Character.isLowerCase(inputChar);
    }
//
//    public static boolean isGoal(char inputChar){
//        return Character.isUpperCase(inputChar);
//    }
}
