package com.skillsoft.mocks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;


public class ArrayListMock {

    @Test
    public void mockArrayList(){

        ArrayList someArrayList = mock(ArrayList.class);

        when(someArrayList.get(0)).thenReturn("Alice");
        when(someArrayList.get(1)).thenReturn("Bob");

        assertEquals("Alice",someArrayList.get(0));
        assertEquals("Bob",someArrayList.get(1));

        when(someArrayList.indexOf("Alice")).thenReturn(100);
        when(someArrayList.indexOf("Bob")).thenReturn(101);

        assertEquals(100, someArrayList.indexOf("Alice"));
        assertEquals(101, someArrayList.indexOf("Bob"));

        when(someArrayList.subList(100,101)).thenReturn(Arrays.asList("Alice","Bob"));
        assertLinesMatch(Arrays.asList("Alice","Bob"),someArrayList.subList(100,101));
    }
}
