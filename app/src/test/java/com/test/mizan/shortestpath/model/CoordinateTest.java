package com.test.mizan.shortestpath.model;

import com.test.mizan.shortestpath.model.Coordinate;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mizanurrahmun on 3/28/17.
 */

public class CoordinateTest {

    @Test
    public void equals_returnsTrue_when_coordinatesAreEqual() {
        assertThat(new Coordinate(1,1), CoreMatchers.is(new Coordinate(1,1)));
    }

    @Test
    public void equals_returnsFalse_when_coordinatesAreNotEqual() {
        assertThat(new Coordinate(1,1), CoreMatchers.not(CoreMatchers.is(new Coordinate(1,2))));
    }
}
