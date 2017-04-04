package com.test.mizan.shortestpath.implementation;

import com.test.mizan.shortestpath.model.Coordinate;
import com.test.mizan.shortestpath.model.Graph;
import com.test.mizan.shortestpath.model.PathInput;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by mizanurrahmun on 3/28/17.
 */

public class PathFinderTest {

    PathFinder finder = new PathFinder(50);

    @Test
    public void constructor_setsMaxCost_when_valueIsPassed() {
        assertEquals(50, new PathFinder(50).getMaxCost());
    }

    @Test
    public void findShortestPath_given_1x1_graph_returnsCorrectPath() {
        Graph graph = new Graph(singletonList(singletonList(1)));

        List<PathInput> inputs = singletonList(
                new PathInput(new Coordinate(1, 1), 1)
        );

        assertListsAreEqual(finder.findShortestPath(graph), inputs);
    }


    @Test
    public void findShortestPath_given_5x6_graph_returnsCorrectPath() {
        Graph graph = new Graph(
                asList(
                        asList(3, 4, 1, 2, 8, 6),
                        asList(6, 1, 8, 2, 7, 4),
                        asList(5, 9, 3, 9, 9, 5),
                        asList(8, 4, 1, 3, 2, 6),
                        asList(3, 7, 2, 8, 6, 4)
                )
        );

        List<PathInput> inputs = asList(
                new PathInput(new Coordinate(1, 1), 3),
                new PathInput(new Coordinate(2, 2), 1),
                new PathInput(new Coordinate(3, 3), 3),
                new PathInput(new Coordinate(4, 4), 3),
                new PathInput(new Coordinate(5, 4), 2),
                new PathInput(new Coordinate(6, 5), 4)
        );

        assertListsAreEqual(finder.findShortestPath(graph), inputs);
    }

    @Test
    public void findShortestPath_given_5x6_graph_example_two_returnsCorrectPath() {
        Graph graph = new Graph(
                asList(
                        asList(3, 4, 1, 2, 8, 6),
                        asList(6, 1, 8, 2, 7, 4),
                        asList(5, 9, 3, 9, 9, 5),
                        asList(8, 4, 1, 3, 2, 6),
                        asList(3, 7, 2, 1, 2, 3)
                )
        );

        List<PathInput> inputs = asList(
                new PathInput(new Coordinate(1, 1), 3),
                new PathInput(new Coordinate(2, 2), 1),
                new PathInput(new Coordinate(3, 1), 1),
                new PathInput(new Coordinate(4, 5), 1),
                new PathInput(new Coordinate(5, 5), 2),
                new PathInput(new Coordinate(6, 5), 3)
        );

        assertListsAreEqual(finder.findShortestPath(graph), inputs);
    }


    @Test
    public void findShortestPath_given_3x5_graph_returnsCorrectPath() {
        Graph graph = new Graph(
                asList(
                        asList(19, 10, 19, 10, 19),
                        asList(21, 23, 20, 19, 12),
                        asList(20, 12, 20, 11, 10)
                )
        );

        List<PathInput> inputs = asList(
                new PathInput(new Coordinate(1, 1), 19),
                new PathInput(new Coordinate(2, 1), 10),
                new PathInput(new Coordinate(3, 1), 19)
        );

        assertListsAreEqual(finder.findShortestPath(graph), inputs);
    }

    @Test
    public void findShortestPath_given_one_column_graph_returnsCorrectPath() {
        Graph graph = new Graph(
                asList(
                        asList(5, 8, 5, 3, 5)
                )
        );

        List<PathInput> inputs = asList(
                new PathInput(new Coordinate(1, 1), 5),
                new PathInput(new Coordinate(2, 1), 8),
                new PathInput(new Coordinate(3, 1), 5),
                new PathInput(new Coordinate(4, 1), 3),
                new PathInput(new Coordinate(5, 1), 5)
        );

        assertListsAreEqual(finder.findShortestPath(graph), inputs);
    }

    @Test
    public void findShortestPath_given_one_row_graph_returnsCorrectPath() {
        Graph graph = new Graph(
                asList(
                        asList(5),
                        asList(8),
                        asList(5),
                        asList(3),
                        asList(5)
                )
        );

        List<PathInput> inputs = asList(
                new PathInput(new Coordinate(1, 4), 3)
        );

        assertListsAreEqual(finder.findShortestPath(graph), inputs);
    }

    @Test
    public void findShortestPath_given_2x5_graph_starting_with_more_than_50_value_returnsCorrectPath() {
        Graph graph = new Graph(
                asList(
                        asList(69, 10, 19, 10, 19),
                        asList(51, 23, 20, 19, 12),
                        asList(60, 12, 20, 11, 10)
                )
        );

        List<PathInput> inputs = asList();

        assertListsAreEqual(finder.findShortestPath(graph), inputs);
    }

    @Test
    public void findShortestPath_given_3x4_graph_one_value_more_than_50_returnsCorrectPath() {
        Graph graph = new Graph(
                asList(
                        asList(60, 3, 3, 6),
                        asList(6, 3, 7, 9),
                        asList(5, 6, 8, 3)
                )
        );

        List<PathInput> inputs = asList(
                new PathInput(new Coordinate(1, 3), 5),
                new PathInput(new Coordinate(2, 2), 3),
                new PathInput(new Coordinate(3, 1), 3),
                new PathInput(new Coordinate(4, 3), 3)
        );

        assertListsAreEqual(finder.findShortestPath(graph), inputs);
    }

