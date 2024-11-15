package controllers;

import constants.MoveDirection;
import models.Game;

import java.io.IOException;

public class GameController {
    public Game game;

    public GameController(Game game) {
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
