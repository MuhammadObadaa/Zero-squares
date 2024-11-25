package models;

import behaviors.Positionable;

import java.util.Objects;

public abstract class Positioned implements Positionable {
    int x;
    int y;

    public Positioned(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    @Override
    public boolean equals(Object obj) {
        return this.x == ((Positioned) obj).x && this.y == ((Positioned) obj).y;
    }
}