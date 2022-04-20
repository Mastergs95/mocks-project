package com.skillsoft.mocks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class LinkedListTest {

    @Spy
    LinkedList<String> linkedListSpy=new LinkedList<>();

    AutoCloseable closeable;

    @BeforeEach
    public void setupTests(){
        closeable=openMocks(this);
    }

    @AfterEach
    public void tearDownTests()throws Exception{
        closeable.close();
    }

    @Test
    public void testLinkedList(){
        linkedListSpy.add("Nora");
        linkedListSpy.add("Fred");
        linkedListSpy.add("Maureen");
        linkedListSpy.add("Ophelia");

        verify(linkedListSpy,times(4)).add(anyString());

        assertEquals(4,linkedListSpy.size());

        when(linkedListSpy.remove(anyString())).thenReturn(true);

        linkedListSpy.remove("Nora");
        linkedListSpy.remove("Ophelia");

        verify(linkedListSpy,times(2)).remove(anyString());

        assertEquals(4,linkedListSpy.size());
    }
}
