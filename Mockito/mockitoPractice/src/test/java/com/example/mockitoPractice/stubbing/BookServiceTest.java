package com.example.mockitoPractice.stubbing;


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
    private  BookService bookService;

    @Mock
    private BookRepository bookRepository;


    @Test
    public void testCalculateTotalCostOfBooks(){
           List<String> bookIds = new ArrayList<>();
           //bookIds.add("1234");
           //bookIds.add("1235");

           bookIds.add("1234");
           bookIds.add("1234");

           Book book1 = new Book("1234","Mockito In Action",500, LocalDate.now());
           Book book2 = new Book("1235","JUnit In Action",400,LocalDate.now());

           //when(bookRepository.findByBookId("1234")).thenReturn(book1);
           //when(bookRepository.findByBookId("1235")).thenReturn(book2);

           //doReturn(book1).when(bookRepository).findByBookId("1234");
           //doReturn(book2).when(bookRepository).findByBookId("1235");

           // when findByBookId called first time, it returns first argument, second time it returns second argument.
           // (i)
           //when(bookRepository.findByBookId("1234")).thenReturn(book1,book1);
           // (ii)
           when(bookRepository.findByBookId("1234")).thenReturn(book1).thenReturn(book1);


           int actualCost = bookService.calculateTotalCost(bookIds);

           assertEquals(1000, actualCost);
    }


    // How to stub void method.
    // when return type of method in class is void
    @Test
    public void testSaveBook(){

        Book book1 = new Book(null,"Mockito In Action",500,LocalDate.now());

        doNothing().when(bookRepository).save(book1); // ==, mockito check the defined book is same as the passed book.
        // object must be equal(references should point to same object)

        bookService.addBook(book1);
    }


    @Test
    public void testSaveBookWithBookRequest(){

        BookRequest bookRequest = new BookRequest("Mockito In Action",500,LocalDate.now());
        Book book = new Book(null,"Mockito In Action",500,LocalDate.now());

        doNothing().when(bookRepository).save(book);
        bookService.addBook(bookRequest);

        // here comparison happens between the two different objects
        // but, it executed successfully. we have overriden equals method in Book class.
        // in overrided equals method comparing based on title,price and date fields of an object.

    }


    @Test
    public void testSaveBookWithBookRequestWithGreaterPrice(){

        BookRequest bookRequest = new BookRequest("Mockito In Action",500,LocalDate.now());
        Book book = new Book(null,"Mockito In Action",500,LocalDate.now());

        //doNothing().when(bookRepository).save(book);

        // we don't do weather this return from if condition or from at the end of the method
        // so stubbing cannot help to test method properly.
        // we need to perform behavior verification.
        bookService.addBook(bookRequest);

    }


}
