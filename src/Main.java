import constants.Color;
import controllers.*;
import controllers.algorithms.*;
import models.*;
import views.Console;
import views.Log;

import java.io.IOException;
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
            HumanPlay(0);
        }
    }

    public static void AIPlay(){
        Game game;
        SearchAlgorithm searchAlgorithm;
        AlgorithmStatistics algorithmStatistics;

        long dfsTimes = 0,bfsTimes = 0,dfsRecTimes = 0,ucsTime = 0, aStarTime = 0,hillClimbingTime = 0;

        Log.out("",false);

        for (int i = 0; i <= 20; i++) {

            String filepath = "./resources/levels/lv" + String.format("%02d", i) + ".zs";

            game = new Game(filepath);

            Console.out("\n\n\t\t" + Color.formColorString(Color.BLUE, Color.BLACK, "Level: " + i ) + "\n");
            game.show();
            Log.out("\n\nLevel: " + i);

            /// BFS:

            searchAlgorithm = new BreadthFirstSearch();
            algorithmStatistics = searchAlgorithm.trigger(game.getState());

            Console.out(algorithmStatistics.toString());
            Log.out(algorithmStatistics.toStringLog());

            /// DFS:

            searchAlgorithm = new IterativeDepthFirstSearch();
            algorithmStatistics = searchAlgorithm.trigger(game.getState());

            Console.out(algorithmStatistics.toString());
            Log.out(algorithmStatistics.toStringLog());

            /// DFS recursive:

            searchAlgorithm = new RecursiveDepthFirstSearch();
            algorithmStatistics = searchAlgorithm.trigger(game.getState());

            Console.out(algorithmStatistics.toString());
            Log.out(algorithmStatistics.toStringLog());

            /// UCS:

            searchAlgorithm = new UniformCostSearch();
            algorithmStatistics = searchAlgorithm.trigger(game.getState());

            Console.out(algorithmStatistics.toString());
            Log.out(algorithmStatistics.toStringLog());

            /// A*:

            searchAlgorithm = new AStarSearch();
            algorithmStatistics = searchAlgorithm.trigger(game.getState());

            Console.out(algorithmStatistics.toString());
            Log.out(algorithmStatistics.toStringLog());

            /// A*:

            searchAlgorithm = new AdvancedAStarSearch();
            algorithmStatistics = searchAlgorithm.trigger(game.getState());

            Console.out(algorithmStatistics.toString());
            Log.out(algorithmStatistics.toStringLog());

            /// Steepest Hill climbing:

            searchAlgorithm = new SteepestHillClimbSearch();
            algorithmStatistics = searchAlgorithm.trigger(game.getState());

            Console.out(algorithmStatistics.toString());
            Log.out(algorithmStatistics.toStringLog());

            /// Simple Hill climbing:

            searchAlgorithm = new SimpleHillClimbSearch();
            algorithmStatistics = searchAlgorithm.trigger(game.getState());

            Console.out(algorithmStatistics.toString());
            Log.out(algorithmStatistics.toStringLog());
        }
//        System.out.println("\n\t\t" + Color.formColor(Color.BLUE, Color.BLACK) + "*** Results ***" + Color.resetColorCode());
//
//        System.out.printf("\n\n" + Color.formColor(Color.GREEN, Color.BLACK) + "Total Bfs Time:" + Color.resetColorCode() + " %.6f sec",bfsTimes/1000000000.0);
//        System.out.printf("\n\n" + Color.formColor(Color.GREEN, Color.BLACK) + "Total Dfs Time:" + Color.resetColorCode() + " %.6f sec",dfsTimes/1000000000.0);
//        System.out.printf("\n\n" + Color.formColor(Color.GREEN, Color.BLACK) + "Total DfsRec Time:" + Color.resetColorCode() + " %.6f sec",dfsRecTimes/1000000000.0);
//        System.out.printf("\n\n" + Color.formColor(Color.GREEN, Color.BLACK) + "Total UCS Time:" + Color.resetColorCode() + " %.6f sec",ucsTime/1000000000.0);
//        System.out.printf("\n\n" + Color.formColor(Color.GREEN, Color.BLACK) + "Total A* Time:" + Color.resetColorCode() + " %.6f sec",aStarTime/1000000000.0);
//        System.out.printf("\n\n" + Color.formColor(Color.GREEN, Color.BLACK) + "Total Hill Climbing Time:" + Color.resetColorCode() + " %.6f sec",hillClimbingTime/1000000000.0);
//
//        System.out.printf("\n\n" + Color.formColor(Color.GREEN, Color.BLACK) + "Total Time:" + Color.resetColorCode() + " %.6f sec",(dfsTimes + bfsTimes + dfsRecTimes + ucsTime + aStarTime + hillClimbingTime)/1000000000.0);
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