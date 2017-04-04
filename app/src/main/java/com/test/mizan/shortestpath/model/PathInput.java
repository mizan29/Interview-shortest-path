package com.test.mizan.shortestpath.model;

/**
 * Created by mizanurrahmun on 3/28/17.
 */

public class PathInput {
    private final Coordinate coordinate;
    private final Integer value;

    public PathInput(Coordinate coordinate, Integer value) {
        this.coordinate = coordinate;
        this.value = value;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PathInput pathInput = (PathInput) o;

        if (coordinate != null ? !coordinate.equals(pathInput.coordinate) : pathInput.coordinate != null)
            return false;
        return value != null ? value.equals(pathInput.value) : pathInput.value == null;

    }

    @Override
    public int hashCode() {
        int result = coordinate != null ? coordinate.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PathInput{" +
                "coordinate=" + coordinate +
                ", value=" + value +
                '}';
    }
}
