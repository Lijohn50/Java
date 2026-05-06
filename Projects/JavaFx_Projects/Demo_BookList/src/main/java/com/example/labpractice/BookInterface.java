package com.example.labpractice;

import java.util.List;

public interface BookInterface {

    void insert(Book book);
    void update(Book book);
    void delete(Book book);
    List<Book> getBook();
}
