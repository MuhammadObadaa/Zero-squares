package controllers;

import behaviors.Stateable;

import java.util.*;

public class FullSearchController {

    public ArrayList<Stateable> UCSearch(Stateable state){
        Set<Stateable> visited = new HashSet<>();

        Stateable finiteState = null, current;

        PriorityQueue<Stateable> priorityQueue = new PriorityQueue<>(
                (o1, o2) -> Integer.compare(o1.getCost(), o2.getCost())
        );

        priorityQueue.add(state);
        visited.add(state);

        while (!priorityQueue.isEmpty()) {
            current = priorityQueue.poll();

            visited.add(current);

            if (current.finishState()) {
                finiteState = current;
                break;
            }

            for (Stateable nextState : current.nextStates()) {
                if (!visited.contains(nextState)) {
                    priorityQueue.add(nextState);
                    visited.add(nextState);

                    nextState.setParent(current);
                }
                if (nextState.finishState()){
                    return getPath(nextState);
                }
            }
        }

        return getPath(finiteState);
    }

    public ArrayList<Stateable> DFSSearch(Stateable state) {
        Set<Stateable> visited = new HashSet<>();

        Stateable finiteState = null, current;

        Stack<Stateable> stack = new Stack<>();

        stack.push(state);
        visited.add(state);

        while (!stack.isEmpty()) {
            current = stack.pop();

            if (current.finishState()) {
                finiteState = current;
                break;
            }

            for (Stateable nextState : current.nextStates())
                if (!visited.contains(nextState)) {
                    visited.add(nextState);
                    stack.push(nextState);

                    nextState.setParent(current);
                }
        }

        return getPath(finiteState);
    }

    public ArrayList<Stateable> DFSRecursiveSearch(Stateable state){
        Set<Stateable> visited = new HashSet<>();
        visited.add(state);

        Stateable stateable = DFSRecursiveSearch(state,visited);

        return getPath(stateable);
    }

    private Stateable DFSRecursiveSearch(Stateable state,Set<Stateable> visited){
        if(state.finishState())
            return state;

        Stateable res = null;

        for (Stateable nextState : state.nextStates()) {
            if(visited.contains(nextState))
                continue;

            visited.add(nextState);
            nextState.setParent(state);

            if((res = DFSRecursiveSearch(nextState,visited)) != null)
                return res;
        }

        return res;
    }

    public ArrayList<Stateable> BFSSearch(Stateable state) {
        Set<Stateable> visited = new HashSet<>();

        Stateable finiteState = null, current;

        Queue<Stateable> queue = new ArrayDeque<>();

        queue.add(state);
        //visited.add(state);

        while (!queue.isEmpty()) {
            current = queue.poll();

            if(visited.contains(current))
                continue;

            visited.add(current);

            if (current.finishState()) {
                finiteState = current;
                break;
            }

            for (Stateable nextState : current.nextStates()) {
                if (!visited.contains(nextState)) {
                    queue.add(nextState);

                    nextState.setParent(current);
                }
                if (nextState.finishState()){
                    return getPath(nextState);
                }
            }
        }

        return getPath(finiteState);
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
}
