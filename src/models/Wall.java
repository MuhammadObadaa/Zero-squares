package models;

public class Wall extends Cell implements Blocker {

    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean blocks(Movable movable, MoveDirection moveDirection) {
        return this.x == moveDirection.getNewX(movable.getX()) &&
                this.y == moveDirection.getNewY(movable.getY());
    }

}