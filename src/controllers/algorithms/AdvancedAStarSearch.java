package controllers.algorithms;

import behaviors.Stateable;
import controllers.SearchAlgorithm;
import models.State;

import java.util.HashSet;
import java.util.PriorityQueue;

public class AdvancedAStarSearch extends SearchAlgorithm {
    public String getAlgorithmName(){
        return "Advanced A Star Search";
    }

    public Stateable run(Stateable state)
    {
        this.visited = new HashSet<>();

        Stateable finiteState = null, current;

        PriorityQueue<Stateable> priorityQueue = new PriorityQueue<>(
                (o1, o2) -> Integer.compare(o1.getAdvancedHeuristic(), o2.getAdvancedHeuristic())
        );

        priorityQueue.add(state);

        while (!priorityQueue.isEmpty()) {
            current = priorityQueue.poll();

            if(this.visited.contains(current))
                continue;
            else
                this.visited.add(current);

            this.visited.add(current);

            if (current.finishState()) {
                finiteState = current;
                break;
            }

            for (Stateable nextState : current.nextStates()) {
                if(!((State)nextState).eachSquareHasGoal())
                    continue;

                priorityQueue.add(nextState);

                nextState.setParent(current);
            }
        }

        return finiteState;
    }
}
