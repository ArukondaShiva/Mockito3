package com.example.mockitoPractice.spies;

public class BookService {

    public Book findBook(String bookId) {
        // unimplemented method
        //return null;
        throw new RuntimeException("Method not implemented");
    }

    public int getAppliedDiscount(Book book, int discountRate) {
        int price = book.getPrice();
        int newPrice = price - (price*discountRate/100);
        return newPrice;
    }
}
