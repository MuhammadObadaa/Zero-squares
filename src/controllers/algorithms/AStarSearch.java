package controllers.algorithms;

import behaviors.Stateable;
import controllers.SearchAlgorithm;

import java.util.HashSet;
import java.util.PriorityQueue;

public class AStarSearch extends SearchAlgorithm {
    public String getAlgorithmName(){
        return "A Star Search";
    }

    public Stateable run(Stateable state)
    {
        this.visited = new HashSet<Stateable>();

        Stateable finiteState = null, current;

        PriorityQueue<Stateable> priorityQueue = new PriorityQueue<>(
                (o1, o2) -> Integer.compare(o1.getHeuristic() + o1.getCost(), o2.getHeuristic() + o2.getCost())
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
                priorityQueue.add(nextState);

                nextState.setParent(current);
            }
        }

        return finiteState;
    }
}
