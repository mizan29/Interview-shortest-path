package com.test.mizan.shortestpath.implementation;

import com.test.mizan.shortestpath.model.Coordinate;
import com.test.mizan.shortestpath.model.Graph;
import com.test.mizan.shortestpath.model.Output;
import com.test.mizan.shortestpath.model.PathInput;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.hamcrest.Matchers.is;

/**
 * Created by mizanurrahmun on 3/28/17.
 */

public class CostManagerTest {

    private PathFinder pathFinder = Mockito.mock(PathFinder.class);
    private CostManager manager = new CostManager(pathFinder);
    private CostManager managerWithMaxCost = new CostManager(new PathFinder(50));


    @Test
    public void findPath_to_pathFinder_and_creates_correctResult_to_right_Path() {
        Graph graph = mock(Graph.class);
        doReturn(5).when(graph).getGraphWidth();

        List<PathInput> path = asList(
                new PathInput(new Coordinate(1, 1), 1),
                new PathInput(new Coordinate(2, 1), 2),
                new PathInput(new Coordinate(3, 1), 3),
                new PathInput(new Coordinate(4, 1), 4),
                new PathInput(new Coordinate(5, 1), 5)
        );

        doReturn(path).when(pathFinder).findShortestPath(graph);

        Output result = manager.result(graph);

        assertTrue(result.isCompleted());
        assertThat(result.getTotalCost(), is(15));
        assertThat(result.getPath(), is(asList(1,1,1,1,1)));
    }

    @Test
    public void findPath_to_False_when_widthOf_Path_not_equal_to_Graph() {
        Graph graph = mock(Graph.class);
        doReturn(5).when(graph).getGraphWidth();

        List<PathInput> path = asList(
                new PathInput(new Coordinate(1, 1), 1),
                new PathInput(new Coordinate(2, 1), 2),
                new PathInput(new Coordinate(3, 1), 3)
        );

        doReturn(path).when(pathFinder).findShortestPath(graph);
        Output result = manager.result(graph);
        assertFalse(result.isCompleted());
    }

    @Test
    public void findPath_given_5x6_graph_example_one_and_creates_correctResult_to_right_Path() {

        Graph graph = new Graph(
                asList(
                        asList(3, 4, 1, 2, 8, 6),
                        asList(6, 1, 8, 2, 7, 4),
                        asList(5, 9, 3, 9, 9, 5),
                        asList(8, 4, 1, 3, 2, 6),
                        asList(3, 7, 2, 8, 6, 4)
                )
        );


        Output result = managerWithMaxCost.result(graph);
        assertTrue(result.isCompleted());
        assertThat(result.getTotalCost(), is(16));
        assertThat(result.getPath(), is(asList(1,2,3,4,4,5)));

    }

    @Test
    public void findPath_given_5x6_graph_example_two_and_creates_correctResult_to_right_Path() {

        Graph graph = new Graph(
                asList(
                        asList(3, 4, 1, 2, 8, 6),
                        asList(6, 1, 8, 2, 7, 4),
                        asList(5, 9, 3, 9, 9, 5),
                        asList(8, 4, 1, 3, 2, 6),
                        asList(3, 7, 2, 1, 2, 3)
                )
        );


        Output result = managerWithMaxCost.result(graph);
        assertTrue(result.isCompleted());
        assertThat(result.getTotalCost(), is(11));
        assertThat(result.getPath(), is(asList(1,2,1,5,5,5)));

    }

    @Test
    public void findPath_given_5x3_graph_example_3_and_creates_correctResult_to_right_Path() {
        Graph graph = new Graph(
                asList(
                        asList(19, 10, 19, 10, 19),
                        asList(21, 23, 20, 19, 12),
                        asList(20, 12, 20, 11, 10)
                )
        );

        Output result = managerWithMaxCost.result(graph);
        assertFalse(result.isCompleted());
        assertThat(result.getTotalCost(), is(48));
        assertThat(result.getPath(), is(asList(1,1,1)));
    }

    @Test
    public void findPath_given_1x5_graph_example_4_and_creates_correctResult_to_right_Path() {
        Graph graph = new Graph(
                asList(
                        asList(5, 8, 5, 3, 5)
                )
        );

        Output result = managerWithMaxCost.result(graph);
        assertTrue(result.isCompleted());
        assertThat(result.getTotalCost(), is(26));
        assertThat(result.getPath(), is(asList(1,1,1,1,1)));
    }

