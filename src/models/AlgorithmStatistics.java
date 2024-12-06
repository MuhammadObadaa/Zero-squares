package models;

import behaviors.Stateable;
import constants.Color;

import java.util.ArrayList;
import java.util.Collections;

public class AlgorithmStatistics {

    Stateable firstState;
    Stateable lastState;

    boolean solutionFound;

    double executionTime;
    double executionMemory;

    long foundPathLong;
    long visitedSetSize;

    String algorithmName;

    public AlgorithmStatistics(Stateable firstState, Stateable lastState, boolean solutionFound, double executionTime, double executionMemory, long visitedSetSize, String algorithmName) {
        this.firstState = firstState;
        this.lastState = lastState;
        this.solutionFound = solutionFound;
        this.executionTime = executionTime;
        this.executionMemory = executionMemory;
        this.visitedSetSize = visitedSetSize;
        this.algorithmName = algorithmName;

        this.foundPathLong = getPath(lastState).size();
    }

    private ArrayList<Stateable> getPath(Stateable state){
        ArrayList<Stateable> path = new ArrayList<>();

        if(state == null)
            return path;

        path.add(state);

        Stateable current = state.getParent();

        while (current!=null) {
            path.add(current);
            current = current.getParent();
        }

        Collections.reverse(path);

        return path;
    }

    @Override
    public String toString() {
        String status = this.solutionFound ? Color.formColorString(Color.GREEN,null,"Solved"):Color.formColorString(Color.RED,null,"No Solution Found");

        return String.format("\n\n<<<<< %s By : %s >>>>>",status,Color.formColorString(Color.BLUE,null,this.algorithmName))+ "\n\n" +
                Color.formColorString(Color.GREEN, null, String.format("Visited set size = %d",this.visitedSetSize)) + "\n" +
                Color.formColorString(Color.GREEN, null, String.format("Path length = %d",this.foundPathLong)) + "\n" +
                Color.formColorString(Color.GREEN, null, String.format("Execution time = %.6f Sec",this.executionTime)) + "\n" +
                Color.formColorString(Color.GREEN, null, String.format("Execution memory = %.2f KB",this.executionMemory));
    }

    public String toStringLog(){
        String status = this.solutionFound ? "Solved":"No Solution Found";

        return String.format("\n\n<<<<< %s By : %s >>>>>",status,this.algorithmName)+ "\n\n" +
                String.format("Visited set size = %d",this.visitedSetSize) + "\n" +
                String.format("Path length = %d",this.foundPathLong) + "\n" +
                String.format("Execution time = %.6f Sec",this.executionTime) + "\n" +
                String.format("Execution memory = %.2f KB",this.executionMemory) + "\n";
    }
}
