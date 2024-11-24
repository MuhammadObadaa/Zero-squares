package controllers;

import behaviors.Stateable;

import java.util.*;

public class FullSearchController {

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

    public ArrayList<Stateable> BFSSearch(Stateable state) {
        Set<Stateable> visited = new HashSet<>();

        Stateable finiteState = null, current;

        Queue<Stateable> queue = new ArrayDeque<>();

        queue.add(state);
        visited.add(state);

        while (!queue.isEmpty()) {
            current = queue.poll();

            if (current.finishState()) {
                finiteState = current;
                break;
            }

            for (Stateable nextState : current.nextStates())
                if (!visited.contains(nextState)) {
                    visited.add(nextState);
                    queue.add(nextState);

                    nextState.setParent(current);
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
