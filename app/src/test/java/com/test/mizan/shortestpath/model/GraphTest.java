package com.test.mizan.shortestpath.model;


import org.hamcrest.Matchers;
import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Created by mizanurrahmun on 3/28/17.
 */

public class GraphTest {

    @Test
    public void getRightNode_returnsStraightRightValue() {
        Coordinate result = new Graph(singletonList(asList(1, 2))).getRightNode(new Coordinate(1, 1));

        assertThat(result, Matchers.is(new Coordinate(2, 1)));
    }

    @Test
    public void getRightNode_returnsNullWhenAtEndOfRow() {
        Coordinate coordinate = new Graph(singletonList(asList(2, 2))).getRightNode(new Coordinate(2, 1));

        assertNull(coordinate);
    }

    @Test
    public void getDiagonalUpNode_returnsUpRightCoordinate() {
        Coordinate result =
                new Graph(asList(
                        asList(1, 1, 2),
                        asList(1, 1, 1)
                )).getDiagonalUpNode(new Coordinate(2, 2));

        assertThat(result, Matchers.is(new Coordinate(3, 1)));
    }

    @Test
    public void getDiagonalDownNode_returnsDownRightCoordinate() {
        Coordinate result =
                new Graph(asList(
                        asList(1, 1, 2),
                        asList(1, 1, 1)
                )).getDiagonalDownNode(new Coordinate(2, 1));

        assertThat(result, Matchers.is(new Coordinate(3, 2)));
    }

    @Test
    public void getCost_returns_theCorrectValue() {
        Graph graph = new Graph(asList(
                asList(1, 4, 7),
                asList(3, 5, 8),
                asList(3, 6, 9)
        ));

        assertThat(graph.getCost(new Coordinate(1, 1)), Matchers.is(1));
        assertThat(graph.getCost(new Coordinate(2, 1)), Matchers.is(4));
        assertThat(graph.getCost(new Coordinate(3, 1)), Matchers.is(7));
        assertThat(graph.getCost(new Coordinate(1, 2)), Matchers.is(3));
        assertThat(graph.getCost(new Coordinate(2, 2)), Matchers.is(5));
        assertThat(graph.getCost(new Coordinate(3, 2)), Matchers.is(8));
        assertThat(graph.getCost(new Coordinate(1, 3)), Matchers.is(3));
        assertThat(graph.getCost(new Coordinate(2, 3)), Matchers.is(6));
        assertThat(graph.getCost(new Coordinate(3, 3)), Matchers.is(9));
    }

    @Test
    public void getHeight_returns_theCorrectValue() {
        int height = new Graph(asList(
                asList(1, 2),
                asList(3, 3),
                asList(5, 7)
        )).getGraphHeight();

        assertThat(height, Matchers.is(3));
    }

    @Test
    public void getWidth_returns_theCorrectValue() {
        int width = new Graph(asList(
                asList(1, 2)
        )).getGraphWidth();

        assertThat(width, Matchers.is(2));
    }
}
