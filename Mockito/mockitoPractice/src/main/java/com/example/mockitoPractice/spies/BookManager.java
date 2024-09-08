package com.example.mockitoPractice.spies;

public class BookManager {

    private BookService bookService;

    public BookManager(BookService bookService){
        this.bookService = bookService;
    }

    public int applyDiscountOnBooks(String bookId,int discountRate){
        Book book = bookService.findBook(bookId); // we need to mock.
        int discountedPrice = bookService.getAppliedDiscount(book,discountRate); // we need to actually call
        // partial mock - spy comes in to the picture.
        return discountedPrice;
    }


}