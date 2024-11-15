package controllers;

import models.State;

import java.util.*;

public class FullSearchController {
    State state;

    public FullSearchController(State state) {
        this.state = state;
    }

    public ArrayList<State> DFSSearch() {
        ArrayList<State> visited = new ArrayList<>(), path = new ArrayList<>();

        HashMap<State, State> parent = new HashMap<>();

        State emptyState = null, current;

        Stack<State> stack = new Stack<>();

        stack.push(this.state);
        visited.add(this.state);

        while (!stack.isEmpty()) {
            current = stack.pop();

            if (current.empty()) {
                emptyState = current;
                break;
            }

            for (State nextState : current.nextStates())
                if (!visited.contains(nextState)) {
                    visited.add(nextState);
                    stack.push(nextState);

                    parent.put(nextState, current);
                }
        }

        if (emptyState == null) {
            return path;
        }

        path.add(emptyState);
        current = emptyState;

        while (parent.containsKey(current)) {
            current = parent.get(current);
            path.add(current);
        }

        Collections.reverse(path);

        return path;
    }
}
