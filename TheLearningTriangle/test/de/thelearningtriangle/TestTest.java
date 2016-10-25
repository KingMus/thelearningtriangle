package de.thelearningtriangle;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.List;
import java.util.LinkedList;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestTest
{
    
    @Test
    public void test()
    {
        assertTrue(true);
    }
    
    @Test
    public void mockitoMockVerifyTest() throws Exception
    {
        // mock creation
        List mockedList = mock(List.class);
        
        // using mock object - it does not throw any "unexpected interaction"
        // exception
        mockedList.add("one");
        
        // selective, explicit, highly readable verification
        verify(mockedList).add("one");
    }
    
    @Test
    public void mockitoMockWhenThenReturnTest() throws Exception
    {
        // you can mock concrete classes, not only interfaces
        @SuppressWarnings("unchecked")
        LinkedList<String> mockedList = mock(LinkedList.class);
        
        // stubbing appears before the actual execution
        when(mockedList.get(0)).thenReturn("first");
        
        assertEquals("first", mockedList.get(0));
    }
    
}
