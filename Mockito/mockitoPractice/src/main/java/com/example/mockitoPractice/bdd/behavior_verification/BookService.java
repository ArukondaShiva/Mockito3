package com.example.mockitoPractice.bdd.behavior_verification;

public class BookService {

    private BookRepository bookRepository;


    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }


    public void updatePrice(String bookId,int updatePrice){

        Book book = bookRepository.findBookById(bookId);

        book.setPrice(updatePrice);
        bookRepository.save(book);
    }


}