    @Test
    public void findShortestPath_given_4x4_graph_with_negative_value_returnsCorrectPath() {
        Graph graph = new Graph(
                asList(
                        asList(6, 3, -5, 9),
                        asList(-5, 2, 4, 10),
                        asList(3, -2, 6, 10),
                        asList(6, -1, -2, 10)
                )
        );

        List<PathInput> inputs = asList(
                new PathInput(new Coordinate(1, 2), -5),
                new PathInput(new Coordinate(2, 3), -2),
                new PathInput(new Coordinate(3, 4), -2),
                new PathInput(new Coordinate(4, 1), 9)
        );

        assertListsAreEqual(finder.findShortestPath(graph), inputs);
    }

    @Test
    public void findShortestPath_given_4x2_graph_sample_11_returnsCorrectPath() {
        Graph graph = new Graph(
                asList(
                        asList(51, 51),
                        asList(0, 51),
                        asList(51, 51),
                        asList(5, 5)
                )
        );

        List<PathInput> inputs = asList(
                new PathInput(new Coordinate(1, 4), 5),
                new PathInput(new Coordinate(2, 4), 5)
        );

        assertListsAreEqual(finder.findShortestPath(graph), inputs);
    }

    @Test
    public void findShortestPath_given_4x3_graph_sample_12_returnsCorrectPath() {
        Graph graph = new Graph(
                asList(
                        asList(51, 51, 51),
                        asList(0, 51, 51),
                        asList(51, 51, 51),
                        asList(5, 5, 51)
                )
        );

        List<PathInput> inputs = asList(
                new PathInput(new Coordinate(1, 4), 5),
                new PathInput(new Coordinate(2, 4), 5)
        );

        assertListsAreEqual(finder.findShortestPath(graph), inputs);
    }

    @Test
    public void findShortestPath_given_2x20_graph_sample_13_large_number_column_returnsCorrectPath() {
        Graph graph = new Graph(
                asList(
                        asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
                        asList(2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2)
                )
        );

        List<PathInput> inputs = asList(
                new PathInput(new Coordinate(1, 1), 1),
                new PathInput(new Coordinate(2, 1), 1),
                new PathInput(new Coordinate(3, 1), 1),
                new PathInput(new Coordinate(4, 1), 1),
                new PathInput(new Coordinate(5, 1), 1),
                new PathInput(new Coordinate(6, 1), 1),
                new PathInput(new Coordinate(7, 1), 1),
                new PathInput(new Coordinate(8, 1), 1),
                new PathInput(new Coordinate(9, 1), 1),
                new PathInput(new Coordinate(10, 1), 1),
                new PathInput(new Coordinate(11, 1), 1),
                new PathInput(new Coordinate(12, 1), 1),
                new PathInput(new Coordinate(13, 1), 1),
                new PathInput(new Coordinate(14, 1), 1),
                new PathInput(new Coordinate(15, 1), 1),
                new PathInput(new Coordinate(16, 1), 1),
                new PathInput(new Coordinate(17, 1), 1),
                new PathInput(new Coordinate(18, 1), 1),
                new PathInput(new Coordinate(19, 1), 1),
                new PathInput(new Coordinate(20, 1), 1)
        );

        assertListsAreEqual(finder.findShortestPath(graph), inputs);
    }

    @Test
    public void findShortestPath_given_20x20_graph_large_input_returnsCorrectPath() {
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

        List<PathInput> inputs = asList(
                new PathInput(new Coordinate(1, 1), 1),
                new PathInput(new Coordinate(2, 1), 1),
                new PathInput(new Coordinate(3, 1), 1),
                new PathInput(new Coordinate(4, 1), 1),
                new PathInput(new Coordinate(5, 1), 1),
                new PathInput(new Coordinate(6, 1), 1),
                new PathInput(new Coordinate(7, 1), 1),
                new PathInput(new Coordinate(8, 1), 1),
                new PathInput(new Coordinate(9, 1), 1),
                new PathInput(new Coordinate(10, 1), 1),
                new PathInput(new Coordinate(11, 1), 1),
                new PathInput(new Coordinate(12, 1), 1),
                new PathInput(new Coordinate(13, 1), 1),
                new PathInput(new Coordinate(14, 1), 1),
                new PathInput(new Coordinate(15, 1), 1),
                new PathInput(new Coordinate(16, 1), 1),
                new PathInput(new Coordinate(17, 1), 1),
                new PathInput(new Coordinate(18, 1), 1),
                new PathInput(new Coordinate(19, 1), 1),
                new PathInput(new Coordinate(20, 1), 1)
        );

        assertListsAreEqual(finder.findShortestPath(graph), inputs);
    }

    @Test
    public void findShortestPath_given_1x3_graph_with_incomplete_path_returnsCorrectPath() {
        Graph graph = new Graph(
                asList(
                        asList(51, -5, -5)
                )
        );

        List<PathInput> inputs = asList();

        assertListsAreEqual(finder.findShortestPath(graph), inputs);
    }

    @Test
    public void findShortestPath_given_1x2_graph_with_incomplete_path_returnsCorrectPath() {
        Graph graph = new Graph(
                asList(
                        asList(51, -5, -2)
                )
        );

        List<PathInput> inputs = asList();

        assertListsAreEqual(finder.findShortestPath(graph), inputs);
    }

    private void assertListsAreEqual(List<PathInput> inputs1, List<PathInput> inputs2) {
        assertThat(inputs1.size(), Matchers.is(inputs2.size()));

        for (int i = 0; i < inputs1.size(); i++) {
            assertThat(inputs1.get(i), Matchers.is(inputs2.get(i)));
        }
    }

}
