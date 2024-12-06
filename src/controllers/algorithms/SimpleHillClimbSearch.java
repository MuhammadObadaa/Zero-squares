package controllers.algorithms;

import behaviors.Stateable;
import controllers.SearchAlgorithm;

import java.util.HashSet;

public class SimpleHillClimbSearch extends SearchAlgorithm {
    public String getAlgorithmName(){
        return "Simple Hill Climb Search";
    }

    public Stateable run(Stateable state)
    {
        visited = new HashSet<>();
        Stateable finiteState = null, current = state,nearestNeighbor = state;

        visited.add(state);

        while(finiteState == null){

            for (Stateable stateable : current.nextStates())
                if(!this.visited.contains(stateable) && current.getHeuristic() > stateable.getHeuristic()) {
                    this.visited.add(stateable);
                    stateable.setParent(current);
                    nearestNeighbor = stateable;
                    break;
            }

            if(nearestNeighbor == current)
                finiteState = current;
            else
                current = nearestNeighbor;
        }

        return finiteState;
    }
}
