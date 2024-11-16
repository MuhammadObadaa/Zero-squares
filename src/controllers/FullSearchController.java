package controllers;

import models.State;

import java.util.*;

public class FullSearchController {
    State state;

    public FullSearchController(State state) {
        this.state = state;
    }

    public ArrayList<State> DFSSearch() {
        ArrayList<State> visited = new ArrayList<>();
        ArrayList<State> path = new ArrayList<>();

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

                    nextState.setParent(current);
                }
        }

        if (emptyState == null) {
            return path;
        }

        path.add(emptyState);
        current = emptyState.getParent();

        while (current!=null) {
            path.add(current);
            current = current.getParent();
        }

        Collections.reverse(path);

        return path;
    }

    public ArrayList<State> BFSSearch() {
        ArrayList<State> visited = new ArrayList<>();
        ArrayList<State> path = new ArrayList<>();

        State emptyState = null, current;

        Queue<State> queue = new ArrayDeque<>();

        queue.add(this.state);
        visited.add(this.state);

        while (!queue.isEmpty()) {
            current = queue.poll();

            if (current.empty()) {
                emptyState = current;
                break;
            }

            for (State nextState : current.nextStates())
                if (!visited.contains(nextState)) {
                    visited.add(nextState);
                    queue.add(nextState);

                    nextState.setParent(current);
                }
        }

        if (emptyState == null) {
            return path;
        }

        path.add(emptyState);
        current = emptyState.getParent();

        while (current!=null) {
            path.add(current);
            current = current.getParent();
        }

        Collections.reverse(path);

        return path;
    }
}
