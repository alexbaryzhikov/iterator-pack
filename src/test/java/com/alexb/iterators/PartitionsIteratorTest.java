package com.alexb.iterators;

import com.alexb.iterators.PartitionsIterator.Templates;
import org.junit.Test;

import static org.junit.Assert.*;

public class PartitionsIteratorTest {

    @Test
    public void templates_zero_elements() {
        Templates templates = new Templates(0, 1);
        assertFalse(templates.hasNext());
    }

    @Test
    public void templates_zero_parts() {
        Templates templates = new Templates(1, 0);
        assertFalse(templates.hasNext());
    }

    @Test
    public void templates_parts_more_than_elements() {
        Templates templates = new Templates(1, 2);
        assertFalse(templates.hasNext());
    }

    @Test
    public void templates_single_part() {
        Templates templates = new Templates(5, 1);
        assertTrue(templates.hasNext());
        assertArrayEquals(new int[]{5}, templates.next());
        assertFalse(templates.hasNext());
    }

    @Test
    public void templates_n2_p2() {
        Templates templates = new Templates(2, 2);
        assertTrue(templates.hasNext());
        assertArrayEquals(new int[]{1, 1}, templates.next());
        assertFalse(templates.hasNext());
    }

    @Test
    public void templates_n5_p5() {
        Templates templates = new Templates(5, 5);
        assertTrue(templates.hasNext());
        assertArrayEquals(new int[]{1, 1, 1, 1, 1}, templates.next());
        assertFalse(templates.hasNext());
    }

    @Test
    public void templates_n3_p2() {
        Templates templates = new Templates(3, 2);
        assertTrue(templates.hasNext());
        assertArrayEquals(new int[]{2, 1}, templates.next());
        assertFalse(templates.hasNext());
    }

    @Test
    public void templates_n4_p2() {
        Templates templates = new Templates(4, 2);
        assertTrue(templates.hasNext());
        assertArrayEquals(new int[]{3, 1}, templates.next());
        assertArrayEquals(new int[]{2, 2}, templates.next());
        assertFalse(templates.hasNext());
    }

    @Test
    public void templates_n6_p3() {
        Templates templates = new Templates(6, 3);
        assertTrue(templates.hasNext());
        assertArrayEquals(new int[]{4, 1, 1}, templates.next());
        assertArrayEquals(new int[]{3, 2, 1}, templates.next());
        assertArrayEquals(new int[]{2, 2, 2}, templates.next());
        assertFalse(templates.hasNext());
    }

    @Test
    public void templates_n10_p3() {
        Templates templates = new Templates(10, 3);
        assertTrue(templates.hasNext());
        assertArrayEquals(new int[]{8, 1, 1}, templates.next());
        assertArrayEquals(new int[]{7, 2, 1}, templates.next());
        assertArrayEquals(new int[]{6, 3, 1}, templates.next());
        assertArrayEquals(new int[]{6, 2, 2}, templates.next());
        assertArrayEquals(new int[]{5, 4, 1}, templates.next());
        assertArrayEquals(new int[]{5, 3, 2}, templates.next());
        assertArrayEquals(new int[]{4, 4, 2}, templates.next());
        assertArrayEquals(new int[]{4, 3, 3}, templates.next());
        assertFalse(templates.hasNext());
    }

    @Test
    public void templates_n10_p4() {
        Templates templates = new Templates(10, 4);
        assertTrue(templates.hasNext());
        assertArrayEquals(new int[]{7, 1, 1, 1}, templates.next());
        assertArrayEquals(new int[]{6, 2, 1, 1}, templates.next());
        assertArrayEquals(new int[]{5, 3, 1, 1}, templates.next());
        assertArrayEquals(new int[]{5, 2, 2, 1}, templates.next());
        assertArrayEquals(new int[]{4, 4, 1, 1}, templates.next());
        assertArrayEquals(new int[]{4, 3, 2, 1}, templates.next());
        assertArrayEquals(new int[]{4, 2, 2, 2}, templates.next());
        assertArrayEquals(new int[]{3, 3, 3, 1}, templates.next());
        assertArrayEquals(new int[]{3, 3, 2, 2}, templates.next());
        assertFalse(templates.hasNext());
    }
}