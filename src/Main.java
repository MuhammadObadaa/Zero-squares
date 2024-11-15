import controllers.FullSearchController;
import controllers.GameController;
import models.*;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {

        Game game = new Game("C:/lv2.zs");

        GameController stateController = new GameController(game);

        FullSearchController f = new FullSearchController(game.getState());
        int i = 0;
        ArrayList<State> s = f.DFSSearch();
        System.out.println(s.size());

        for (State state : s) {
            System.out.println(++i);
            state.print();
        }

        game.show();

        while(!game.finished()){
            try {
                stateController.control();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            game.show();
        }

        System.out.println("Winner");
    }
}