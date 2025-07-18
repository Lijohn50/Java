package com.example.labpractice;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookService implements BookInterface {

    @Override
    public void insert(Book book) {
        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "insert into bookinfo values(?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, book.getNumber());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setDouble(3, book.getPrice());
            preparedStatement.setInt(4, book.getQuantity());
            preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("failed to connect to database");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Book book) {
        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "update bookinfo set title = ?, price = ?, quantity = ? where number = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(4, book.getNumber());
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setDouble(2, book.getPrice());
            preparedStatement.setInt(3, book.getQuantity());

            preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("failed to connect to database");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Book book) {
        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "delete from bookinfo where number = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, book.getNumber());
            preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("failed to connect to database");
            e.printStackTrace();
        }

    }

    @Override
    public List<Book> getBook() {

        List<Book> bookList = new ArrayList<>();
        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "select * from bookinfo;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                Book book = new Book(resultSet.getInt("number"),resultSet.getString("title"),resultSet.getDouble("price"),resultSet.getInt("quantity"));
                bookList.add(book);
            }
        }
        catch(SQLException e)
        {
            System.out.println("failed to connect to database");
            e.printStackTrace();
        }

        return bookList;
    }
}
