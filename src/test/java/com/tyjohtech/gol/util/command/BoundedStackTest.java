package com.tyjohtech.gol.util.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoundedStackTest {
    @Test
    void push_overLimit() {
        BoundedStack<String> strings = new BoundedStack<>(2);

        strings.push("one");
        strings.push("two");
        strings.push("three");

        assertEquals(2, strings.size());
        assertEquals(strings.pop(), "three");
        assertEquals(strings.pop(), "two");
    }
}