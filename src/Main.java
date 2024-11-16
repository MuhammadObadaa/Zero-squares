import controllers.*;
import models.*;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {

        Game game = new Game("./resources/levels/lv05.zs");

        GameController stateController = new GameController(game);

        FullSearchController f = new FullSearchController(game.getState());
        int i = 0;
        long start = System.currentTimeMillis();
        ArrayList<State> s = f.BFSSearch();
        long end = System.currentTimeMillis();

        System.out.println((end - start)/1000.0);

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