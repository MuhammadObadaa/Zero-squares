import behaviors.Stateable;
import constants.Color;
import controllers.*;
import models.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException, IOException {

        chooseAndPick();
    }

    public static void chooseAndPick() throws IOException {

        int in = 3;

        while(in != 1 && in != 2){
            System.out.println("Choose between:\n1). Let the AI play\n2). Let me play");
            in = (new Scanner(System.in)).nextInt();
        }

        if(in == 1){
            AIPlay();
        }else{
            HumanPlay(1);
        }
    }

    public static void AIPlay(){
        Game game;
        FullSearchController controller = new FullSearchController();
        ArrayList<Stateable> path;

        long dfsTimes = 0,bfsTimes = 0,dfsRecTimes = 0,ucsTime = 0, aStarTime = 0;

        for (int i = 1; i <= 20; i++) {

            String filepath = "./resources/levels/lv" + String.format("%02d", i) + ".zs";

            game = new Game(filepath);

            System.out.println("\n\n\t\t" + Color.formColor(Color.BLUE, Color.BLACK) + "Level: " + i + Color.resetColorCode() + "\n");
            game.show();

            /// BFS:

            System.out.println("\n\t\t" + Color.formColor(Color.RED, Color.BLACK) + " BFS Approach " + Color.resetColorCode());

            long start = System.nanoTime();
            path = controller.BFSSearch(game.getState());
            long end = System.nanoTime();

            bfsTimes += (end - start);

            System.out.printf("Execution Time:%.6f sec\n",(end - start)/1000000000.0);
            System.out.println("Shortest found path long:" + path.size());

            /// DFS:

            System.out.println("\n\t\t" + Color.formColor(Color.RED, Color.BLACK) + " DFS Approach " + Color.resetColorCode());

            start = System.nanoTime();
            path = controller.DFSSearch(game.getState());
            end = System.nanoTime();

            dfsTimes += (end - start);

            System.out.printf("Execution Time:%.6f sec\n",(end - start)/1000000000.0);
            System.out.println("Found path long:" + path.size());

            /// DFS recursive:

            System.out.println("\n\t\t" + Color.formColor(Color.RED, Color.BLACK) + " DFS Recursive Approach " + Color.resetColorCode());

            start = System.nanoTime();
            path = controller.DFSRecursiveSearch(game.getState());
            end = System.nanoTime();

            dfsRecTimes += (end - start);

            System.out.printf("Execution Time:%.6f sec\n",(end - start)/1000000000.0);
            System.out.println("Found path long:" + path.size());

            /// UCS:

            System.out.println("\n\t\t" + Color.formColor(Color.RED, Color.BLACK) + " UCS Approach " + Color.resetColorCode());

            start = System.nanoTime();
            path = controller.UCSearch(game.getState());
            end = System.nanoTime();

            ucsTime += (end - start);

            System.out.printf("Execution Time:%.6f sec\n",(end - start)/1000000000.0);
            System.out.println("Found path long:" + path.size());

            /// UCS:

            System.out.println("\n\t\t" + Color.formColor(Color.RED, Color.BLACK) + " A* Approach " + Color.resetColorCode());

            start = System.nanoTime();
            path = controller.AStarSearch(game.getState());
            end = System.nanoTime();

            aStarTime += (end - start);

            System.out.printf("Execution Time:%.6f sec\n",(end - start)/1000000000.0);
            System.out.println("Found path long:" + path.size());
        }
        System.out.println("\n\t\t" + Color.formColor(Color.BLUE, Color.BLACK) + "*** Results ***" + Color.resetColorCode());

        System.out.printf("\n\n" + Color.formColor(Color.GREEN, Color.BLACK) + "Total Bfs Time:" + Color.resetColorCode() + " %.6f sec",bfsTimes/1000000000.0);
        System.out.printf("\n\n" + Color.formColor(Color.GREEN, Color.BLACK) + "Total Dfs Time:" + Color.resetColorCode() + " %.6f sec",dfsTimes/1000000000.0);
        System.out.printf("\n\n" + Color.formColor(Color.GREEN, Color.BLACK) + "Total DfsRec Time:" + Color.resetColorCode() + " %.6f sec",dfsRecTimes/1000000000.0);
        System.out.printf("\n\n" + Color.formColor(Color.GREEN, Color.BLACK) + "Total UCS Time:" + Color.resetColorCode() + " %.6f sec",ucsTime/1000000000.0);
        System.out.printf("\n\n" + Color.formColor(Color.GREEN, Color.BLACK) + "Total A* Time:" + Color.resetColorCode() + " %.6f sec",aStarTime/1000000000.0);

        System.out.printf("\n\n" + Color.formColor(Color.GREEN, Color.BLACK) + "Total Time:" + Color.resetColorCode() + " %.6f sec",(dfsTimes + bfsTimes + dfsRecTimes + ucsTime + aStarTime)/1000000000.0);
    }

    public static void HumanPlay(int i){
        Game game = new Game("./resources/levels/lv" + String.format("%02d", i) + ".zs");
        GameController controller = new GameController(game);



        while(!game.finished()){
            game.show();

            try {
                controller.control();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Winner");

        if(i != 20){
            HumanPlay(++i);
        }
    }
}