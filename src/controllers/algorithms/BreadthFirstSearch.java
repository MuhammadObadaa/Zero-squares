package controllers.algorithms;

import behaviors.Stateable;
import controllers.SearchAlgorithm;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;

public class BreadthFirstSearch extends SearchAlgorithm {

    public String getAlgorithmName(){
        return "Breadth First Search";
    }

    public Stateable run(Stateable state)
    {
        this.visited = new HashSet<>();

        Stateable finiteState = null, current;

        Queue<Stateable> queue = new ArrayDeque<>();

        queue.add(state);

        while (!queue.isEmpty()) {
            current = queue.poll();

            if(this.visited.contains(current))
                continue;

            this.visited.add(current);

            if (current.finishState()) {
                finiteState = current;
                break;
            }

            for (Stateable nextState : current.nextStates()) {
                if (!this.visited.contains(nextState)) {
                    queue.add(nextState);

                    nextState.setParent(current);
                }
                if (nextState.finishState()){
                    return nextState;
                }
            }
        }

        return finiteState;
    }
}
