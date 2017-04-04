package com.test.mizan.shortestpath.model;

import java.util.List;

/**
 * Created by mizanurrahmun on 3/28/17.
 */

public class Graph {
    private final List<List<Integer>> grid;

    public Graph(List<List<Integer>> grid) {
        this.grid = grid;
    }

    public Coordinate getRightNode(Coordinate coordinate) {
        if (coordinate.getX() + 1 > grid.get(coordinate.getY() - 1).size())
            return null;
        return new Coordinate(coordinate.getX() + 1, coordinate.getY());
    }

    public Coordinate getDiagonalUpNode(Coordinate coordinate) {
        Coordinate right = getRightNode(coordinate);
        if (right == null)
            return null;
        int y = coordinate.getY() - 1;
        return new Coordinate(right.getX(), y == 0 ? grid.size() : y);
    }

    public Coordinate getDiagonalDownNode(Coordinate coordinate) {
        Coordinate right = getRightNode(coordinate);
        if (right == null)
            return null;
        int y = coordinate.getY() + 1;
        return new Coordinate(right.getX(), y > grid.size() ? 1 : y);
    }

    public int getGraphWidth() {
        return grid.get(0).size();
    }

    public int getGraphHeight() {
        return grid.size();
    }

    public int getCost(Coordinate coordinate) {
        return grid.get(coordinate.getY() - 1).get(coordinate.getX() - 1);
    }

    public List<List<Integer>> getGrid() {
        return grid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Graph graph = (Graph) o;

        return grid != null ? grid.equals(graph.grid) : graph.grid == null;

    }

    @Override
    public int hashCode() {
        return grid != null ? grid.hashCode() : 0;
    }
}
