package controllers;

import constants.MoveDirection;
import models.Game;
import models.State;

import java.io.IOException;

public class StateController {
    public Game game;

    public StateController(Game game) {
        this.game = game;
    }

    public void control() throws IOException {
        char in = (char)System.in.read();
        System.in.read();

        MoveDirection moveDirection;

        switch (in) {
            case 'a':
                moveDirection = MoveDirection.LEFT;
                break;
            case 'd':
                moveDirection = MoveDirection.RIGHT;
                break;
            case 'w':
                moveDirection = MoveDirection.UP;
                break;
            case 's':
                moveDirection = MoveDirection.DOWN;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + in);
        }

        this.game.move(moveDirection);
    }
}
