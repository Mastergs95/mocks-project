package com.skillsoft.mocks;

import java.util.Iterator;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RemoteMachineProxyTest {

    private Iterator<String> iteratorMock;

    @BeforeEach
    public void setupMocks(){
        iteratorMock=mock(Iterator.class);

        when(iteratorMock.next())
                .thenReturn("John Deere, john@infomoto.com,23")
                .thenReturn("James Cameron, james@infomoto.com,45")
                .thenReturn("Justine Smith, justine@infomoto.com,28")
                .thenReturn("Johanna Robbins, johanna@infomoto.com,38");
    }

    @AfterEach
    public void releaseMocks(){
        iteratorMock=null;
    }

    @Test
    public void testRemoteMachineProxyGetNextFile(){

        RemoteMachineProxy proxy = new RemoteMachineProxy("test_url");
        proxy.setTestIterator(iteratorMock);

        when(iteratorMock.hasNext()).thenReturn(true);
        assertEquals("john@infomoto.com",proxy.getNextFile());

        when(iteratorMock.hasNext()).thenReturn(true);
        assertEquals("james@infomoto.com",proxy.getNextFile());

        when(iteratorMock.hasNext()).thenReturn(true);
        assertEquals("justine@infomoto.com",proxy.getNextFile());

        when(iteratorMock.hasNext()).thenReturn(true);
        assertEquals("johanna@infomoto.com",proxy.getNextFile());

        when(iteratorMock.hasNext()).thenReturn(false);
        assertNull(proxy.getNextFile());
    }
}
