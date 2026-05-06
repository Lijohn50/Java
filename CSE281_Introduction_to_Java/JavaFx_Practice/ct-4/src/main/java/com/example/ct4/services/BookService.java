package com.example.ct4.services;

import com.example.ct4.interfaces.BookInterface;
import com.example.ct4.model.Book;
import com.example.ct4.utility.ConnectionSingleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookService implements BookInterface {

    @Override
    public void insert(Book book)
    {
        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO bookinfo VALUES('" + book.getNumber() + "','" + book.getTitle() + "'," +  book.getPrice() + "," +  book.getQuantity() + ");";
            statement.execute(query);
        }
        catch(SQLException e)
        {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Book book) {

        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "DELETE FROM bookinfo WHERE number =  '" + book.getNumber() + "';" ;
            statement.execute(query);
        }
        catch(SQLException e)
        {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }

    }

    @Override
    public void update(Book book) {

        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "UPDATE bookinfo SET number = '" + book.getNumber() + "' WHERE title = '" + book.getTitle() + "';" ;
            statement.execute(query);
        }
        catch(SQLException e)
        {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }

    }

    @Override
    public ObservableList<Book> getBooks() {

        ObservableList<Book> books = FXCollections.observableArrayList();
        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM bookinfo;" ;
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {

                Book book = new Book();
                book.setNumber(resultSet.getString("number"));
                book.setTitle(resultSet.getString("title"));
                book.setPrice(resultSet.getDouble("price"));
                book.setQuantity(resultSet.getInt("quantity"));
                books.add(book);

            }
        }
        catch (SQLException e)
        {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }

        return books;
    }
}
