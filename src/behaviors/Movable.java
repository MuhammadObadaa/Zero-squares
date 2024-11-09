package behaviors;

import constants.MoveDirection;

public interface Movable extends Positionable {

    public void move(MoveDirection moveDirection);

}