    @Test
    public void findPath_given_5x1_graph_example_5_and_creates_correctResult_to_right_Path() {
        Graph graph = new Graph(
                asList(
                        asList(5),
                        asList(8),
                        asList(5),
                        asList(3),
                        asList(5)
                )
        );

        Output result = managerWithMaxCost.result(graph);
        assertTrue(result.isCompleted());
        assertThat(result.getTotalCost(), is(3));
        assertThat(result.getPath(), is(asList(4)));
    }

    @Test
    public void findPath_given_3x5_graph_example_8_starting_value_over_50_and_creates_correctResult_to_right_Path() {
        Graph graph = new Graph(
                asList(
                        asList(69, 10, 19, 10, 19),
                        asList(51, 23, 20, 19, 12),
                        asList(60, 12, 20, 11, 10)
                )
        );

        Output result = managerWithMaxCost.result(graph);
        assertFalse(result.isCompleted());
        assertThat(result.getTotalCost(), is(0));
        assertThat(result.getPath().isEmpty(), is(true));
    }

    @Test
    public void findPath_given_3x4_graph_example_9_one_value_over_50_and_creates_correctResult_to_right_Path() {
        Graph graph = new Graph(
                asList(
                        asList(60, 3, 3, 6),
                        asList(6, 3, 7, 9),
                        asList(5, 6, 8, 3)
                )
        );

        Output result = managerWithMaxCost.result(graph);
        assertTrue(result.isCompleted());
        assertThat(result.getTotalCost(), is(14));
        assertThat(result.getPath(), is(asList(3,2,1,3)));
    }

    @Test
    public void findPath_given_4x4_graph_example_10_with_negative_value_and_creates_correctResult_to_right_Path() {
        Graph graph = new Graph(
                asList(
                        asList(6, 3, -5, 9),
                        asList(-5, 2, 4, 10),
                        asList(3, -2, 6, 10),
                        asList(6, -1, -2, 10)
                )
        );

        Output result = managerWithMaxCost.result(graph);
        assertTrue(result.isCompleted());
        assertThat(result.getTotalCost(), is(0));
        assertThat(result.getPath(), is(asList(2,3,4,1)));
    }

    @Test
    public void findPath_given_4x2_graph_example_11_and_creates_correctResult_to_right_Path() {
        Graph graph = new Graph(
                asList(
                        asList(51, 51),
                        asList(0, 51),
                        asList(51, 51),
                        asList(5, 5)
                )
        );

        Output result = managerWithMaxCost.result(graph);
        assertTrue(result.isCompleted());
        assertThat(result.getTotalCost(), is(10));
        assertThat(result.getPath(), is(asList(4,4)));
    }

    @Test
    public void findPath_given_4x3_graph_example_12_and_creates_correctResult_to_right_Path() {
        Graph graph = new Graph(
                asList(
                        asList(51, 51, 51),
                        asList(0, 51, 51),
                        asList(51, 51, 51),
                        asList(5, 5, 51)
                )
        );

        Output result = managerWithMaxCost.result(graph);
        assertFalse(result.isCompleted());
        assertThat(result.getTotalCost(), is(10));
        assertThat(result.getPath(), is(asList(4,4)));
    }

    @Test
    public void findPath_given_2x20_graph_example_13_large_number_columns_and_creates_correctResult_to_right_Path() {
        Graph graph = new Graph(
                asList(
                        asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
                        asList(2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2)
                )
        );

        Output result = managerWithMaxCost.result(graph);
        assertTrue(result.isCompleted());
        assertThat(result.getTotalCost(), is(20));
        assertThat(result.getPath(), is(asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)));
    }

    @Test
    public void findPath_given_20x20_graph_and_creates_correctResult_to_right_Path() {
        Graph graph = new Graph(
                asList(
                        asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
                        asList(2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2),
                        asList(3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3),
                        asList(4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4),
                        asList(5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5),
                        asList(6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6),
                        asList(7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7),
                        asList(8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8),
                        asList(9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9),
                        asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10),
                        asList(12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12),
                        asList(13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13),
                        asList(14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14),
                        asList(15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15),
                        asList(16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16),
                        asList(17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17),
                        asList(18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18),
                        asList(19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19),
                        asList(20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20)
                )
        );

        Output result = managerWithMaxCost.result(graph);
        assertTrue(result.isCompleted());
        assertThat(result.getTotalCost(), is(20));
        assertThat(result.getPath(), is(asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)));
    }

    @Test
    public void findPath_given_1x2_with_incomplete_graph_and_creates_correctResult_to_right_Path() {
        Graph graph = new Graph(
                asList(
                        asList(51, -5)
                )
        );

        Output result = managerWithMaxCost.result(graph);
        assertFalse(result.isCompleted());
        assertThat(result.getTotalCost(), is(0));
        assertThat(result.getPath().isEmpty(), is(true));
    }

}
