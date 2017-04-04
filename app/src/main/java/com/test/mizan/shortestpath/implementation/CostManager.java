package com.test.mizan.shortestpath.implementation;

import com.test.mizan.shortestpath.model.Graph;
import com.test.mizan.shortestpath.model.Output;
import com.test.mizan.shortestpath.model.PathInput;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mizanurrahmun on 3/28/17.
 */

public class CostManager {

    private final PathFinder pathFinder;

    public CostManager(PathFinder pathFinder) {
        this.pathFinder = pathFinder;
    }

    // return output result
    public Output result(Graph graph) {
        List<PathInput> path = pathFinder.findShortestPath(graph);
        return new Output(path.size() == graph.getGraphWidth(), totalCost(path), pathIndexesInRow(path));
    }

    // Total cost from an Input Path
    private int totalCost(List<PathInput> paths) {
        int total = 0;
        for (PathInput path : paths) {
            total += path.getValue();
        }
        return total;
    }

    // return list of row indexes from a paths
    private List<Integer> pathIndexesInRow(List<PathInput> path) {
        List<Integer> indexes = new ArrayList<>();
        for (PathInput input : path) {
            indexes.add(input.getCoordinate().getY());
        }
        return indexes;
    }

}
