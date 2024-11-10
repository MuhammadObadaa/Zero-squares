import constants.Color;
import constants.MoveDirection;
import controllers.StateController;
import models.Cell;
import models.CompositeCell;
import models.Game;
import models.Square;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {

        Square a = new Square(1,1,Color.BLACK);
        Cell b = a.clone();


        Game game = new Game("C:/lv2.zs");

        StateController stateController = new StateController(game);

        game.show();

        while(true){
            try {
                stateController.control();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            game.show();
        }
    }
}