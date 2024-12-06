package behaviors;

import constants.MoveDirection;

public interface Movable extends Positionable {

    void move(MoveDirection moveDirection);

}
