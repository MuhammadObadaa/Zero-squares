package behaviors;

import java.util.ArrayList;

public interface Stateable {
    ArrayList<Stateable> nextStates();

    boolean finishState();

    Stateable getParent();

    void setParent(Stateable parent);
}
