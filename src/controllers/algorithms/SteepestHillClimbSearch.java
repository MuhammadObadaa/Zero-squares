package controllers.algorithms;

import behaviors.Stateable;
import controllers.SearchAlgorithm;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class SteepestHillClimbSearch extends SearchAlgorithm {
    public String getAlgorithmName(){
        return "Steepest Hill Climb Search";
    }

    public Stateable run(Stateable state)
    {
        Stateable finiteState = null, current = state,nearestNeighbor;

        ArrayList<Stateable> nextStates;

        while(finiteState == null){
            nextStates = current.nextStates();
            nextStates.sort((o1,o2) -> Integer.compare(o1.getHeuristic(),o2.getHeuristic()));

            try {
                nearestNeighbor = nextStates.getFirst();
            }catch (NoSuchElementException e){
                finiteState = current;
                break;
            }

            nearestNeighbor.setParent(current);

            if(nearestNeighbor.getHeuristic() < current.getHeuristic())
                current = nearestNeighbor;
            else
                finiteState = current;
        }

        return finiteState;
    }
}
