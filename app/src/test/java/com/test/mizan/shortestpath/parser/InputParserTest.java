package com.test.mizan.shortestpath.parser;

import com.test.mizan.shortestpath.model.Graph;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertThat;

/**
 * Created by mizanurrahmun on 3/28/17.
 */

public class InputParserTest {
    private InputParser inputParser = new InputParser();

    @Test
    public void graphInput_oneRow_returns_correctGraph() {
        Graph graph = new Graph(singletonList(asList(1, 1, 1, 1, 1, 1)));
        Graph input = inputParser.parseInput("1 1 1 1 1 1");
        assertThat(input, CoreMatchers.is(graph));
    }
    @Test
    public void graphInput_multipleRows_returns_correctGraph() {
        Graph graph = new Graph(asList(
                asList(1,1,1,1,1),
                asList(2,2,2,2,2)
        ));
        Graph input = inputParser.parseInput("1 1 1 1 1\n2 2 2 2 2");
        assertThat(input, CoreMatchers.is(graph));
    }

    @Test(expected = RuntimeException.class)
    public void parseInput_throwsExceptionIfNotAllValuesAreIntegers() {
        inputParser.parseInput("1 1 M 6 7");
    }

    @Test(expected = RuntimeException.class)
    public void parseInput_throwsExceptionIfNotAllRowsAreSameSize() {
        inputParser.parseInput("1 1\n1 1 1");
    }
}
