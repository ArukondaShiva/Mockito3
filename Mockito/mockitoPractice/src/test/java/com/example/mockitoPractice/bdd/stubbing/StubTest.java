package com.example.mockitoPractice.bdd.stubbing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class StubTest {


    @InjectMocks
    private BookService bookService;


    @Mock
    private BookRepository bookRepository;



    @Test
    public void testStubbingInTraditionalMockitoStyle(){

        Book book1 = new Book("1234","Mockito In Action",500, LocalDate.now());
        Book book2 = new Book("1235","JUnit In Action",400,LocalDate.now());

        List<Book> newBooks = new ArrayList<>();
        newBooks.add(book1);
        newBooks.add(book2);

        when(bookRepository.findNewBooks(7)).thenReturn(newBooks);

        List<Book> newBooksWithAppliedDiscount = bookService.getNewBooksWithAppliedDiscount(10,7);

        assertEquals(2,newBooksWithAppliedDiscount.size());
        assertEquals(450,newBooksWithAppliedDiscount.get(0).getPrice());
        assertEquals(360,newBooksWithAppliedDiscount.get(1).getPrice());
    }


    @Test
    @DisplayName("Given - new Books, when - Get New Books With Applied Discount method is called, Then - New books with applied discount is returned")
    public void test_Given_NewBooks_When_GetNewBooksWithAppliedDiscount_Then_NewBooksWithAppliedDiscountIsReturned(){

        // 1- Arrange - Given
            // Initialization of the System under test and initialization of Mocks.
            // As we are using Annotations, these two steps were already done.
            // Remaining Arrangement code
        Book book1 = new Book("1234","Mockito In Action",500, LocalDate.now());
        Book book2 = new Book("1235","JUnit In Action",400,LocalDate.now());

        List<Book> newBooks = new ArrayList<>();
        newBooks.add(book1);
        newBooks.add(book2);

        given(bookRepository.findNewBooks(7)).willReturn(newBooks);

        // Act - When
        List<Book> newBooksWithAppliedDiscount = bookService.getNewBooksWithAppliedDiscount(10,7);

        // Assert - Then
        //assertEquals(2,newBooksWithAppliedDiscount.size());
        //assertEquals(450,newBooksWithAppliedDiscount.get(0).getPrice());
        //assertEquals(360,newBooksWithAppliedDiscount.get(1).getPrice());

        // AssertJ - BDDAssertions
        then(newBooksWithAppliedDiscount).isNotNull();
        then(newBooksWithAppliedDiscount.size()).isEqualTo(2);
        then(newBooksWithAppliedDiscount.get(0).getPrice()).isEqualTo(450);

    }


}
