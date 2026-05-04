package com.example.ct4.interfaces;


import com.example.ct4.model.Book;

import java.util.List;

public interface BookInterface {

    void insert(Book book);
    void delete(Book book);
    void update(Book book);
    List<Book> getBooks();
}
