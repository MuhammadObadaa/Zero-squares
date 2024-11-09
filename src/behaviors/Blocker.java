package behaviors;

import constants.MoveDirection;

public interface Blocker extends Positionable {
    public boolean blocks(Movable movable, MoveDirection moveDirection);
}
