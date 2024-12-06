package controllers.algorithms;

import behaviors.Stateable;
import controllers.SearchAlgorithm;

import java.util.*;

public class IterativeDepthFirstSearch extends SearchAlgorithm {

    public String getAlgorithmName(){
        return "Iterative Depth First Search";
    }

    public Stateable run(Stateable state) {
        this.visited = new HashSet<>();

        Stateable finiteState = null, current;

        Stack<Stateable> stack = new Stack<>();

        stack.push(state);
        this.visited.add(state);

        while (!stack.isEmpty()) {
            current = stack.pop();

            if (current.finishState()) {
                finiteState = current;
                break;
            }

            for (Stateable nextState : current.nextStates())
                if (!this.visited.contains(nextState)) {
                    this.visited.add(nextState);
                    stack.push(nextState);

                    nextState.setParent(current);
                }
        }

        return finiteState;
    }
}
