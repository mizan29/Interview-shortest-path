package com.test.mizan.shortestpath.model;

import java.util.List;

/**
 * Created by mizanurrahmun on 3/28/17.
 */

public class Output {

    private final boolean completed;
    private final int totalCost;
    private final List<Integer> path;

    public Output(boolean completed, int totalCost, List<Integer> path) {
        this.completed = completed;
        this.totalCost = totalCost;
        this.path = path;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public List<Integer> getPath() {
        return path;
    }
}
