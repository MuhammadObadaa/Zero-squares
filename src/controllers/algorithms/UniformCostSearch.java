package controllers.algorithms;

import behaviors.Stateable;
import controllers.SearchAlgorithm;
import models.AlgorithmStatistics;

import java.util.*;

public class UniformCostSearch extends SearchAlgorithm {

    private long visitedSetCount;

    public String getAlgorithmName(){
        return "Uniform Cost Search";
    }

    public AlgorithmStatistics trigger(Stateable state){
        long startTime = System.nanoTime();

        Runtime runtime = Runtime.getRuntime();

        // Garbage collect before measuring to minimize interference
        runtime.gc();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        Stateable lastState = this.run(state);

        double executionTime = (System.nanoTime() - startTime)/1000000000.0;

        // Measure memory usage after the function call
        runtime.gc();
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();

        // Calculate the difference
        double memoryUsed = (memoryAfter - memoryBefore)/1024.0;

        return new AlgorithmStatistics(state,lastState,lastState.finishState(),executionTime,memoryUsed,this.visitedSetCount,this.getAlgorithmName());
    }

    public Stateable run(Stateable state)
    {
        Map<Stateable,Integer> visited = new HashMap<>();

        Stateable finiteState = null, current;
        Integer cost;

        PriorityQueue<Stateable> priorityQueue = new PriorityQueue<>(
                (o1, o2) -> Integer.compare(o1.getCost(), o2.getCost())
        );

        priorityQueue.add(state);
        visited.put(state,state.getCost());

        while (!priorityQueue.isEmpty()) {
            current = priorityQueue.poll();

            cost = visited.get(current);
            if(cost != null && cost > current.getCost())
                continue;

            if (current.finishState()) {
                finiteState = current;
                break;
            }

            for (Stateable nextState : current.nextStates()) {
                cost = visited.get(nextState);
                if (cost == null || cost > nextState.getCost()) {
                    priorityQueue.add(nextState);
                    visited.put(nextState, nextState.getCost());

                    nextState.setParent(current);
                }
                if (nextState.finishState()){
                    this.visitedSetCount = visited.size();
                    return nextState;
                }
            }
        }


        this.visitedSetCount = visited.size();

        return finiteState;
    }
}
