package models;

public class Square extends Goal implements Movable, Blocker{
    private Color color;

    public Square(int x, int y){
        super(x, y);
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void move(MoveDirection moveDirection) {
        this.x = moveDirection.getNewX(this.x);
        this.y = moveDirection.getNewY(this.y);
    }


    @Override
    public boolean blocks(Movable movable, MoveDirection moveDirection) {
        return this.x == moveDirection.getNewX(movable.getX()) &&
                this.y == moveDirection.getNewY(movable.getY());
    }
}
