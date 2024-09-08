package com.example.mockitoPractice.test_doubles.dummy;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class DummyTest {


    @Test
    public void demoDummy(){

        BookRepository bookRepository = new FakeBookRepository();
        EmailService emailService = new DummyEmailService();

        BookService bookService = new BookService(bookRepository,emailService);

        bookService.addBook(new Book("1234","Mockito In Action",300, LocalDate.now()));
        bookService.addBook(new Book("1235","JUnit5 In Action",400, LocalDate.now()));

        assertEquals(2,bookService.findNumberOfBooks());
    }


    @Test
    public void demoDummyWithMockito(){

        BookRepository bookRepository = mock(BookRepository.class);
        EmailService emailService = mock(EmailService.class);
        BookService bookService = new BookService(bookRepository,emailService);

        Book book1 = new Book("1234","Mockito In Action",250,LocalDate.now());
        Book book2 = new Book("1235","JUnit In Action",350,LocalDate.now());

        Collection<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        when(bookRepository.findAll()).thenReturn(books);

        assertEquals(2,bookService.findNumberOfBooks());
    }


}
