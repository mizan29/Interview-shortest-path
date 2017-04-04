package com.test.mizan.shortestpath.parser;

import com.test.mizan.shortestpath.model.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mizanurrahmun on 3/28/17.
 */

public class InputParser {
    public Graph parseInput(String input) {

        List<String> rows = Arrays.asList(input.split("\n"));
        List<List<Integer>> grid = new ArrayList<>();

        int rowSize = 0;
        for (String row : rows) {
            List<String> stringRowsList = Arrays.asList(row.split(" "));
            if (rowSize == 0)
                rowSize = stringRowsList.size();
            if (stringRowsList.size() != rowSize) {
                throw new RuntimeException("All rows require same number of Integers.");
            }

            List<Integer> integerRowsList = new ArrayList<>();
            for (String value : stringRowsList) {
                try {
                    integerRowsList.add(Integer.parseInt(value));
                } catch (Exception e) {
                    throw new RuntimeException("Invalid matrix");
                }
            }
            grid.add(integerRowsList);
        }

        return new Graph(grid);
    }
}
