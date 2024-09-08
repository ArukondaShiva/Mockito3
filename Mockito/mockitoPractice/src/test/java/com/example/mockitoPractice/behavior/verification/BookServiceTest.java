package com.example.mockitoPractice.behavior.verification;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private  BookService bookService;

    @Mock
    private BookRepository bookRepository;


    //when it comes to behavior verification, @Spy works same as @Mock
    //@Spy
    //private BookRepository bookRepository;



    @Test
    public void testAddBook(){

        Book book = new Book(null,"Mockito In Action",500, LocalDate.now());
        bookService.addBook(book);

        // verifying invocation on mock (by default one time)
        //verify(bookRepository).save(book);

        // when save was never called
        verify(bookRepository,never()).save(book);
    }


    @Test
    public void testSaveBookWithBookRequestWithGreaterPrice(){

        BookRequest bookRequest = new BookRequest("Mockito In Action",500,LocalDate.now());
        Book book = new Book(null,"Mockito In Action",500,LocalDate.now());

        bookService.addBook(bookRequest);

        //verifying how many times save method was called on bookRepository mock.
        verify(bookRepository,times(0)).save(book);
    }

    @Test
    public void testSaveBookWithBookRequestWithGreaterPrice1(){

        BookRequest bookRequest = new BookRequest("Mockito In Action",600,LocalDate.now());
        Book book = new Book(null,"Mockito In Action",600,LocalDate.now());

        bookService.addBook(bookRequest);

        //verifying how many times save method was called on bookRepository mock.
        verify(bookRepository,times(1)).save(book);

        // for one time we can remove the count
        //verify(bookRepository).save(book);
    }


    @Test
    public void testSaveBookWithBookRequestWithGreaterPrice2(){

        BookRequest bookRequest = new BookRequest("Mockito In Action",500,LocalDate.now());
        Book book = new Book(null,"Mockito In Action",500,LocalDate.now());

        bookService.addBook(bookRequest);

        //verifying how many times save method was called on bookRepository mock.
        //verify(bookRepository,times(0)).save(book);

        // for 0 times, mockito provides a method 'never'
        verify(bookRepository,never()).save(book);
    }



    @Test
    public void testUpdatePrice(){
        bookService.updatePrice(null,600);

        // to verify when no interactions were made on bookRepository
        verifyNoInteractions(bookRepository);
    }


    @Test
    public void testUpdatePrice2(){

        Book book = new Book("1234","Mockito In Action",500,LocalDate.now());
        when(bookRepository.findBookById("1234")).thenReturn(book);
        bookService.updatePrice("1234",500);

        verify(bookRepository).findBookById("1234");
        verify(bookRepository).save(book);

        // after save method,when we want to make sure that No more interactions were made.
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void testUpdatePrice3(){

        // in above examples mockito just verified whether save and findById methods called or not irrespective of order.
        // This example focused on order of invocation.

        Book book = new Book("1234","Mockito In Action",500,LocalDate.now());
        when(bookRepository.findBookById("1234")).thenReturn(book);
        bookService.updatePrice("1234",500);

        InOrder inOrder = inOrder(bookRepository);

        inOrder.verify(bookRepository).findBookById("1234");
        inOrder.verify(bookRepository).save(book);

    }




    @Test
    public void testSaveBookWithBookRequestWithGreaterPrice3(){

        // This is to check At least and At most Invocations.

        BookRequest bookRequest = new BookRequest("Mockito In Action",600,LocalDate.now());
        Book book = new Book(null,"Mockito In Action",600,LocalDate.now());

        bookService.addBook(bookRequest);
        bookService.addBook(bookRequest);
        bookService.addBook(bookRequest);

        //verify(bookRepository,times(2)).save(book);

        verify(bookRepository,atLeast(2)).save(book);

        verify(bookRepository,atMost(5)).save(book);
        //verify(bookRepository,atMostOnce()).save(book);
        verify(bookRepository,atLeastOnce()).save(book);

    }




}
