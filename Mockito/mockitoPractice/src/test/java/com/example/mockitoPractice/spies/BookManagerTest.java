package com.example.mockitoPractice.spies;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class BookManagerTest {


    @InjectMocks
    private BookManager bookManager;


    // creating spy using annotations.
    @Spy
    private BookService bookService;


    /*
    @Test
    public void testMockitoSpy(){

        // another way of creating a spy without annotations
        //BookService bookService = spy(BookService.class);


        BookManager bookManager = new BookManager(bookService);
    }

     */


    @Test
    public void testMockitoSpy(){
        // we need to mock findBook because it is communicating to database or not implemented.
        // we need to call getAppliedDiscount method to calculate the discounted price.

        Book book = new Book("1234","Mockito In Action",500, LocalDate.now());

        doReturn(book).when(bookService).findBook("1234");
        //when(bookService.findBook("1234")).thenReturn(book); // Here, mockito won't stub. it calls the method.
        // so, whenever stubbing with spy use 'doReturn'

        int actualDiscount =  bookManager.applyDiscountOnBooks("1234",10);
        assertEquals(450,actualDiscount);
    }





}
