package controllers.algorithms;

import behaviors.Stateable;
import controllers.SearchAlgorithm;

import java.util.HashSet;

public class RecursiveDepthFirstSearch extends SearchAlgorithm {

    public String getAlgorithmName(){
        return "Recursive Depth First Search";
    }

    public Stateable run(Stateable state) {
        this.visited = new HashSet<>();
        this.visited.add(state);

        return RecursiveDFSearch(state);
    }

    private Stateable RecursiveDFSearch(Stateable state){
        if(state.finishState())
            return state;

        Stateable res = null;

        for (Stateable nextState : state.nextStates()) {
            if(this.visited.contains(nextState))
                continue;

            this.visited.add(nextState);
            nextState.setParent(state);

            if((res = RecursiveDFSearch(nextState)) != null)
                return res;
        }

        return res;
    }
}
