package constants;

import models.Positioned;

import java.util.Comparator;
import java.util.function.Function;

public enum MoveDirection {
    UP(new Comparator<Positioned>() {
        @Override
        public int compare(Positioned Positioned1, Positioned Positioned2) {
            return Integer.compare(Positioned1.getX(),Positioned2.getX());
        }
    },x -> {
        return x-=1;
    },y -> {
        return y;
    }),
    DOWN(new Comparator<Positioned>() {
        @Override
        public int compare(Positioned Positioned1, Positioned Positioned2) {
            return Integer.compare(Positioned2.getX(),Positioned1.getX());
        }
    },x -> {
        return x+=1;
    },y -> {
        return y;
    }),
    LEFT(new Comparator<Positioned>() {
        @Override
        public int compare(Positioned Positioned1, Positioned Positioned2) {
            return Integer.compare(Positioned1.getY(),Positioned2.getY());
        }
    },x -> {
        return x;
    },y -> {
        return y-=1;
    }),
    RIGHT(new Comparator<Positioned>() {
        @Override
        public int compare(Positioned Positioned1, Positioned Positioned2) {
            return Integer.compare(Positioned2.getY(),Positioned1.getY());
        }
    },x -> {
        return x;
    },y -> {
        return y+=1;
    });

    private final Comparator<Positioned> comp;
    private Function <Integer, Integer> newX;
    private Function <Integer, Integer> newY;

    private MoveDirection(Comparator<Positioned> comp,Function<Integer, Integer> newX,Function<Integer, Integer> newY) {
        this.comp = comp;
        this.newX = newX;
        this.newY = newY;
    }

    public Comparator<Positioned> getComparator() {
        return comp;
    }

    public int getNewX(int x) {
        return newX.apply(x);
    }

    public int getNewY(int y) {
        return newY.apply(y);
    }
}
