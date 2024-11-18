package behaviors;

import java.util.ArrayList;

public interface Stateable {
    public ArrayList<Stateable> nextStates();

    public boolean finishState();

    public Stateable getParent();

    public void setParent(Stateable parent);
}
