package com.test.mizan.shortestpath.implementation;

import android.util.Log;

import com.test.mizan.shortestpath.model.Coordinate;
import com.test.mizan.shortestpath.model.Graph;
import com.test.mizan.shortestpath.model.PathInput;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mizanurrahmun on 3/28/17.
 */

public class PathFinder {

    private final int maxCost;


    public PathFinder(int maxCost) {
        this.maxCost = maxCost;
    }

    /*
     This method takes Graph as input and provides shortest path as output
    */
    public List<PathInput> findShortestPath(Graph graph) {
        List<PathInput> bestPath = new ArrayList<>();
        PathInput pathInput;
        int[][] input = graphToArray(graph);
        int[][] output = generateOutputMatrix(input);
        int[] path = generateShortestPath(output);
        for (int i = 0; i < path.length; i++) {
            if (output[path[i]][i] <= maxCost) {
                pathInput = new PathInput(new Coordinate(i + 1, path[i] + 1), input[path[i]][i]);
                bestPath.add(pathInput);
            }else {
                break;
            }
        }

        return bestPath;
    }

    //convert list of list of integer to a two dimensional integer array
    private int[][] graphToArray(Graph graph) {
        int[][] input;
        int row = 0, column = 0;
        List<List<Integer>> grid = graph.getGrid();
        input = new int[grid.size()][grid.get(0).size()];
        for (List<Integer> list : grid) {
            for (Integer value : list) {
                input[row][column] = value;
                column++;
            }
            row++;
            column = 0;
        }
        return input;
    }

    // Building a temporary matrix with minimum cost associated to every point
    // Input works as a two-dimensional array and output data structure is same as input
    private int[][] generateOutputMatrix(int[][] input) {
        int numberOfRow = input.length;
        int numberOfColumn = input[0].length;
        int up, straight = 0, down = 0;
        int[][] output = new int[numberOfRow][numberOfColumn];

        for (int j = 0; j < numberOfRow; j++) {
            output[j][0] = input[j][0];
        }
        for (int column = 1; column < numberOfColumn; column++) {
            for (int row = 0; row < numberOfRow; row++) {
                if (row == 0) {
                    up = output[numberOfRow - 1][column - 1];
                    if (numberOfRow > 1) {
                        straight = output[row][column - 1];
                    }
                    if (numberOfRow > 2) {
                        down = output[row + 1][column - 1];
                    }
                } else if (row == (numberOfRow - 1)) {
                    up = output[0][column - 1];
                    straight = output[row - 1][column - 1];
                    down = output[row][column - 1];
                } else {
                    up = output[row - 1][column - 1];
                    straight = output[row][column - 1];
                    down = output[row + 1][column - 1];
                }

                if (numberOfRow == 1) {
                    output[row][column] = up + input[row][column];
                } else if (numberOfRow > 2) {
                    output[row][column] = minimum(up + input[row][column], straight + input[row][column],
                            down + input[row][column]);
                } else {
                    output[row][column] = minimum(up + input[row][column], straight + input[row][column]);
                }
            }
        }
        return output;
    }

    // Get a array of shortest path from the temporary output two dimensional array
    private int[] generateShortestPath(int[][] output) {
        int straight, down = 0, up = 0;
        int numberOfRow = output.length;
        int numberOfColumn = output[0].length;
        int[] path = new int[numberOfColumn];
        int minRow = 0;
        int minNumber = 0;

        for (int column = numberOfColumn - 1; column >= 0; column--) {
            if (column == numberOfColumn - 1) {
                for (int row = 0; row < numberOfRow; row++) {
                    if (row == 0) {
                        minNumber = output[row][column];
                    } else {
                        minRow = (output[row][column] < minNumber) ? row : minRow;
                        minNumber = (output[row][column] < minNumber) ? output[row][column] : minNumber;
                    }
                }

            } else {
                int tempRow = minRow;
                if (tempRow == 0) {
                    straight = output[minRow][column];
                    if (numberOfRow > 1) {
                        down = output[minRow + 1][column];
                        minRow = (straight < down) ? minRow : minRow + 1;
                        minNumber = (straight < down) ? straight : down;
                    }
                    if (numberOfRow > 2) {
                        up = output[numberOfRow - 1][column];
                        minRow = (up < minNumber) ? numberOfRow - 1 : minRow;
                    }

                } else if (tempRow == (numberOfRow - 1)) {
                    straight = output[minRow][column];
                    down = output[0][column];
                    minRow = (straight < down) ? minRow : 0;
                    minNumber = (straight < down) ? straight : down;

                    if (numberOfRow > 2) {
                        up = output[tempRow - 1][column];
                        minRow = (up < minNumber) ? tempRow - 1 : minRow;
                    }

                } else {
                    straight = output[minRow - 1][column];
                    down = output[minRow][column];
                    up = output[minRow + 1][column];
                    minRow = (straight < down) ? minRow - 1 : minRow;
                    minNumber = (straight < down) ? straight : down;
                    minRow = (up < minNumber) ? tempRow + 1 : minRow;
                }
            }
            path[column] = minRow;
        }
        return path;
    }

    //get minimum values from three integers
    public int minimum(int a, int b, int c) {
        int min;
        min = minimum(a, b);
        min = (min < c) ? min : c;
        return min;
    }

    //overload minimum method to work for two integers
    public int minimum(int a, int b) {
        return (a < b) ? a : b;
    }

    public int getMaxCost() {
        return maxCost;
    }
}
