package com.example.mockitoPractice.argument_matchers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;


    //when it comes to behavior verification, @Spy works same as @Mock
    //@Spy
    //private BookRepository bookRepository;


    @Test
    public void testUpdatePrice(){
        Book book1 = new Book("1234","Mockito In Action",600, LocalDate.now());
        Book book2 = new Book("1234","Mockito In Action",500, LocalDate.now());

        //when(bookRepository.findBookById("1234")).thenReturn(book1);
        //when(bookRepository.findBookById(any())).thenReturn(book1);
        when(bookRepository.findBookById(any(String.class))).thenReturn(book1);

        //bookService.updatePrice("1234",500);
        bookService.updatePrice("xyz",500);

        verify(bookRepository).save(book2);
    }



    // Argument Matchers should be provided for all arguments.
    // Argument Matchers cannot be used outside stubbing/verification.

    @Test
    public void testInvalidUseOfArgumentMatchers(){
        Book book = new Book("1234","Mockito In Action",600,LocalDate.now());
        when(bookRepository.findBookByTitleAndPublishedDate(eq("Mockito In Action"),any())).thenReturn(book);
        Book actualBook = bookService.getBookByTitleAndPublishDate("Mockito In Action",LocalDate.now());

        assertEquals("Mockito In Action",actualBook.getTitle());

    }



    //more flexible Argument Matchers.
    @Test
    public void testSpecificTypeOfArgumentMatchers(){
        Book book = new Book("1234","Mockito In Action",600,LocalDate.now());
        when(bookRepository.findBookByTitleAndPriceAndIsDigital(anyString(),anyInt(),anyBoolean())).thenReturn(book);
        Book actualBook = bookService.getBookByTitleAndPriceAndIsDigital("Mockito In Action",600,true);

        assertEquals("Mockito In Action",actualBook.getTitle());
    }




    @Test
    public void testCollectionTypeArgumentMatchers(){
        List<Book> books = new ArrayList<>();
        Book book = new Book("1234","Mockito In Action",600, LocalDate.now());
        books.add(book);

        bookService.addBooks(books);

        //verify(bookRepository).saveAll(books);
        verify(bookRepository).saveAll(anyList()); // similarly, we can use anySet, anyMao, anyCollection.
    }


    // startsWith,endsWith,contains
    @Test
    public void testStringTypeOfArgumentMatchers(){
        Book book = new Book("1234","Mockito In Action",600,LocalDate.now());
        when(bookRepository.findBookByTitleAndPriceAndIsDigital(startsWith("Mockito"),anyInt(),anyBoolean())).thenReturn(book);
        Book actualBook = bookService.getBookByTitleAndPriceAndIsDigital("Mockito In Action",600,true);

        assertEquals("Mockito In Action",actualBook.getTitle());
    }


}