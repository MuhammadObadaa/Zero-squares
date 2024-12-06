package controllers;

import behaviors.Stateable;
import models.AlgorithmStatistics;

import java.util.Set;

public abstract class SearchAlgorithm {
    protected Set<Stateable> visited;

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

        long visitedSize = (this.visited == null ? 0:this.visited.size());

        return new AlgorithmStatistics(state,lastState,(lastState != null && lastState.finishState()),executionTime,memoryUsed,visitedSize,this.getAlgorithmName());
    }

    abstract public Stateable run(Stateable state);

    abstract protected String getAlgorithmName();
}
