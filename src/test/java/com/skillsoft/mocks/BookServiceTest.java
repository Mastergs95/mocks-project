package com.skillsoft.mocks;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepositoryMock;

    @InjectMocks
    private BookService bookService;

    @Test
    public void testAddBook(){

        ArgumentCaptor<Book> inputBookCaptor=ArgumentCaptor.forClass(Book.class);

        doNothing().when(bookRepositoryMock).addBook(inputBookCaptor.capture());

        Book book = new Book("book456","Game of Thrones","George R");
        bookService.addBook(book);

        assertEquals(book,inputBookCaptor.getValue());

        bookService.addBook(new Book(
                "book789","Hunger Games","Suzanne Collins"));

        assertEquals(
                new Book("book789","Hunger Games","Suzanne Collins"),
                inputBookCaptor.getValue());

        verify(bookRepositoryMock,times(2)).addBook(isA(Book.class));
    }


    @Test
    public void testUpdateBook(){


        bookService.updateBook(new Book(
                "book123","Diary of kid (Series)","Jeff Kinney"));

        verify(bookRepositoryMock,atLeastOnce()).updateBook(isA(Book.class));


    }

    @Test
    public void testDeleteBook() {

        bookService.deleteBook("book123");

        verify(bookRepositoryMock,atLeastOnce()).deleteBook(anyString());
    }


}
