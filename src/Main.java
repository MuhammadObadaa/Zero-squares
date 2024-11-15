import constants.MoveDirection;
import controllers.StateController;
import models.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {

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